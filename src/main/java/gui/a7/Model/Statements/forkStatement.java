package gui.a7.Model.Statements;

import gui.a7.Model.Exceptions.EvaluationException;
import gui.a7.Model.State.programState;
import gui.a7.Model.Structures.*;
import gui.a7.Model.Types.iType;
import gui.a7.Model.Values.iValue;

import java.io.BufferedReader;
import java.util.Collection;
import java.util.Hashtable;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

public class forkStatement implements iStatement
{
    iStatement statement;

    public forkStatement(iStatement statement)
    {
        this.statement = statement;
    }

    @Override
    public String toString()
    {
        return "fork(" + statement.toString() + ")";
    }

    @Override
    public programState execute(programState state) throws EvaluationException {
        iHeap<Integer, iValue> heap = state.getHeap();
        iStack<iStatement> stack = new adtStack<>();
        iList<iValue> output = state.getOutput();
        ConcurrentMap<String, iValue> dict;
        dict = state.getSymbolTable().entrySet().stream().collect(Collectors.toConcurrentMap(Map.Entry::getKey, e -> e.getValue().deepCopy()));
        iDictionary<String, iValue> symbolTable = new adtDictionary<String, iValue>(dict);
        iDictionary<String, BufferedReader> fileTable = state.getFileTable();
        return new programState(stack, symbolTable, output, statement, fileTable, heap, state.getCountSemaphoreTable());

    }

    @Override
    public iStatement deepCopy()
    {
        return new forkStatement(statement.deepCopy());
    }

    @Override
    public iDictionary<String, iType> typeCheck(iDictionary<String, iType> typeEnvironment) throws EvaluationException
    {
        statement.typeCheck(typeEnvironment);
        return typeEnvironment;
    }
}
