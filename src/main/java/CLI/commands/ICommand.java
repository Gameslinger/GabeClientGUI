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
public interface ICommand {
    public String getName();
    public String response(String[] args);
    public String[] getKeys();
    public String getHelp();
    //public String getHelp();
    
}
