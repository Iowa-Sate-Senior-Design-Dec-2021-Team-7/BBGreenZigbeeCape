package webhost.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import webhost.components.GlobalUtils;
import webhost.components.JsonDateSerializer;
import webhost.enums.DataPayloadType;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
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

    @ManyToOne(targetEntity = EndDevice.class, fetch = FetchType.EAGER)
    private EndDevice device;
    
    @Column(name="type")
    private DataPayloadType type;

    @Column(name="value")
    private Double value;

    @Column(name="timestamp")
    @JsonSerialize(using = JsonDateSerializer.class)
    private Date timestamp;
    
    public DataPayload() {
        this.setDevice(null);
        this.setType(DataPayloadType.NONE);
        this.setValue(new Double(0.0));
        this.setTimestamp(null);
    }
    
    public Integer getId_db() { return id_db; }
    public void setId_db(Integer id_db) { this.id_db = id_db; }
    
    public EndDevice getDevice() { return device; }
    public void setDevice(EndDevice device) { this.device = device; }
    
    public DataPayloadType getType() { return type; }
    public void setType(DataPayloadType type) { this.type = type; }
    
    public Double getValue() { return value; }
    public void setValue(Double value) { this.value = value; }

    public Date getTimestamp() { return timestamp; }
    public void setTimestamp(Date timestamp) { this.timestamp = timestamp; }
}
