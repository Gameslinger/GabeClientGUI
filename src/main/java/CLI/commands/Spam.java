/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CLI.commands;

import CLI.CLI;
import CLI.commands.Threads.SpamThread;
import gabeclientgui.ChatWindow;

/**
 *
 * @author Gabe
 */
public class Spam implements ICommand{
    ChatWindow cw;
    CLI cli;
    public Spam(ChatWindow cw,CLI cli){
        this.cw = cw;
        this.cli = cli;
    }
    @Override
    public String getName() {
        return "Spam";
    }

    @Override
    public String response(String[] args) {
        StringBuilder message = new StringBuilder();
        for(int i = 3; i < args.length; i++){
            message.append(args[i]);
            if(i<args.length+1) message.append(' ');
        }
        
        new Thread(new SpamThread(Integer.parseInt(args[1]),Long.parseLong(args[2]),message.toString(),cw.getIcom(),cli)).start();
        return "";
    }

    @Override
    public String[] getKeys() {
    return new String[]{"spam","sp"};
    }
    @Override
    public String getHelp(){
        return "Spams Chat with times and delay ex !sp 10(amount) 1000(delay ms) Hello World";
    }
}


