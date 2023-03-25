package cl.bci.user.exception;

public class BadRequestException extends Exception {
	
	private static final long serialVersionUID = 7113087421352533213L;
    private String className;
    private Integer code;
    
    public BadRequestException(String className, String message) {
        super(message);
        this.className=className;
    }

    public BadRequestException() {
        super();
    }

    public BadRequestException(String message, Throwable cause) {
        super(message, cause);
    }

    public BadRequestException(String message) {
        super(message);
    }

    public BadRequestException(Integer code, String message) {
        super(message);
        this.code=code;
    }
    public BadRequestException(Throwable cause) {
        super(cause);
    }

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	} 

}
