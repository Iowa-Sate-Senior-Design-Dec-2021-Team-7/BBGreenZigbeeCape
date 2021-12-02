package webhost.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webhost.components.GlobalUtils;
import webhost.exceptions.WebHostException;
import webhost.http_wrappers.UpdateStringWrapper;

@Service
public class IndexService {
    
    /* ************************************************* START INSTANCE VARIABLES ************************************************** */
    
    
    private GlobalUtils gUtils;
    
    private String htmlPrefix;
    
    /* ************************************************** END INSTANCE VARIABLES *************************************************** */
    
    /* **************************************************** START CONSTRUCTORS ***************************************************** */
    
    @Autowired
    public IndexService(GlobalUtils gUtils) {
        
        this.gUtils = gUtils;
        
        htmlPrefix = "index" + GlobalUtils.getFileSep();
    }
    
    /* ***************************************************** END CONSTRUCTORS ****************************************************** */
    
    /* ****************************************************** START GET HELPERS **************************************************** */
    
    public String get_index() { return htmlPrefix + "index"; }
    
    public String get_helloworld() { return "Hello!"; }
    
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
