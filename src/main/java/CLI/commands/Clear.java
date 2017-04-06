/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CLI.commands;

import CLI.MessageList;

/**
 *
 * @author Gabe
 */
public class Clear implements ICommand{
    MessageList messageList;
    public Clear(MessageList cw) {
        this.messageList = cw;
    }
    
    @Override
    public String getName() {
        return "Clear";
    }

    @Override
    public String response(String[] args) {
       messageList.getMessages().clear();
       
       return ""; 
    }

    @Override
    public String[] getKeys() {
        return new String[]{"cl","clear"};
    }
    @Override
    public String getHelp(){
        return "Clears chat ex !cl";
    }
}
