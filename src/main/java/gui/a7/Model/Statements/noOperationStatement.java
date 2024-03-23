package gui.a7.Model.Statements;

import gui.a7.Model.Exceptions.EvaluationException;
import gui.a7.Model.State.programState;
import gui.a7.Model.Structures.iDictionary;
import gui.a7.Model.Types.iType;

public class noOperationStatement implements iStatement
{
    @Override
    public programState execute(programState state)
    {
        return null;
    }

    @Override
    public String toString()
    {
        return "No operation";
    }

    @Override
    public iStatement deepCopy()
    {
        return new noOperationStatement();
    }

    @Override
    public iDictionary<String, iType> typeCheck(iDictionary<String, iType> typeEnvironment) throws EvaluationException
    {
        return typeEnvironment;
    }
}
