package gui.a7.Model.Statements;

import gui.a7.Model.Exceptions.EvaluationException;
import gui.a7.Model.Expressions.iExpression;
import gui.a7.Model.State.programState;
import gui.a7.Model.Structures.iDictionary;
import gui.a7.Model.Types.iType;
import gui.a7.Model.Types.intType;
import gui.a7.Model.Values.iValue;
import gui.a7.Model.Values.intValue;
import javafx.util.Pair;

import java.util.Vector;

public class createSemaphoreStatement implements iStatement
{
    private String variable;
    private iExpression expression1;

    public createSemaphoreStatement(String variable, iExpression expression1) {
        this.variable = variable;
        this.expression1 = expression1;
    }

    @Override
    public iDictionary<String, iType> typeCheck(iDictionary<String, iType> typeEnvironment) throws EvaluationException
    {
        iType variableType = typeEnvironment.lookup(variable);
        if (variableType == null)
            throw new EvaluationException("The variable hasn't been declared!");
        if (!variableType.equals(new intType()))
            throw new EvaluationException("The variable should be an integer!");
        if (!expression1.typeCheck(typeEnvironment).equals(new intType()))
            throw new EvaluationException("The expression should evaluate to an integer!");
        return typeEnvironment;
    }

    @Override
    public programState execute(programState state) throws EvaluationException
    {
        iValue number1 = expression1.evaluate(state.getSymbolTable(), state.getHeap());
        if (!number1.getType().equals(new intType()))
            throw new EvaluationException("The expression should evaluate to an integer!");

        int expression1_int = ((intValue) number1).getValue();
        programState.countSemaphoreLock.lock();

        int newFreeLocation = state.getCountSemaphoreTable().put(new Pair<>(expression1_int, new Vector<>()));
        iValue variableValue = state.getSymbolTable().lookup(variable);
        if (variableValue == null)
            throw new EvaluationException("The variable has not been declared!");
        if (!variableValue.getType().equals(new intType()))
            throw new EvaluationException("The variable should have integer type!");

        state.getSymbolTable().put(variable, new intValue(newFreeLocation));
        programState.countSemaphoreLock.unlock();

        return null;
    }

    @Override
    public iStatement deepCopy()
    {
        return new createSemaphoreStatement(variable, expression1);
    }

    @Override
    public String toString()
    {
        return "createSemaphore(" + variable + ", " + expression1.toString() + ")";
    }

}
