package ru.shefer.converter;

public interface Converter<T, S> {
    S convert(T t);
}
