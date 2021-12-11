#!/bin/bash

# sets up the header pins used for serial communication between the cape and the
#	BeagleBone
# 	For pin configurations, see 'https://wiki.seeedstudio.com/BeagleBone-Green-Gateway/'

config-pin P9.11 uart 		# UART4_Rx
config-pin P9.13 uart 		# UART4_Tx

config-pin P9.19 i2c 		# I2C2_SCL
config-pin P9.20 i2c 		# I2C2_SDA

config-pin P9.17 spi_cs		# SPI0_CS
config-pin P9.18 spi		# SPI0_D1
config-pin P9.21 spi 		# SPI0_D0
config-pin p9.22 spi_sclk	# SPI0_SCLK
