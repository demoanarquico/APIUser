package cl.bci.user.exception;

public class InternalServerErrorException extends Exception {
	
	private static final long serialVersionUID = 7113087421352533213L;
    private String className;
    private Integer code;
	
	public InternalServerErrorException(String className, String message) {
        super(message);
        this.className=className;
    }

    public InternalServerErrorException() {
        super();
    }

    public InternalServerErrorException(String message, Throwable cause) {
        super(message, cause);
    }

    public InternalServerErrorException(String message) {
        super(message);
    }

    public InternalServerErrorException(Integer code, String message) {
        super(message);
        this.code=code;
    }
    public InternalServerErrorException(Throwable cause) {
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
