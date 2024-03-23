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

public class newHeapStatement implements iStatement
{
    String var_name;
    iExpression exp;

    public newHeapStatement(String var_name, iExpression exp) {
        this.var_name = var_name;
        this.exp = exp;
    }

    @Override
    public String toString() {
        return "new(" + var_name + ", " + exp.toString() + ");";
    }

    @Override
    public programState execute(programState state) throws EvaluationException {
        iValue val = state.getSymbolTable().getValue(var_name);
        if (!(val.getType() instanceof refType RefVal))
            throw new EvaluationException("<NEW_HEAP_STMT> Variable is not of type reference!");

        iValue val_exp = exp.evaluate(state.getSymbolTable(), state.getHeap());

        if (!val_exp.getType().equals(RefVal.getInner()))
            throw new EvaluationException("<NEW_HEAP_STMT> Type of expression and type of variable do not match!");

        int addr = state.getHeap().put(val_exp);
        state.getSymbolTable().update(var_name, new refValue(addr, val_exp.getType()));

        return null;
    }

    @Override
    public iStatement deepCopy() {
        return new newHeapStatement(var_name, exp.deepCopy());
    }

    @Override
    public iDictionary<String, iType> typeCheck(iDictionary<String, iType> typeEnvironment) throws EvaluationException
    {
        iType typeVariable = typeEnvironment.getValue(var_name);
        iType typeExpression = exp.typeCheck(typeEnvironment);
        if (typeVariable.equals(new refType(typeExpression)))
            return typeEnvironment;
        else
            throw new EvaluationException("NEW HEAP: right hand side and left hand side have different types!");
    }
}
