package org.example;

public interface MyArrayList<T> {

    public Object add(T element);

    public Object add(int index, T element);

    T get(int index);

    Object remove(int index);

    void clear();
}
