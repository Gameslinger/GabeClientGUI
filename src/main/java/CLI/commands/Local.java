/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CLI.commands;

import CLI.MessageList;
import ClientGUI.ChatWindow;

/**
 *
 * @author Gabriel.Maxfield
 */
public class Local implements ICommand{
    MessageList cw;
    public Local(MessageList cw){
        this.cw = cw;
    }
    @Override
    public String getName() {
    return "Local Send";
    }

    @Override
    public String response(String[] args) {
        //Echo string and not return anything to print!
        String result = "";
        for(int i = 1; i < args.length; i++){
            result+= args[i]+" ";
        }
        cw.getMessages().add(result);
        
        return "";
    }

    @Override
    public String[] getKeys() {
        return new String[]{"local","l"};
    }
    @Override
    public String getHelp(){
        return "Prints commands locally ex !l";
    }
}
