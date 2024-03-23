package gui.a7.Controller;

import gui.a7.Model.Exceptions.MyException;
import gui.a7.Model.State.programState;

public interface iController
{

    void executeAll(programState state) throws MyException;

    programState getCurrentProgramState() throws MyException;

}
