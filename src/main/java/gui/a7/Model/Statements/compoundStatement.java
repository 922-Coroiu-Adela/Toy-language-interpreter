package gui.a7.Model.Statements;

import gui.a7.Model.Exceptions.EvaluationException;
import gui.a7.Model.Structures.iStack;
import gui.a7.Model.Structures.iDictionary;
import gui.a7.Model.State.programState;
import gui.a7.Model.Types.iType;


public class compoundStatement implements iStatement
{
    iStatement one;
    iStatement two;

    @Override
    public  String toString()
    {
        return "(" + one.toString() + "; " + two.toString() + ")";
    }

    public compoundStatement(iStatement one, iStatement two)
    {
        this.one = one;
        this.two = two;
    }

    @Override
    public programState execute(programState state) throws EvaluationException
    {
        iStack<iStatement> stack = state.getExecutionStack();
        stack.push(two);
        stack.push(one);
        return null;
    }

    @Override
    public iStatement deepCopy()
    {
        return new compoundStatement(one.deepCopy(), two.deepCopy());
    }

    @Override
    public iDictionary<String, iType> typeCheck(iDictionary<String, iType> typeEnvironment) throws EvaluationException
    {
        return two.typeCheck(one.typeCheck(typeEnvironment));
    }

}
