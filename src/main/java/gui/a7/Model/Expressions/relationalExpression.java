package gui.a7.Model.Expressions;

import gui.a7.Model.Exceptions.EvaluationException;
import gui.a7.Model.Structures.iDictionary;
import gui.a7.Model.Structures.iHeap;
import gui.a7.Model.Types.boolType;
import gui.a7.Model.Types.iType;
import gui.a7.Model.Types.intType;
import gui.a7.Model.Values.iValue;
import gui.a7.Model.Values.intValue;
import gui.a7.Model.Values.boolValue;

public class relationalExpression implements iExpression
{
    iExpression firstExpression;
    iExpression secondExpression;
    String operator;

    public relationalExpression(iExpression firstExpression, iExpression secondExpression, String operator)
    {
        this.firstExpression = firstExpression;
        this.secondExpression = secondExpression;
        this.operator = operator;
    }

    @Override
    public String toString()
    {
        return firstExpression.toString() + " " + operator + " " + secondExpression.toString();
    }

    @Override
    public iValue evaluate(iDictionary<String, iValue> table, iHeap<Integer, iValue> heap) throws EvaluationException
    {
        iValue value1, value2;
        value1 = firstExpression.evaluate(table, heap);
        if (value1.getType().equals(new intType()))
        {
            value2 = secondExpression.evaluate(table, heap);
            if (value2.getType().equals(new intType()))
            {
                intValue intValue1 = (intValue) value1;
                intValue intValue2 = (intValue) value2;
                int number1, number2;
                number1 = intValue1.getValue();
                number2 = intValue2.getValue();
                switch (operator)
                {
                    case "<":
                        return new boolValue(number1 < number2);
                    case "<=":
                        return new boolValue(number1 <= number2);
                    case "==":
                        return new boolValue(number1 == number2);
                    case "!=":
                        return new boolValue(number1 != number2);
                    case ">":
                        return new boolValue(number1 > number2);
                    case ">=":
                        return new boolValue(number1 >= number2);
                    default:
                        throw new EvaluationException("Invalid operator!");
                }
            }
            else
                throw new EvaluationException("Second operand is not an integer!");
        }
        else
            throw new EvaluationException("First operand is not an integer!");
    }

    @Override
    public iExpression deepCopy()
    {
        return new relationalExpression(firstExpression.deepCopy(), secondExpression.deepCopy(), operator);
    }

    @Override
    public iType typeCheck(iDictionary<String, iType> typeEnvironment) throws EvaluationException
    {
        iType type1, type2;
        type1 = firstExpression.typeCheck(typeEnvironment);
        type2 = secondExpression.typeCheck(typeEnvironment);
        if (type1.equals(new intType()))
        {
            if (type2.equals(new intType()))
                return new boolType();
            else
                throw new EvaluationException("Second operand is not an integer!");
        }
        else
            throw new EvaluationException("First operand is not an integer!");
    }
}
