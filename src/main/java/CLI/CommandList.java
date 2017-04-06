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
    public Map<String,ICommand> getMap();
    public List<ICommand> getList();
    public String scanString(String str);
}
