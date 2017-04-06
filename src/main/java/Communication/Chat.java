/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Communication;

import CLI.MessageList;
import javafx.collections.ObservableList;

/**
 *
 * @author Gabe
 */
public abstract class Chat implements Communicateable, MessageList{
    String address;
    
    @Override
    public abstract ICommunication getCom();
    
    @Override
    public abstract ObservableList<String> getMessages();
    /**
     * Returns connection address
     * @return 
     */
    public String getAddress(){
        return address;
    }
    /**
     * Sets Connection address
     * @param address 
     */
    public void setAddress(String address){
        this.address = address;
    }
}
