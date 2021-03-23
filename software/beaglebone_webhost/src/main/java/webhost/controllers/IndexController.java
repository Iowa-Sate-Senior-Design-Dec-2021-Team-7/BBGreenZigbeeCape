package webhost.controllers;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import webhost.WebHostApplication;
import webhost.components.GlobalUtils;
import webhost.enums.WebHostExceptionType;
import webhost.http_wrappers.ReturnObjectWrapper;
import webhost.http_wrappers.UpdateExceptionWrapper;
import webhost.services.IndexService;

import java.util.ArrayList;

@Controller
@RequestMapping(value = "")
public class IndexController {
    
    /* ************************************************* START INSTANCE VARIABLES ************************************************** */
    
    /**
     * {@code GlobalUtils} {@code Component}. Grants the ability to use global variables and methods common to other {@code classes} in this Application
     * @see webhost.components.GlobalUtils
     */
    private GlobalUtils gUtils;
    
    /**
     * {@code Log} for this controller
     */
    private Log log = LogFactory.getLog(WebHostApplication.class);
    
    /**
     * {@code IndexSevice} for this controller
     * @see webhost.services.IndexService
     */
    private IndexService indexService;
    
    /**
     * Directory {@code HTML} resources are in
     */
    private String resourcePrefix;
    
    /* ************************************************** END INSTANCE VARIABLES *************************************************** */
    
    /* **************************************************** START CONSTRUCTORS ***************************************************** */
    
    /**
     * Default constructor
     * @param gUtils
     *      See {@code this.gUtils}
     * @param indexService
     *      See {@code this.indexService}
     */
    @Autowired
    public IndexController(GlobalUtils gUtils, IndexService indexService) {
        
        this.gUtils = gUtils;
        this.indexService = indexService;
    
        resourcePrefix = "templates" + GlobalUtils.getFileSep() + "index" + GlobalUtils.getFileSep();
    }
    
    /* ***************************************************** END CONSTRUCTORS ****************************************************** */
    
    /* **************************************************** START GET MAPPINGS ***************************************************** */
    
    /**
     * Index page
     * @return
     * 		Default HTML path
     */
    @GetMapping("")
    public String get_index() { return indexService.get_index(); }
    
    /**
     * Debug data return
     * @return
     *        {@code String} "Hello World!"
     */
    @GetMapping("/hello")
    @ResponseBody
    public ReturnObjectWrapper<String> get_helloworld() {
        
        String toReturn;
        try { toReturn = indexService.get_helloworld(); }
        catch (Exception e) {
    
            UpdateExceptionWrapper exception = GlobalUtils.generateExceptionMap(e.getClass(), WebHostExceptionType.SYSTEM, 550, e.getMessage());
            log.error(e.getMessage(), e);
            ArrayList<UpdateExceptionWrapper> exceptionList = new ArrayList<>();
            exceptionList.add(exception);
            return null;
        }
        
        String logMessage = "Said hello to client";
        GlobalUtils.successHandler(log, logMessage, null);
        
        return new ReturnObjectWrapper<>(200, toReturn, null);
    }
    
    /* ***************************************************** END GET MAPPINGS ****************************************************** */
    
    /* **************************************************** END INDEX CONTROLLER *************************************************** */
}
