package gui.a7.View;

import gui.a7.Model.Exceptions.MyException;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class textMenu {
    private Map<String, Command> commands;

    public textMenu() {
        this.commands = new HashMap<>();
    }

    public void addCommand(Command command) {
        commands.put(command.getKey(), command);
    }

    private void printMenu() {
        for (Command command : commands.values()) {
            String line = String.format("%4s: %s", command.getKey(), command.getDescription());
            System.out.println(line);
        }
    }

    public void show() {
        Scanner scanner = new Scanner(System.in);
        boolean working = true;
        while (working) {
            printMenu();
            System.out.printf("Input the option: ");
            String key = scanner.nextLine();
            Command command = commands.get(key);
            if (command == null) {
                System.out.println("Invalid option");
                continue;
            }
            try {
                command.execute();
            } catch (MyException e) {
                if (e.getMessage() == "Exit")
                    working = false;
                else
                    System.out.println(e.getMessage());
            }
            //scanner.close();
        }
    }
}

