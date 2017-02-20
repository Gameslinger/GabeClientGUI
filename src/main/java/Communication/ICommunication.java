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
    public void connect(String address);
    public void send(String msg);
    public String recieve();
}
