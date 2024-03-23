package gui.a7.Model.Structures;

import gui.a7.Model.Exceptions.ListException;
import java.util.List;

public interface iList<T> extends iClearable
{
    T pop() throws ListException;
    void append(T value);
    boolean isEmpty();
    //List<T> getList();

    String toString();

    public List<T> getList();

}
