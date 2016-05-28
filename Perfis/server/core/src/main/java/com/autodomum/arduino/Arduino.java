package com.autodomum.arduino;


import com.fazecast.jSerialComm.SerialPort;

import java.io.PrintWriter;

/**
 * @author sabrina on 27/05/16.
 */
public class Arduino {

    private SerialPort chosenPort;
    private boolean isOpen = false;

    public void connect(String portDescriptor) {
        chosenPort = SerialPort.getCommPort(portDescriptor);
        chosenPort.setComPortTimeouts(SerialPort.TIMEOUT_SCANNER, 0, 0);
        if (chosenPort.openPort()) {
            // wait after connecting, so the bootloader can finish
            try {
                Thread.sleep(100);
            } catch (Exception e) {
            }

            isOpen = true;
        }
    }

    public void sendData(String message) {
        if (isOpen) {
            PrintWriter output = new PrintWriter(chosenPort.getOutputStream());
            while (true) {
                output.print(message);
                output.flush();
                try {
                    Thread.sleep(100);
                } catch (Exception e) {
                }
            }
        } else {
            throw new IllegalStateException("Uma conexao deveria ser estabelecida");
        }
    }

    public void disconnect() {
        // disconnect from the serial port
        chosenPort.closePort();
        isOpen = false;
    }

}
