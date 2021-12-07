
#include <unistd.h>
#include <stdio.h>

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

void makeJsonString();

extern uint16_t data_count;
extern char jsonData[128];


static OPT3001 opt3001;

/*
 * These values narrow the limits of the sensor to
 * allow those running the example to test the interrupt feature
 */
#define TEMPORARY_LOWLIMIT   30
#define TEMPORARY_HIGHLIMIT  4000

/* Number of samples to read from the sensor */
#define NUM_SAMPLES 60

/* Stack size in bytes */
#define ALARMTHREADSTACKSIZE  512

/* Range of possible I2C slave addresses */
#define MIN_ADDRESS         0x08
#define MAX_ADDRESS         0x78

void makeJsonString() {
    sprintf(jsonData,"{lightData:%d}",data_count);
}

void *mainThread(void *arg0)
{
    data_count = 2;
    uint16_t sample;
    int32_t luxValue;
    uint8_t data;
    uint8_t addr;

    /* I2C structures*/
    I2C_Handle      i2c;
    I2C_Params      i2cParams;
    I2C_Transaction i2cTransaction;

    /* Alarm pthread structures */
//    pthread_t           alarm;
    pthread_attr_t      attrs;
    struct sched_param  priParam;
    int                 retc;

    /* Call driver init functions */
    GPIO_init();
    I2C_init();
    data_count = 3;
//    sem_init(&semaphoreAlarm, 0, 0);

    /* Initialize the alarm thread attributes structure with default values */
    pthread_attr_init(&attrs);

    /* Set priority, detach state, and stack size attributes for alarm thread */
    priParam.sched_priority = 2;
    retc = pthread_attr_setschedparam(&attrs, &priParam);
    retc |= pthread_attr_setdetachstate(&attrs, PTHREAD_CREATE_DETACHED);
//    retc |= pthread_attr_setstacksize(&attrs, ALARMTHREADSTACKSIZE);
    if (retc != 0) {
        /* Failed to set attributes */
        data_count = 401;
        while (1) {}
    }

    /* Create the alarm thread */
//    retc = pthread_create(&alarm, &attrs, alarmThread, NULL);
//    if (retc != 0) {
//        /* pthread_create() failed */
//        while (1) {}
//    }

    /*
     * Set OPT3001 Power pin to high (give device power) and then sleep briefly
     * to allow device to power up
     */
    GPIO_setConfig(CONFIG_GPIO_OPT3001_POWER,
        GPIO_CFG_OUT_STD | GPIO_CFG_OUT_HIGH);
    sleep(1);
    data_count = 4;

    /* Setup GPIO Interrupt Function */
//    GPIO_setCallback(CONFIG_GPIO_OPT3001_INTERRUPT, opt3001LimitInterruptFxn);

    /* Create I2C for usage */
    I2C_Params_init(&i2cParams);
    i2cParams.bitRate = I2C_400kHz;
    i2c = I2C_open(CONFIG_I2C_OPT3001, &i2cParams);
    if (i2c == NULL) {
        data_count = 402;
        while (1);
    }
    else {
        data_count = 5;
//        Display_printf(display, 0, 0, (char *)"I2C Initialized!");
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
        if (I2C_transfer(i2c, &i2cTransaction)) {
            data_count = 6;
//            Display_printf(display, 0, 0,
//                (char *)"I2C device found at address 0x%x!", addr);
        }
    }

//    Display_printf(display, 0, 0, (char *)"Finished looking for I2C devices.",
//        addr);

    /* Make instance of OPT3001 and then enable the interrupt pin */
    if(!opt3001.init(i2c, OPT3001::SlaveAddress::ADDRPIN_GND))
    {
        data_count = 403;
//        Display_printf(display, 0, 0,
//            (char *)"Failed to communicate with OPT3001!");
        while (1);
    }
    GPIO_enableInt(CONFIG_GPIO_OPT3001_INTERRUPT);

    /* Configure the LED Pin and turn on user LED */
    GPIO_setConfig(CONFIG_GPIO_RLED, GPIO_CFG_OUT_STD | GPIO_CFG_OUT_LOW);
    GPIO_write(CONFIG_GPIO_RLED, CONFIG_GPIO_LED_ON);

    data_count = 7;
//
//    /* Print basic OPT3001 device info */
//    Display_printf(display, 0, 0, (char *)"Information about "
//        "the OPT3001 device:");
//    Display_printf(display, 0, 0, (char *)"I2C Slave Address:\t0x%x",
//        OPT3001::SlaveAddress::ADDRPIN_GND);
//    Display_printf(display, 0, 0, (char *)"Device ID:\t\t0x%x",
//        opt3001.getDeviceID());
//    Display_printf(display, 0, 0, (char *)"Manufacturer ID:\t0x%x",
//        opt3001.getManufacturerID());
    opt3001.resetConfiguration();
//    Display_printf(display, 0, 0, (char *)"Configuration Register:\t0x%x",
//        opt3001.getConfiguration());
//    Display_printf(display, 0, 0, (char *)"Low Limit:\t\t%u lux",
//        opt3001.getLowLimit());
//    Display_printf(display, 0, 0, (char *)"High Limit:\t\t%u lux\n",
//        opt3001.getHighLimit());
//
    /* Set low limit & high limits */
    if(opt3001.setLowLimit(TEMPORARY_LOWLIMIT) &&
        opt3001.setHighLimit(TEMPORARY_HIGHLIMIT))
    {
        data_count = 8;
//        Display_printf(display, 0, 0,
//            (char *)"Set low limit to %u lux", opt3001.getLowLimit());
//        Display_printf(display, 0, 0,
//            (char *)"Set high limit to %u lux\n", opt3001.getHighLimit());
    }
    else
    {
        data_count = 404;
//        Display_printf(display, 0, 0, (char *)"Failed to set limits");
        while(1);
    }
//
//
    /* Read data in continuous conversions mode */
    if(!opt3001.setConversionMode(OPT3001::ConversionMode::CONTINUOUS_CONVERSIONS))
    {
        data_count = 405;
//        Display_printf(display, 0, 0,
//            (char *)"Failed to Set OPT3001 to continuous conversions mode.");
        while(1);
    }
    data_count = 9;
    sleep(3);
    data_count = 3;
    sleep(1);
    data_count = 2;
    sleep(1);
    data_count = 1;
    sleep(1);
    data_count = 0;
//
    /*
     * Read the OPT3001 sensor NUM_SAMPLES times and display the values.
     * Sleep between readings
     */
//    Display_printf(display, 0, 0, (char *)"\nReading samples from OPT3001:");
//
    for (sample = 1; sample <= NUM_SAMPLES; sample++) {

        /* Sleep between every sample */
        sleep(1);
        luxValue = opt3001.getResult();

        /* If -1 is returned from getResult(), then the I2C transaction failed */
        if(luxValue != -1)
        {
            data_count = luxValue;
            makeJsonString();
//            Display_printf(display, 0, 0, (char *)"Sample: #%u)\t%u lux",
//                sample, luxValue);
        }
    }
//
//    /* Set OPT3001 to shutdown mode */
//    if(!opt3001.setConversionMode(OPT3001::ConversionMode::SHUTDOWN))
//    {
//        Display_printf(display, 0, 0,
//            (char *)"Failed to set device to shutdown mode");
//    }
//
//    /* Reset limits back to default values */
//    if(!(opt3001.resetLowLimit() && opt3001.resetHighLimit()))
//    {
//        Display_printf(display, 0, 0,
//            (char *)"Failed to reset limits to factory default values");
//    }
//
//    /* Close the I2C connection */
//    I2C_close(i2c);
//    Display_printf(display, 0, 0, (char *)"\nI2C closed!");

    return (NULL);
}
