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
public class Echo implements ICommand{

    @Override
    public String getName() {
        return "Echo";
    }

    @Override
    public String response(String args[]) {
        String result = "";
        for(int i = 1; i < args.length; i++){
            result+=args[i]+" ";
        }
        System.out.println(result);
        return result;
    }

    @Override
    public String[] getKeys() {
        return new String[]{"e","echo"};
        
    }
    
}
