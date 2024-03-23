package gui.a7.Model.Expressions;

import gui.a7.Model.Exceptions.EvaluationException;
import gui.a7.Model.Structures.iDictionary;
import gui.a7.Model.Structures.iHeap;
import gui.a7.Model.Types.iType;
import gui.a7.Model.Values.iValue;


public class variableExpression implements iExpression
{
    private final String variable;

    public variableExpression(String variable)
    {
        this.variable = variable;
    }

    @Override
    public iValue evaluate(iDictionary<String, iValue> table, iHeap<Integer, iValue> heap) throws EvaluationException
    {
        return table.getValue(variable);
    }

    public String toString()
    {
        return variable;
    }

    @Override
    public iExpression deepCopy()
    {
        return new variableExpression(variable);
    }

    @Override
    public iType typeCheck(iDictionary<String, iType> typeEnvironment) throws EvaluationException
    {
        return typeEnvironment.getValue(variable);
    }
}
