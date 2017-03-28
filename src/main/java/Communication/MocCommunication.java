/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Communication;

/**
 *
 * @author Gabe
 */
public class MocCommunication implements ICommunication{

    @Override
    public void connect(String address,String channel) {
        System.out.println("Connecting to server!");
        System.out.println("Connected!");
        //Success?
    }

    @Override
    public void send(String msg) {
        
    }

    @Override
    public String recieve() {
        //Randomly get Message;
        if(Math.random()>0.97){
            return "Hello!";
        }
        return null;
    }

}
