package gui.a7.Model.Statements;

import gui.a7.Model.Exceptions.EvaluationException;
import gui.a7.Model.State.programState;
import gui.a7.Model.Structures.iDictionary;
import gui.a7.Model.Types.iType;

public class programTermination implements iStatement
{
    @Override
    public programState execute(programState state)
    {
        return null;
    }

    @Override
    public String toString()
    {
        return "End of the program execution";
    }

    @Override
    public iStatement deepCopy()
    {
        return new programTermination();
    }

    @Override
    public iDictionary<String, iType> typeCheck(iDictionary<String, iType> typeEnvironment) throws EvaluationException
    {
        return typeEnvironment;
    }
}
