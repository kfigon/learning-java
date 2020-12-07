package com.example.learningjava.lambda;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.*;

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
        IntSupplier fun2 = () -> 10;

        assertThat(fun.get()).isEqualTo(5);
        assertThat(fun2.getAsInt()).isEqualTo(10);
    }

    @Test
    void unary() {
//      1 arg, same type on return
        UnaryOperator<Integer> inc = i -> i+1;
        assertThat(inc.apply(1)).isEqualTo(2);
    }

    @Test
    void functions() {
//        map one thing to another
        Function<String, Integer> mapper = (x) -> x.length() + 10;
        assertThat(mapper.apply("x")).isEqualTo(11);
    }
    @Data
    @AllArgsConstructor
    private static class Foo{
        private Integer id;
        private String name;
    }

    @Test
    void composeFunctions() {
        Function<Foo, String> getName = Foo::getName;
        Function<String, Integer> getLen = String::length;
        Function<Integer, String> getString = Object::toString;

        assertThat(
                getName
                .andThen(getLen)
                .andThen(getString)
                .apply(new Foo(1, "Mike"))
        ).isEqualTo("4");

//        getLen(getName(obj))
        Integer foobar = getLen.compose(getName).apply(new Foo(1, "Foobar"));
        assertThat(foobar).isEqualTo(6);
    }

}
