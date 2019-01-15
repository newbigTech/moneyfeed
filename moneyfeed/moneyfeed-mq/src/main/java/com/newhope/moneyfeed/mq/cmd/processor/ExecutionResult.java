package com.newhope.moneyfeed.mq.cmd.processor;

public class ExecutionResult {
	private int errorCode = 0;
	private String errorMsg = null;
	private boolean repeatable = false;
	
	public boolean isError()
	{
		return errorCode != 0;
	}
	public int getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	public boolean isRepeatable() {
		return repeatable;
	}
	public void setRepeatable(boolean repeatable) {
		this.repeatable = repeatable;
	}
	
}
