# I2CTest
I have created this tool to scan all addresses on and i2c port, similar to i2cdetect you can run on raspberry pi.

## Info
It seems like there is no simple way to scan through i2c addresses on device PICO-PI-IMX7. This program allows you to do it, simply run the program and observe Logcat.

Program is set to scan I2C1 port, change this to scan different port.

Example output: (device on address 0x76)
```
        0  1  2  3  4  5  6  7  8  9  A  B  C  D  E  F
    00:          -- -- -- -- -- -- -- -- -- -- -- -- -- 
    10: -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- 
    20: -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- 
    30: -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- 
    40: -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- 
    50: -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- 
    60: -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- 
    70: -- -- -- -- -- -- 76 -- 
```
