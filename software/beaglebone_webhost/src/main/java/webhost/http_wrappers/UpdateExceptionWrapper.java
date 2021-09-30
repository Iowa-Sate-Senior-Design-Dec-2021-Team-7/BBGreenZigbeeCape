package webhost.http_wrappers;

import webhost.enums.WebHostExceptionType;

/**
 * Wrapper class to log a thrown {@code Exception} from
 */
public class UpdateExceptionWrapper {
    
    /* ************************************************* START INSTANCE VARIABLES ************************************************** */
    
    /**
     * {@code Class} of the {@code Exception} thrown
     */
    private Class<?> exceptionClass;
    /**
     * {@code URL} that the {@code Exception} was thrown at
     */
    private String urlFrom;
    /**
     * Debug number of the thrown {@code Exception}
     */
    private Integer exceptionStatus;
    /**
     * {@code WebClientExceptionType} of the thrown {@code Exception}
     */
    private WebHostExceptionType exceptionType;
    /**
     * {@code Exception.message} to log for debugging
     */
    private String exceptionMessage;
    
    /* ************************************************** END INSTANCE VARIABLES *************************************************** */
    
    /* **************************************************** START CONSTRUCTORS ***************************************************** */
    
    /**
     * Default constructor
     */
    public UpdateExceptionWrapper() {
    
        this.setExceptionClass(null);
        this.setUrlFrom(new String());
        this.setExceptionStatus(null);
        this.setExceptionMessage(null);
        this.setExceptionType(null);
    }
    
    /* ***************************************************** END CONSTRUCTORS ****************************************************** */
    
    /* *************************************************** START GETTERS/SETTERS *************************************************** */
    
    /**
     * Get {@code exceptionClass} of {@code ExceptionUpdateWrapper}
     * @return
     * 		exceptionClass
     */
    public Class<?> getExceptionClass() { return exceptionClass; }
    /**
     * Set {@code exceptionClass} of {@code ExceptionUpdateWrapper}
     * @param exceptionClass
     * 		Desired {@code exceptionClass} for {@code ExceptionUpdateWrapper}
     */
    public void setExceptionClass(Class<?> exceptionClass) { this.exceptionClass = exceptionClass; }
    
    /**
     * Get {@code urlFrom} of {@code ExceptionUpdateWrapper}
     * @return
     * 		urlFrom
     */
    public String getUrlFrom() { return urlFrom; }
    /**
     * Set {@code urlFrom} of {@code ExceptionUpdateWrapper}
     * @param urlFrom
     * 		Desired {@code urlFrom} for {@code ExceptionUpdateWrapper}
     */
    public void setUrlFrom(String urlFrom) { this.urlFrom = urlFrom; }
    /**
     * Get {@code exceptionStatus} of {@code ExceptionUpdateWrapper}
     * @return
     * 		exceptionStatus
     */
    public Integer getExceptionStatus() {
        return exceptionStatus;
    }
    /**
     * Set {@code exceptionStatus} of {@code ExceptionUpdateWrapper}
     * @param exceptionStatus
     * 		Desired {@code exceptionStatus} for {@code ExceptionUpdateWrapper}
     */
    public void setExceptionStatus(Integer exceptionStatus) {
        this.exceptionStatus = exceptionStatus;
    }
    
    /**
     * Get {@code exceptionType} of {@code ExceptionUpdateWrapper}
     * @return
     * 		exceptionType
     */
    public WebHostExceptionType getExceptionType() {
        return exceptionType;
    }
    /**
     * Set {@code exceptionType} of {@code ExceptionUpdateWrapper}
     * @param exceptionType
     * 		Desired {@code exceptionType} for {@code ExceptionUpdateWrapper}
     */
    public void setExceptionType(WebHostExceptionType exceptionType) {
        this.exceptionType = exceptionType;
    }
    
    /**
     * Get {@code exceptionMessage} of {@code ExceptionUpdateWrapper}
     * @return
     * 		exceptionMessage
     */
    public String getExceptionMessage() {
        return exceptionMessage;
    }
    /**
     * Set {@code exceptionMessage} of {@code ExceptionUpdateWrapper}
     * @param exceptionMessage
     * 		Desired {@code exceptionMessage} for {@code ExceptionUpdateWrapper}
     */
    public void setExceptionMessage(String exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
    }
    
    /* **************************************************** END GETTERS/SETTERS **************************************************** */
    
    /* *********************************************** END EXCEPTION UPDATE WRAPPER ************************************************ */
}
