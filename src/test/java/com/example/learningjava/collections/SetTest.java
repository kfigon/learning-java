package com.example.learningjava.collections;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class SetTest {
    // no order, no duplicates
    // no indexing, retrieving concrete element - only iterate

    @Test
    void test1() {
        Set<String> set = new HashSet<>(); // best performance, using hashmap underlying (just using keys, without values)
        set.add("1");
        set.add("2");
        set.add("2");
        set.add("2");
        set.add("3");

        assertThat(set)
                .hasSize(3)
                .containsExactly("1","2","3");
    }
}
