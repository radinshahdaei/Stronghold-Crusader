package View.Game;

import Controller.TradeMenuController;
import Model.Trade;
import View.Commands.TradeMenuCommands;

import java.util.regex.Matcher;

import static Controller.Controller.*;
import static View.InputOutput.input;

public class TradeMenu {

    public void run() {
        String command;
        Matcher matcher;
        while(true) {
            TradeMenuController.showNotification();
            command = input();
            if((matcher = TradeMenuCommands.getMatcher(command , TradeMenuCommands.TRADE)) != null) {
                String resourceType = removeDoubleQuote(matcher.group("resourceType"));
                int resourceAmount = Integer.parseInt(removeDoubleQuote(matcher.group("resourceAmount")));
                int price = Integer.parseInt(removeDoubleQuote(matcher.group("price")));
                String message = removeDoubleQuote(matcher.group("message"));
                String toUsername = removeDoubleQuote(matcher.group("username"));

                Trade current = new Trade(resourceType , resourceAmount , price , message , findUserByUsername(toUsername) , currentUser);
                TradeMenuController.addTrade(current);
            }
            else if((matcher = TradeMenuCommands.getMatcher(command , TradeMenuCommands.LIST)) != null) {
                TradeMenuController.showList();
            }
            else if((matcher = TradeMenuCommands.getMatcher(command , TradeMenuCommands.ACCEPT)) != null) {
                int id = Integer.parseInt(removeDoubleQuote(matcher.group("id")));
                String message = removeDoubleQuote(matcher.group("message"));
                TradeMenuController.acceptTrade(id);
            }
            else if((matcher = TradeMenuCommands.getMatcher(command , TradeMenuCommands.HISTORY)) != null) {
                TradeMenuController.showHistory();
            }
        }
    }
}
