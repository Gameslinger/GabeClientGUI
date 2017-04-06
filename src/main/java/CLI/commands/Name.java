/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CLI.commands;

import CLI.CLI;
import CLI.Nameable;


/**
 *
 * @author Gabriel.Maxfield
 */
public class Name implements ICommand{
    Nameable nm;
    public Name(Nameable nm){
        this.nm = nm;
    }
    @Override
    public String getName() {
        return "Name";
    }

    @Override
    public String response(String[] args) {
        String name = "";
        for(int i = 1; i < args.length; i++){
            name+= args[i]+" ";
        }
        nm.setName(name.substring(0,name.length()-1));
        return "";
    }

    @Override
    public String[] getKeys() {
        return new String[]{"n","name"};
    }
    @Override
    public String getHelp(){
        return "Sets username ex !n John Cena";
    }
}
