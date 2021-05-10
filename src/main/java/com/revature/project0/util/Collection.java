package com.revature.project0.util;

public interface Collection<T> {

    int size();
    boolean contains(T data);
    void add(T data);
    T remove(T data);

}
