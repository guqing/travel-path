package xyz.guqing.travelpath.controller;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import xyz.guqing.travelpath.entity.annotation.WriteLog;
import xyz.guqing.travelpath.entity.dto.MyUserDetails;
import xyz.guqing.travelpath.entity.support.DeleteConstant;
import xyz.guqing.travelpath.entity.support.LogType;
import xyz.guqing.travelpath.entity.vo.PresetPointExcelVO;
import xyz.guqing.travelpath.entity.vo.PresetSchemeExcelVO;
import xyz.guqing.travelpath.entity.model.PresetScheme;
import xyz.guqing.travelpath.entity.model.Presetpoint;
import xyz.guqing.travelpath.entity.vo.PresetSchemeVO;
import xyz.guqing.travelpath.listener.ExcelListener;
import xyz.guqing.travelpath.service.PresetPointService;
import xyz.guqing.travelpath.service.PresetSchemeService;
import xyz.guqing.travelpath.utils.Result;
import xyz.guqing.travelpath.utils.SecurityUserHelper;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * 预设卡口方案处理
 *
 * @author guqin
 * @date 2019-08-09 10:59
 */
@RestController
@RequestMapping("/preset")
public class PresetSchemeController {
    private static final Logger logger = LoggerFactory.getLogger(PresetSchemeController.class);
    private PresetSchemeService presetSchemeService;

    @Autowired
    public PresetSchemeController(PresetSchemeService presetSchemeService) {
        this.presetSchemeService = presetSchemeService;
    }

    @PostMapping("/save")
    @WriteLog(value = "新增预设卡口方案", type = LogType.INSERT)
    public Object createScheme(@RequestBody PresetSchemeVO presetSchemeVO) {
        Object error = validateParameter(presetSchemeVO);
        if (error != null) {
            return error;
        }

        MyUserDetails user = (MyUserDetails) SecurityUserHelper.getCurrentPrincipal();
        Integer userId = user.getId();

        PresetScheme presetScheme = presetSchemeService.savePresetScheme(presetSchemeVO, userId);
        return Result.ok(presetScheme);
    }

    @GetMapping("/list")
    public Object listSchemeByPage(@RequestParam(value="current",defaultValue = "1") Integer pageNo,
                                   @RequestParam(value = "pageSize",defaultValue = "10") Integer pageSize) {
        MyUserDetails user = (MyUserDetails) SecurityUserHelper.getCurrentPrincipal();
        Integer userId = user.getId();
        PageInfo<PresetScheme> presetSchemes = presetSchemeService.listSechemeByPage(pageNo, pageSize, userId);
        return Result.okList(presetSchemes);
    }

    @GetMapping("/getScheme/{preId}")
    public Object getPresetPointScheme(@PathVariable("preId") Long preId) {
        List<Presetpoint> presetPointList = presetSchemeService.getPresetPointsByPreId(preId);
        return Result.okList(presetPointList);
    }

    @GetMapping("/list-point/{id}")
    public Object findPresetPointList(@PathVariable("id") Long preId) {
        PresetScheme scheme = presetSchemeService.getSchemeById(preId);
        return Result.ok(scheme);
    }

    @PutMapping("/update")
    @WriteLog(value = "更新预设卡口方案", type = LogType.UPDATE)
    public Object updateScheme(@RequestBody PresetSchemeVO presetSchemeVO) {
        if(presetSchemeVO.getId() == null) {
            return Result.badArgument();
        }
        presetSchemeService.updateScheme(presetSchemeVO);
        return Result.ok();
    }

    /**
     * 逻辑删除
     * @return 返回操作信息
     */
    @PostMapping("/trash/{id}")
    @WriteLog(value = "删除预设卡口方案到回收站", type = LogType.DELETE)
    public Object throwTrash(@PathVariable("id") Long id) {
        presetSchemeService.logicalDeleted(id);
        return Result.ok();
    }

    @GetMapping("/trash/query")
    public Object findTrashByPage(@RequestParam(value="current", defaultValue = "1") Integer current,
                                  @RequestParam(value="pageSize", defaultValue = "10") Integer pageSize) {
        MyUserDetails user = (MyUserDetails) SecurityUserHelper.getCurrentPrincipal();
        Integer userId = user.getId();
        PageInfo<PresetScheme> presetSchemes = presetSchemeService.findTrashByPage(current, pageSize, userId);
        return Result.okList(presetSchemes);
    }

    @PostMapping("/trash/deleteById/{id}")
    public Object deleteTrashById(@PathVariable("id") Long id) {
        presetSchemeService.sureDeleteById(id);
        return Result.ok();
    }

    @PostMapping("/trash/batchDelete")
    public Object batchDeleteTrash(@RequestBody List<Long> ids) {
        presetSchemeService.batchSureDelete(ids);
        return Result.ok();
    }

    @PostMapping("/trash/recover/{id}")
    public Object recoverTrashById(@PathVariable("id") Long id) {
        presetSchemeService.updateDeleted(id, DeleteConstant.RETAIN);
        return Result.ok();
    }

    /**
     * 批量逻辑删除
     * @return 返回删除结果信息
     */
    @PostMapping("/batch-trash")
    @WriteLog(value = "批量删除预设卡口方案到回收站", type = LogType.DELETE)
    public Object batchTrash(@RequestBody List<Long> ids) {
        presetSchemeService.batchLogicalDeleted(ids);
        return Result.ok();
    }

    @PostMapping("/download")
    public void downloadSchemeWithPoints(@RequestBody List<Long> ids, HttpServletResponse response) throws IOException {
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("utf-8");
        response.setHeader("Content-disposition", "attachment;filename=PresetSchemeWithPoint.xlsx");

        ServletOutputStream outputStream = response.getOutputStream();
        List<PresetSchemeVO> presetSchemeVoList = presetSchemeService.listSchemeByIds(ids);

        // 写sheet1
        ExcelWriter writer = EasyExcelFactory.getWriter(outputStream);
        Sheet schemeSheet = new Sheet(1, 1, PresetSchemeExcelVO.class);
        schemeSheet.setSheetName("预设卡口方案");
        List<PresetSchemeExcelVO> presetSchemeExcelVoList = transferToSchemeExcelList(presetSchemeVoList);
        writer.write(presetSchemeExcelVoList, schemeSheet);

        // 写sheet2
        List<PresetPointExcelVO> presetPointExcelVoList = transferToPresetPointExcelVO(presetSchemeVoList);
        Sheet presetPointSheet = new Sheet(2, 1, PresetPointExcelVO.class);
        presetPointSheet.setSheetName("预设卡口坐标点");
        presetPointSheet.setAutoWidth(true);
        writer.write(presetPointExcelVoList, presetPointSheet);

        writer.finish();
        outputStream.flush();
    }

    /**
     * 预选卡口方案数据Excel文件上传
     * <li>1. 创建excel对应的实体对象
     * <li>2. 由于默认异步读取excel，所以需要创建excel一行一行的回调监听器
     * <li>3. 直接读即可
     */
    @PostMapping("/upload")
    @WriteLog(value = "从excel上传预设卡口方案数据",type = LogType.INSERT)
    public Object upload(MultipartFile file) {
        MyUserDetails user = (MyUserDetails) SecurityUserHelper.getCurrentPrincipal();
        Integer userId = user.getId();

        // 异步执行任务
        return new Callable<Object>() {
            @Override
            public Object call() throws Exception {
                // 读取并保存excel数据
                presetSchemeService.saveUploadSchemeRecord(file, userId);
                return Result.ok();
            }
        };
    }

    /**
     * @param presetSchemeVoList 预设卡口方案VO集合
     * @return 适合导出Excel的presetSchemeExcelVOList
     */
    private List<PresetSchemeExcelVO> transferToSchemeExcelList(List<PresetSchemeVO> presetSchemeVoList) {
        List<PresetSchemeExcelVO> preSetSchemeExcelList = new ArrayList<>();
        presetSchemeVoList.forEach(presetSchemeVO -> {
            PresetSchemeExcelVO presetSchemeExcelVO = new PresetSchemeExcelVO();
            BeanUtils.copyProperties(presetSchemeVO, presetSchemeExcelVO);

            preSetSchemeExcelList.add(presetSchemeExcelVO);
        });
        return preSetSchemeExcelList;
    }

    /**
     * @param presetSchemeVoList 预设卡口方案VO集合
     * @return 适合导出Excel的PresetPointExcelVOList
     */
    private List<PresetPointExcelVO> transferToPresetPointExcelVO(List<PresetSchemeVO> presetSchemeVoList) {
        List<PresetPointExcelVO> presetPointExcelVoList = new ArrayList<>();
        presetSchemeVoList.forEach(presetSchemeVO -> {
            List<Presetpoint> preSetPointList = presetSchemeVO.getPresetpoints();
            preSetPointList.forEach(preSetPoint -> {
                PresetPointExcelVO presetPointExcelVO = new PresetPointExcelVO();
                BeanUtils.copyProperties(preSetPoint, presetPointExcelVO);

                presetPointExcelVoList.add(presetPointExcelVO);
            });
        });
        return presetPointExcelVoList;
    }

    /**
     * 校验参数是否合法
     *
     * @param presetSchemeVO 预设卡口方案Vo
     * @return 合法返回null，否则返回错误信息
     */
    private Object validateParameter(PresetSchemeVO presetSchemeVO) {
        if (StringUtils.isBlank(presetSchemeVO.getName())) {
            return Result.badArgumentValue();
        }

        if (CollectionUtils.isEmpty(presetSchemeVO.getPresetpoints())) {
            return Result.badArgumentValue();
        }
        return null;
    }
}

