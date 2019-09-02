package com.yizhu.lj.romote;

import java.util.List;

/**
 * 外部接口返回数据机构体
 * @param <T>
 */
public class ResponseData<T>{

	/** 单个对象返回*/
	private T data;

	/** 多个对象返回*/
	private List<T> dataList;

	private String code;
	
	private Object meta;
	
	private long status;
	
	private String msg;
	
	private long ts;

	private long total;
	
	private long recordsTotal;
	
	private long recordsFiltered;
	
	private long totalCount;


	public T getData() {
		return data;
	}


	public void setData(T data) {
		this.data = data;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}


	public Object getMeta() {
		return meta;
	}


	public void setMeta(Object meta) {
		this.meta = meta;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public long getStatus() {
		return status;
	}


	public void setStatus(long status) {
		this.status = status;
	}


	public long getTs() {
		return ts;
	}


	public void setTs(long ts) {
		this.ts = ts;
	}


	public long getTotal() {
		return total;
	}


	public void setTotal(long total) {
		this.total = total;
	}


	public long getRecordsTotal() {
		return recordsTotal;
	}


	public void setRecordsTotal(long recordsTotal) {
		this.recordsTotal = recordsTotal;
	}


	public long getRecordsFiltered() {
		return recordsFiltered;
	}


	public void setRecordsFiltered(long recordsFiltered) {
		this.recordsFiltered = recordsFiltered;
	}


	public List<T> getDataList() {
		return dataList;
	}


	public void setDataList(List<T> dataList) {
		this.dataList = dataList;
	}


	public long getTotalCount() {
		return totalCount;
	}


	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}












	
	
	
	
}
