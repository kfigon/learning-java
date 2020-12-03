package com.example.learningjava.basicthings;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

class BasicTest {

    @Test
    void foo() {
        int a = Integer.MAX_VALUE;
        a++;
        assertThat(a).isNegative();
        assertThat(a).isEqualTo(Integer.MIN_VALUE);
        System.out.println(a);
    }
}
