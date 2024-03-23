package gui.a7.Model.Statements;

import gui.a7.Model.Exceptions.EvaluationException;
import gui.a7.Model.Exceptions.ExecutionException;
import gui.a7.Model.Exceptions.MyException;
import gui.a7.Model.Expressions.iExpression;
import gui.a7.Model.State.programState;
import gui.a7.Model.Structures.iDictionary;
import gui.a7.Model.Types.iType;
import gui.a7.Model.Types.stringType;
import gui.a7.Model.Values.stringValue;
import gui.a7.Model.Values.iValue;

public class openRFile implements iStatement
{
    iExpression expression;

    public openRFile(iExpression expression)
    {
        this.expression = expression;
    }

    @Override
    public String toString()
    {
        return "openRFile(" + expression.toString() + ");";
    }

    @Override
    public programState execute(programState state) throws ExecutionException, EvaluationException
    {
        iValue typeCheck = expression.evaluate(state.getSymbolTable(), state.getHeap());
        if (!typeCheck.getType().equals(new stringType()))
            throw new ExecutionException("openRFile: expression is not a string");

        stringValue fileName = (stringValue) typeCheck;
        if (state.getFileTable().isDefined(fileName.getValue()))
            throw new ExecutionException("openRFile: file already opened");

        try
        {
            java.io.BufferedReader bufferedReader = new java.io.BufferedReader(new java.io.FileReader(fileName.getValue()));
            state.getFileTable().put(fileName.getValue(), bufferedReader);
        }
        catch (java.io.FileNotFoundException exception)
        {
            throw new ExecutionException("openRFile: file does not exist");
        }
        return null;
    }

    @Override
    public iStatement deepCopy()
    {
        return new openRFile(expression.deepCopy());
    }

    @Override
    public iDictionary<String, iType> typeCheck(iDictionary<String, iType> typeEnvironment) throws EvaluationException
    {
        iType typeExpression = expression.typeCheck(typeEnvironment);
        if (typeExpression.equals(new stringType()))
            return typeEnvironment;
        else
            throw new EvaluationException("openRFile: expression is not a string");
    }
}
