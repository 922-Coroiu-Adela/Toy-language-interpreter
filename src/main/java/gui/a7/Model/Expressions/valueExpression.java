package gui.a7.Model.Expressions;

import gui.a7.Model.Exceptions.EvaluationException;
import gui.a7.Model.Structures.iDictionary;
import gui.a7.Model.Structures.iHeap;
import gui.a7.Model.Types.iType;
import gui.a7.Model.Values.iValue;


public class valueExpression implements  iExpression
{
    public iValue value;

    public valueExpression(iValue value)
    {
        this.value = value;
    }

    @Override
    public iValue evaluate(iDictionary<String, iValue> table, iHeap<Integer, iValue> heap) throws EvaluationException
    {
        return value;
    }

    public String toString()
    {
        return value.toString();
    }

    @Override
    public iExpression deepCopy()
    {
        return new valueExpression(value.deepCopy());
    }

    @Override
    public iType typeCheck(iDictionary<String, iType> typeEnvironment) throws EvaluationException
    {
        return value.getType();
    }

}
