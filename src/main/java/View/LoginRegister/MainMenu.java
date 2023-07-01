package View.LoginRegister;

import View.Commands.MainMenuCommands;
import Controller.Controller;
import Controller.ManageData;

import java.util.regex.Matcher;

import static View.InputOutput.input;
import static View.InputOutput.output;

public class MainMenu {
    public String run() throws InterruptedException {
        output("[REGISTER][LOGIN]");
        String command;
        Matcher matcher;
        while (true) {
            command = input();
            if(command.matches("\\s*show\\s+related\\s+commands\\s*")) {
                output("exit");
                output("enter register menu");
                output("enter loggin menu");
            }
            else if ((matcher = MainMenuCommands.getMatcher(command, MainMenuCommands.EXIT)) != null) {
                output("Exited!");
                if (!Controller.stayLoggedIn) Controller.setCurrentUser(null);
                ManageData.saveCurrentUser();
                ManageData.saveUsers();
                return "exit";
            } else if ((matcher = MainMenuCommands.getMatcher(command, MainMenuCommands.REGISTER_MENU)) != null) {
                output("Entered Register Menu!");
                RegisterMenu registerMenu = new RegisterMenu();
                registerMenu.run();
            } else if ((matcher = MainMenuCommands.getMatcher(command, MainMenuCommands.LOGIN_MENU)) != null) {
                output("Entered Login Menu!");
                LoginMenu loginMenu = new LoginMenu();
                if (loginMenu.run()) return "logged in";
            } else {
                output("Invalid command!");
            }
        }
    }
}