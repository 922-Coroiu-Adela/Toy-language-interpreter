package gui.a7.Model.Structures;

import gui.a7.Model.Exceptions.MyException;

import java.util.Map;

public class adtHeap<TKey, TVal> implements iHeap<Integer, TVal>
{
    private Map<Integer, TVal> heap;
    private int freeLocation = 1;

    public adtHeap(Map<Integer, TVal> heap)
    {
        this.heap = heap;
    }

    @Override
    public Integer put(TVal val)
    {
        heap.put(freeLocation, (TVal) val);
        freeLocation++;
        return freeLocation - 1;
    }

    @Override
    public void update(Integer key, TVal val) throws MyException
    {
        if (!heap.containsKey(key))
            throw new MyException("Key not found in dictionary!");
        heap.put(key, val);
    }

    @Override
    public TVal get(Integer key) throws MyException
    {
        if (!heap.containsKey(key))
            throw new MyException("Key not found in dictionary!");
        return heap.get(key);
    }

    @Override
    public void remove(Integer key) throws MyException
    {
        if (!heap.containsKey(key))
            throw new MyException("Key not found in dictionary!");
        heap.remove(key);
    }

    @Override
    public boolean isDefined(Integer key)
    {
        return heap.containsKey(key);
    }

    @Override
    public int size()
    {
        return heap.size();
    }

    @Override
    public boolean isEmpty()
    {
        return heap.isEmpty();
    }

    @Override
    public String toString()
    {
        StringBuilder s = new StringBuilder();
        for (Integer key : heap.keySet())
            s.append(key.toString()).append(" -> ").append(heap.get(key).toString()).append("\n");
        return s.toString();
    }

    @Override
    public void clear()
    {
        heap.clear();
    }

    @Override
    public void set(Map<Integer, TVal> heap)
    {
        this.heap = (Map<Integer, TVal>) heap;
    }

    @Override
    public Map<Integer, TVal> getContent()
    {
        return (Map<Integer, TVal>) heap;
    }
}
