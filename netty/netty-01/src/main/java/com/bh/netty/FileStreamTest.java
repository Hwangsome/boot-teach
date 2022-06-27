package com.bh.netty;

import java.io.BufferedInputStream;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

public class FileStreamTest {

    private static final String FileName = "file.txt";

    public static void main(String[] args) throws IOException {
        OutputStream output = new FileOutputStream("readme.txt");
        output.write(72); // H
        output.write(101); // e
        output.write(108); // l
        output.write(108); // l
        output.write(111); // o
        output.close();
    }
}