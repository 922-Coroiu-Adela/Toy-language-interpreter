package gui.a7.Model.Statements;

import gui.a7.Model.Exceptions.EvaluationException;
import gui.a7.Model.Exceptions.ExecutionException;
import gui.a7.Model.Expressions.iExpression;
import gui.a7.Model.State.programState;
import gui.a7.Model.Structures.iDictionary;
import gui.a7.Model.Types.iType;
import gui.a7.Model.Types.stringType;
import gui.a7.Model.Values.iValue;
import gui.a7.Model.Values.stringValue;

import java.io.BufferedReader;

public class closeRFile implements iStatement
{
    iExpression expression;

    public closeRFile(iExpression expression)
    {
        this.expression = expression;
    }

    @Override
    public String toString()
    {
        return "closeRFile(" + expression.toString() + ");";
    }

    @Override
    public iStatement deepCopy()
    {
        return new closeRFile(expression.deepCopy());
    }

    @Override
    public programState execute(programState state) throws ExecutionException, EvaluationException
    {
        iValue typeCheck = expression.evaluate(state.getSymbolTable(), state.getHeap());
        if (!typeCheck.getType().equals(new stringType()))
            throw new ExecutionException("closeRFile: expression is not a string");

        stringValue fileName = (stringValue) typeCheck;
        if (!state.getFileTable().isDefined(fileName.getValue()))
            throw new ExecutionException("closeRFile: file is not opened");

        BufferedReader bufferedReader = (BufferedReader) state.getFileTable().getValue(fileName.getValue());
        try
        {
            bufferedReader.close();
        }
        catch (Exception exception)
        {
            throw new ExecutionException("closeRFile: file does not exist");
        }
        state.getFileTable().remove(fileName.getValue());

        return null;
    }

    @Override
    public iDictionary<String, iType> typeCheck(iDictionary<String, iType> typeEnvironment) throws EvaluationException
    {
        return null;
    }
}
