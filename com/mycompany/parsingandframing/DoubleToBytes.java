/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.parsingandframing;

/**
 * Converting a double to an eight-byte array and back to a double.
 *
 * @author Alex Simonelis
 * @version October, 2011
 * @author cdavis
 * Modified by Carlton Davis, November 2020
 */

/**
 *
 * @author cdavis
 * Modified by Carlton Davis, November 2020
 */

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Scanner;

public class DoubleToBytes {

    public static void main(String[] args) throws IOException {
        DoubleToBytes myObject = new DoubleToBytes();
        double d = 123456789999.234243D; //myObject.getDouble();

        System.out.println("Input double: " + d);
        byte[] myByteArray = myObject.doubleToByteArray(d);
        myByteArray = doubleToByteArrayNew(d);
        double outputValue = myObject.byteArrayToDouble(myByteArray);
        System.out.println("Output value: " + outputValue);
    }

    public double getDouble() {
        Scanner myScanner = new Scanner(System.in);
        System.out.println("Enter a double : ");
        String input = myScanner.nextLine();  // Read user input
        Scanner newScanner = new Scanner(input);
        int flag = 0;
        while (flag == 0) {
            if (newScanner.hasNextDouble()) {
                flag = 1;
            } else {
                System.out.println(input + " is not a double. Enter a double : ");
                input = myScanner.nextLine();
                newScanner = new Scanner(input);
                if (newScanner.hasNextDouble()) {
                    flag = 1;
                }
            }
        }
        return Double.parseDouble(input);
    }

    public static byte[] doubleToByteArrayNew(double numDouble) throws IOException {
        byte[] arrayByte = new byte[8];
        ByteBuffer bb = ByteBuffer.allocate(8);
        bb.putDouble(numDouble);
        arrayByte = bb.array();

        System.out.println("doubleToByteArrayNew: ".concat(Arrays.toString(arrayByte)));

        return arrayByte;
    }
    public byte[] doubleToByteArray(double numDouble) {
        byte[] arrayByte = new byte[8];
        long numLong;

        numLong = Double.doubleToRawLongBits(numDouble);
        System.out.println(numLong);
        arrayByte[0] = (byte) (numLong >>> 56);
        arrayByte[1] = (byte) (numLong >>> 48);
        arrayByte[2] = (byte) (numLong >>> 40);
        arrayByte[3] = (byte) (numLong >>> 32);
        arrayByte[4] = (byte) (numLong >>> 24);
        arrayByte[5] = (byte) (numLong >>> 16);
        arrayByte[6] = (byte) (numLong >>> 8);
        arrayByte[7] = (byte) numLong;

        System.out.println("doubleToByteArrayOld: ".concat(Arrays.toString(arrayByte)));

        return arrayByte;
    }

    public double byteArrayToDouble(byte[] arrayByte) {
        double numDouble;
        long numLong;

        numLong = (((long) arrayByte[0] & 0x00000000000000FFL) << 56) | (((long) arrayByte[1] & 0x00000000000000FFL) << 48) |
                (((long) arrayByte[2] & 0x00000000000000FFL) << 40) | (((long) arrayByte[3] & 0x00000000000000FFL) << 32) |
                (((long) arrayByte[4] & 0x00000000000000FFL) << 24) | (((long) arrayByte[5] & 0x00000000000000FFL) << 16) |
                (((long) arrayByte[6] & 0x00000000000000FFL) << 8) | ((long) arrayByte[7] & 0x00000000000000FFL);

        numDouble = Double.longBitsToDouble(numLong);

        return numDouble;
    }

}
