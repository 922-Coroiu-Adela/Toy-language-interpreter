package gui.a7.Model.Structures;

import gui.a7.Model.Exceptions.StackException;
import gui.a7.Model.Structures.iStack;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;

public class adtStack<T> implements iStack<T>
{
    private final Deque<T> stack;

    public adtStack()
    {
        stack = new LinkedList<>();
    }

    public adtStack(Deque<T> stack)
    {
        this.stack = stack;
    }

    @Override
    public T pop()
    {
        return stack.pop();
    }

    @Override
    public void push(T value)
    {
        stack.push(value);
    }

    @Override
    public boolean isEmpty()
    {
        return stack.isEmpty();
    }

    @Override
    public T peek() throws StackException
    {
        if (stack.isEmpty())
            throw new StackException("Stack is empty!");
        return stack.peek();
    }

    /*
    @Override
    public Iterator<T> iterator()
    {
        return stack.iterator();
    }*/

    @Override
    public Deque<T> getStack()
    {
        return stack;
    }

    @Override
    public String toString()
    {
        StringBuilder string = new StringBuilder();
        for (T element : stack)
        {
            string.append(element.toString());
            string.append("\n");
        }
        return string.toString();
    }

    @Override
    public void clear()
    {
        stack.clear();
    }

}
