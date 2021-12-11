package webhost.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum DataPayloadType {
    @JsonProperty("TEMP_C")
    TEMP_C,
    @JsonProperty("TEMP_F")
    TEMP_F,
    @JsonProperty("LIGHT")
    LIGHT,
    @JsonProperty("HUMID")
    HUMID,
    @JsonProperty("ACCEL")
    ACCEL,
    @JsonProperty("GYRO")
    GYRO,
    @JsonProperty("CONTROL")
    CONTROL,
    @JsonProperty("NONE")
    NONE;
}
