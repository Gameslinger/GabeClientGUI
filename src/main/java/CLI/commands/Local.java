/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CLI.commands;

import CLI.CLI;
import gabeclientgui.ChatWindow;

/**
 *
 * @author Gabriel.Maxfield
 */
public class Local implements ICommand{
    CLI cli;
    ChatWindow cw;
    public Local(CLI cli,ChatWindow cw){
        this.cli = cli;
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
        cw.messages.getItems().add(result);
        
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
