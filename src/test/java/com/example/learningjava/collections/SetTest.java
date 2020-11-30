package com.example.learningjava.collections;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
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

    @Test
    void test2() {
        Set<String> set = new HashSet<>();
        set.add("1");
        set.add("2");
        set.add("3");

        Set<String> set2 = new HashSet<>();
        set2.add("1");

        set.retainAll(set2);

        assertThat(set).containsExactly("1");
    }

    @Test
    void testDistincts() {
        List<String> first = Arrays.asList("1", "2", "3", "4");
        List<String> second = Arrays.asList("3", "5", "1", "1", "2", "4");

        Set<String> distincts = new HashSet<>(first);
        distincts.addAll(second);

        assertThat(distincts).hasSize(5).containsExactly("1","2","3","4","5");
    }
}
