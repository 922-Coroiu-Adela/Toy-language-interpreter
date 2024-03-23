package gui.a7.Model.Statements;

import gui.a7.Model.Exceptions.EvaluationException;
import gui.a7.Model.State.programState;
import gui.a7.Model.Structures.iDictionary;
import gui.a7.Model.Types.iType;
import gui.a7.Model.Types.intType;
import gui.a7.Model.Values.iValue;
import gui.a7.Model.Values.intValue;
import javafx.util.Pair;

import java.util.List;

public class releaseSemaphoreStatement implements iStatement
{
    private String variable;

    public releaseSemaphoreStatement(String variable)
    {
        this.variable = variable;
    }

    @Override
    public programState execute(programState state) throws EvaluationException {
        iValue variableValue = state.getSymbolTable().lookup(variable);
        if (variableValue == null)
            throw new EvaluationException("The variable has not been declared!");
        if (!variableValue.getType().equals(new intType()))
            throw new EvaluationException("The variable should have integer type!");

        int countSemaphoreLocation = ((intValue) variableValue).getValue();
        programState.countSemaphoreLock.lock();
        Pair<Integer, List<Integer>> semaphoreEntry = state.getCountSemaphoreTable().lookup(countSemaphoreLocation);
        if (semaphoreEntry == null)
        {
            programState.countSemaphoreLock.unlock();
            throw new EvaluationException("Invalid semaphore location!");
        }

        Integer semaphore = semaphoreEntry.getKey();
        List<Integer> list1 = semaphoreEntry.getValue();

        Integer programId = state.getId();
        if (list1.contains(programId))
            list1.remove(programId);

        programState.countSemaphoreLock.unlock();
        return null;
    }

    @Override
    public String toString()
    {
        return "release(" + variable + ")";
    }

    @Override
    public iDictionary<String, iType> typeCheck(iDictionary<String, iType> typeEnvironment) throws EvaluationException
    {
        iType typeVariable = typeEnvironment.getValue(variable);
        if (typeVariable == null)
            throw new EvaluationException("The variable hasn't been declared!");
        if (!typeVariable.equals(new intType()))
            throw new EvaluationException("The variable should be an integer!");
        return typeEnvironment;
    }

    @Override
    public iStatement deepCopy()
    {
        return new releaseSemaphoreStatement(variable);
    }
}
