package com.aw;

import java.util.EmptyStackException;
import java.util.Enumeration;


public interface StackCache<T> {

    void push(T element);

    T pop() throws EmptyStackException;

    boolean empty();

    void clear();

    Enumeration<T> elements();

}
