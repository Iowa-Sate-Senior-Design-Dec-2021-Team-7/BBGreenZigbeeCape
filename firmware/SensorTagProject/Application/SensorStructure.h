
enum SENSOR_STATUS {
    INIT = 0,

};

typedef struct {
    SENSOR_STATUS status;
    long int value;
} SensorData;

#define DEFAULT_SENSOR_DATA SensorData s = { .status = SENSOR_STATUS.INIT, .value = 0 }
