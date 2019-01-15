package com.newhope.order.biz.hepler.selector;

import com.newhope.moneyfeed.api.enums.ResultCode;

public abstract class SegmentSelector {

	protected String segmentName;

	protected String segmentKey;

	protected String segment1;

	protected String segment2;

	protected String currentSegment;

	protected int segmentMaxLeftQty;

	protected int segmentMaxQty;

	protected String sysParamType;

	protected String sysParamCode;

	protected String bizCode;

	protected ResultCode errCode;

	protected String lockKey;

	public String getBizCode() {
		return bizCode;
	}

	public void setBizCode(String bizCode) {
		this.bizCode = bizCode;
	}

	public String getSegmentKey() {
		return segmentKey;
	}

	public String getSegmentName() {
		return segmentName;
	}

	public void setSegmentName(String segmentName) {
		this.segmentName = segmentName;
	}

	public void setSegmentKey(String segmentKey) {
		this.segmentKey = segmentKey;
	}

	public String getSysParamType() {
		return sysParamType;
	}

	public void setSysParamType(String sysParamType) {
		this.sysParamType = sysParamType;
	}

	public String getSysParamCode() {
		return sysParamCode;
	}

	public void setSysParamCode(String sysParamCode) {
		this.sysParamCode = sysParamCode;
	}

	public String getSegment1() {
		return segment1;
	}

	public void setSegment1(String segment1) {
		this.segment1 = segment1;
	}

	public String getSegment2() {
		return segment2;
	}

	public void setSegment2(String segment2) {
		this.segment2 = segment2;
	}

	public String getCurrentSegment() {
		return currentSegment;
	}

	public void setCurrentSegment(String currentSegment) {
		this.currentSegment = currentSegment;
	}

	public int getSegmentMaxLeftQty() {
		return segmentMaxLeftQty;
	}

	public void setSegmentMaxLeftQty(int segmentMaxLeftQty) {
		this.segmentMaxLeftQty = segmentMaxLeftQty;
	}

	public int getSegmentMaxQty() {
		return segmentMaxQty;
	}

	public void setSegmentMaxQty(int segmentMaxQty) {
		this.segmentMaxQty = segmentMaxQty;
	}

	public ResultCode getErrCode() {
		return errCode;
	}

	public void setErrCode(ResultCode errCode) {
		this.errCode = errCode;
	}

	public String getLockKey() {
		return lockKey;
	}

	public void setLockKey(String lockKey) {
		this.lockKey = lockKey;
	}

	public void initQty(int maxQty, int leftQty) {
		throw new UnsupportedOperationException();
	}

	public void initSysParam() {
		throw new UnsupportedOperationException();
	}

	public void initError() {
		throw new UnsupportedOperationException();
	}
}
