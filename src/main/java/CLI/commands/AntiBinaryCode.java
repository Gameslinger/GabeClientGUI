/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CLI.commands;

/**
 *
 * @author Gabe
 */
public class AntiBinaryCode implements ICommand{

    @Override
    public String getName() {
        return "Anti-Binary Code";
    }

    @Override
    public String response(String[] args) {
        String result = "";
        for(int i = 1; i < args.length; i++){
            result+=(char)(Integer.parseInt(args[i], 2));
        }
        return result;
    }

    @Override
    public String[] getKeys() {
        return new String[]{"abc","antibinary"};
    }
    @Override
    public String getHelp(){
        return "Reverses Binary Code ex: !abc !bc Hello World";
    }
}
