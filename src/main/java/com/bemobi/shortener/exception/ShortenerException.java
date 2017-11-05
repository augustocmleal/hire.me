package com.bemobi.shortener.exception;

public class ShortenerException extends Exception{
	
	private static final long serialVersionUID = 1L;

	private String errorCode;
	private String errorMsg;
	private String errorDescription;
	
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	public String getErrorDescription() {
		return errorDescription;
	}
	public void setErrorDescription(String errorDescription) {
		this.errorDescription = errorDescription;
	}
	public ShortenerException(String errorCode, String errorMsg, String errorDescription) {
		super();
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
		this.errorDescription = errorDescription;
	}


	
	

}
