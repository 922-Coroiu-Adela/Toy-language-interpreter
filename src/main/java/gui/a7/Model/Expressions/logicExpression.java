package gui.a7.Model.Expressions;

import gui.a7.Model.Exceptions.EvaluationException;
import gui.a7.Model.Structures.iDictionary;
import gui.a7.Model.Structures.iHeap;
import gui.a7.Model.Types.*;
import gui.a7.Model.Values.*;


public class logicExpression implements iExpression
{
    public iExpression expression1;
    public iExpression expression2;
    String operator;

    public logicExpression(iExpression expression1, iExpression expression2, String operator)
    {
        this.expression1 = expression1;
        this.expression2 = expression2;
        this.operator = operator;
    }

    @Override
    public iValue evaluate(iDictionary<String, iValue> table, iHeap<Integer, iValue> heap) throws EvaluationException
    {
        iValue value1, value2;
        value1 = expression1.evaluate(table, heap);
        if (value1.getType().equals(new boolType()))
        {
            value2 = expression2.evaluate(table, heap);
            if (value2.getType().equals(new boolType()))
            {
                boolValue boolValue1 = (boolValue) value1;
                boolValue boolValue2 = (boolValue) value2;
                boolean bool1, bool2;
                bool1 = boolValue1.getValue();
                bool2 = boolValue2.getValue();
                switch (operator)
                {
                    case "&&":
                        return new boolValue(bool1 && bool2);
                    case "||":
                        return new boolValue(bool1 || bool2);
                    default:
                        throw new EvaluationException("Invalid operator!");
                }
            }
            else
                throw new EvaluationException("Second operand is not a boolean!");
        }
        else
            throw new EvaluationException("First operand is not a boolean!");
    }


    public String toString()
    {
        return expression1.toString() + " " + operator + " " + expression2.toString();
    }

    @Override
    public iExpression deepCopy()
    {
        return new logicExpression(expression1.deepCopy(), expression2.deepCopy(), operator);
    }

    @Override
    public iType typeCheck(iDictionary<String, iType> typeEnvironment) throws EvaluationException
    {
        iType type1, type2;
        type1 = expression1.typeCheck(typeEnvironment);
        type2 = expression2.typeCheck(typeEnvironment);
        if (type1.equals(new boolType()))
        {
            if (type2.equals(new boolType()))
                return new boolType();
            else
                throw new EvaluationException("Second operand is not a boolean!");
        }
        else
            throw new EvaluationException("First operand is not a boolean!");
    }
}
