package com.example.learningjava.collections;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

class MapTest {

    @Test
    void test1() {
        Map<String, String> mapa = new HashMap<>();
        mapa.put("1", "asd");
        mapa.put("1", "sad"); // will replace
        assertThat(mapa.get("1")).isEqualTo("sad");

        assertThat(mapa.get("2")).isNull();
        assertThat(mapa.containsKey("2")).isFalse();
    }
}
