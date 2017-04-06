/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CLI.commands;

import CLI.CLI;
import CLI.CommandList;

/**
 *
 * @author Gabriel.Maxfield
 */
public class Help implements ICommand {
    CommandList commandList;
    
    public Help(CommandList cli){
        this.commandList = cli;
    }
    @Override
    public String getName() {
        return "Help";
    }

    @Override
    public String response(String[] args) {
        if(args.length>1){
            return "!l "+commandList.getMap().get(args[1]).getHelp()+" !!";
        }
        String result="!l ";
        for(ICommand command : commandList.getList()){
            String keys="";
            for(int i = 0; i < command.getKeys().length; i++){
                keys+=command.getKeys()[i]+", ";
            }
            result += ("Name: "+command.getName()+", Keys: "+keys+"\n");
        }
        return result;
    }

    @Override
    public String[] getKeys() {
        return new String[] {"h","help"};
    }
    @Override
    public String getHelp(){
        return "Prints info on commands ex !help [command]";
    }
}
