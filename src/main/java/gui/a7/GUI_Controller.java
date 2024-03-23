package gui.a7;

import gui.a7.Controller.Controller;
import gui.a7.Model.Exceptions.EvaluationException;
import gui.a7.Model.Expressions.*;
import gui.a7.Model.State.programState;
import gui.a7.Model.Statements.*;
import gui.a7.Model.Structures.*;
import gui.a7.Model.Types.*;
import gui.a7.Model.Values.boolValue;
import gui.a7.Model.Values.iValue;
import gui.a7.Model.Values.intValue;
import gui.a7.Model.Values.stringValue;
import gui.a7.Repository.iRepository;
import gui.a7.Repository.memoryRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.Scene;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GUI_Controller
{
    @FXML
    protected ListView<iStatement> programsList;

    public void initialize()
    {
        ObservableList<iStatement> programs = getPrograms();
        programsList.setItems(programs);
    }

    private ObservableList<iStatement> getPrograms()
    {

        ArrayList<iStatement> programs = new ArrayList<>();

        iStatement ex1 = new compoundStatement(new variableDeclaration("v", new intType()),
                new compoundStatement(new assignmentStatement("v", new valueExpression(new intValue(2))),
                        new printStatement(new variableExpression("v"))));
        programs.add(ex1);


        iStatement ex2 = new compoundStatement(new variableDeclaration("a", new intType()),
                new compoundStatement(new variableDeclaration("b", new intType()),
                        new compoundStatement(
                                new assignmentStatement("a",
                                        new arithmeticExpression('+', new valueExpression(new intValue(2)),
                                                new arithmeticExpression('*', new valueExpression(new intValue(3)),
                                                        new valueExpression(new intValue(5))))),
                                new compoundStatement(
                                        new assignmentStatement("b",
                                                new arithmeticExpression('+', new variableExpression("a"), new valueExpression(new intValue(1)))),
                                        new printStatement(new variableExpression("b"))))));
        programs.add(ex2);


        iStatement ex3 = new compoundStatement(new variableDeclaration("a", new boolType()),
                new compoundStatement(new variableDeclaration("v", new intType()),
                        new compoundStatement(new assignmentStatement("a", new valueExpression(new boolValue(true))),
                                new compoundStatement(new ifStatement(new variableExpression("a"), new assignmentStatement("v",
                                        new valueExpression(new intValue(2))), new assignmentStatement("v", new valueExpression(new intValue(3)))),
                                        new printStatement(new variableExpression("v"))))));
        programs.add(ex3);


        iStatement ex4 = new compoundStatement(new variableDeclaration("varf", new stringType()),
                new compoundStatement(new assignmentStatement("varf", new valueExpression(new stringValue("test.in"))),
                        new compoundStatement(new openRFile(new variableExpression("varf")),
                                new compoundStatement(new variableDeclaration("varc", new intType()),
                                        new compoundStatement(new readFile(new variableExpression("varf"), new variableExpression("varc")),
                                                new compoundStatement(new printStatement(new variableExpression("varc")),
                                                        new compoundStatement(new readFile(new variableExpression("varf"), new variableExpression("varc")),
                                                                new compoundStatement(new printStatement(new variableExpression("varc")),
                                                                        new closeRFile(new variableExpression("varf"))))))))));
        programs.add(ex4);


        iStatement ex5 = new compoundStatement(new variableDeclaration("a", new intType()),
                new compoundStatement(new variableDeclaration("b", new intType()),
                        new compoundStatement(new assignmentStatement("a", new valueExpression(new intValue(2))),
                                new compoundStatement(new ifStatement(new relationalExpression(new variableExpression("a"), new valueExpression(new intValue(3)), "<"), new assignmentStatement("b",
                                        new valueExpression(new intValue(10))), new assignmentStatement("b", new valueExpression(new intValue(5)))),
                                        new printStatement(new variableExpression("b"))))));

        programs.add(ex5);


        iStatement ex6 = new compoundStatement(new variableDeclaration("v", new refType(new intType())),
                new compoundStatement(new newHeapStatement("v", new valueExpression(new intValue(20))),
                        new compoundStatement(new variableDeclaration("a", new refType(new refType(new intType()))),
                                new compoundStatement(new newHeapStatement("a", new variableExpression("v")),
                                        new compoundStatement(new newHeapStatement("v", new valueExpression(new intValue(30))),
                                                new compoundStatement(new printStatement(new variableExpression("v")),
                                                        new compoundStatement(new printStatement(new readHeapExpression(new variableExpression("v"))),
                                                                new compoundStatement(new printStatement(new variableExpression("a")),
                                                                        new compoundStatement(new printStatement(new readHeapExpression(new variableExpression("a"))),
                                                                                new printStatement(new readHeapExpression(new readHeapExpression(new variableExpression("a")))))))))))));
        programs.add(ex6);


        iStatement ex7 = new compoundStatement(new variableDeclaration("v", new intType()),
                new compoundStatement(new assignmentStatement("v", new valueExpression(new intValue(4))),
                        new compoundStatement(new whileStatement(new relationalExpression(new variableExpression("v"), new valueExpression(new intValue(0)), ">"),
                                new compoundStatement(new printStatement(new variableExpression("v")),
                                        new assignmentStatement("v", new arithmeticExpression('-', new variableExpression("v"), new valueExpression(new intValue(1)))))),
                                new printStatement(new variableExpression("v")))));
        programs.add(ex7);


        iStatement ex8 = new compoundStatement(new variableDeclaration("v", new intType()),
                new compoundStatement(new assignmentStatement("v", new valueExpression(new intValue(9))),
                        new compoundStatement(new variableDeclaration("a", new refType(new intType())),
                                new compoundStatement(new newHeapStatement("a", new valueExpression(new intValue(11))),
                                        new compoundStatement(new forkStatement(new compoundStatement(new heapWriteStatement("a", new valueExpression(new intValue(12))),
                                                new compoundStatement(new assignmentStatement("v", new valueExpression(new intValue(10))),
                                                        new compoundStatement(new printStatement(new variableExpression("v")),
                                                                new printStatement(new readHeapExpression(new variableExpression("a"))))))),
                                                new compoundStatement(new printStatement(new variableExpression("v")),
                                                        new compoundStatement(new printStatement(new readHeapExpression(new variableExpression("a"))),
                                                                new noOperationStatement())))))));
        programs.add(ex8);

        iStatement ex9 =
                new compoundStatement(
                        new variableDeclaration("v1", new refType(new intType())),
                        new compoundStatement(new variableDeclaration("cnt", new intType()),
                                new compoundStatement(new newHeapStatement("v1", new valueExpression(new intValue(1))),
                                        new compoundStatement(new createSemaphoreStatement("cnt", new readHeapExpression(new variableExpression("v1"))),
                                                new compoundStatement(new forkStatement(
                                                                new compoundStatement(new acquireSemaphoreStatement("cnt"),
                                                                        new compoundStatement(new heapWriteStatement("v1", new arithmeticExpression('*', new readHeapExpression(new variableExpression("v1")), new valueExpression(new intValue(10)))),
                                                                                new compoundStatement(new printStatement(new readHeapExpression(new variableExpression("v1"))), new releaseSemaphoreStatement("cnt"))))),
                                                        new compoundStatement(new forkStatement(
                                                                        new compoundStatement(new acquireSemaphoreStatement("cnt"),
                                                                                new compoundStatement(new heapWriteStatement("v1", new arithmeticExpression('*', new readHeapExpression(new variableExpression("v1")), new valueExpression(new intValue(10)))),
                                                                                        new compoundStatement(new heapWriteStatement("v1", new arithmeticExpression('*', new readHeapExpression(new variableExpression("v1")), new valueExpression(new intValue(2)))),
                                                                                                new compoundStatement(new printStatement(new readHeapExpression(  new variableExpression("v1"))), new releaseSemaphoreStatement("cnt")))))),
                                                                new compoundStatement(new acquireSemaphoreStatement("cnt"),
                                                                        new compoundStatement(new printStatement(new arithmeticExpression('-', new readHeapExpression(new variableExpression("v1")), new valueExpression(new intValue(1)))), new releaseSemaphoreStatement("cnt")))))))));
        programs.add(ex9);

        return FXCollections.observableList(programs);
    }

    public void selectedProgram(MouseEvent mouseEvent) throws EvaluationException, IOException {
        iStatement selectedProgram = programsList.getSelectionModel().getSelectedItem();
        iDictionary<String, iType> typeEnvironment = new adtDictionary<>();
        try
        {
            selectedProgram.typeCheck(typeEnvironment);
        }
        catch (Exception e)
        {
            System.out.println("Typecheck error: " + e.getMessage());
        }
        int programId = programsList.getSelectionModel().getSelectedIndex();

        iStack<iStatement> executionStack = new adtStack<>();
        iDictionary<String, iValue> symbolTable = new adtDictionary<>();
        iHeap<Integer, iValue> heap = new adtHeap<>(new HashMap<Integer, iValue>());
        iDictionary<String, BufferedReader> fileTable = new adtDictionary<>();
        iList<iValue> output = new adtList<>();
        iSemaphore countSemaphoreTable = new adtCountSemaphore();
        programState program = new programState(executionStack, symbolTable, output, selectedProgram,fileTable, heap, countSemaphoreTable);
        List<programState> prgList = new ArrayList<>();
        prgList.add(program);
        iRepository repository = new memoryRepository(prgList, "log" + programId + ".txt");
        Controller controller = new Controller(repository);

        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("controller-view.fxml"));
        loader.setControllerFactory(param -> new GUI_View_Controller(controller, programId));
        URL url = getClass().getResource("styles.css");
        if (url == null)
        {
            System.out.println("Resource not found. Aborting.");
            System.exit(-1);
        }
        String css = url.toExternalForm();
        Scene scene = new Scene(loader.load(), 640, 480);
        scene.getStylesheets().add(css);
        stage.setTitle("Main Menu");
        stage.setScene(scene);
        stage.show();

        //System.out.println(controller.toString());
    }
}
