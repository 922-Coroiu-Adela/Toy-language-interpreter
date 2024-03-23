package gui.a7.Model.Structures;

import java.util.Deque;
import gui.a7.Model.Exceptions.StackException;


public interface iStack<T> extends iClearable
{
    T pop();
    void push(T value);
    boolean isEmpty();
    T peek() throws StackException;
    Deque<T> getStack();

    String toString();
}
