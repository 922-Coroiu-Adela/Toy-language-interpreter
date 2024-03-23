package gui.a7.Model.Statements;

import gui.a7.Model.Exceptions.EvaluationException;
import gui.a7.Model.Exceptions.ExecutionException;
import gui.a7.Model.Expressions.iExpression;
import gui.a7.Model.State.programState;
import gui.a7.Model.Structures.iDictionary;
import gui.a7.Model.Structures.iHeap;
import gui.a7.Model.Types.iType;
import gui.a7.Model.Types.intType;
import gui.a7.Model.Types.stringType;
import gui.a7.Model.Values.iValue;
import gui.a7.Model.Values.intValue;
import gui.a7.Model.Values.stringValue;

import java.io.BufferedReader;

public class readFile implements iStatement
{
    iExpression expression;
    iExpression variable;

    public readFile(iExpression expression, iExpression variable)
    {
        this.expression = expression;
        this.variable = variable;
    }

    @Override
    public String toString()
    {
        return "readFile(" + expression.toString() + ", " + variable.toString() + ");";
    }

    @Override
    public programState execute(programState state) throws ExecutionException, EvaluationException
    {
        // check if variable is defined
        if (!state.getSymbolTable().isDefined(variable.toString()))
            throw new ExecutionException("readFile: variable is not defined");

        // check if expression is a string
        iDictionary<String, iValue> symbolTable = state.getSymbolTable();
        iHeap<Integer, iValue> heap = state.getHeap();
        iValue typeCheck = expression.evaluate(symbolTable, heap);
        if (!typeCheck.getType().equals(new stringType()))
            throw new ExecutionException("readFile: expression is not a string");

        // check if file is opened
        stringValue fileName = (stringValue) typeCheck;
        if (!state.getFileTable().isDefined(fileName.getValue()))
            throw new ExecutionException("readFile: file is not opened");


        // read from file
        String line = null;
        try
        {
            line = ((BufferedReader) state.getFileTable().getValue(fileName.getValue())).readLine();
        }
        catch (Exception exception)
        {
            throw new ExecutionException("readFile: file does not exist");
        }


        iValue value;
        if (line == null)
            value = new intValue(0);
        else
        {
            try
            {
                value = new intValue(Integer.parseInt(line));
            }
            catch (Exception exception)
            {
                throw new ExecutionException("readFile: file does not contain an integer");
            }
        }
        symbolTable.update(variable.toString(), value);

        return null;
    }

    @Override
    public iStatement deepCopy()
    {
        return new readFile(expression.deepCopy(), variable.deepCopy());
    }

    @Override
    public iDictionary<String, iType> typeCheck(iDictionary<String, iType> typeEnvironment) throws EvaluationException
    {
        iType typeExpression = expression.typeCheck(typeEnvironment);
        iType typeVariable = variable.typeCheck(typeEnvironment);
        if (typeExpression.equals(new stringType()))
        {
            if (typeVariable.equals(new intType())) {
                return typeEnvironment;
            } else
                throw new EvaluationException("readFile: variable is not an integer");
        }
        else
            throw new EvaluationException("readFile: expression is not a string");
    }
}
