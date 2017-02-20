/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CLI.commands;

/**
 *
 * @author Gabriel.Maxfield
 */
public class Escapes implements ICommand{

    @Override
    public String getName() {
        return "Escapes";
    }

    @Override
    public String response(String[] args) {
        String result = "";
        for(int i = 1; i < args.length; i++){
            result+=args[i]+" ";
        }
        
        return result.replaceAll("\\\\n", "\n").replaceAll("\\\\b", "\b").replaceAll("\\\\t", "\t");
        
    }

    @Override
    public String[] getKeys() {
        return new String[] {"es","escapes"};
    }
    
}
