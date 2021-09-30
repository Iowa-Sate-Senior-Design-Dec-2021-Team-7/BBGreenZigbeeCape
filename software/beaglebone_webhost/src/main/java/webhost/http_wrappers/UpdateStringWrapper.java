package webhost.http_wrappers;

import webhost.enums.WebHostExceptionType;

/**
 * Wrapper class to input a {@code String} through a request
 */
public class UpdateStringWrapper {
    
    /* ************************************************* START INSTANCE VARIABLES ************************************************** */
    
    /**
     * {@code String} payload of this wra[[er
     */
    private String str;
    
    /* ************************************************** END INSTANCE VARIABLES *************************************************** */
    
    /* **************************************************** START CONSTRUCTORS ***************************************************** */
    
    /**
     * Default constructor
     */
    public UpdateStringWrapper() {
    
        this.setStr(null);
    }
    
    /* ***************************************************** END CONSTRUCTORS ****************************************************** */
    
    /* *************************************************** START GETTERS/SETTERS *************************************************** */
    
    /**
     * Get {@code str} of {@code UpdateStringWrapper}
     * @return
     * 		{@code str}
     */
    public String getStr() { return str; }
    /**
     * Set {@code str} of {@code UpdateStringWrapper}
     * @param str
     * 		Desired {@code str} for {@code UpdateStringWrapper}
     */
    public void setStr(String str) { this.str = str; }
    
    /* **************************************************** END GETTERS/SETTERS **************************************************** */
    
    /* *********************************************** END EXCEPTION UPDATE WRAPPER ************************************************ */
}
