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
public class Quit implements ICommand{

    @Override
    public String getName() {
        return "Quit";
    }

    @Override
    public String response(String[] args) {
        System.out.println("See you later!");
        System.exit(0);
        //Exit before
        return null;
    }

    @Override
    public String[] getKeys() {
        return new String[]{"q","quit","exit"};
    }
    @Override
    public String getHelp(){
        return "Closes client ex !q";
    }
}
