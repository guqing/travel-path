package xyz.guqing.travelpath.utils;

import java.io.Serializable;
import java.util.List;

/**
 * @author guqing
 * @date 2019/8/9
 */
public class Page implements Serializable{
	private static final long serialVersionUID = 1L;

	/**
	 * 开始数据的索引
	 */
	private Integer pageNum;
	/**
	 * 每一页的数量
	 */
    private Integer pageSize;
	/**
	 * 总共的数据量
	 */
	private Integer total;

	private List<?> pageResult;
    /**
     * 提供一个构造方法
     * @param pageNum 页号
     * @param pageSize 页大小
     */ 
    public Page(Integer pageNum, Integer pageSize) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
    }

	/**
	 * 获取分页开始记录数
	 * @return 返回分页开始记录数
	 */
	public Integer getpageBegin(){
		return pageNum <= 1 ? 0 : (pageNum - 1) * pageSize;
	}

	/**
	 * @return 返回分页大小
	 */
	public Integer getPageLimit(){
		return this.pageSize;
	}
	
    /**
     * @return 判断是否有上一页，如果有返回true
     */
    public boolean isHasPreviouse(){
        if(pageNum == 0) {
			return false;
		}
        return true;

    }

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public Integer getPageNum() {
		return pageNum;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public List<?> getPageResult() {
		return pageResult;
	}

	public void setPageResult(List<?> pageResult) {
		this.pageResult = pageResult;
	}
	
}