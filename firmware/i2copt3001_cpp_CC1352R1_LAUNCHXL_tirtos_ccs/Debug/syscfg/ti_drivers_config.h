/*
 *  ======== ti_drivers_config.h ========
 *  Configured TI-Drivers module declarations
 *
 *  The macros defines herein are intended for use by applications which
 *  directly include this header. These macros should NOT be hard coded or
 *  copied into library source code.
 *
 *  Symbols declared as const are intended for use with libraries.
 *  Library source code must extern the correct symbol--which is resolved
 *  when the application is linked.
 *
 *  DO NOT EDIT - This file is generated for the CC1352R1_LAUNCHXL
 *  by the SysConfig tool.
 */
#ifndef ti_drivers_config_h
#define ti_drivers_config_h

#define CONFIG_SYSCONFIG_PREVIEW

#define CONFIG_CC1352R1_LAUNCHXL
#ifndef DeviceFamily_CC13X2
#define DeviceFamily_CC13X2
#endif

#include <ti/devices/DeviceFamily.h>

#include <stdint.h>

/* support C++ sources */
#ifdef __cplusplus
extern "C" {
#endif


/*
 *  ======== CCFG ========
 */


/*
 *  ======== GPIO ========
 */

/* DIO6, LaunchPad LED Red */
extern const uint_least8_t                  CONFIG_GPIO_LED_0_CONST;
#define CONFIG_GPIO_LED_0                   0
/* DIO29, OPT3001 Power */
extern const uint_least8_t                  CONFIG_GPIO_OPT3001_POWER_CONST;
#define CONFIG_GPIO_OPT3001_POWER           1
/* DIO27, OPT3001 Interrupt */
extern const uint_least8_t                  CONFIG_GPIO_OPT3001_INTERRUPT_CONST;
#define CONFIG_GPIO_OPT3001_INTERRUPT       2
#define CONFIG_TI_DRIVERS_GPIO_COUNT        3

/* LEDs are active high */
#define CONFIG_GPIO_LED_ON  (1)
#define CONFIG_GPIO_LED_OFF (0)

#define CONFIG_LED_ON  (CONFIG_GPIO_LED_ON)
#define CONFIG_LED_OFF (CONFIG_GPIO_LED_OFF)


/*
 *  ======== I2C ========
 */

/*
 *  SCL: DIO4
 *  SDA: DIO5
 *  BAS Sensors Mark II I2C
 */
extern const uint_least8_t              CONFIG_I2C_OPT3001_CONST;
#define CONFIG_I2C_OPT3001              0
#define CONFIG_TI_DRIVERS_I2C_COUNT     1

/* ======== I2C Addresses and Speeds ======== */
#include <ti/drivers/I2C.h>

/* ---- CONFIG_I2C_OPT3001 I2C bus components ---- */

/* BP-BASSENSORSMKII/TMP117 address and max speed */
#define CONFIG_I2C_OPT3001_BP_BASSENSORSMKII_TMP117_ADDR     (0x48)
#define CONFIG_I2C_OPT3001_BP_BASSENSORSMKII_TMP117_MAXSPEED (400U) /* Kbps */

/* BP-BASSENSORSMKII/OPT3001 address and max speed */
#define CONFIG_I2C_OPT3001_BP_BASSENSORSMKII_OPT3001_ADDR     (0x44)
#define CONFIG_I2C_OPT3001_BP_BASSENSORSMKII_OPT3001_MAXSPEED (2600U) /* Kbps */

/* BP-BASSENSORSMKII/HDC2080 address and max speed */
#define CONFIG_I2C_OPT3001_BP_BASSENSORSMKII_HDC2080_ADDR     (0x40)
#define CONFIG_I2C_OPT3001_BP_BASSENSORSMKII_HDC2080_MAXSPEED (400U) /* Kbps */

/* BP-BASSENSORSMKII/BMI160_BMM150 address and max speed */
#define CONFIG_I2C_OPT3001_BP_BASSENSORSMKII_BMI160_BMM150_ADDR     (0x69)
#define CONFIG_I2C_OPT3001_BP_BASSENSORSMKII_BMI160_BMM150_MAXSPEED (1000U) /* Kbps */

/* CONFIG_I2C_OPT3001 max speed (supported by all components) */
#define CONFIG_I2C_OPT3001_MAXSPEED   (400U) /* Kbps */
#define CONFIG_I2C_OPT3001_MAXBITRATE ((I2C_BitRate)I2C_400kHz)


/*
 *  ======== PIN ========
 */
#include <ti/drivers/PIN.h>

extern const PIN_Config BoardGpioInitTable[];

/* XDS110 UART, Parent Signal: CONFIG_UART_0 TX, (DIO13) */
#define CONFIG_PIN_0                   0x0000000d
/* XDS110 UART, Parent Signal: CONFIG_UART_0 RX, (DIO12) */
#define CONFIG_PIN_1                   0x0000000c
/* LaunchPad LED Red, Parent Signal: CONFIG_GPIO_LED_0 GPIO Pin, (DIO6) */
#define CONFIG_PIN_2                   0x00000006
/* OPT3001 Power, Parent Signal: CONFIG_GPIO_OPT3001_POWER GPIO Pin, (DIO29) */
#define CONFIG_PIN_3                   0x0000001d
/* OPT3001 Interrupt, Parent Signal: CONFIG_GPIO_OPT3001_INTERRUPT GPIO Pin, (DIO27) */
#define CONFIG_PIN_4                   0x0000001b
/* BAS Sensors Mark II I2C, Parent Signal: CONFIG_I2C_OPT3001 SDA, (DIO5) */
#define CONFIG_PIN_5                   0x00000005
/* BAS Sensors Mark II I2C, Parent Signal: CONFIG_I2C_OPT3001 SCL, (DIO4) */
#define CONFIG_PIN_6                   0x00000004
#define CONFIG_TI_DRIVERS_PIN_COUNT    7


/*
 *  ======== UART ========
 */

/*
 *  TX: DIO13
 *  RX: DIO12
 *  XDS110 UART
 */
extern const uint_least8_t              CONFIG_UART_0_CONST;
#define CONFIG_UART_0                   0
#define CONFIG_TI_DRIVERS_UART_COUNT    1


/*
 *  ======== Board_init ========
 *  Perform all required TI-Drivers initialization
 *
 *  This function should be called once at a point before any use of
 *  TI-Drivers.
 */
extern void Board_init(void);

/*
 *  ======== Board_initGeneral ========
 *  (deprecated)
 *
 *  Board_initGeneral() is defined purely for backward compatibility.
 *
 *  All new code should use Board_init() to do any required TI-Drivers
 *  initialization _and_ use <Driver>_init() for only where specific drivers
 *  are explicitly referenced by the application.  <Driver>_init() functions
 *  are idempotent.
 */
#define Board_initGeneral Board_init

#ifdef __cplusplus
}
#endif

#endif /* include guard */
