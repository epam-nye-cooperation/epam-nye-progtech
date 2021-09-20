package hu.nye.progtech.javarecap.generics;

import java.util.Objects;

public class Box<T> {

    private T value;

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Box<?> box = (Box<?>) o;
        return Objects.equals(value, box.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
