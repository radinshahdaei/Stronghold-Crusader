package View.Start;

import Controller.ProfileMenuController;
import View.Commands.ProfileMenuCommands;

import static View.InputOutput.input;
import static View.InputOutput.output;

import java.util.regex.Matcher;

public class ProfileMenu {
    public void run() {
        String command;
        Matcher matcher;
        while (true) {
            command = input();
            if ((matcher = ProfileMenuCommands.getMatcher(command, ProfileMenuCommands.BACK)) != null) {
                output("Entered Start Menu");
                return;
            }
            else if ((matcher = ProfileMenuCommands.getMatcher(command, ProfileMenuCommands.EXIT)) != null) {
                ProfileMenuController.exit();
                output("Exited");
                System.exit(0);
            }
            else if ((matcher = ProfileMenuCommands.getMatcher(command, ProfileMenuCommands.CHANGE_USERNAME)) != null) {
                ProfileMenuController.changeUserName(matcher);
            }
            else if ((matcher = ProfileMenuCommands.getMatcher(command, ProfileMenuCommands.CHANGE_NICKNAME)) != null) {
                ProfileMenuController.changeNickname(matcher);
            }
            else if ((matcher = ProfileMenuCommands.getMatcher(command, ProfileMenuCommands.CHANGE_PASSWORD)) != null) {
                ProfileMenuController.changePassword(matcher);
            }
            else if ((matcher = ProfileMenuCommands.getMatcher(command, ProfileMenuCommands.CHANGE_EMAIL)) != null) {
                ProfileMenuController.changeEmail(matcher);
            }
            else if ((matcher = ProfileMenuCommands.getMatcher(command, ProfileMenuCommands.CHANGE_SLOGAN)) != null) {
                ProfileMenuController.changeSlogan(matcher);
            }
            else if ((matcher = ProfileMenuCommands.getMatcher(command, ProfileMenuCommands.REMOVE_SLOGAN)) != null) {
                ProfileMenuController.removeSlogan();
            }
            else if ((matcher = ProfileMenuCommands.getMatcher(command, ProfileMenuCommands.DISPLAY_HIGHSCORE)) != null) {
                ProfileMenuController.displayHighScore();
            }
            else if ((matcher = ProfileMenuCommands.getMatcher(command, ProfileMenuCommands.DISPLAY_SLOGAN)) != null) {
                ProfileMenuController.displaySlogan();
            }
            else if ((matcher = ProfileMenuCommands.getMatcher(command, ProfileMenuCommands.DISPLAY_RANK)) != null) {
                ProfileMenuController.displayRank();
            }
            else if ((matcher = ProfileMenuCommands.getMatcher(command, ProfileMenuCommands.DISPLAY)) != null) {
                ProfileMenuController.display();
            }
            else {
                output("Invalid command");
            }
        }
    }
}
