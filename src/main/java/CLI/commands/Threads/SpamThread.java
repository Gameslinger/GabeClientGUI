/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CLI.commands.Threads;

import CLI.CommandList;
import Communication.ICommunication;


/**
 *
 * @author Gabe
 */
public class SpamThread implements Runnable{
    String message;
    int times;
    ICommunication icom;
    long delay;
    CommandList cl;
    /**
     * Constructs a SpamThread to Spam chat
     * @param times
     * @param delay
     * @param message
     * @param icom
     * @param cl 
     */
    public SpamThread(int times,long delay,String message, ICommunication icom,CommandList cl){
        this.times = times;
        this.message = message;
        this.icom = icom;
        this.delay = delay;
        this.cl = cl;
    }
    
    @Override
    public void run() {
    while(times-->0){
           icom.send(cl.scanString(message));
           try{
               Thread.sleep(delay);
           }catch(Exception e){
               //Poo...
               e.printStackTrace();
           }
         }
    }
    
}
