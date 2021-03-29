package webhost.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import webhost.components.GlobalUtils;

/**
 * Service for {@code HTMLStaticController}
 */
@Service
public class HTMLStaticService {
    
    /* ************************************************* START INSTANCE VARIABLES ************************************************** */
    
    /**
     * {@code GlobalUtils} {@code Component}. Grants the ability to use global variables and methods common to other {@code classes} in this Application
     * @see webhost.components.GlobalUtils
     */
    private GlobalUtils gUtils;
    
    /**
     * File path to html files
     */
    private String htmlPrefix;
    
    /* ************************************************** END INSTANCE VARIABLES *************************************************** */
    
    /* **************************************************** START CONSTRUCTORS ***************************************************** */
    
    /**
     * Default constructor
     * @param gUtils
     * 		See {@code this.gUtils}
     */
    @Autowired
    public HTMLStaticService(GlobalUtils gUtils) {
        
        this.gUtils = gUtils;
        
        htmlPrefix = "htmlStatic" + GlobalUtils.getFileSep();
    }
    
    /* ***************************************************** END CONSTRUCTORS ****************************************************** */
    
    /* *************************************************** START REQUEST HELPERS *************************************************** */
    
    public String handle_HTMLError(Object stat) {
        
        if (stat != null) {
            
            int code = Integer.parseInt(stat.toString());
            if (code == HttpStatus.BAD_REQUEST.value()) { return htmlPrefix + "error_400"; }
            else if (code == HttpStatus.UNAUTHORIZED.value()) { return htmlPrefix + "error_401"; }
            else if (code == HttpStatus.NOT_FOUND.value()) { return htmlPrefix + "error_404"; }
            else if (code == HttpStatus.BAD_GATEWAY.value()) { return htmlPrefix + "error_502"; }
        }
        
        return htmlPrefix + "error_index";
    }
    
    /* **************************************************** END REQUEST HELPERS **************************************************** */
    
    /* *************************************************** END HTML STATIC SERVICE ************************************************* */
}
