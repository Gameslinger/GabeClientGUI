/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Communication;

/**
 *
 * @author Gabriel.Maxfield
 */

public interface ICommunication {
    /**
     * Connects to redis server at address on channel
     * @param address
     * @param channel 
     */
    public void connect(String address, String channel);
    /**
     * Sends msg to the server
     * @param msg 
     */
    public void send(String msg);
    /**
     * Returns address communications are sent to
     * @return 
     */
    public String getAddress();
}
