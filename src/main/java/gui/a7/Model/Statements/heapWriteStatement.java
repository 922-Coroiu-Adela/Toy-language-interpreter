package gui.a7.Model.Statements;

import gui.a7.Model.Exceptions.EvaluationException;
import gui.a7.Model.Exceptions.MyException;
import gui.a7.Model.Expressions.iExpression;
import gui.a7.Model.State.programState;
import gui.a7.Model.Structures.iDictionary;
import gui.a7.Model.Types.iType;
import gui.a7.Model.Types.refType;
import gui.a7.Model.Values.iValue;
import gui.a7.Model.Values.refValue;

public class heapWriteStatement implements iStatement
{
    private String var_name;
    private iExpression expression;

    public heapWriteStatement(String var_name, iExpression expression)
    {
        this.var_name = var_name;
        this.expression = expression;
    }

    @Override
    public String toString()
    {
        return "writeHeap(" + var_name + ", " + expression.toString() + ");";
    }

    @Override
    public iStatement deepCopy()
    {
        return new heapWriteStatement(var_name, expression.deepCopy());
    }

    @Override
    public iDictionary<String, iType> typeCheck(iDictionary<String, iType> typeEnvironment) throws EvaluationException
    {
        iType typeVariable = typeEnvironment.getValue(var_name);
        iType typeExpression = expression.typeCheck(typeEnvironment);
        if (typeVariable.equals(new refType(typeExpression)))
            return typeEnvironment;
        else
            throw new EvaluationException("WRITE HEAP: right hand side and left hand side have different types!");
    }

    @Override
    public programState execute(programState state) throws EvaluationException
    {
        if (!state.getSymbolTable().isDefined(var_name))
            throw new EvaluationException("<WRITE_HEAP_STMT> Variable is not defined!");

        iType typeVariable = state.getSymbolTable().getValue(var_name).getType();
        if (!(typeVariable instanceof refType))
            throw new EvaluationException("<WRITE_HEAP_STMT> Variable is not of type reference!");

        refValue ref_value = (refValue) state.getSymbolTable().getValue(var_name);
        int address = ref_value.getAddr();
        if (!state.getHeap().isDefined(address))
            throw new EvaluationException("<WRITE_HEAP_STMT> Address is not defined in heap!");

        iValue expression_value = expression.evaluate(state.getSymbolTable(), state.getHeap());
        if (!expression_value.getType().equals(ref_value.getInner()))
            throw new EvaluationException("<WRITE_HEAP_STMT> Type of expression and type of variable do not match!");
        try {
            state.getHeap().update(address, expression_value);
        } catch (MyException e) {
            throw new RuntimeException(e);
        }

        return null;
    }
}
