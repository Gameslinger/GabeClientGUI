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
public class MockCom implements ICommunication{
    String address;
    @Override
    public void connect(String address,String channel) {
        System.out.println("Connecting to server!");
        System.out.println("Connected!");
        //Success?
        this.address = address;
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

    @Override
    public String getAddress() {
        return address;
    }

}
