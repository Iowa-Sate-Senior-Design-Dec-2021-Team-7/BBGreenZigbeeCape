EESchema Schematic File Version 4
EELAYER 30 0
EELAYER END
$Descr A4 11693 8268
encoding utf-8
Sheet 3 3
Title ""
Date ""
Rev ""
Comp ""
Comment1 ""
Comment2 ""
Comment3 ""
Comment4 ""
$EndDescr
$Comp
L BeagleBone:BeagleBone_P8 U?
U 1 1 609FD186
P 2900 4050
F 0 "U?" H 2900 6815 50  0000 C CNN
F 1 "BeagleBone_P8" H 2900 6724 50  0000 C CNN
F 2 "" H 3500 6800 50  0001 C CNN
F 3 "" H 3500 6800 50  0001 C CNN
	1    2900 4050
	1    0    0    -1  
$EndComp
$Comp
L BeagleBone:BeagleBone_P9 U?
U 1 1 609FDC70
P 6450 4050
F 0 "U?" H 6450 6815 50  0000 C CNN
F 1 "BeagleBone_P9" H 6450 6724 50  0000 C CNN
F 2 "" H 5950 6700 50  0001 C CNN
F 3 "" H 5950 6700 50  0001 C CNN
	1    6450 4050
	1    0    0    -1  
$EndComp
Text HLabel 5300 2250 0    50   Input ~ 0
BeagleBone_Reset
Text HLabel 4700 850  0    50   Output ~ 0
LAN_Heartbeat
Text HLabel 4700 950  0    50   Input ~ 0
Network_Connect
Text HLabel 5250 3650 0    50   Input ~ 0
UART_TX
Text HLabel 7500 3150 2    50   Input ~ 0
SPI_MISO
Text HLabel 7500 3250 2    50   Output ~ 0
SPI_MOSI
Text HLabel 7500 3350 2    50   Output ~ 0
SPI_CS
Text HLabel 7500 3050 2    50   Output ~ 0
SPI_CLK
Text HLabel 7500 3950 2    50   Output ~ 0
I2C2_SCL
Text HLabel 7500 3850 2    50   BiDi ~ 0
I2C2_SDA
Wire Wire Line
	5800 2250 5300 2250
Text HLabel 5250 3750 0    50   Output ~ 0
UART_RX
Wire Wire Line
	5800 3750 5250 3750
Wire Wire Line
	5250 3650 5800 3650
Wire Wire Line
	7100 3950 7500 3950
Wire Wire Line
	7500 3850 7100 3850
Wire Wire Line
	7500 3350 7100 3350
Wire Wire Line
	7100 3250 7500 3250
Wire Wire Line
	7500 3150 7100 3150
Wire Wire Line
	7500 3050 7100 3050
Wire Wire Line
	4700 950  7100 950 
Wire Wire Line
	7100 950  7100 1550
Wire Wire Line
	4700 850  7150 850 
Wire Wire Line
	7150 850  7150 1650
Wire Wire Line
	7150 1650 7100 1650
Text Notes 5100 800  0    50   ~ 0
Random GPIO pin chozen.
Text Notes 5100 1050 0    50   ~ 0
Random GPIO pin chozen.
Wire Wire Line
	2250 1650 1800 1650
Wire Wire Line
	2250 1550 1450 1550
Wire Wire Line
	1450 1550 1450 1650
Wire Wire Line
	5800 1550 5400 1550
Wire Wire Line
	5800 1650 5300 1650
Wire Wire Line
	5300 1550 5300 1650
Wire Wire Line
	5800 2400 5200 2400
Wire Wire Line
	5200 2400 5200 2500
Wire Wire Line
	5800 2500 5300 2500
Connection ~ 5200 2500
Wire Wire Line
	5200 2500 5050 2500
Wire Wire Line
	5800 2600 5350 2600
Wire Wire Line
	5300 2600 5300 2500
Connection ~ 5300 2500
Wire Wire Line
	5300 2500 5200 2500
Wire Wire Line
	5800 2700 5400 2700
Wire Wire Line
	5350 2700 5350 2600
Connection ~ 5350 2600
Wire Wire Line
	5350 2600 5300 2600
Wire Wire Line
	5800 2800 5450 2800
Wire Wire Line
	5400 2800 5400 2700
Connection ~ 5400 2700
Wire Wire Line
	5400 2700 5350 2700
Wire Wire Line
	5800 2900 5450 2900
Wire Wire Line
	5450 2900 5450 2800
Connection ~ 5450 2800
Wire Wire Line
	5450 2800 5400 2800
$Comp
L power:GND #PWR?
U 1 1 608F7A9D
P 1800 1650
F 0 "#PWR?" H 1800 1400 50  0001 C CNN
F 1 "GND" H 1805 1477 50  0000 C CNN
F 2 "" H 1800 1650 50  0001 C CNN
F 3 "" H 1800 1650 50  0001 C CNN
	1    1800 1650
	1    0    0    -1  
$EndComp
Connection ~ 1800 1650
Wire Wire Line
	1800 1650 1450 1650
$Comp
L power:GND #PWR?
U 1 1 608F7F64
P 5050 2500
F 0 "#PWR?" H 5050 2250 50  0001 C CNN
F 1 "GND" H 5055 2327 50  0000 C CNN
F 2 "" H 5050 2500 50  0001 C CNN
F 3 "" H 5050 2500 50  0001 C CNN
	1    5050 2500
	1    0    0    -1  
$EndComp
$Comp
L power:+3.3V #PWR?
U 1 1 608FAB4C
P 5400 1550
F 0 "#PWR?" H 5400 1400 50  0001 C CNN
F 1 "+3.3V" H 5415 1723 50  0000 C CNN
F 2 "" H 5400 1550 50  0001 C CNN
F 3 "" H 5400 1550 50  0001 C CNN
	1    5400 1550
	1    0    0    -1  
$EndComp
Connection ~ 5400 1550
Wire Wire Line
	5400 1550 5300 1550
$EndSCHEMATC
