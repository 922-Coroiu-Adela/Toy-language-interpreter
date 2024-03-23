package gui.a7.Model.Statements;

import gui.a7.Model.Exceptions.EvaluationException;
import gui.a7.Model.Exceptions.ExecutionException;
import gui.a7.Model.Expressions.iExpression;
import gui.a7.Model.State.programState;
import gui.a7.Model.Types.iType;
import gui.a7.Model.Types.boolType;
import gui.a7.Model.Values.iValue;
import gui.a7.Model.Values.boolValue;
import gui.a7.Model.Structures.iDictionary;

public class ifStatement implements iStatement
{
    private iExpression expression;
    private iStatement thenStatement;
    private iStatement elseStatement;

    public ifStatement(iExpression expression, iStatement thenStatement, iStatement elseStatement)
    {
        this.expression = expression;
        this.thenStatement = thenStatement;
        this.elseStatement = elseStatement;
    }

    @Override
    public programState execute(programState state) throws ExecutionException, EvaluationException
    {
        iValue condition = expression.evaluate(state.getSymbolTable(), state.getHeap());
        if (!condition.getType().equals(new boolType()))
            throw new ExecutionException("Condition expression is not of type bool!");

        boolValue boolCondition = (boolValue)condition;
        if (boolCondition.getValue())
            state.getExecutionStack().push(thenStatement);
        else
            state.getExecutionStack().push(elseStatement);
        return null;
    }

    @Override
    public String toString()
    {
        return "if(" + expression.toString() + ") then(" + thenStatement.toString() + ") else(" + elseStatement.toString() + ")";
    }

    @Override
    public iStatement deepCopy()
    {
        return new ifStatement(expression.deepCopy(), thenStatement.deepCopy(), elseStatement.deepCopy());
    }

    @Override
    public iDictionary<String, iType> typeCheck(iDictionary<String, iType> typeEnvironment) throws EvaluationException
    {
        iType typeExpression = expression.typeCheck(typeEnvironment);
        if (typeExpression.equals(new boolType()))
        {
            thenStatement.typeCheck(typeEnvironment);
            elseStatement.typeCheck(typeEnvironment);
            return typeEnvironment;
        }
        else
            throw new EvaluationException("The condition of IF has not the type bool!");
    }
}
