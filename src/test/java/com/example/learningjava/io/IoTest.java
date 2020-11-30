package com.example.learningjava.io;

import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.assertj.core.api.Assertions.assertThat;

public class IoTest {
    @Test
    void readWithScanner() throws IOException {
        String fileName = "target/test-classes/myFile.txt";
        List<String> lines = new ArrayList<>();
//        scanner closes source
        try(Scanner s = new Scanner(new FileInputStream(fileName))) {
            while(s.hasNextLine()) {
                lines.add(s.nextLine());
            }
        }
        assertThat(lines).containsExactly("hello", "new line", "foo");
    }
}
