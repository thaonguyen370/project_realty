package com.df.commonmodel.exceptions;

public class FactoryException extends Exception {

    private static final long serialVersionUID = 1L;

    private final ErrorCode errorCode;
    
    public FactoryException(ErrorCode errorCode, String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
        this.errorCode = errorCode;
    }

    public FactoryException(ErrorCode errorCode, String detailMessage) {
        super(detailMessage);
        this.errorCode = errorCode;
    }

    public FactoryException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
        this.errorCode = null;
    }

    public FactoryException(Throwable throwable) {
        super(throwable);
        this.errorCode = null;
    }

    public FactoryException(String detailMessage) {
        super(detailMessage);
        this.errorCode = null;
    }

    public FactoryException() {
    	this.errorCode = null;
    }

	/**
	 * @return the errorCode
	 */
	public ErrorCode getErrorCode() {
		return errorCode;
	}
    
}
