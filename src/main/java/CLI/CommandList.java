/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CLI;

import CLI.commands.ICommand;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Gabe
 */
public interface CommandList {
    /**
     * Returns a map of commands
     * @return 
     */
    public Map<String,ICommand> getMap();
    /**
     * Returns a list of commands
     * @return 
     */
    public List<ICommand> getList();
    /**
     * Processes strings and returns result
     * @param str
     * @return 
     */
    public String scanString(String str);
}
