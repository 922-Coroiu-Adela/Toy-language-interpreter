package gui.a7.Repository;

import gui.a7.Model.Exceptions.MyException;
import gui.a7.Model.State.programState;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.List;

public class memoryRepository implements iRepository
{
    List<programState> programStateList;
    String logFilePath;

    public memoryRepository(List<programState> progList, String logFilePath)
    {
        this.programStateList = progList;
        this.logFilePath = logFilePath;

    }

    @Override
    public void setProgramStateList(List<programState> state)
    {
        this.programStateList = state;
    }

    @Override
    public List<programState> getProgramStateList() throws MyException
    {
        return this.programStateList;
    }

    @Override
    public void logProgramStateExecution(programState prgstate) throws MyException
    {
        PrintWriter logFile = null;
        try
        {
            logFile = new PrintWriter(new BufferedWriter(new FileWriter(logFilePath, true)));
            logFile.println(prgstate.toString());
        }
        catch (Exception exception)
        {
            throw new MyException(exception.getMessage());
        }
        finally
        {
            if (logFile != null)
                logFile.close();
        }
    }

    public void clearLogFile() throws MyException
    {
        PrintWriter logFile = null;
        try
        {
            logFile = new PrintWriter(new BufferedWriter(new FileWriter(logFilePath, false)));
            logFile.println("");

        }
        catch (Exception exception)
        {
            throw new MyException(exception.getMessage());
        }
        finally
        {
            if (logFile != null)
                logFile.close();
        }
    }



}
