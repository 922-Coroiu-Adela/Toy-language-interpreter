package gui.a7.Model.Expressions;

import gui.a7.Model.Exceptions.EvaluationException;
import gui.a7.Model.Structures.iDictionary;
import gui.a7.Model.Structures.iHeap;
import gui.a7.Model.Types.iType;
import gui.a7.Model.Types.intType;
import gui.a7.Model.Values.iValue;
import gui.a7.Model.Values.intValue;


public class arithmeticExpression implements  iExpression
{
    iExpression expression1;
    iExpression expression2;
    char operator;

    @Override
    public iValue evaluate(iDictionary<String, iValue> table, iHeap<Integer, iValue> heap) throws EvaluationException
    {
        iValue value1, value2;
        value1 = expression1.evaluate(table, heap);
        if (value1.getType().equals(new intType()))
        {
            value2 = expression2.evaluate(table, heap);
            if (value2.getType().equals(new intType()))
            {
                intValue intValue1 = (intValue) value1;
                intValue intValue2 = (intValue) value2;
                int number1, number2;
                number1 = intValue1.getValue();
                number2 = intValue2.getValue();
                switch (operator)
                {
                    case '+':
                        return new intValue(number1 + number2);
                    case '-':
                        return new intValue(number1 - number2);
                    case '*':
                        return new intValue(number1 * number2);
                    case '/':
                        if (number2 == 0)
                            throw new EvaluationException("Division by zero!");
                        else
                            return new intValue(number1 / number2);
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

    public arithmeticExpression(char op, iExpression expression1, iExpression expression2)
    {
        this.expression1 = expression1;
        this.expression2 = expression2;
        this.operator = op;
    }

    public String toString()
    {
        return expression1.toString() + operator + expression2.toString();
    }

    public iExpression deepCopy()
    {
        return new arithmeticExpression(operator, expression1.deepCopy(), expression2.deepCopy());
    }

    public iType typeCheck(iDictionary<String, iType> typeEnvironment) throws EvaluationException
    {
        iType type1, type2;
        type1 = expression1.typeCheck(typeEnvironment);
        type2 = expression2.typeCheck(typeEnvironment);
        if (type1.equals(new intType()))
        {
            if (type2.equals(new intType()))
                return new intType();
            else
                throw new EvaluationException("Second operand is not an integer!");
        }
        else
            throw new EvaluationException("First operand is not an integer!");
    }

}
