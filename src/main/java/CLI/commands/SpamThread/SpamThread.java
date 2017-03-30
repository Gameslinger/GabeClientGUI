/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CLI.commands.SpamThread;

import Communication.ICommunication;


/**
 *
 * @author Gabe
 */
public class SpamThread implements Runnable{
    String message;
    int times;
    ICommunication icom;
    public SpamThread(int times,String message, ICommunication icom){
        this.times = times;
        this.message = message;
        this.icom = icom;
    }
    
    @Override
    public void run() {
    while(times-->0){
           icom.send(message);
         }
    }
    
}
