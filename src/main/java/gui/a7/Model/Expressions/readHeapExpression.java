package gui.a7.Model.Expressions;

import gui.a7.Model.Exceptions.EvaluationException;
import gui.a7.Model.Exceptions.MyException;
import gui.a7.Model.Structures.iDictionary;
import gui.a7.Model.Structures.iHeap;
import gui.a7.Model.Types.iType;
import gui.a7.Model.Values.iValue;
import gui.a7.Model.Values.refValue;
import gui.a7.Model.Types.refType;

public class readHeapExpression implements iExpression
{
    private iExpression expression;

    public readHeapExpression(iExpression expression)
    {
        this.expression = expression;
    }

    @Override
    public String toString()
    {
        return "readHeap(" + expression.toString() + ")";
    }

    @Override
    public iValue evaluate(iDictionary<String, iValue> table, iHeap<Integer, iValue> heap) throws EvaluationException
    {
        iValue value = expression.evaluate(table, heap);
        if (!(value instanceof refValue))
            throw new EvaluationException("Expression is not of type reference!");

        int address = ((refValue) value).getAddr();
        if (!heap.isDefined(address))
            throw new EvaluationException("Address is not defined in heap!");

        try
        {
            return heap.get(address);
        }
        catch (MyException e)
        {
            throw new RuntimeException(e);
        }
    }

    @Override
    public iExpression deepCopy()
    {
        return new readHeapExpression(expression.deepCopy());
    }

    @Override
    public iType typeCheck(iDictionary<String, iType> typeEnvironment) throws EvaluationException
    {
        iType type = expression.typeCheck(typeEnvironment);
        if (type instanceof refType)
        {
            refType refType = (refType) type;
            return refType.getInner();
        }
        else
            throw new EvaluationException("Expression is not of type reference!");
    }
}
