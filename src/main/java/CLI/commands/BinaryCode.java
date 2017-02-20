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
public class BinaryCode implements ICommand{

    @Override
    public String getName() {
        return "Binary Code";
    }

    @Override
    public String response(String[] args) {
        String response = "";
        for(int i = 1; i < args.length; i++){
            for(int e = 0; e < args[i].length();e++){
                response+=Integer.toBinaryString(args[i].charAt(e))+" ";
            }
            if(i < args.length-1){
                response+= Integer.toBinaryString(' ')+" ";
            }
        }
        return response;
    }

    @Override
    public String[] getKeys() {
        return new String[]{"bc","binary"};
    }
    
}
