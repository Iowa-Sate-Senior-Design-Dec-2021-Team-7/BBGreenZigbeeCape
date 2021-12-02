/*
 * Copyright (c) 2015-2020, Texas Instruments Incorporated
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 * *  Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 *
 * *  Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 *
 * *  Neither the name of Texas Instruments Incorporated nor the names of
 *    its contributors may be used to endorse or promote products derived
 *    from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS;
 * OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 * WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR
 * OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE,
 * EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

/*
 *  ======== uartecho.c ========
 */
#include <stdint.h>
#include <stddef.h>
#include <string.h>
#include <unistd.h>

/* POSIX Header files */
#include <semaphore.h>

/* Driver Header files */
#include <ti/drivers/GPIO.h>
#include <ti/drivers/UART.h>
#include <ti/drivers/apps/LED.h>

/* Driver configuration */
#include "ti_drivers_config.h"

#define JSON_MAXLEN 1023

/*********************************************************************
 * EXTERNAL VARIABLES
 */
extern LED_Handle gRedLedHandle;

/*********************************************************************
 * GLOBAL VARIABLES
 */
UART_Handle bb_uartHandle = NULL;

/*********************************************************************
 * LOCAL VARIABLES
 */
char buf_recieve[JSON_MAXLEN + 1];
char buf_output[JSON_MAXLEN + 8];

/*********************************************************************
 * GLOBAL FUNCTIONS
 */
void bb_uartInit();
static int_fast32_t bb_uartRead(void *buf, size_t maxCount);
static int_fast32_t bb_uartWrite(void *buf, size_t count);

/*
 *  ======== mainThread ========
 */
void *bb_uartThread(void *arg0)
{
    int i = 0;
    if (bb_uartHandle == NULL) { bb_uartInit(); }

    /* Loop forever echoing */
    while (1) {
        int_fast32_t readBytes = bb_uartRead(&buf_recieve, JSON_MAXLEN);

        strcat(buf_output, buf_recieve);
        buf_output[6 + readBytes + 0] = '\r';
        buf_output[6 + readBytes + 1] = '\n';

        bb_uartWrite(&buf_output, strlen(buf_output));

        for (i = 0; i < sizeof(buf_recieve); i++) { buf_recieve[i] = '\0'; }
        for (i = 6; i < sizeof(buf_output); i++) { buf_output[i] = '\0'; }
    }
}

void bb_uartInit() {

    UART_Params     uartParams;
    buf_recieve[0] = '\0';
    buf_output[0] = '\0';
    strcpy(buf_output, "Echo: ");

    while (gRedLedHandle == NULL) { } //stall init until red led is initialized by zstack

    /* Call driver init functions */
    GPIO_init();
    UART_init();

    /* Configure the LED pin */
    GPIO_setConfig(CONFIG_GPIO_RLED, GPIO_CFG_OUT_STD | GPIO_CFG_OUT_LOW);

    /* Create a UART with data processing off. */
    UART_Params_init(&uartParams);
    uartParams.readMode = UART_MODE_BLOCKING;
    uartParams.writeMode = UART_MODE_BLOCKING;
    uartParams.readDataMode = UART_DATA_TEXT;
    uartParams.writeDataMode = UART_DATA_TEXT;
    uartParams.readReturnMode = UART_RETURN_NEWLINE;
    uartParams.baudRate = 115200;

    bb_uartHandle = UART_open(CONFIG_UART_0, &uartParams);

    if (bb_uartHandle == NULL) {
        /* UART_open() failed */
        while(1) {
            LED_startBlinking(gRedLedHandle, 500, LED_BLINK_FOREVER);
        }
    }

    /* Turn on user LED to indicate successful initialization */
    LED_setOn(gRedLedHandle, LED_BRIGHTNESS_MAX);
}
static int_fast32_t bb_uartRead(void *buf, size_t maxCount) {
    if (bb_uartHandle != NULL) { return UART_read(bb_uartHandle, buf, maxCount); }
    return 0;
}
static int_fast32_t bb_uartWrite(void *buf, size_t count) {
    if (bb_uartHandle != NULL) { return UART_write(bb_uartHandle, buf, count); }
    return 0;
}
