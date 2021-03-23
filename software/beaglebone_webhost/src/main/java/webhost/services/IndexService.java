package webhost.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webhost.components.GlobalUtils;
import webhost.exceptions.WebHostException;
import webhost.http_wrappers.UpdateStringWrapper;

/**
 * Service for {@code IndexController}
 */
@Service
public class IndexService {
    
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
    public IndexService(GlobalUtils gUtils) {
        
        this.gUtils = gUtils;
        
        htmlPrefix = "index" + GlobalUtils.getFileSep();
    }
    
    /* ***************************************************** END CONSTRUCTORS ****************************************************** */
    
    /* ****************************************************** START GET HELPERS **************************************************** */
    
    public String get_index() { return htmlPrefix + "index"; }
    
    public String get_helloworld() { return "Hello Client!"; }
    
    /* ******************************************************* END GET HELPERS ***************************************************** */
    
    /* ****************************************************** START POST HELPERS *************************************************** */
    
    public String post_helloname(UpdateStringWrapper wrapper) throws WebHostException { return "Hello [" + wrapper.getStr() + "]!"; }
    
    /* ******************************************************* END POST HELPERS **************************************************** */
    
    /* ****************************************************** START PUT HELPERS **************************************************** */
    
    
    
    /* ******************************************************* END PUT HELPERS ***************************************************** */
    
    /* ***************************************************** START DELETE HELPERS ************************************************** */
    
    
    
    /* ****************************************************** END DELETE HELPERS *************************************************** */
    
    /* *************************************************** END HTML STATIC SERVICE ************************************************* */
}
