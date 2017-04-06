/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CLI;

import javafx.collections.ObservableList;

/**
 *
 * @author Gabe
 */
public interface MessageList {
    /**
     * Returns list of messages
     * @return 
     */
    public ObservableList<String> getMessages();
}
