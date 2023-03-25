package cl.bci.user.exception;

public class NotFoundException extends Exception {
	
	private static final long serialVersionUID = 7113087421352533213L;
    private String className;
    private Integer code;
    
    public NotFoundException(String className, String message) {
        super(message);
        this.className=className;
    }

    public NotFoundException() {
        super();
    }

    public NotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(Integer code, String message) {
        super(message);
        this.code=code;
    }
    public NotFoundException(Throwable cause) {
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
