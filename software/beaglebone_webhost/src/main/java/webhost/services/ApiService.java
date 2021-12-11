package webhost.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webhost.components.GlobalUtils;
import webhost.entities.DataPayload;
import webhost.entities.EndDevice;
import webhost.repositories.DataPayloadRepository;
import webhost.repositories.EndDeviceRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class ApiService {
    
    /* ************************************************* START INSTANCE VARIABLES ************************************************** */
    
    private GlobalUtils gUtils;
    
    private final EndDeviceRepository deviceRepository;
    
    private final DataPayloadRepository payloadRepository;
    
    /* ************************************************** END INSTANCE VARIABLES *************************************************** */
    
    /* **************************************************** START CONSTRUCTORS ***************************************************** */
    
    @Autowired
    public ApiService(GlobalUtils gUtils, EndDeviceRepository deviceRepository, DataPayloadRepository payloadRepository) {
        
        this.gUtils = gUtils;
        this.deviceRepository = deviceRepository;
        this.payloadRepository = payloadRepository;
    }
    
    /* ***************************************************** END CONSTRUCTORS ****************************************************** */
    
    /* ****************************************************** START GET HELPERS **************************************************** */
    
    public List<EndDevice> getDevices() {

        List<EndDevice> devices = deviceRepository.findAll();
        for (EndDevice d : devices) { d.setPayloads(new ArrayList<>()); }
        return deviceRepository.findAll();
    }
    
    public List<DataPayload> getDataPayloads() { return payloadRepository.findAll(); }
    
    public List<DataPayload> getDataPayload(EndDevice device) {
        
        List<DataPayload> result = new ArrayList<>();
        List<DataPayload> payloads = payloadRepository.findAll();
        for (DataPayload p : payloads) {
            if (p.getDevice().getId_network().equals(device.getId_network())) { result.add(p); }
        }
        return result;
    }
    
    /* ******************************************************* END GET HELPERS ***************************************************** */
    
    /* ****************************************************** START POST HELPERS *************************************************** */
    
    
    
    /* ******************************************************* END POST HELPERS **************************************************** */
    
    /* ****************************************************** START PUT HELPERS **************************************************** */
    
    
    
    /* ******************************************************* END PUT HELPERS ***************************************************** */
    
    /* ***************************************************** START DELETE HELPERS ************************************************** */
    
    public Boolean clearDatabase() {

        payloadRepository.deleteAll();
        deviceRepository.deleteAll();
        return new Boolean(true);
    }
    
    /* ****************************************************** END DELETE HELPERS *************************************************** */
    
    /* *************************************************** END HTML STATIC SERVICE ************************************************* */
}
