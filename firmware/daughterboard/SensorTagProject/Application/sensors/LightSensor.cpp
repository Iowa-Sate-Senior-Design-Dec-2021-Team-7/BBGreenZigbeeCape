
#include <unistd.h>
#include <stdio.h>
#include <string.h>

/* Driver Header files */
#include <ti/drivers/GPIO.h>
#include <ti/drivers/I2C.h>
#include <ti/display/Display.h>

/* Threading header files */
#include <pthread.h>
#include <semaphore.h>

/* Include OPT3001 driver file */
#include "OPT3001.h"

/* Include Common sensor file */
#include "SensorStructure.h"

/* Driver Configuration */
#include "ti_drivers_config.h"


/* Thread function prototypes */
extern "C" {
    void  *mainThread(void *arg0);
}

extern uint16_t sensor_data;
extern char sensor_status[16];


static OPT3001 opt3001;

/*
 * These values narrow the limits of the sensor to
 * allow those running the example to test the interrupt feature
 */
#define TEMPORARY_LOWLIMIT   30
#define TEMPORARY_HIGHLIMIT  4000

/* Number of samples to read from the sensor */
#define NUM_SAMPLES 600000

/* Stack size in bytes */
#define ALARMTHREADSTACKSIZE  512

/* Range of possible I2C slave addresses */
#define MIN_ADDRESS         0x08
#define MAX_ADDRESS         0x78

/*
 * mainThread is the function that will run the sensor continuously. It used PThreads and depends on the OPT3001 sensor files
 */
void *mainThread(void *arg0)
{
    strcpy(sensor_status,"STARTING");
    uint16_t sample;
    int32_t luxValue;
    uint8_t data;
    uint8_t addr;

    /* I2C structures*/
    I2C_Handle      i2c;
    I2C_Params      i2cParams;
    I2C_Transaction i2cTransaction;

    /* Alarm pthread structures */
    pthread_attr_t      attrs;
    struct sched_param  priParam;
    int                 retc;

    /* Call driver init functions */
    GPIO_init();
    I2C_init();

    /* Initialize the alarm thread attributes structure with default values */
    pthread_attr_init(&attrs);

    /* Set priority, detach state, and stack size attributes for alarm thread */
    priParam.sched_priority = 2;
    retc = pthread_attr_setschedparam(&attrs, &priParam);
    retc |= pthread_attr_setdetachstate(&attrs, PTHREAD_CREATE_DETACHED);
    if (retc != 0) {
        /* Failed to set attributes */
        strcpy(sensor_status,"FAILED");
        while (1) {}
    }


    /*
     * Set OPT3001 Power pin to high (give device power) and then sleep briefly
     * to allow device to power up
     */
    GPIO_setConfig(CONFIG_GPIO_OPT3001_POWER,
        GPIO_CFG_OUT_STD | GPIO_CFG_OUT_HIGH);
    sleep(1);

    /* Create I2C for usage */
    I2C_Params_init(&i2cParams);
    i2cParams.bitRate = I2C_400kHz;
    i2c = I2C_open(CONFIG_I2C_OPT3001, &i2cParams);
    if (i2c == NULL) {
        strcpy(sensor_status,"FAILED");
        while (1);
    }

    /* Setup transaction to find slave devices */
    i2cTransaction.writeBuf = &data;
    i2cTransaction.writeCount = 1;
    i2cTransaction.readBuf = &data;
    i2cTransaction.readCount = 0;
    data = 0;

    /*
     * Attempt transaction with every possible slave address. If a transaction
     * returns successfully, then that slave address is valid and will be
     * printed to the console.
     */
    for (addr = MIN_ADDRESS; addr < MAX_ADDRESS; addr++) {
        i2cTransaction.slaveAddress = addr;
    }

    /* Make instance of OPT3001 and then enable the interrupt pin */
    if(!opt3001.init(i2c, OPT3001::SlaveAddress::ADDRPIN_GND))
    {
        strcpy(sensor_status,"FAILED");
        while (1);
    }
    GPIO_enableInt(CONFIG_GPIO_OPT3001_INTERRUPT);

    /* Configure the LED Pin and turn on user LED */
    GPIO_setConfig(CONFIG_GPIO_RLED, GPIO_CFG_OUT_STD | GPIO_CFG_OUT_LOW);
    GPIO_write(CONFIG_GPIO_RLED, CONFIG_GPIO_LED_ON);

    opt3001.resetConfiguration();

    /* Set low limit & high limits */
    if(opt3001.setLowLimit(TEMPORARY_LOWLIMIT) &&
        opt3001.setHighLimit(TEMPORARY_HIGHLIMIT))
    {
        strcpy(sensor_status,"INITIALIZED");
    }
    else
    {
        strcpy(sensor_status,"FAILED");
        while(1);
    }

    /* Read data in continuous conversions mode */
    if(!opt3001.setConversionMode(OPT3001::ConversionMode::CONTINUOUS_CONVERSIONS))
    {
        strcpy(sensor_status,"FAILED");
        while(1);
    }

    // Slight Pause for Dramatic Effect
    sleep(3);

    /*
     * Read the OPT3001 sensor NUM_SAMPLES times and display the values.
     * Sleep between readings
     */
    while(1) {
//    for (sample = 1; sample <= NUM_SAMPLES; sample++) {

        /* Sleep between every sample */
        sleep(1);
        luxValue = opt3001.getResult();

        /* If -1 is returned from getResult(), then the I2C transaction failed */
        if(luxValue != -1)
        {
            strcpy(sensor_status,"READING");
            sensor_data = luxValue;
        }
    }

//    strcpy(sensor_status,"DONE");

    return (NULL);
}
