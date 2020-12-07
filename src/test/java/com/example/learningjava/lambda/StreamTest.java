package com.example.learningjava.lambda;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

class StreamTest {
    @Data
    @AllArgsConstructor
    private static class Foo {
        private String name;
    }

    @Test
    void basicStream() {
        List<String> words = Arrays.asList("first,second,third,h,x,a,hello,asd".split(","));

        words.stream()
                .filter(x -> x.length() > 3)
                .map(Foo::new)
                .forEach(System.out::println);
    }
}
