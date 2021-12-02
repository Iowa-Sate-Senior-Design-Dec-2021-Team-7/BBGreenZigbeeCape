package webhost.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import webhost.components.GlobalUtils;
import webhost.enums.DataPayloadType;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="payloads")
public class DataPayload {
    
    @Transient
    private final GlobalUtils gUtils = new GlobalUtils(null);
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id_db;
    
    @JsonBackReference
    @ManyToOne(targetEntity = EndDevice.class)
    private EndDevice device;
    
    @Column(name="type")
    private DataPayloadType dataType;
    
    @ElementCollection(targetClass=Double.class)
    @Column(name="value")
    private List<Double> dataValue;
    
    public DataPayload() {
        this.setDevice(null);
        this.setDataType(DataPayloadType.NONE);
        this.setDataValue(new ArrayList<>());
    }
    
    public Integer getId_db() { return id_db; }
    public void setId_db(Integer id_db) { this.id_db = id_db; }
    
    public EndDevice getDevice() { return device; }
    public void setDevice(EndDevice device) { this.device = device; }
    
    public DataPayloadType getDataType() { return dataType; }
    public void setDataType(DataPayloadType dataType) { this.dataType = dataType; }
    
    public List<Double> getDataValue() { return dataValue; }
    public void setDataValue(ArrayList<Double> dataValue) { this.dataValue = dataValue; }
}
