package gui.a7.Model.Statements;

import gui.a7.Model.Exceptions.EvaluationException;
import gui.a7.Model.Expressions.iExpression;
import gui.a7.Model.State.programState;
import gui.a7.Model.Structures.iDictionary;
import gui.a7.Model.Types.boolType;
import gui.a7.Model.Types.iType;
import gui.a7.Model.Values.boolValue;

public class whileStatement implements iStatement
{
    iExpression condition;
    iStatement statement;

    public whileStatement(iExpression c, iStatement s)
    {
        condition = c;
        statement = s;
    }

    @Override
    public programState execute(programState state) throws EvaluationException
    {
        if(!condition.evaluate(state.getSymbolTable(), state.getHeap()).getType().equals(new boolType()))
        {
            throw new EvaluationException("<ERROR> Condition is not a boolean");
        }
        boolValue val = (boolValue) condition.evaluate(state.getSymbolTable(), state.getHeap());
        if(val.getValue())
        {
            state.getExecutionStack().push(this);
            state.getExecutionStack().push(statement);
        }
        return null;

    }

    @Override
    public iStatement deepCopy() {
        return new whileStatement(condition.deepCopy(), statement.deepCopy());
    }

    @Override
    public String toString() {
        return "while(" + condition.toString() + "){" + statement.toString() + "}";
    }

    @Override
    public iDictionary<String, iType> typeCheck(iDictionary<String, iType> typeEnvironment) throws EvaluationException
    {
        iType typeCondition = condition.typeCheck(typeEnvironment);
        if (typeCondition.equals(new boolType()))
        {
            statement.typeCheck(typeEnvironment);
            return typeEnvironment;
        }
        else
            throw new EvaluationException("The condition of WHILE has not the type bool!");
    }
}
