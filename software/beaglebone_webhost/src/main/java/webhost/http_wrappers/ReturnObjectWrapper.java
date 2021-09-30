package webhost.http_wrappers;

import java.util.ArrayList;
import java.util.List;

public class ReturnObjectWrapper<T> {
    
    /**
     * HTTP Execution status number. Used for error detection in the frontend javascript
     * <br>
     * <ul>
     * 		<li>{@code status >= 200 && status < 300}: Backend code executed and returned correctly</li>
     * 		<li>{@code status >= 500 && status < 550}: Backend code executed and a {@code SalesClientException} was thrown</li>
     * 		<li>{@code status >= 550 && status < 555}: Backend code executed and an {@code Exception} (not a {@code WebHostException}) was thrown</li>
     * </ul>
     */
    private int status;
    /**
     * {@code Object} to return to the frontend
     * <br>
     * May be as simple as a {@code String} message or as complicated as a database entity wrapper
     */
    private T payload;
    /**
     * See {@code ExceptionUpdateWrapper}
     * <br>
     * Used to send {@code Exception} info to the frontend if any
     */
    private List<UpdateExceptionWrapper> exception;
    
    /**
     * Default constructor
     *
     * @deprecated
     * 		Advisable to define an error {@code status}, a return {@code payload} and a return {@code message} on creation.
     * 		<br>
     * 		These can be set after creation, but this object should be created and then immediately returned by the function using this class.
     */
    @Deprecated
    public ReturnObjectWrapper() {
        
        status = 200;
        payload = null;
        exception = null;
    }
    
    /**
     * Constructs a {@code ReturnObjectWrapper}
     * @param status
     * 		Error {@code short} desired
     * @param payload
     * 		Return {@code T} desired
     * @param exception
     * 		Return {@code WebClientExceptionType} and {@code String} desired
     */
    public ReturnObjectWrapper(int status, T payload, ArrayList<UpdateExceptionWrapper> exception) {
        
        this.setStatus(status);
        this.setPayload(payload);
        this.setException(exception);
    }
    
    /**
     * Get the error {@code status} of {@code ReturnObjectWrapper}
     * @return
     * 		{@code status}
     */
    public int getStatus() { return status; }
    /**
     * Set {@code status} of {@code ReturnObjectWrapper}
     * @param status
     * 		Desired error {@code status} for {@code ReturnObjectWrapper}
     */
    public void setStatus(int status) { this.status = status; }
    
    /**
     * Get the {@code payload} of {@code ReturnObjectWrapper}
     * @return
     * 		{@code payload}
     */
    public T getPayload() { return payload; }
    /**
     * Set {@code payload} of {@code ReturnObjectWrapper}
     * @param payload
     * 		Desired {@code payload} for {@code ReturnObjectWrapper}
     */
    public void setPayload(T payload) { this.payload = payload; }
    
    /**
     * Get {@code exception} of {@code ReturnObjectWrapper}
     * @return
     * 		{@code exception}
     */
    public List<UpdateExceptionWrapper> getException() { return exception; }
    /**
     * Set {@code exception} of {@code ReturnObjectWrapper}
     * @param exception
     * 		Desired {@code exception} for {@code ReturnObjectWrapper}
     */
    public void setException(ArrayList<UpdateExceptionWrapper> exception) { this.exception = exception; }
}
