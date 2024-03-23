package gui.a7.Model.Statements;

import gui.a7.Model.Exceptions.EvaluationException;
import gui.a7.Model.Exceptions.ExecutionException;
import gui.a7.Model.State.programState;
import gui.a7.Model.Structures.iDictionary;
import gui.a7.Model.Types.iType;

public interface iStatement
{
    programState execute(programState state) throws ExecutionException, EvaluationException;
    iStatement deepCopy();

    iDictionary<String, iType> typeCheck(iDictionary<String, iType> typeEnvironment) throws EvaluationException;

}
