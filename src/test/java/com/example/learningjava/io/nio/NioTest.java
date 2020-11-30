package com.example.learningjava.io.nio;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;

//java1.4
// nieblokujace - asynchroniczne IO!
// watek sie nie blokuje

//ale tez new IO - w javie 1.7 rozszerzono mocno nio
class NioTest {

//    nio nie uzywa bajtow, ale chunkow
//    buffers, channels, selectors

//    praca z plikami - nio.FileSystems i path - kopie itd.
    @Test
    void path() throws IOException {
//        new File - nie tworzy pliku, to placeholder w pamieci
//        nio.Path troche lepszy niz io.File
        Path p = FileSystems.getDefault().getPath("target/test-classes/myFile.txt");
        try(Scanner s = new Scanner(Files.newBufferedReader(p))) {
            while(s.hasNextLine()) {
                System.out.println(s.nextLine());
            }
        }
    }

    @Test
    void readAll() throws IOException {
        Path p = FileSystems.getDefault().getPath("target/test-classes/myFile.txt");
        List<String> lines = Files.readAllLines(p);
        System.out.println(lines);
    }
}
