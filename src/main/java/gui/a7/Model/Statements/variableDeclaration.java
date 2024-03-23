package gui.a7.Model.Statements;

import gui.a7.Model.Exceptions.EvaluationException;
import gui.a7.Model.Exceptions.ExecutionException;
import gui.a7.Model.State.programState;
import gui.a7.Model.Structures.iDictionary;
import gui.a7.Model.Types.iType;
import gui.a7.Model.Values.iValue;


public class variableDeclaration implements iStatement
{
    private String name;
    private iType type;

    public variableDeclaration(String name, iType type)
    {
        this.name = name;
        this.type = type;
    }

    @Override
    public programState execute(programState state) throws ExecutionException, EvaluationException
    {
        iDictionary<String, iValue> symbolTable = state.getSymbolTable();
        if (symbolTable.containsKey(name))
            throw new ExecutionException("Variable " + name + " already declared!");
        else
            symbolTable.put(name, type.getDefaultValue());
        return null;
    }

    @Override
    public String toString()
    {
        return type.toString() + " " + name;
    }

    @Override
    public iStatement deepCopy()
    {
        return new variableDeclaration(name, type);
    }

    @Override
    public iDictionary<String, iType> typeCheck(iDictionary<String, iType> typeEnvironment) throws EvaluationException
    {
        typeEnvironment.put(name, type);
        return typeEnvironment;
    }
}
