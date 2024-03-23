package gui.a7.Model.Statements;

import gui.a7.Model.Exceptions.EvaluationException;
import gui.a7.Model.Exceptions.ExecutionException;
import gui.a7.Model.State.programState;
import gui.a7.Model.Structures.iDictionary;
import gui.a7.Model.Structures.iHeap;
import gui.a7.Model.Types.iType;
import gui.a7.Model.Values.iValue;
import gui.a7.Model.Expressions.iExpression;


public class printStatement implements iStatement
{
    private iExpression expression;

    public printStatement(iExpression expression)
    {
        this.expression = expression;
    }

    ///TODO: check if this is correct
    @Override
    public programState execute(programState state) throws ExecutionException, EvaluationException
    {
        iDictionary<String, iValue> symbolTable = state.getSymbolTable();
        iHeap<Integer, iValue> heap = state.getHeap();
        iValue value = expression.evaluate(symbolTable, heap);
        state.getOutput().append(value);
        return  null;
    }

    @Override
    public String toString()
    {
        return "print(" + expression.toString() + ")";
    }

    @Override
    public iStatement deepCopy()
    {
        return new printStatement(expression.deepCopy());
    }

    @Override
    public iDictionary<String, iType> typeCheck(iDictionary<String, iType> typeEnvironment) throws EvaluationException
    {
        expression.typeCheck(typeEnvironment);
        return typeEnvironment;
    }
}
