# Senior Design -- BeagleBone Green Gateway IoT Hub

---

## Website
https://sddec21-07.sd.ece.iastate.edu

## Hackster.io page
https://www.hackster.io/iowa-state-senior-design-dec-2021-proj7/BeagleBone-green-wireless-zigbee-cape-c2da61

## Project email
sddec21-07@iastate.edu

## Devs:
	- Parker Larsen (pjlarsen)
	- Taylor Weil (tcweil)
	- Sean Griffen (griffens)
	- Sterling Hulling (shulling)

---

See CHANGELOG.md for official release updates/changes

---

### Scope:
	Research a new or existing wireless technology and add it to the BeagleBone Green and build an IoT network to demonstrate its new capabilities.

### Stretch scope:
	1. Create a system of printed circuit boards to connect to the Zigbee network to show the capabilities and use cases of the Zigbee network.
	2. Program and debug the CC1352 microcontroller Using UART.
	3. Create a data-collection demo to demonstrate basic interactions between the Cape's Zigbee network and the BeagleBone's capabilities
	3. Extend the demo to demonstrate the BeagleBone controlling/commanding Zigbee network devices
	4. Extend the demo to demonstrate devices on the Zigbee network controlling/commanding other Zigbee network devices

---

### Requirements:
	1. Create printed circuit board to allow the BeagleBone to connect to a Zigbee network.
	2. Establish communication between the Cape and the BeagleBone using UART
	3. Create a data-collection demo to demonstrate basic interactions between the Cape's Zigbee network and the BeagleBone's capabilities


### Stretch Requirements:
	1. Create a system of printed circuit boards to connect to the Zigbee network to show the capabilities and use cases of the Zigbee network.
	2. Program and the CC1352 microcontroller using UART.
	3. Extend the communication between the Cape and the BeagleBone to SPI and I2C
	4. Create a demo to show the uses of the BeagleBone's new capabilities
		1. Show that data can be collected from devices on the Cape's Zigbee network
		2. Show that the BeagleBone can see devices on the Cape's Zigbee network and what each device does
		3. Show that the BeagleBone can send commands to devices on the Cape's Zigbee network
		4. Show that devices on the Cape's Zigbee network can send commands to other devices on the same network
	5. Build in support for IFTT using the BeagleBone as an interface for devices on the Cape's Zigbee network
	6. Build in support for MQTT using the BeagleBone as an interface for devices on the Cape's Zigbee network
	7. Build in support for home automation software (HomeAssistant and/or OpenHAB) using the BeagleBone as an interface for devices on the Cape's Zigbee network

---

### Directory Roles:
	- hardware: for pcb designs created and other hardware-specific resources
	- firmware: for code being deployed on the BeagleBone Green Gateway, IoT cape, or other created daughter boards
	- software: for code not being deployed on a hardware module. i.e. web-servers, apps, and other non-controller code
	- docs: for project documents and classwork

---
