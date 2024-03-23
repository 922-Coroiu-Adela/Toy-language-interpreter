package gui.a7.Model.Structures;

import gui.a7.Model.Exceptions.MyException;

import java.util.Map;

public interface iHeap<TKey, TVal> extends iClearable
{
    public TKey put(TVal val);

    public void update(TKey key, TVal val) throws MyException;

    public TVal get(TKey key) throws MyException;

    public void remove(TKey key) throws MyException;

    public boolean isDefined(TKey key);

    public int size();
    public boolean isEmpty();

    public void set(Map<Integer,TVal> heap);

    public Map<Integer,TVal> getContent();
}
