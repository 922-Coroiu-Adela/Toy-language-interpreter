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


public class acquireSemaphoreStatement implements iStatement
{
    private String variable;

    public acquireSemaphoreStatement(String variable)
    {
        this.variable = variable;
    }

    @Override
    public iStatement deepCopy()
    {
        return new acquireSemaphoreStatement(variable);
    }

    @Override
    public programState execute(programState state) throws EvaluationException
    {
        iValue variableValue = state.getSymbolTable().lookup(variable);
        if (variableValue == null)
            throw new EvaluationException("The variable has not been declared!");
        if (!variableValue.getType().equals(new intType()))
            throw new EvaluationException("The variable should have integer type!");

        int index = ((intValue) variableValue).getValue();
        programState.countSemaphoreLock.lock();

        Pair<Integer, List<Integer>> countSemaphoreEntry = state.getCountSemaphoreTable().lookup(index);
        if (countSemaphoreEntry == null)
        {
            programState.countSemaphoreLock.unlock();
            throw new EvaluationException("The index is not in the semaphore table!");
        }

        Integer n1 = countSemaphoreEntry.getKey();
        List<Integer> list1 = countSemaphoreEntry.getValue();
        if (n1 > list1.size()
        )
        {
            if (!list1.contains(state.getId()))
                list1.add(state.getId());
        }
        else
            state.getExecutionStack().push(this);
        programState.countSemaphoreLock.unlock();
        return null;

    }

    @Override
    public String toString()
    {
        return "acquire(" + variable + ")";
    }

    @Override
    public iDictionary<String, iType> typeCheck(iDictionary<String, iType> typeEnvironment) throws EvaluationException, EvaluationException {
        iType variableType = typeEnvironment.lookup(variable);
        if (variableType == null)
            throw new EvaluationException("The variable hasn't been declared!");
        if (!variableType.equals(new intType()))
            throw new EvaluationException("The variable should be an integer!");
        return typeEnvironment;
    }


}
