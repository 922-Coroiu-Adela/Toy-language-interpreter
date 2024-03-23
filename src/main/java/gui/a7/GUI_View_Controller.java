package gui.a7;

import gui.a7.Controller.Controller;
import gui.a7.Model.Exceptions.MyException;
import gui.a7.Model.State.programState;
import gui.a7.Model.Statements.iStatement;
import gui.a7.Model.Structures.iDictionary;
import gui.a7.Model.Structures.iStack;
import gui.a7.Model.Values.iValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.util.Pair;

import java.io.BufferedReader;
import java.util.List;
import java.util.Map;

public class GUI_View_Controller
{
    public Label programTitle;
    public Label programCount;
    public Button stepButton;
    public Button runButton;
    public ListView<String> programsList;
    public TableView<TableEntry> heapTable;
    public TableColumn<TableEntry, String> heapAddressColumn;
    public TableColumn<TableEntry, String> heapValueColumn;
    public TableView<TableEntry> symbolTable;
    public TableColumn<TableEntry, String> symbolVariableColumn;
    public TableColumn<TableEntry, String> symbolValueColumn;
    public ListView<iValue> outputList;
    public TableView<TableEntry> fileTable;
    public TableColumn<TableEntry, String> fileNameColumn;
    public TableColumn<TableEntry, String> fileReaderColumn;
    public TableView<TripleTableEntry> semaphoreTable;
    public TableColumn<TripleTableEntry, String> semaphoreIndexColumn;
    public TableColumn<TripleTableEntry, String> semaphoreValueColumn;
    public TableColumn<TripleTableEntry, String> semaphoreListColumn;
    public ListView<iStatement> executionStackList;
    private Controller controller;
    private int index;
    private iStack<iStatement> currentStack;
    private iDictionary<String, iValue> currentTable;

    public GUI_View_Controller(Controller controller, int index)
    {
        this.controller = controller;
        this.index = index;
    }

    public void initialize() throws MyException
    {
        programTitle.setText("Program " + index);
        programCount.setText("Currently running threads: " + controller.getRunningPrgCount());
        heapAddressColumn.setCellValueFactory(cellData -> cellData.getValue().getKey());
        heapValueColumn.setCellValueFactory(cellData -> cellData.getValue().getValue());
        fileNameColumn.setCellValueFactory(cellData -> cellData.getValue().getKey());
        fileReaderColumn.setCellValueFactory(cellData -> cellData.getValue().getValue());
        symbolVariableColumn.setCellValueFactory(cellData -> cellData.getValue().getKey());
        symbolValueColumn.setCellValueFactory(cellData -> cellData.getValue().getValue());
        semaphoreIndexColumn.setCellValueFactory(cellData -> cellData.getValue().getKey());
        semaphoreValueColumn.setCellValueFactory(cellData -> cellData.getValue().getValue());
        semaphoreListColumn.setCellValueFactory(cellData -> cellData.getValue().getList());
        setProgramView();

        programsList.getSelectionModel().selectFirst();
        programsList.fireEvent(new javafx.scene.input.MouseEvent(javafx.scene.input.MouseEvent.MOUSE_CLICKED, 0, 0,
                0, 0, javafx.scene.input.MouseButton.PRIMARY, 1, true, true, true, true, true,
                true, true, true, true, true, null));
    }

    private void setProgramView() throws MyException
    {
        ObservableList<String> data = FXCollections.observableArrayList();
        for(programState program: this.controller.getRepository().getProgramStateList())
            data.add("Program " + program.getId());
        this.programsList.setItems(data);
    }

    public void oneStep(MouseEvent mouseEvent) throws MyException
    {
        List<programState> programList = controller.removeCompletedPrograms(controller.getRepository().getProgramStateList());
        controller.removeCompletedPrograms(controller.getRepository().getProgramStateList());
        try
        {
            if (!programList.isEmpty())
            {
                List<programState> finalProgramList = programList;
                programList.forEach(prg -> prg.getHeap().set(controller.safeGarbageCollector(finalProgramList, prg.getHeap().getContent())));
                controller.oneStepForAllPrograms(programList);
                controller.removeCompletedPrograms(controller.getRepository().getProgramStateList());
                programCount.setText("Currently running threads: " + controller.getRunningPrgCount());

                setHeapView();
                setListView();
                setFileView();
                setSemaphoreTable();
                setProgramView();
                setSymbolTableView(this.currentTable);
                setExecutionStack(this.currentStack);

            }
            else
            {
                this.controller.getRepository().setProgramStateList(programList);
                this.programCount.setText("Currently running threads: 0");
                this.stepButton.setVisible(false);
                this.runButton.setVisible(false);
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    private void setHeapView() throws MyException
    {
        ObservableList<TableEntry> heapData = FXCollections.observableArrayList();
        for (Map.Entry<Integer, iValue> entry : this.controller.getCurrentProgramState().getHeap().getContent().entrySet()) {
            heapData.add(new TableEntry(entry.getKey().toString(), entry.getValue().toString()));
        }
        this.heapTable.setItems(heapData);
    }

    private void setSemaphoreTable() throws MyException
    {
        ObservableList<TripleTableEntry> semaphoreData = FXCollections.observableArrayList();
        for (Map.Entry<Integer, Pair<Integer, List<Integer>>> entry : this.controller.getCurrentProgramState().getCountSemaphoreTable().getContent().entrySet())
        {
            semaphoreData.add(new TripleTableEntry(entry.getKey().toString(), entry.getValue().getKey().toString(), entry.getValue().getValue().toString()));
        }
        this.semaphoreTable.setItems(semaphoreData);
    }

    private void setListView() throws MyException {
        ObservableList<iValue> data = FXCollections.observableArrayList();
        data.addAll(this.controller.getCurrentProgramState().getOutput().getList());
        this.outputList.setItems(data);
    }

    private void setFileView() throws MyException
    {
        ObservableList<TableEntry> fileData = FXCollections.observableArrayList();
        for (Map.Entry<String, BufferedReader> entry : this.controller.getCurrentProgramState().getFileTable().getContent().entrySet())
        {
            fileData.add(new TableEntry(entry.getKey(), entry.getValue().toString()));
        }
        this.fileTable.setItems(fileData);
    }


    private void setSymbolTableView(iDictionary<String, iValue> currentTable)
    {
        ObservableList<TableEntry> symTableData = FXCollections.observableArrayList();
        for (Map.Entry<String, iValue> entry : currentTable.getContent().entrySet())
        {
            symTableData.add(new TableEntry(entry.getKey(), entry.getValue().toString()));
        }
        this.symbolTable.setItems(symTableData);
    }

    private void setExecutionStack(iStack<iStatement> currentStack) {
        ObservableList<iStatement> exeStackData = FXCollections.observableArrayList();
        exeStackData.addAll(currentStack.getStack());
        this.executionStackList.setItems(exeStackData);
    }

    public void allStep(MouseEvent mouseEvent) {
        try
        {
            List<programState> programList = this.controller.removeCompletedPrograms(this.controller.getRepository().getProgramStateList());
            while(!programList.isEmpty()) {
                this.oneStep(mouseEvent);
                programList = this.controller.getRepository().getProgramStateList();
            }
            this.programCount.setText("Currently running threads: 0");
            this.stepButton.setVisible(false);
            this.runButton.setVisible(false);
        }
        catch (Exception e)
        {
            System.out.println("All step error: " + e.getMessage());
        }
    }

    public void programSelected(MouseEvent mouseEvent) throws MyException
    {
        String selectedProgram = programsList.getSelectionModel().getSelectedItem().split(" ")[1];
        for(programState program: this.controller.getRepository().getProgramStateList()) {
            if(Integer.parseInt(selectedProgram) == program.getId()) {
                this.currentTable = program.getSymbolTable();
                this.currentStack = program.getExecutionStack();
                setSymbolTableView(program.getSymbolTable());
                setExecutionStack(program.getExecutionStack());
                break;
            }
        }
    }

}
