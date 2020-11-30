package com.example.learningjava.io;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.jupiter.api.Test;

import java.io.*;

import static org.assertj.core.api.Assertions.assertThat;

class ObjectSerializationTest {

    @Data
    @AllArgsConstructor
    private static class Foo implements Serializable {
//        if mismatched uid - exception
        private static final long serialVersionUID = 1L; // good practice for deserialization
        private int id;
        private String name;
    }

    @Test
    void write() throws IOException {
        Foo foo = new Foo(1, "asd");
        try(ObjectOutputStream o = new ObjectOutputStream(new FileOutputStream("target/test-classes/myObj.txt"))) {
            o.writeObject(foo);
        }
    }

    @Test
    void read() throws IOException, ClassNotFoundException {
        try(ObjectInputStream o = new ObjectInputStream(new FileInputStream("target/test-classes/myObj.txt"))) {
            Foo foo = (Foo)o.readObject();
            assertThat(foo.getId()).isEqualTo(1);
            assertThat(foo.getName()).isEqualTo("asd");
        }
    }

}
