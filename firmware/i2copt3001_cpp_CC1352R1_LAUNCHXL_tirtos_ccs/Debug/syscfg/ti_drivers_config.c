/*
 *  ======== ti_drivers_config.c ========
 *  Configured TI-Drivers module definitions
 *
 *  DO NOT EDIT - This file is generated for the CC1352R1_LAUNCHXL
 *  by the SysConfig tool.
 */

#include <stddef.h>
#include <stdint.h>

#ifndef DeviceFamily_CC13X2
#define DeviceFamily_CC13X2
#endif

#include <ti/devices/DeviceFamily.h>

#include "ti_drivers_config.h"

/*
 *  ============================= Display =============================
 */

#include <ti/display/Display.h>
#include <ti/display/DisplayUart.h>

#define CONFIG_Display_COUNT 1

#define Display_UARTBUFFERSIZE 1024
static char displayUARTBuffer[Display_UARTBUFFERSIZE];

DisplayUart_Object displayUartObject;

const DisplayUart_HWAttrs displayUartHWAttrs = {
    .uartIdx      = CONFIG_UART_0,
    .baudRate     = 115200,
    .mutexTimeout = (unsigned int)(-1),
    .strBuf       = displayUARTBuffer,
    .strBufLen    = Display_UARTBUFFERSIZE
};

const Display_Config Display_config[CONFIG_Display_COUNT] = {
    /* CONFIG_Display_0 */
    /* XDS110 UART */
    {
        .fxnTablePtr = &DisplayUartMin_fxnTable,
        .object      = &displayUartObject,
        .hwAttrs     = &displayUartHWAttrs
    },
};

const uint_least8_t Display_count = CONFIG_Display_COUNT;

/*
 *  =============================== GPIO ===============================
 */

#include <ti/drivers/GPIO.h>
#include <ti/drivers/gpio/GPIOCC26XX.h>

#define CONFIG_GPIO_COUNT 3

/*
 *  ======== gpioPinConfigs ========
 *  Array of Pin configurations
 */
GPIO_PinConfig gpioPinConfigs[] = {
    /* CONFIG_GPIO_LED_0 : LaunchPad LED Red */
    GPIOCC26XX_DIO_06 | GPIO_CFG_OUT_STD | GPIO_CFG_OUT_STR_MED | GPIO_CFG_OUT_LOW,
    /* CONFIG_GPIO_OPT3001_POWER : OPT3001 Power */
    GPIOCC26XX_DIO_29 | GPIO_CFG_OUT_STD | GPIO_CFG_OUT_STR_HIGH | GPIO_CFG_OUT_HIGH,
    /* CONFIG_GPIO_OPT3001_INTERRUPT : OPT3001 Interrupt */
    GPIOCC26XX_DIO_27 | GPIO_CFG_IN_PU | GPIO_CFG_IN_INT_FALLING,
};

/*
 *  ======== gpioCallbackFunctions ========
 *  Array of callback function pointers
 *
 *  NOTE: Unused callback entries can be omitted from the callbacks array to
 *  reduce memory usage by enabling callback table optimization
 *  (GPIO.optimizeCallbackTableSize = true)
 */
GPIO_CallbackFxn gpioCallbackFunctions[] = {
    /* CONFIG_GPIO_LED_0 : LaunchPad LED Red */
    NULL,
    /* CONFIG_GPIO_OPT3001_POWER : OPT3001 Power */
    NULL,
    /* CONFIG_GPIO_OPT3001_INTERRUPT : OPT3001 Interrupt */
    NULL,
};

const uint_least8_t CONFIG_GPIO_LED_0_CONST = CONFIG_GPIO_LED_0;
const uint_least8_t CONFIG_GPIO_OPT3001_POWER_CONST = CONFIG_GPIO_OPT3001_POWER;
const uint_least8_t CONFIG_GPIO_OPT3001_INTERRUPT_CONST = CONFIG_GPIO_OPT3001_INTERRUPT;

/*
 *  ======== GPIOCC26XX_config ========
 */
const GPIOCC26XX_Config GPIOCC26XX_config = {
    .pinConfigs = (GPIO_PinConfig *)gpioPinConfigs,
    .callbacks = (GPIO_CallbackFxn *)gpioCallbackFunctions,
    .numberOfPinConfigs = 3,
    .numberOfCallbacks = 3,
    .intPriority = (~0)
};

/*
 *  =============================== I2C ===============================
 */

#include <ti/drivers/I2C.h>
#include <ti/drivers/i2c/I2CCC26XX.h>
#include <ti/drivers/power/PowerCC26XX.h>

#define CONFIG_I2C_COUNT 1

/*
 *  ======== i2cCC26xxObjects ========
 */
I2CCC26XX_Object i2cCC26xxObjects[CONFIG_I2C_COUNT];

/*
 *  ======== i2cCC26xxHWAttrs ========
 */
const I2CCC26XX_HWAttrsV1 i2cCC26xxHWAttrs[CONFIG_I2C_COUNT] = {
    /* CONFIG_I2C_OPT3001 */
    /* BAS Sensors Mark II I2C */
    {
        .baseAddr    = I2C0_BASE,
        .powerMngrId = PowerCC26XX_PERIPH_I2C0,
        .intNum      = INT_I2C_IRQ,
        .intPriority = (~0),
        .swiPriority = 0,
        .sclPin      = IOID_4,
        .sdaPin      = IOID_5
    },
};

/*
 *  ======== I2C_config ========
 */
const I2C_Config I2C_config[CONFIG_I2C_COUNT] = {
    /* CONFIG_I2C_OPT3001 */
    /* BAS Sensors Mark II I2C */
    {
        .object      = &i2cCC26xxObjects[CONFIG_I2C_OPT3001],
        .hwAttrs     = &i2cCC26xxHWAttrs[CONFIG_I2C_OPT3001]
    },
};

const uint_least8_t CONFIG_I2C_OPT3001_CONST = CONFIG_I2C_OPT3001;
const uint_least8_t I2C_count = CONFIG_I2C_COUNT;

/*
 *  =============================== PIN ===============================
 */
#include <ti/drivers/PIN.h>
#include <ti/drivers/pin/PINCC26XX.h>

#define CONFIG_PIN_COUNT 7

const PIN_Config BoardGpioInitTable[CONFIG_PIN_COUNT + 1] = {
    /* XDS110 UART, Parent Signal: CONFIG_UART_0 TX, (DIO13) */
    CONFIG_PIN_0 | PIN_GPIO_OUTPUT_EN | PIN_GPIO_LOW | PIN_PUSHPULL | PIN_DRVSTR_MED,
    /* XDS110 UART, Parent Signal: CONFIG_UART_0 RX, (DIO12) */
    CONFIG_PIN_1 | PIN_INPUT_EN | PIN_PULLDOWN | PIN_IRQ_DIS,
    /* LaunchPad LED Red, Parent Signal: CONFIG_GPIO_LED_0 GPIO Pin, (DIO6) */
    CONFIG_PIN_2 | PIN_GPIO_OUTPUT_EN | PIN_GPIO_LOW | PIN_PUSHPULL | PIN_DRVSTR_MED,
    /* OPT3001 Power, Parent Signal: CONFIG_GPIO_OPT3001_POWER GPIO Pin, (DIO29) */
    CONFIG_PIN_3 | PIN_GPIO_OUTPUT_EN | PIN_GPIO_HIGH | PIN_PUSHPULL | PIN_DRVSTR_MED,
    /* OPT3001 Interrupt, Parent Signal: CONFIG_GPIO_OPT3001_INTERRUPT GPIO Pin, (DIO27) */
    CONFIG_PIN_4 | PIN_INPUT_EN | PIN_PULLUP | PIN_IRQ_DIS,
    /* BAS Sensors Mark II I2C, Parent Signal: CONFIG_I2C_OPT3001 SDA, (DIO5) */
    CONFIG_PIN_5 | PIN_INPUT_EN | PIN_PULLUP | PIN_IRQ_DIS,
    /* BAS Sensors Mark II I2C, Parent Signal: CONFIG_I2C_OPT3001 SCL, (DIO4) */
    CONFIG_PIN_6 | PIN_INPUT_EN | PIN_PULLUP | PIN_IRQ_DIS,

    PIN_TERMINATE
};

const PINCC26XX_HWAttrs PINCC26XX_hwAttrs = {
    .intPriority = (~0),
    .swiPriority = 0
};

/*
 *  =============================== Power ===============================
 */
#include <ti/drivers/Power.h>
#include <ti/drivers/power/PowerCC26X2.h>
#include "ti_drivers_config.h"

extern void PowerCC26XX_standbyPolicy(void);
extern bool PowerCC26XX_calibrate(unsigned int);

const PowerCC26X2_Config PowerCC26X2_config = {
    .enablePolicy             = true,
    .policyInitFxn            = NULL,
    .policyFxn                = PowerCC26XX_standbyPolicy,
    .calibrateFxn             = PowerCC26XX_calibrate,
    .calibrateRCOSC_LF        = true,
    .calibrateRCOSC_HF        = true,
    .enableTCXOFxn            = NULL
};

/*
 *  =============================== UART ===============================
 */

#include <ti/drivers/UART.h>
#include <ti/drivers/uart/UARTCC26XX.h>
#include <ti/drivers/Power.h>
#include <ti/drivers/power/PowerCC26X2.h>
#include <ti/devices/cc13x2_cc26x2/inc/hw_memmap.h>
#include <ti/devices/cc13x2_cc26x2/inc/hw_ints.h>

#define CONFIG_UART_COUNT 1

UARTCC26XX_Object uartCC26XXObjects[CONFIG_UART_COUNT];

static unsigned char uartCC26XXRingBuffer0[32];

static const UARTCC26XX_HWAttrsV2 uartCC26XXHWAttrs[CONFIG_UART_COUNT] = {
  {
    .baseAddr           = UART1_BASE,
    .intNum             = INT_UART1_COMB,
    .intPriority        = (~0),
    .swiPriority        = 0,
    .powerMngrId        = PowerCC26X2_PERIPH_UART1,
    .ringBufPtr         = uartCC26XXRingBuffer0,
    .ringBufSize        = sizeof(uartCC26XXRingBuffer0),
    .rxPin              = IOID_12,
    .txPin              = IOID_13,
    .ctsPin             = PIN_UNASSIGNED,
    .rtsPin             = PIN_UNASSIGNED,
    .txIntFifoThr       = UARTCC26XX_FIFO_THRESHOLD_1_8,
    .rxIntFifoThr       = UARTCC26XX_FIFO_THRESHOLD_4_8,
    .errorFxn           = NULL
  },
};

const UART_Config UART_config[CONFIG_UART_COUNT] = {
    {   /* CONFIG_UART_0 */
        .fxnTablePtr = &UARTCC26XX_fxnTable,
        .object      = &uartCC26XXObjects[CONFIG_UART_0],
        .hwAttrs     = &uartCC26XXHWAttrs[CONFIG_UART_0]
    },
};

const uint_least8_t CONFIG_UART_0_CONST = CONFIG_UART_0;
const uint_least8_t UART_count = CONFIG_UART_COUNT;

#include <stdbool.h>

#include <ti/devices/cc13x2_cc26x2/driverlib/ioc.h>
#include <ti/devices/cc13x2_cc26x2/driverlib/cpu.h>

#include <ti/drivers/pin/PINCC26XX.h>

/*
 *  ======== Board_sendExtFlashByte ========
 */
void Board_sendExtFlashByte(PIN_Handle pinHandle, uint8_t byte)
{
    uint8_t i;

    /* SPI Flash CS */
    PIN_setOutputValue(pinHandle, IOID_20, 0);

    for (i = 0; i < 8; i++) {
        PIN_setOutputValue(pinHandle, IOID_10, 0);  /* SPI Flash CLK */

        /* SPI Flash MOSI */
        PIN_setOutputValue(pinHandle, IOID_9, (byte >> (7 - i)) & 0x01);
        PIN_setOutputValue(pinHandle, IOID_10, 1);  /* SPI Flash CLK */

        /*
         * Waste a few cycles to keep the CLK high for at
         * least 45% of the period.
         * 3 cycles per loop: 8 loops @ 48 Mhz = 0.5 us.
         */
        CPUdelay(8);
    }

    PIN_setOutputValue(pinHandle, IOID_10, 0);  /* CLK */
    PIN_setOutputValue(pinHandle, IOID_20, 1);  /* CS */

    /*
     * Keep CS high at least 40 us
     * 3 cycles per loop: 700 loops @ 48 Mhz ~= 44 us
     */
    CPUdelay(700);
}

/*
 *  ======== Board_wakeUpExtFlash ========
 */
void Board_wakeUpExtFlash(void)
{
    PIN_Config extFlashPinTable[] = {
        /* SPI Flash CS */
        IOID_20 | PIN_GPIO_OUTPUT_EN | PIN_GPIO_HIGH | PIN_PUSHPULL |
                PIN_INPUT_DIS | PIN_DRVSTR_MED,
        PIN_TERMINATE
    };
    PIN_State extFlashPinState;
    PIN_Handle extFlashPinHandle = PIN_open(&extFlashPinState, extFlashPinTable);

    /*
     *  To wake up we need to toggle the chip select at
     *  least 20 ns and ten wait at least 35 us.
     */

    /* Toggle chip select for ~20ns to wake ext. flash */
    PIN_setOutputValue(extFlashPinHandle, IOID_20, 0);
    /* 3 cycles per loop: 1 loop @ 48 Mhz ~= 62 ns */
    CPUdelay(1);
    PIN_setOutputValue(extFlashPinHandle, IOID_20, 1);
    /* 3 cycles per loop: 560 loops @ 48 Mhz ~= 35 us */
    CPUdelay(560);

    PIN_close(extFlashPinHandle);
}

/*
 *  ======== Board_shutDownExtFlash ========
 */
void Board_shutDownExtFlash(void)
{
    /*
     *  To be sure we are putting the flash into sleep and not waking it,
     *  we first have to make a wake up call
     */
    Board_wakeUpExtFlash();

    PIN_Config extFlashPinTable[] = {
        /* SPI Flash CS*/
        IOID_20 | PIN_GPIO_OUTPUT_EN | PIN_GPIO_HIGH | PIN_PUSHPULL |
                PIN_INPUT_DIS | PIN_DRVSTR_MED,
        /* SPI Flash CLK */
        IOID_10 | PIN_GPIO_OUTPUT_EN | PIN_GPIO_LOW | PIN_PUSHPULL |
                PIN_INPUT_DIS | PIN_DRVSTR_MED,
        /* SPI Flash MOSI */
        IOID_9 | PIN_GPIO_OUTPUT_EN | PIN_GPIO_LOW | PIN_PUSHPULL |
                PIN_INPUT_DIS | PIN_DRVSTR_MED,
        /* SPI Flash MISO */
        IOID_8 | PIN_INPUT_EN | PIN_PULLDOWN,
        PIN_TERMINATE
    };
    PIN_State extFlashPinState;
    PIN_Handle extFlashPinHandle = PIN_open(&extFlashPinState, extFlashPinTable);

    uint8_t extFlashShutdown = 0xB9;

    Board_sendExtFlashByte(extFlashPinHandle, extFlashShutdown);

    PIN_close(extFlashPinHandle);
}


#include <ti/drivers/Board.h>

/*
 *  ======== Board_initHook ========
 *  Perform any board-specific initialization needed at startup.  This
 *  function is declared weak to allow applications to override it if needed.
 */
void __attribute__((weak)) Board_initHook(void)
{
}

/*
 *  ======== Board_init ========
 *  Perform any initialization needed before using any board APIs
 */
void Board_init(void)
{
    /* ==== /ti/drivers/Power initialization ==== */
    Power_init();

    /* ==== /ti/drivers/PIN initialization ==== */
    if (PIN_init(BoardGpioInitTable) != PIN_SUCCESS) {
        /* Error with PIN_init */
        while (1);
    }
    /* ==== /ti/devices/CCFGTemplate initialization ==== */


    Board_shutDownExtFlash();

    Board_initHook();
}

