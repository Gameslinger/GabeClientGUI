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
public class Replace implements ICommand{

    @Override
    public String getName() {
        return "Replace";
    }

    @Override
    public String response(String[] args) {
    String result = "";
    for(int i = 1; i < args.length; i++){
        result+=args[i];
        if(i<args.length-1)result+=" ";
    }
        String parts[] = result.split("~");
        return parts[2].replaceAll(parts[0], parts[1]);
    }

    @Override
    public String[] getKeys() {
    return new String[]{"rep","replace"};
    }
    
}
