package com.example.learningjava.practice;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

class FooTest {
    private String[] uniqueNames(String[] names1, String[] names2) {
        Set<String> names = new HashSet<>(Arrays.asList(names1));
        names.addAll(Arrays.asList(names2));
        return names.toArray(new String[]{});
    }
    @Test
    void test1() {
        String[] names1 = new String[] {"Ava", "Emma", "Olivia"};
        String[] names2 = new String[] {"Olivia", "Sophia", "Emma"};
        String[] strings = uniqueNames(names1, names2);
        for(String i : strings)
            System.out.println(i);
    }


    public static class TextInput {
        private StringBuilder value = new StringBuilder();
        public void add(char c) {
            value.append(c);
        }

        public String getValue() {
            return value.toString();
        }
    }

    public static class NumericInput extends TextInput {
        @Override
        public void add(char c) {
            if (Character.isDigit(c)) {
                super.add(c);
            }
        }
    }

    @Test
    void test2() {
        TextInput input = new NumericInput();
        input.add('1');
        input.add('a');
        input.add('0');
        System.out.println(input.getValue());
    }
}
