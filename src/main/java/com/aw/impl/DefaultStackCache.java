package com.aw.impl;

import java.util.EmptyStackException;
import java.util.Enumeration;
import java.util.Stack;

import com.aw.StackCache;


public class DefaultStackCache<T> implements StackCache<T> {

    private Stack<T> stack = new Stack<>();

    @Override
    public void push(T element) {
        stack.push(element);
    }

    @Override
    public T pop() throws EmptyStackException {
        return stack.pop();
    }

    @Override
    public boolean empty() {
        return stack.empty();
    }

    @Override
    public void clear() {
        stack.clear();
    }

    @Override
    public Enumeration<T> elements() {
        return stack.elements();
    }
}
