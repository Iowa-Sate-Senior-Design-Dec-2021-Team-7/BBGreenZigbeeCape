package webhost.controllers;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import webhost.WebHostApplication;
import webhost.components.GlobalUtils;
import webhost.services.HTMLStaticService;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Controller
public class HTMLStaticController implements ErrorController {
    
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
     * {@code HTMLStaticService} for this controller
     * @see webhost.services.HTMLStaticService
     */
    private HTMLStaticService htmlStaticService;
    
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
     * @param htmlStaticService
     *      See {@code this.hService}
     */
    @Autowired
    public HTMLStaticController(GlobalUtils gUtils, HTMLStaticService htmlStaticService) {
        
        this.gUtils = gUtils;
        this.htmlStaticService = htmlStaticService;
        
        resourcePrefix = "templates" + GlobalUtils.getFileSep() + "htmlStatic" + GlobalUtils.getFileSep();
    }
    
    /* ***************************************************** END CONSTRUCTORS ****************************************************** */
    
    /* ************************************************** START REQUEST MAPPINGS *************************************************** */
    
    @RequestMapping("/error")
    public String handle_HTMLError(HttpServletRequest request) {
    
        Object stat = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
    
        return htmlStaticService.handle_HTMLError(stat);
    }
    
    @Override
    public String getErrorPath() { return "/error"; }
    
    /* *************************************************** END REQUEST MAPPINGS **************************************************** */
    
    /* ************************************************* END HTML STATIC CONTROLLER ************************************************ */
    
}
