package webhost.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.web.bind.annotation.CrossOrigin;
import webhost.components.GlobalUtils;
import webhost.enums.DataPayloadType;
import webhost.enums.EndDeviceType;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(maxAge=3600)
@Entity
@Table(name="devices")
public class EndDevice {
    
    @Transient
    private final GlobalUtils gUtils = new GlobalUtils(null);
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id_db;

    @JsonIgnore
    @OneToMany(targetEntity = DataPayload.class, mappedBy = "device", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<DataPayload> payloads;
    
    private String id_network;

    private EndDeviceType type_device;

    private DataPayloadType type_data;
    
    public EndDevice() {
        this.setPayloads(new ArrayList<>());
        this.setId_network("");
        this.setType_data(DataPayloadType.NONE);
        this.setType_device(EndDeviceType.SENSOR);
    }
    
    public Integer getId_db() { return id_db; }
    public void setId_db(Integer id_db) { this.id_db = id_db; }
    
    public List<DataPayload> getPayloads() { return payloads; }
    public void setPayloads(ArrayList<DataPayload> payloads) { this.payloads = payloads; }
    
    public String getId_network() { return id_network; }
    public void setId_network(String id_network) { this.id_network = id_network; }
    
    public EndDeviceType getType_device() { return type_device; }
    public void setType_device(EndDeviceType type_device) { this.type_device = type_device; }
    
    public DataPayloadType getType_data() { return type_data; }
    public void setType_data(DataPayloadType type_data) { this.type_data = type_data; }
    
    public void addPayload(DataPayload payload) { payloads.add(payload); }
}
