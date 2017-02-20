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
public class Reverse implements ICommand{

    @Override
    public String getName() {
        return "reverse";
    }


    @Override
    public String response(String[] args) {
        String result="";
        for(int i = args.length-1; i > 0; i--){
            for(int e = args[i].length()-1; e >= 0; e--){
                result+=args[i].charAt(e);
            }
            result+=" ";
        }
        return result;
    }

    @Override
    public String[] getKeys() {
        return new String[]{"r","reverse"};
    }
    
}
