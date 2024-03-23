package gui.a7.Model.Structures;

import gui.a7.Model.Exceptions.EvaluationException;
import javafx.util.Pair;

import java.util.List;

public interface iSemaphore extends iDictionary<Integer, Pair<Integer, List<Integer>>>
{
    int put(Pair<Integer, List<Integer>> value) throws EvaluationException;
    int getFreeLocation();
}
