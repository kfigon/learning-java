package com.example.learningjava.generics;

import org.assertj.core.internal.IterableElementComparisonStrategy;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class GenericsTest {

    interface Foo {
        void asd();
    }

    class Asd1 implements Foo {
        @Override
        public void asd() {
            System.out.println("do thing");
        }
    }

    class Bar implements Foo {
        @Override
        public void asd() {
            System.out.println("other thing");
        }
    }
    class BarChild extends Bar {

    }

    private <T extends Foo> void doFoo(T x) {
        x.asd();
    }

    // accepts all subclasses
    private void doSomethingWithUpperBound(List<? extends Number> anyList) {
        anyList.forEach(System.out::print);
    }

    // accepts all superclasses. Not usable in signature, only in generic types:
//    private <T super Integer> void doSomethingWithLowerBound(T anyList) - fail!
    private void doSomethingWithLowerBound(List<? super Integer> anyList) {
        anyList.forEach(System.out::print);
    }

    private void doSomething(List<?> anyList) {
        anyList.forEach(System.out::print);
    }

    @Test
    void testGenerics() {
        doFoo(new Asd1());
        doFoo(new Bar());
    }

    @Test
    void wildcard() {
        doSomething(Arrays.asList("1","2","3","54"));
    }

    @Test
    void bounded() {
        System.out.println("Upper");
        doSomethingWithUpperBound(Arrays.asList(123, 123.5, new BigDecimal(123)));

        System.out.println("lower");
//      List<Float> anyList = List.of(123.4, 432.3);
//        doSomethingWithLowerBound(anyList);
        List<Integer> anyList = List.of(123, 432);
        doSomethingWithLowerBound(anyList);
    }
}
