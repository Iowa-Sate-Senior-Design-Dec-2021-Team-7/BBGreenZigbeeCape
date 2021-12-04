# invoke SourceDir generated makefile for app.pem4f
app.pem4f: .libraries,app.pem4f
.libraries,app.pem4f: package/cfg/app_pem4f.xdl
	$(MAKE) -f /Users/taylorweil/sddec21-07/firmware/SensorTagProject/src/makefile.libs

clean::
	$(MAKE) -f /Users/taylorweil/sddec21-07/firmware/SensorTagProject/src/makefile.libs clean

