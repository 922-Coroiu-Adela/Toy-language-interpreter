package gui.a7.Controller;

import gui.a7.Model.Exceptions.MyException;
import gui.a7.Model.Exceptions.ExecutionException;
import gui.a7.Model.Exceptions.EvaluationException;
import gui.a7.Model.Exceptions.StackException;
import gui.a7.Model.State.programState;
import gui.a7.Model.Types.refType;
import gui.a7.Model.Values.iValue;
import gui.a7.Model.Values.refValue;
import gui.a7.Repository.iRepository;
import gui.a7.Model.Structures.iStack;
import gui.a7.Model.Statements.iStatement;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.stream.Collectors;

public class Controller implements iController
{
    iRepository repository;
    int currentProgram = 0;
    ExecutorService executor = java.util.concurrent.Executors.newFixedThreadPool(2);

    public Controller(iRepository repository)
    {
        this.repository = repository;
    }

    public Map<Integer, iValue> safeGarbageCollector(List<programState> programStateList, Map<Integer,iValue> heap)
    {
        Collection<iValue> symTableValues = programStateList.stream()
                .flatMap(p->p.getSymbolTable().getContent().values().stream())
                .toList();

        List<Integer> symTableAddr = getAddrFromSymTable(symTableValues, heap);

        return heap.entrySet()
                .stream()
                .filter(e->symTableAddr.contains(e.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
//    List<Integer> getAddrFromSymTableFunctional(Collection<iValue> symTableValues)
//    {
//        return symTableValues.stream()
//                .filter(v-> v instanceof refValue)
//                .map(v-> {refValue v1 = (refValue)v; return v1.getAddr();})
//                .collect(Collectors.toList());
//    }

    List<Integer> getAddrFromSymTable(Collection<iValue> symTableValues, Map<Integer, iValue> heap)
    {
        List<Integer> addresses = new java.util.ArrayList<>();
        for (iValue value : symTableValues)
        {
            while (value instanceof refValue ref)
            {
                int address = ref.getAddr();
                addresses.add(address);
                value = heap.get(address);
            }
        }
        return addresses;
    }

    void log(List<programState> programList)
    {
        programList.forEach(program -> {
            try {
                repository.logProgramStateExecution(program);
            } catch (MyException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public void oneStepForAllPrograms(List<programState> programList) throws MyException
    {
        log(programList);
        List<Callable<programState>> callList = programList.stream()
                .map((program) -> (Callable<programState>) (program::executeOneStep))
                .collect(Collectors.toList());

        try
        {
            List<programState> newProgramList = executor.invokeAll(callList).stream()
                    .map(future -> {
                        try {
                            return future.get();
                        } catch (Exception exception) {
                            throw new RuntimeException(exception);
                        }
                    })
                    .filter(Objects::nonNull)
                    .toList();
            // add the new created threads to the list of existing threads
            programList.addAll(newProgramList);
        }
        catch (InterruptedException e)
        {
            throw new RuntimeException(e);
        }
        // after the execution, print the PrgState List into the log file
        programList.forEach(program -> {
            try {
                repository.logProgramStateExecution(program);
            } catch (MyException e) {
                throw new RuntimeException(e);
            }
        });
        // Save the current programs in the repository
        repository.setProgramStateList(programList);
    }

    public List<programState> removeCompletedPrograms(List<programState> programList)
    {
        return programList.stream()
                .filter(programState::isNotCompleted)
                .collect(Collectors.toList());
    }

    @Override
    public void executeAll(programState state) throws MyException
    {
        // remove the completed programs
        List<programState> programList = removeCompletedPrograms(repository.getProgramStateList());
        while(!programList.isEmpty())
        {
            List<programState> newProgramList = programList;
            programList.forEach(p -> p.getHeap().set(safeGarbageCollector(newProgramList, p.getHeap().getContent())));
            oneStepForAllPrograms(programList);
            // remove the completed programs
            programList = removeCompletedPrograms(repository.getProgramStateList());
        }
        // close the log file
        log(programList);
    }

    @Override
    public programState getCurrentProgramState() throws MyException
    {
        List<programState> programList = repository.getProgramStateList();
        return programList.get(currentProgram);
    }

    public int getRunningPrgCount() throws MyException {
        return repository.getProgramStateList().size();
    }

    public iRepository getRepository()
    {
        return repository;
    }
}
