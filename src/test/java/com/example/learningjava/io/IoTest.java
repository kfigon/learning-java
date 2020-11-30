package com.example.learningjava.io;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.assertj.core.api.Assertions.assertThat;

class IoTest {
    @Test
    void readWithScanner() throws IOException {
        String fileName = "target/test-classes/myFile.txt";
        List<String> lines = new ArrayList<>();
//        scanner closes source
//        we can specify delimiter in scanner
//        fileinputstream - raw data
        try(Scanner s = new Scanner(new FileInputStream(fileName))) {
            while(s.hasNextLine()) {
                lines.add(s.nextLine());
            }
        }
        assertThat(lines).containsExactly("hello", "new line", "foo");
    }

    @Test
    void bufferedReader() throws IOException {
        String fileName = "target/test-classes/myFile.txt";
        List<String> lines = new ArrayList<>();

//        buffers using some predefined buffer instead of char by char
//        faster, but more memory needed
        //        filereader - character data
        try(Scanner s = new Scanner(new BufferedReader(new FileReader(fileName)))) {
            while(s.hasNextLine()) {
                lines.add(s.nextLine());
            }
        }
        assertThat(lines).containsExactly("hello", "new line", "foo");
    }
//    DataOutputStream do pisania danych w roznej postaci, kodowanie etc
//    RandomAccessFile - nie tylko strumieniowo, czytanie bajtowo jak z tablicy
}
