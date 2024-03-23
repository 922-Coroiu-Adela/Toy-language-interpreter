package gui.a7.Model.Structures;

import gui.a7.Model.Exceptions.ListException;
import java.util.List;
import gui.a7.Model.Structures.iList;
import java.util.LinkedList;


public class adtList<T> implements iList<T>
{
    public List<T> list;

    public adtList(List<T> list)
    {
        this.list = list;
    }

    public adtList()
    {
        this.list = new java.util.LinkedList<>();
    }

    @Override
    public T pop() throws ListException
    {
        if (list.isEmpty())
            throw new ListException("List is empty!");
        return list.remove(0);
    }

    @Override
    public void append(T value)
    {
        list.add(value);
    }

    @Override
    public boolean isEmpty()
    {
        return list.isEmpty();
    }

    /*
    @Override
    public List<T> getList()
    {
        return list;
    }*/
    /*
    @Override
    public java.util.Iterator<T> iterator()
    {
        return list.iterator();
    }*/

    @Override
    public String toString()
    {
        StringBuilder string = new StringBuilder();
        for (T element : list)
        {
            string.append(element.toString());
            string.append("\n");
        }
        return string.toString();
    }

    @Override
    public void clear()
    {
        list.clear();
    }

    @Override
    public List<T> getList()
    {
        return list;
    }

}
