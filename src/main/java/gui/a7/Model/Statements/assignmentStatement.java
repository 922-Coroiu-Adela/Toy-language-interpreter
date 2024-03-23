package gui.a7.Model.Statements;

import gui.a7.Model.Expressions.iExpression;
import gui.a7.Model.State.programState;
import gui.a7.Model.Exceptions.EvaluationException;
import gui.a7.Model.Exceptions.ExecutionException;
import gui.a7.Model.Structures.iHeap;
import gui.a7.Model.Types.iType;
import gui.a7.Model.Values.iValue;
import gui.a7.Model.Structures.iDictionary;

public class assignmentStatement implements iStatement
{
    private String variableName;
    private iExpression expression;

    public assignmentStatement(String variableName, iExpression expression)
    {
        this.variableName = variableName;
        this.expression = expression;
    }

    @Override
    public programState execute(programState state) throws ExecutionException, EvaluationException
    {
        iDictionary<String, iValue> symbolTable = state.getSymbolTable();
        iHeap<Integer, iValue> heap = state.getHeap();
        iValue value = expression.evaluate(symbolTable, heap);
        if (symbolTable.containsKey(variableName))
        {
            iType variableType = (symbolTable.getValue(variableName)).getType();
            if (value.getType().equals(variableType))
                symbolTable.update(variableName, value);
            else
                throw new ExecutionException("Declared type of variable " + variableName + " and type of the assigned expression do not match!");
        }
        else
            throw new ExecutionException("The used variable " + variableName + " was not declared before!");
        return null;
    }

    @Override
    public String toString()
    {
        return variableName + "=" + expression.toString();
    }

    @Override
    public iStatement deepCopy()
    {
        return new assignmentStatement(variableName, expression.deepCopy());
    }

    @Override
    public iDictionary<String, iType> typeCheck(iDictionary<String, iType> typeEnvironment) throws EvaluationException
    {
        iType typeVariable = typeEnvironment.getValue(variableName);
        iType typeExpression = expression.typeCheck(typeEnvironment);
        if (typeVariable.equals(typeExpression))
            return typeEnvironment;
        else
            throw new EvaluationException("Assignment: right hand side and left hand side have different types!");
    }
}
