/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CLI.commands;

import CLI.CommandList;
import CLI.commands.Threads.SpamThread;
import Communication.Chat;

/**
 *
 * @author Gabe
 */
public class Spam implements ICommand{
    Chat chat;
    CommandList cl;
    public Spam(Chat chat,CommandList cl){
        this.chat = chat;
        this.cl = cl;
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
        
        new Thread(new SpamThread(Integer.parseInt(args[1]),Long.parseLong(args[2]),message.toString(),chat.getCom(),cl)).start();
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


