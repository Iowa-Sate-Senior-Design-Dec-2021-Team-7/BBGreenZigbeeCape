package webhost.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum EndDeviceType {
    @JsonProperty("SENSOR")
    SENSOR,
    @JsonProperty("CONTROLLER")
    CONTROLLER;
}
