package com.example.learningjava.lambda;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class StreamTest {
    @Data
    @AllArgsConstructor
    private static class Foo {
        private String name;
    }

//    intermediate methods - limit, map, filter
//    terminate - actual run the stream, foreach, collect etc.

    @Test
    void basicStream() {
        List<String> words = Arrays.asList("first,second,third,h,x,a,hello,asd".split(","));

        words.stream()
                .filter(x -> x.length() > 3)
                .map(Foo::new)
                .forEach(System.out::println);
    }

//    Stream.concat!!!
//    distinct - uzywa equals do sprawdzenia czy to samo

    @AllArgsConstructor
    @Data
    private static class Bar{
        private List<String> vals;
    }

    @Test
    void flatMap() {
//        map single object to multiple objects

        Stream.of(getBars())
                // .map(Bar::getVals) will return to List<String>
                .flatMap(x -> x.getVals().stream())
                .forEach(System.out::println);
    }

    private Bar[] getBars() {
        return new Bar[]{new Bar(Arrays.asList("one", "two", "three")),
                new Bar(Collections.singletonList("four")),
                new Bar(Arrays.asList("five", "six"))};
    }

    @Test
    void group() {
        Map<Integer, List<Bar>> collect = Arrays.stream(getBars())
                .collect(Collectors.groupingBy(bar -> bar.vals.size()));

        Bar[] bars = getBars();
        assertThat(collect.get(3)).containsExactly(bars[0]); // with list(one, two, three)
        assertThat(collect.get(1)).containsExactly(bars[1]); // with list(four)
        assertThat(collect.get(2)).containsExactly(bars[2]); // with list(five, six)
    }
}
