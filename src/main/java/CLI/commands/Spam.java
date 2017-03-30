/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CLI.commands;

import CLI.commands.SpamThread.SpamThread;
import gabeclientgui.ChatWindow;

/**
 *
 * @author Gabe
 */
public class Spam implements ICommand{
    ChatWindow cw;
    public Spam(ChatWindow cw){
        this.cw = cw;
    }
    @Override
    public String getName() {
        return "Spam";
    }

    @Override
    public String response(String[] args) {
        
        StringBuilder message = new StringBuilder();
        for(int i = 2; i < args.length; i++){
            message.append(args[i]);
            if(i<args.length+1) message.append(' ');
        }
        new Thread(new SpamThread(Integer.parseInt(args[1]),message.toString(),cw.getIcom())).start();
        return "";
    }

    @Override
    public String[] getKeys() {
    return new String[]{"spam","sp"};
    }
    
}


