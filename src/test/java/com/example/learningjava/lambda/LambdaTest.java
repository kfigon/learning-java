package com.example.learningjava.lambda;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;

import static org.assertj.core.api.Assertions.assertThat;

class LambdaTest {
//    lambda in java - interface with one method
//    closured variables in lambda - final
    @Test
    void foo() {
        List<String> strings = Arrays.asList("one", "two", "three");
        Collections.sort(strings, (a,b) -> a.compareTo(b));
//        or with types
//        Collections.sort(strings, (String a, String b) -> a.compareTo(b));
    }

    @Test
    void consumer() {
//        takes 1 argument, return void
        Consumer<String> fun = System.out::println;
        fun.accept("hello!");
    }

    @Test
    void consumerAndThen() {
        Consumer<String> fun = System.out::println;
        Consumer<String> fun2 = (x) -> System.out.println("aaaasd"+x);
        Consumer<String> fun3 = (x) -> System.out.println("damn, third"+x);

//  hello
// aaaaasdhello!
// damn, thirdhello!
        fun.andThen(fun2.andThen(fun3)).accept("hello!");
    }

    @Test
    void predicate() {
        Predicate<Integer> moreThan5 = x -> x > 5;
        Predicate<Integer> lessThan10 = x -> x < 10;

        Predicate<Integer> combined = moreThan5.and(lessThan10);
        assertThat(combined.test(6)).isTrue();
        assertThat(combined.test(9)).isTrue();

        assertThat(combined.test(10)).isFalse();
        assertThat(combined.test(11)).isFalse();
        assertThat(combined.test(4)).isFalse();
        assertThat(combined.test(5)).isFalse();
    }

    @Test
    void supplier() {
//        return value, no arguments
        Supplier<Integer> fun = () -> 5;
        assertThat(fun.get()).isEqualTo(5);
    }
}
