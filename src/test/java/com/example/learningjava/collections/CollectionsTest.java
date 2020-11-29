package com.example.learningjava.collections;

import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class CollectionsTest {
// set, list, queue/dequeue
// maps

//    usually when using things other than lists
//    we'll need to provide comparable interface and overload equals and hash

    @AllArgsConstructor
    class Foo implements Comparable<Foo> {
        int id;
        @Override
        public int compareTo(Foo o) {
            if (this.id > o.id) {
                return 1;
            } else if (this.id < o.id) {
                return -1;
            }
            return 0;
        }

        @Override
        public String toString() {
            return "Foo{" + id + "} ";
        }
    }

    @Test
    void test1() {
        List<Foo> list = Arrays.asList(new Foo(3), new Foo(5), new Foo(123), new Foo(-1));
        list.forEach(System.out::print);
        Collections.sort(list);
        System.out.println();
        list.forEach(System.out::print);
    }

    @Test
    void test2() {
        List<String> foo = Arrays.asList("1234", "432", "123231", "1");
        foo.sort((o1, o2) -> { // comparator interface
            if (o1.length() > o2.length()) return 1;
            else if (o1.length() < o2.length()) return -1;
            return 0;
        });
        System.out.println(foo);
    }

}
