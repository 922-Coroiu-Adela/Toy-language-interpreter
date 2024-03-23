package gui.a7.Model.Structures;

import gui.a7.Model.Exceptions.EvaluationException;
import javafx.util.Pair;
import java.util.List;


public class adtCountSemaphore extends adtDictionary<Integer, Pair<Integer, List<Integer>>> implements iSemaphore
{
    private int freeLocation;

    public adtCountSemaphore()
    {
        super();
        this.freeLocation = 1;
    }


    @Override
    public int put(Pair<Integer, List<Integer>> value) throws EvaluationException
    {
        super.put(freeLocation, value);
        synchronized (this)
        {
            freeLocation = freeLocation + 1;
        }
        return freeLocation - 1;
    }

    @Override
    public void put(Integer key, Pair<Integer, List<Integer>> value) throws EvaluationException
    {
        if (!key.equals(freeLocation))
            throw new EvaluationException("Invalid semaphore table location!");
        super.put(key, value);
        synchronized (this)
        {
            freeLocation = freeLocation + 1;
        }
    }


    @Override
    public int getFreeLocation()
    {
        int freeLocationAddress = 1;
        while (this.dictionary.get(freeLocationAddress) != null)
            freeLocationAddress = freeLocationAddress + 1;
        return freeLocationAddress;
    }
}
