package xyz.guqing.travelpath.utils;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author guqing
 * @date 2019/8/9
 */
public class Result {
    public static Object ok() {
        Map<String, Object> obj = new HashMap<String, Object>();
        obj.put("code", 0);
        obj.put("message", "成功");
        return obj;
    }

    public static Object ok(Object data) {
        Map<String, Object> obj = new HashMap<String, Object>();
        obj.put("code", 0);
        obj.put("message", "成功");
        obj.put("data", data);
        return obj;
    }

    public static Object okList(PageInfo pageInfo) {
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("list", pageInfo.getList());

        data.put("total", pageInfo.getTotal());
        data.put("pages", pageInfo.getPages());
        data.put("page", pageInfo.getPageNum());
        data.put("limit", pageInfo.getPageSize());

        return ok(data);
    }

    public static Object okList(List list) {
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("list", list);

        listPageModel(list, data);

        return ok(data);
    }

    public static Object okList(List list, List pagedList) {
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("list", list);

        listPageModel(pagedList, data);

        return ok(data);
    }

    public static Object fail() {
        Map<String, Object> obj = new HashMap<String, Object>();
        obj.put("code", -1);
        obj.put("message", "错误");
        return obj;
    }

    public static Object fail(int errno, String message) {
        Map<String, Object> obj = new HashMap<String, Object>();
        obj.put("code", errno);
        obj.put("message", message);
        return obj;
    }

    public static Object badArgument() {
        return fail(401, "参数不对");
    }

    public static Object badArgumentValue() {
        return fail(402, "参数值不对");
    }

    public static Object unlogin() {
        return fail(501, "请登录");
    }

    public static Object serious() {
        return fail(502, "系统内部错误");
    }

    public static Object unsupport() {
        return fail(503, "业务不支持");
    }

    public static Object updatedDateExpired() {
        return fail(504, "更新数据已经失效");
    }

    public static Object updatedDataFailed() {
        return fail(505, "更新数据失败");
    }

    public static Object unauthz() {
        return fail(506, "无操作权限");
    }

    public static Object RPCFailed() {
        return fail(507, "远程调用失败");
    }

    public static Object repeatOps() {
        return fail(508, "重复操作");
    }

    /**
     * 判断是否是Page实例，是则获取page数据设置给分页否则就是用list数据
     * @param list
     * @param data
     */
    private static void listPageModel(List list, Map<String, Object> data) {
        if (list instanceof Page) {
            Page page = (Page) list;
            data.put("total", page.getTotal());
            data.put("page", page.getPageNum());
            data.put("limit", page.getPageSize());
            data.put("pages", page.getPages());
        } else {
            data.put("total", list.size());
            data.put("page", 1);
            data.put("limit", list.size());
            data.put("pages", 1);
        }
    }
}
