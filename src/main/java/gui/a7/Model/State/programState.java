package gui.a7.Model.State;

import gui.a7.Model.Exceptions.EvaluationException;
import gui.a7.Model.Structures.*;
import gui.a7.Model.Types.iType;
import gui.a7.Model.Values.iValue;
import gui.a7.Model.Statements.iStatement;
import gui.a7.Model.Values.stringValue;

import java.io.BufferedReader;
import java.util.Collections;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class programState
{
    public iStack<iStatement> executionStack;
    public iDictionary<String, iValue> symbolTable;
    public iHeap<Integer, iValue> heap;
    public iList<iValue> output;

    public iDictionary<String, BufferedReader> fileTable;

    public iStatement originalProgram;

    public iSemaphore countSemaphoreTable;

    static public Lock countSemaphoreLock = new ReentrantLock();

    public static int idCounter = 0;

    public int id;

    public programState(iStack<iStatement> executionStack, iDictionary<String, iValue> symbolTable, iList<iValue> output, iStatement program, iDictionary<String, BufferedReader> fileTable, iHeap<Integer, iValue> heap, iSemaphore countSemaphoreTable) throws EvaluationException
    {
        incrementId();
        this.executionStack = executionStack;
        this.symbolTable = symbolTable;
        this.output = output;
        this.originalProgram = program.deepCopy();

        this.executionStack.push(program);
        this.fileTable = fileTable;
        this.countSemaphoreTable = countSemaphoreTable;
        this.heap = heap;
    }

    private synchronized void incrementId()
    {
        idCounter++;
        id = idCounter;
    }

    public boolean isNotCompleted()
    {
        return !executionStack.isEmpty();
    }

    public programState executeOneStep() throws Exception
    {
        if (executionStack.isEmpty())
            throw new Exception("Program state stack is empty!");

        iStatement currentStatement = executionStack.pop();
        return currentStatement.execute(this);
    }

    public int getId()
    {
        return id;
    }

    public iStatement getOriginalProgram()
    {
        return originalProgram;
    }

    public iStack<iStatement> getExecutionStack()
    {
        return executionStack;
    }

    public iDictionary<String, iValue> getSymbolTable()
    {
        return symbolTable;
    }

    public iList<iValue> getOutput()
    {
        return output;
    }

    public iDictionary<String, BufferedReader> getFileTable()
    {
        return fileTable;
    }

    public iHeap<Integer, iValue> getHeap()
    {
        return heap;
    }

    public void setExecutionStack(iStack<iStatement> executionStack)
    {
        this.executionStack = executionStack;
    }

    public void setSymbolTable(iDictionary<String, iValue> symbolTable)
    {
        this.symbolTable = symbolTable;
    }

    public void setOutput(iList<iValue> output)
    {
        this.output = output;
    }

    public void setOriginalProgram(iStatement originalProgram)
    {
        this.originalProgram = originalProgram;
    }

    public iSemaphore getCountSemaphoreTable()
    {
        return countSemaphoreTable;
    }

    @Override
    public String toString()
    {
        String return_string = "{\n";
        return_string += "ID: " + id + "\n";
        return_string += "Execution Stack:\n";
        return_string += executionStack.toString();
        return_string += "\nHeap:\n";
        return_string += heap.toString();
        return_string += "\nSymbol Table:\n";
        return_string += symbolTable.toString();
        return_string += "\nOutput:\n";
        return_string += output.toString();
        return_string += "\nFileTable:\n";
        return_string += fileTable.toString();
        return_string += "\nCountSemaphoreTable:\n";
        return_string += countSemaphoreTable.toString();
        return_string += "\n}\n";
        return return_string;
    }

    public void reset()
    {
        executionStack.clear();
        symbolTable.clear();
        output.clear();
        fileTable.clear();
        heap.clear();
        countSemaphoreTable.clear();
        iStatement program = originalProgram.deepCopy();
        executionStack.push(program);
    }

}
