//package gui.a7.View;
//
//import gui.a7.Model.Exceptions.EvaluationException;
//import gui.a7.Model.Expressions.*;
//import gui.a7.Model.State.programState;
//import gui.a7.Model.Statements.*;
//import gui.a7.Model.Structures.*;
//import gui.a7.Model.Types.*;
//import gui.a7.Model.Values.boolValue;
//import gui.a7.Model.Values.iValue;
//import gui.a7.Model.Values.intValue;
//import gui.a7.Model.Values.stringValue;
//import gui.a7.Repository.iRepository;
//import gui.a7.Repository.memoryRepository;
//import gui.a7.Controller.Controller;
//
//import java.io.BufferedReader;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//
//public class Interpreter
//{
//    public static void main(String[] args) throws EvaluationException
//    {
//        iStatement ex1 = new compoundStatement(new variableDeclaration("v", new intType()),
//                new compoundStatement(new assignmentStatement("v", new valueExpression(new intValue(2))),
//                        new printStatement(new variableExpression("v"))));
//
//        try
//        {
//            ex1.typeCheck(new adtDictionary<String, iType>());
//        }
//        catch (EvaluationException e)
//        {
//            System.out.println(e.getMessage());
//            System.exit(0);
//        }
//        iStack<iStatement> executionStack1 = new adtStack<>();
//        iList<iValue> output1 = new adtList<>();
//        iDictionary<String, iValue> symbolTable1 = new adtDictionary<>();
//        iDictionary<String, BufferedReader> fileTable1 = new adtDictionary<>();
//        iHeap<Integer, iValue> heap1 = new adtHeap<Integer, iValue>(new HashMap<Integer, iValue>());
//        programState state1 = new programState(executionStack1, symbolTable1, output1, ex1, fileTable1, heap1);
//        List<programState> programStateList1 = new ArrayList<programState>();
//        programStateList1.add(state1);
//        String logFilePath1 = "log1.txt";
//        iRepository repository1 = new memoryRepository(programStateList1, logFilePath1);
//        Controller controller1 = new Controller(repository1);
//
//
//        iStatement ex2 = new compoundStatement(new variableDeclaration("a", new intType()),
//                new compoundStatement(new variableDeclaration("b", new intType()),
//                        new compoundStatement(
//                                new assignmentStatement("a",
//                                        new arithmeticExpression('+', new valueExpression(new intValue(2)),
//                                                new arithmeticExpression('*', new valueExpression(new intValue(3)),
//                                                        new valueExpression(new intValue(5))))),
//                                new compoundStatement(
//                                        new assignmentStatement("b",
//                                                new arithmeticExpression('+', new variableExpression("a"), new valueExpression(new intValue(1)))),
//                                        new printStatement(new variableExpression("b"))))));
//
//        try
//        {
//            ex2.typeCheck(new adtDictionary<String, iType>());
//        }
//        catch (EvaluationException e)
//        {
//            System.out.println(e.getMessage());
//            System.exit(0);
//        }
//
//        iStack<iStatement> executionStack2 = new adtStack<>();
//        iList<iValue> output2 = new adtList<>();
//        iDictionary<String, iValue> symbolTable2 = new adtDictionary<>();
//        iDictionary<String, BufferedReader> fileTable2 = new adtDictionary<>();
//        iHeap<Integer, iValue> heap2 = new adtHeap<Integer, iValue>(new HashMap<Integer, iValue>());
//        programState state2 = new programState(executionStack2, symbolTable2, output2, ex2, fileTable2, heap2);
//        List<programState> programStateList2 = new ArrayList<programState>();
//        programStateList2.add(state2);
//        String logFilePath2 = "log2.txt";
//        iRepository repository2 = new memoryRepository(programStateList2, logFilePath2);
//        Controller controller2 = new Controller(repository2);
//
//
//        iStatement ex3 = new compoundStatement(new variableDeclaration("a", new boolType()),
//                new compoundStatement(new variableDeclaration("v", new intType()),
//                        new compoundStatement(new assignmentStatement("a", new valueExpression(new boolValue(true))),
//                                new compoundStatement(new ifStatement(new variableExpression("a"), new assignmentStatement("v",
//                                        new valueExpression(new intValue(2))), new assignmentStatement("v", new valueExpression(new intValue(3)))),
//                                        new printStatement(new variableExpression("v"))))));
//
//        try
//        {
//            ex3.typeCheck(new adtDictionary<String, iType>());
//        }
//        catch (EvaluationException e)
//        {
//            System.out.println(e.getMessage());
//            System.exit(0);
//        }
//
//        iStack<iStatement> executionStack3 = new adtStack<>();
//        iList<iValue> output3 = new adtList<>();
//        iDictionary<String, iValue> symbolTable3 = new adtDictionary<>();
//        iDictionary<String, BufferedReader> fileTable3 = new adtDictionary<>();
//        iHeap<Integer, iValue> heap3 = new adtHeap<Integer, iValue>(new HashMap<Integer, iValue>());
//        programState state3 = new programState(executionStack3, symbolTable3, output3, ex3, fileTable3, heap3);
//        List<programState> programStateList3 = new ArrayList<programState>();
//        programStateList3.add(state3);
//        String logFilePath3 = "log3.txt";
//        iRepository repository3 = new memoryRepository(programStateList3, logFilePath3);
//        Controller controller3 = new Controller(repository3);
//
//
//        iStatement ex4 = new compoundStatement(new variableDeclaration("varf", new stringType()),
//                new compoundStatement(new assignmentStatement("varf", new valueExpression(new stringValue("test.in"))),
//                        new compoundStatement(new openRFile(new variableExpression("varf")),
//                                new compoundStatement(new variableDeclaration("varc", new intType()),
//                                        new compoundStatement(new readFile(new variableExpression("varf"), new variableExpression("varc")),
//                                                new compoundStatement(new printStatement(new variableExpression("varc")),
//                                                        new compoundStatement(new readFile(new variableExpression("varf"), new variableExpression("varc")),
//                                                                new compoundStatement(new printStatement(new variableExpression("varc")),
//                                                                        new closeRFile(new variableExpression("varf"))))))))));
//
//        try
//        {
//            ex4.typeCheck(new adtDictionary<String, iType>());
//        }
//        catch (EvaluationException e)
//        {
//            System.out.println(e.getMessage());
//            System.exit(0);
//        }
//
//        iStack<iStatement> executionStack4 = new adtStack<>();
//        iList<iValue> output4 = new adtList<>();
//        iDictionary<String, iValue> symbolTable4 = new adtDictionary<>();
//        iDictionary<String, BufferedReader> fileTable4 = new adtDictionary<>();
//        iHeap<Integer, iValue> heap4 = new adtHeap<Integer, iValue>(new HashMap<Integer, iValue>());
//        programState state4 = new programState(executionStack4, symbolTable4, output4, ex4, fileTable4, heap4);
//        List<programState> programStateList4 = new ArrayList<programState>();
//        programStateList4.add(state4);
//        String logFilePath4 = "log4.txt";
//        iRepository repository4 = new memoryRepository(programStateList4, logFilePath4);
//        Controller controller4 = new Controller(repository4);
//
//
//       iStatement ex5 = new compoundStatement(new variableDeclaration("a", new intType()),
//                new compoundStatement(new variableDeclaration("b", new intType()),
//                        new compoundStatement(new assignmentStatement("a", new valueExpression(new intValue(2))),
//                                new compoundStatement(new ifStatement(new relationalExpression(new variableExpression("a"), new valueExpression(new intValue(3)), "<"), new assignmentStatement("b",
//                                        new valueExpression(new intValue(10))), new assignmentStatement("b", new valueExpression(new intValue(5)))),
//                                        new printStatement(new variableExpression("b"))))));
//
//        try
//        {
//            ex5.typeCheck(new adtDictionary<String, iType>());
//        }
//        catch (EvaluationException e)
//        {
//            System.out.println(e.getMessage());
//            System.exit(0);
//        }
//
//        iStack<iStatement> executionStack5 = new adtStack<>();
//        iList<iValue> output5 = new adtList<>();
//        iDictionary<String, iValue> symbolTable5 = new adtDictionary<>();
//        iDictionary<String, BufferedReader> fileTable5 = new adtDictionary<>();
//        iHeap<Integer, iValue> heap5 = new adtHeap<Integer, iValue>(new HashMap<Integer, iValue>());
//        programState state5 = new programState(executionStack5, symbolTable5, output5, ex5, fileTable5, heap5);
//        List<programState> programStateList5 = new ArrayList<programState>();
//        programStateList5.add(state5);
//        String logFilePath5 = "log5.txt";
//        iRepository repository5 = new memoryRepository(programStateList5, logFilePath5);
//        Controller controller5 = new Controller(repository5);
//
//
//    iStatement ex6 = new compoundStatement(new variableDeclaration("v", new refType(new intType())),
//            new compoundStatement(new newHeapStatement("v", new valueExpression(new intValue(20))),
//                    new compoundStatement(new variableDeclaration("a", new refType(new refType(new intType()))),
//                            new compoundStatement(new newHeapStatement("a", new variableExpression("v")),
//                                    new compoundStatement(new newHeapStatement("v", new valueExpression(new intValue(30))),
//                                            new compoundStatement(new printStatement(new variableExpression("v")),
//                                                    new compoundStatement(new printStatement(new readHeapExpression(new variableExpression("v"))),
//                                                            new compoundStatement(new printStatement(new variableExpression("a")),
//                                                                    new compoundStatement(new printStatement(new readHeapExpression(new variableExpression("a"))),
//                                                                            new printStatement(new readHeapExpression(new readHeapExpression(new variableExpression("a")))))))))))));
//
//        try
//        {
//            ex6.typeCheck(new adtDictionary<String, iType>());
//        }
//        catch (EvaluationException e)
//        {
//            System.out.println(e.getMessage());
//            System.exit(0);
//        }
//
//        iStack<iStatement> executionStack6 = new adtStack<>();
//        iList<iValue> output6 = new adtList<>();
//        iDictionary<String, iValue> symbolTable6 = new adtDictionary<>();
//        iDictionary<String, BufferedReader> fileTable6 = new adtDictionary<>();
//        iHeap<Integer, iValue> heap6 = new adtHeap<Integer, iValue>(new HashMap<Integer, iValue>());
//        programState state6 = new programState(executionStack6, symbolTable6, output6, ex6, fileTable6, heap6);
//        List<programState> programStateList6 = new ArrayList<programState>();
//        programStateList6.add(state6);
//        String logFilePath6 = "log6.txt";
//        iRepository repository6 = new memoryRepository(programStateList6, logFilePath6);
//        Controller controller6 = new Controller(repository6);
//
//        //  int v; v=4; (while (v>0) print(v);v=v-1);print(v)
//
//        iStatement ex7 = new compoundStatement(new variableDeclaration("v", new intType()),
//                new compoundStatement(new assignmentStatement("v", new valueExpression(new intValue(4))),
//                        new compoundStatement(new whileStatement(new relationalExpression(new variableExpression("v"), new valueExpression(new intValue(0)), ">"),
//                                new compoundStatement(new printStatement(new variableExpression("v")),
//                                        new assignmentStatement("v", new arithmeticExpression('-', new variableExpression("v"), new valueExpression(new intValue(1)))))),
//                                new printStatement(new variableExpression("v")))));
//
//        try
//        {
//            ex7.typeCheck(new adtDictionary<String, iType>());
//        }
//        catch (EvaluationException e)
//        {
//            System.out.println(e.getMessage());
//            System.exit(0);
//        }
//
//        iStack<iStatement> executionStack7 = new adtStack<>();
//        iList<iValue> output7 = new adtList<>();
//        iDictionary<String, iValue> symbolTable7 = new adtDictionary<>();
//        iDictionary<String, BufferedReader> fileTable7 = new adtDictionary<>();
//        iHeap<Integer, iValue> heap7 = new adtHeap<Integer, iValue>(new HashMap<Integer, iValue>());
//        programState state7 = new programState(executionStack7, symbolTable7, output7, ex7, fileTable7, heap7);
//        List<programState> programStateList7 = new ArrayList<programState>();
//        programStateList7.add(state7);
//        String logFilePath7 = "log7.txt";
//        iRepository repository7 = new memoryRepository(programStateList7, logFilePath7);
//        Controller controller7 = new Controller(repository7);
//
//
//        iStatement ex8 = new compoundStatement(new variableDeclaration("v", new intType()),
//                new compoundStatement(new assignmentStatement("v", new valueExpression(new intValue(9))),
//                        new compoundStatement(new variableDeclaration("a", new refType(new intType())),
//                                new compoundStatement(new newHeapStatement("a", new valueExpression(new intValue(11))),
//                                        new compoundStatement(new forkStatement(new compoundStatement(new heapWriteStatement("a", new valueExpression(new intValue(12))),
//                                                new compoundStatement(new assignmentStatement("v", new valueExpression(new intValue(10))),
//                                                        new compoundStatement(new printStatement(new variableExpression("v")),
//                                                                new printStatement(new readHeapExpression(new variableExpression("a"))))))),
//                                                new compoundStatement(new printStatement(new variableExpression("v")),
//                                                        new compoundStatement(new printStatement(new readHeapExpression(new variableExpression("a"))),
//                                                                new noOperationStatement())))))));
//
//        try
//        {
//            ex8.typeCheck(new adtDictionary<String, iType>());
//        }
//        catch (EvaluationException e)
//        {
//            System.out.println(e.getMessage());
//            System.exit(0);
//        }
//
//        iStack<iStatement> executionStack8 = new adtStack<>();
//        iList<iValue> output8 = new adtList<>();
//        iDictionary<String, iValue> symbolTable8 = new adtDictionary<>();
//        iDictionary<String, BufferedReader> fileTable8 = new adtDictionary<>();
//        iHeap<Integer, iValue> heap8 = new adtHeap<Integer, iValue>(new HashMap<Integer, iValue>());
//        programState state8 = new programState(executionStack8, symbolTable8, output8, ex8, fileTable8, heap8);
//        List<programState> programStateList8 = new ArrayList<programState>();
//        programStateList8.add(state8);
//        String logFilePath8 = "log8.txt";
//        iRepository repository8 = new memoryRepository(programStateList8, logFilePath8);
//        Controller controller8 = new Controller(repository8);
//
//
//
//
//        //cleaning the log files
//        try
//        {
//            repository1.clearLogFile();
//            repository2.clearLogFile();
//            repository3.clearLogFile();
//            repository4.clearLogFile();
//            repository5.clearLogFile();
//            repository6.clearLogFile();
//            repository7.clearLogFile();
//            repository8.clearLogFile();
//        }
//        catch (Exception e)
//        {
//            System.out.println(e.getMessage());
//        }
//
//        textMenu menu = new textMenu();
//        menu.addCommand(new exitCommand("0", "exit"));
//        menu.addCommand(new runExample("1", ex1.toString(), controller1));
//        menu.addCommand(new runExample("2", ex2.toString(), controller2));
//        menu.addCommand(new runExample("3", ex3.toString(), controller3));
//        menu.addCommand(new runExample("4", ex4.toString(), controller4));
//        menu.addCommand(new runExample("5", ex5.toString(), controller5));
//        menu.addCommand(new runExample("6", ex6.toString(), controller6));
//        menu.addCommand(new runExample("7", ex7.toString(), controller7));
//        menu.addCommand(new runExample("8", ex8.toString(), controller8));
//        menu.show();
//    }
//}
