package com.example.learningjava.contract;

import org.junit.jupiter.api.Test;

import java.util.Objects;

import static org.assertj.core.api.Fail.fail;

class EqualHashTest {

//    equal
//    default - referential equality

//    reflexive - referential equality - must be equal to itself
//    symmetric - x.equals(y) == y.equals(x)
//    transitive - x.equals(y) and y.equals(z) then x.equals(z)
//    consistent - no randomness

//    common violation - by inheritance - Money extends Voucher
//    and voucher checks different things. Voucher then should favour composition over inheritance


//    hash
//  internal consistency - hashCode may only change if a property of equal changes
//  equal objects must have same hash
//  unequal MAY have same hash - buckets

    private static class Foo {
        private int id;
        private int name;

        public Foo(int id, int name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public int getName() {
            return name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Foo foo = (Foo) o;
            return id == foo.id &&
                    name == foo.name;
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, name);
        }
    }
}
