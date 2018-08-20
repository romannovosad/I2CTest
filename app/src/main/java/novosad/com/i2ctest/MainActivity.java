package novosad.com.i2ctest;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.things.pio.I2cDevice;
import com.google.android.things.pio.PeripheralManager;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

/**
 * i2cdetect for PICO-PI-IMX7
 */
public class MainActivity extends Activity {
    private static final String TAG = MainActivity.class.getSimpleName();

    // I2C Device Name
    private static final String I2C_DEVICE_NAME = "I2C1";

    private I2cDevice mDevice;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        PeripheralManager manager = PeripheralManager.getInstance();

        List<String> deviceList = manager.getI2cBusList();
        if (deviceList.isEmpty()) {
            Log.i(TAG, "No I2C bus available on this device.");
        } else {
            Log.i(TAG, "List of available devices: " + deviceList);
        }

        StringBuilder reading = new StringBuilder();
        reading.append(I2C_DEVICE_NAME);
        reading.append(" address sccan:\n    0  1  2  3  4  5  6  7  8  9  A  B  C  D  E  F\n00:          ");

        // Scanning I2C Slave Addresses
        for (int i2cAddress = 3; i2cAddress < 120; i2cAddress++) {
            if ((i2cAddress % 16) == 0)
                reading.append(String.format(Locale.US, "\n%d: ", (i2cAddress / 16 * 10)));
            // Attempt to access the I2C device
            try {
                mDevice = manager.openI2cDevice(I2C_DEVICE_NAME, i2cAddress);
                mDevice.readRegByte(0x0);
                reading.append(String.format(Locale.US, "%02X ", i2cAddress));
            } catch (IOException e) {
                reading.append("-- ");
            }
        }
        Log.d(TAG, reading.toString());
        finish();
        System.exit(0);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (mDevice != null) {
            try {
                mDevice.close();
                mDevice = null;
                Log.d(TAG, "Device closed");
            } catch (IOException e) {
                Log.w(TAG, "Unable to close I2C device", e);
            }
        }
    }

}
