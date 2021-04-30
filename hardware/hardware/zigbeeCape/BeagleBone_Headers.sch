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
L BeagleBone:BeagleBone_P8 U4
U 1 1 609FD186
P 2900 4050
F 0 "U4" H 2900 6815 50  0000 C CNN
F 1 "BeagleBone_P8" H 2900 6724 50  0000 C CNN
F 2 "" H 3500 6800 50  0001 C CNN
F 3 "" H 3500 6800 50  0001 C CNN
	1    2900 4050
	1    0    0    -1  
$EndComp
$Comp
L BeagleBone:BeagleBone_P9 U5
U 1 1 609FDC70
P 6450 4050
F 0 "U5" H 6450 6815 50  0000 C CNN
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
Text Notes 5100 800  0    50   ~ 0
Random GPIO pin chozen.
Text Notes 5100 1050 0    50   ~ 0
Random GPIO pin chozen.
Wire Wire Line
	2250 1550 1450 1550
Wire Wire Line
	1450 1550 1450 1650
Wire Wire Line
	5800 1650 5300 1650
Wire Wire Line
	5300 1550 5300 1650
Wire Wire Line
	5800 2400 5200 2400
Wire Wire Line
	5200 2400 5200 2500
Connection ~ 5200 2500
$Comp
L power:GND #PWR046
U 1 1 608F7A9D
P 1450 1800
F 0 "#PWR046" H 1450 1550 50  0001 C CNN
F 1 "GND" H 1455 1627 50  0000 C CNN
F 2 "" H 1450 1800 50  0001 C CNN
F 3 "" H 1450 1800 50  0001 C CNN
	1    1450 1800
	1    0    0    -1  
$EndComp
$Comp
L power:GND #PWR047
U 1 1 608F7F64
P 4800 2550
F 0 "#PWR047" H 4800 2300 50  0001 C CNN
F 1 "GND" H 4805 2377 50  0000 C CNN
F 2 "" H 4800 2550 50  0001 C CNN
F 3 "" H 4800 2550 50  0001 C CNN
	1    4800 2550
	1    0    0    -1  
$EndComp
$Comp
L power:+3.3V #PWR048
U 1 1 608FAB4C
P 5300 1500
F 0 "#PWR048" H 5300 1350 50  0001 C CNN
F 1 "+3.3V" H 5315 1673 50  0000 C CNN
F 2 "" H 5300 1500 50  0001 C CNN
F 3 "" H 5300 1500 50  0001 C CNN
	1    5300 1500
	1    0    0    -1  
$EndComp
NoConn ~ 5800 3500
NoConn ~ 5800 3400
NoConn ~ 5800 3300
NoConn ~ 5800 3150
NoConn ~ 5800 3050
NoConn ~ 5800 3950
NoConn ~ 5800 3850
NoConn ~ 7100 3700
NoConn ~ 7100 3600
NoConn ~ 7100 3500
NoConn ~ 7100 2900
NoConn ~ 7100 2800
NoConn ~ 7100 2700
NoConn ~ 7100 2600
NoConn ~ 7100 2500
NoConn ~ 7100 2400
NoConn ~ 7100 1750
NoConn ~ 7100 1850
NoConn ~ 7100 1950
NoConn ~ 7100 2050
NoConn ~ 7100 2150
NoConn ~ 7100 2300
NoConn ~ 5800 2150
NoConn ~ 5800 2050
NoConn ~ 5800 1950
NoConn ~ 5800 1850
NoConn ~ 5800 1750
NoConn ~ 2250 3950
NoConn ~ 2250 3850
NoConn ~ 2250 3750
NoConn ~ 2250 3650
NoConn ~ 2250 3550
NoConn ~ 2250 3450
NoConn ~ 2250 3350
NoConn ~ 2250 3250
NoConn ~ 2250 3150
NoConn ~ 2250 3050
NoConn ~ 2250 2950
NoConn ~ 2250 2850
NoConn ~ 2250 2750
NoConn ~ 2250 2650
NoConn ~ 2250 2550
NoConn ~ 2250 2450
NoConn ~ 2250 2350
NoConn ~ 2250 2250
NoConn ~ 2250 2150
NoConn ~ 3550 2800
NoConn ~ 3550 2900
NoConn ~ 3550 3000
NoConn ~ 3550 3100
NoConn ~ 3550 3200
NoConn ~ 3550 3300
NoConn ~ 3550 3400
NoConn ~ 3550 3500
NoConn ~ 3550 3600
NoConn ~ 3550 3700
NoConn ~ 3550 3800
NoConn ~ 3550 3900
NoConn ~ 3550 2450
NoConn ~ 3550 2350
NoConn ~ 3550 2250
NoConn ~ 3550 2150
NoConn ~ 3550 2050
NoConn ~ 3550 1550
NoConn ~ 3550 1650
NoConn ~ 3550 1750
NoConn ~ 3550 1850
NoConn ~ 3550 1950
NoConn ~ 2250 2050
NoConn ~ 2250 1900
NoConn ~ 2250 1800
Text Notes 850  7300 0    295  ~ 59
BeagleBone Headers
Wire Wire Line
	5300 1550 5800 1550
Wire Wire Line
	5300 1500 5300 1550
Connection ~ 5300 1550
Wire Wire Line
	5200 2500 5800 2500
Wire Wire Line
	5800 2900 5200 2900
Wire Wire Line
	5200 2900 5200 2800
Wire Wire Line
	5800 2800 5200 2800
Connection ~ 5200 2800
Wire Wire Line
	5200 2800 5200 2700
Wire Wire Line
	5800 2700 5200 2700
Connection ~ 5200 2700
Wire Wire Line
	5200 2700 5200 2600
Wire Wire Line
	5800 2600 5200 2600
Connection ~ 5200 2600
Wire Wire Line
	5200 2600 5200 2500
Wire Wire Line
	5200 2400 4800 2400
Wire Wire Line
	4800 2400 4800 2550
Connection ~ 5200 2400
Wire Wire Line
	7350 1650 7350 850 
Wire Wire Line
	4700 850  7350 850 
Wire Wire Line
	7100 1650 7350 1650
Wire Wire Line
	7100 1550 7250 1550
Wire Wire Line
	7250 1550 7250 950 
Wire Wire Line
	4700 950  7250 950 
Wire Wire Line
	1450 1650 1450 1800
Wire Wire Line
	1450 1650 2250 1650
Connection ~ 1450 1650
$EndSCHEMATC
