package gui.a7.Model.Expressions;

import gui.a7.Model.Exceptions.EvaluationException;
import gui.a7.Model.Structures.iDictionary;
import gui.a7.Model.Structures.iHeap;
import gui.a7.Model.Types.iType;
import gui.a7.Model.Values.iValue;

public interface iExpression
{
    iValue evaluate(iDictionary<String, iValue> table, iHeap<Integer, iValue> heap) throws EvaluationException;

    iType typeCheck(iDictionary<String, iType> typeEnvironment) throws EvaluationException;
    iExpression deepCopy();
}
