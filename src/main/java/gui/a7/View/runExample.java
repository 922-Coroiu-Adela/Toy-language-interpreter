package gui.a7.View;

import gui.a7.Controller.Controller;
import gui.a7.Model.Exceptions.StackException;
import gui.a7.Model.State.programState;
import gui.a7.Model.Statements.iStatement;
import gui.a7.Model.Structures.*;
import gui.a7.Model.Values.iValue;
import gui.a7.Repository.iRepository;
import gui.a7.Repository.memoryRepository;

import java.io.BufferedReader;
import java.util.Deque;
import java.util.Iterator;

public class runExample extends Command
{

    private Controller controller;
    public runExample(String key, String desc, Controller controller)
    {
        super(key, desc);
        this.controller = controller;
    }

    @Override
    public void execute()
    {
        try
        {
            programState programState = controller.getCurrentProgramState();
            controller.executeAll(programState);
        }
        catch (Exception e)
        {

            System.out.println(e.getMessage());
        }

    }
}