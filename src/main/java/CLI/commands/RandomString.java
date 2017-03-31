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
public class RandomString implements ICommand{

    @Override
    public String getName() {
        return "Random String";
    }

    @Override
    public String response(String[] args) {
        int length = Integer.parseInt(args[1]);
       // int range = Integer.parseInt(args[3]);
        int min = Integer.parseInt(args[2]);
        int range = Integer.parseInt(args[3]) - min;
        String result = "";
        //Construct String:
        for(int i = 0; i < length; i++){
            result+=(char)(int)(Math.random()*range+min);
        }
        return result;
    }

    @Override
    public String[] getKeys() {
        return new String[]{"rs","randstring"};
    }
    
}
