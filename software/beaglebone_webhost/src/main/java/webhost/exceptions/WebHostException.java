package webhost.exceptions;

import webhost.enums.WebHostExceptionType;

/**
 * {@code Exception} type thrown by this application
 */
public class WebHostException extends Exception {
    
    /**
     * Exception type for debugging purposes
     */
    private WebHostExceptionType type;
    
    /**
     * Default constructor
     */
    public WebHostException() { super(); }
    
    /**
     * Constructs a {@code WebHostException} with {@code String} {@code message}
     * @param message
     * 		See {@code java.lang.Exception.Exception(String message)}
     */
    public WebHostException(String message) { super(message); }
    
    /**
     * Get {@code type} of this {@code WebHostException}
     * @return
     * 		{@code type}
     */
    public WebHostExceptionType getType() { return type; }
    /**
     * Set {@code type} of this {@code WebHostException}
     * @param type
     * 		Desired {@code type} for {@code WebHostException}
     */
    public void setType(WebHostExceptionType type) { this.type = type; }
}
