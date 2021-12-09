package webhost.controllers;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import webhost.WebHostApplication;
import webhost.components.GlobalUtils;
import webhost.entities.DataPayload;
import webhost.entities.EndDevice;
import webhost.enums.WebHostExceptionType;
import webhost.http_wrappers.*;
import webhost.services.ApiService;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/api")
public class ApiController {
    
    /* ************************************************* START INSTANCE VARIABLES ************************************************** */
    
    private GlobalUtils gUtils;
    
    private Log log = LogFactory.getLog(WebHostApplication.class);
    
    private ApiService apiService;
    
    /* ************************************************** END INSTANCE VARIABLES *************************************************** */
    
    /* **************************************************** START CONSTRUCTORS ***************************************************** */
    
    @Autowired
    public ApiController(GlobalUtils gUtils, ApiService apiService) {
        
        this.gUtils = gUtils;
        this.apiService = apiService;
    }
    
    /* ***************************************************** END CONSTRUCTORS ****************************************************** */
    
    /* **************************************************** START GET MAPPINGS ***************************************************** */
    
    @GetMapping("/get-devices")
    @ResponseBody
    public ReturnObjectWrapper<List<EndDevice>> get_getDevices() {
        
        List<EndDevice> result = null;
        try { result = apiService.getDevices(); }
        catch(Exception e) {
            
            UpdateExceptionWrapper exception = GlobalUtils.generateExceptionMap(e.getClass(), WebHostExceptionType.SYSTEM, 550, e.getMessage());
            log.error(e.getMessage(), e);
            ArrayList<UpdateExceptionWrapper> exceptionList = new ArrayList<>();
            exceptionList.add(exception);
            return new ReturnObjectWrapper<>(550, null, exceptionList);
        }
        
        String logMessage = "Retrieved all devices from database";
        GlobalUtils.successHandler(log, logMessage, logMessage);
        return new ReturnObjectWrapper<>(200, result, null);
    }
    
    @GetMapping("/get-payloads")
    @ResponseBody
    public ReturnObjectWrapper<List<DataPayload>> get_getPayloads() {
        
        List<DataPayload> result = null;
        try { result = apiService.getDataPayloads(); }
        catch(Exception e) {
            
            UpdateExceptionWrapper exception = GlobalUtils.generateExceptionMap(e.getClass(), WebHostExceptionType.SYSTEM, 550, e.getMessage());
            log.error(e.getMessage(), e);
            ArrayList<UpdateExceptionWrapper> exceptionList = new ArrayList<>();
            exceptionList.add(exception);
            return new ReturnObjectWrapper<>(550, null, exceptionList);
        }
        
        String logMessage = "Retrieved all data payloads from database";
        GlobalUtils.successHandler(log, logMessage, logMessage);
        return new ReturnObjectWrapper<>(200, result, null);
    }
    
    @GetMapping("/get-devicepayload")
    @ResponseBody
    public ReturnObjectWrapper<List<DataPayload>> get_getPayload(@RequestBody EndDevice device) {
        
        List<DataPayload> result = null;
        try { result = apiService.getDataPayload(device); }
        catch(Exception e) {
            
            UpdateExceptionWrapper exception = GlobalUtils.generateExceptionMap(e.getClass(), WebHostExceptionType.SYSTEM, 550, e.getMessage());
            log.error(e.getMessage(), e);
            ArrayList<UpdateExceptionWrapper> exceptionList = new ArrayList<>();
            exceptionList.add(exception);
            return new ReturnObjectWrapper<>(550, null, exceptionList);
        }
        
        String logMessage = "Retrieved data payloads corresponding to device [" + device.getId_network() + "] from the database";
        GlobalUtils.successHandler(log, logMessage, logMessage);
        return new ReturnObjectWrapper<>(200, result, null);
    }
    
    /* ***************************************************** END GET MAPPINGS ****************************************************** */
    
    /* **************************************************** START POST MAPPINGS **************************************************** */
    
    
    
    /* ***************************************************** END POST MAPPINGS ***************************************************** */
    
    /* **************************************************** START PUT MAPPINGS ***************************************************** */
    
    
    
    /* ***************************************************** END PUT MAPPINGS ****************************************************** */
    
    /* *************************************************** START DELETE MAPPINGS *************************************************** */
    
    
    
    /* **************************************************** END DELETE MAPPINGS **************************************************** */
    
    /* **************************************************** END INDEX CONTROLLER *************************************************** */
}
