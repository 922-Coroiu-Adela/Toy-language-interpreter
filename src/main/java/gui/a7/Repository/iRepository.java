package gui.a7.Repository;

import gui.a7.Model.Exceptions.MyException;
import gui.a7.Model.State.programState;
import java.util.List;

public interface iRepository
{
    void setProgramStateList(List<programState> state);
    List<programState> getProgramStateList() throws MyException;
    void logProgramStateExecution(programState prgstate) throws MyException;
    void clearLogFile() throws MyException;
}
