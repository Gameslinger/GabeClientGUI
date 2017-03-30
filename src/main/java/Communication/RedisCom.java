/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Communication;

import CLI.Nameable;
import io.reactivex.rxjavafx.schedulers.JavaFxScheduler;
import io.reactivex.schedulers.Schedulers;
import javafx.scene.control.ListView;
import world.drj.drjclass.client.jedis.JedisClient;

/**
 *
 * @author Gabe
 */
public class RedisCom implements ICommunication{
    final ListView<String> messages;
    JedisClient client;
    Nameable name;
    String myLastMessage = "",address;
    //SubThread subThread;
    public RedisCom(Nameable name, ListView<String> messages){
        this.messages = messages;
        this.name = name;
    }
    @Override
    public void connect(String address,String channel) {
        this.address = address;
        System.out.println("-"+address+"-");
        client = new JedisClient(address,channel);
        client.observe().subscribeOn(Schedulers.io()).observeOn(JavaFxScheduler.platform()).subscribe(
            s->{
                if(s.equals(myLastMessage))return;
            System.out.println("Message recieved: "+s);
            messages.getItems().add(s);
        });
      
    }

    @Override
    public void send(String msg) {
        myLastMessage = name.getName()+"> "+msg;
        client.send(myLastMessage);
    }

    @Override
    public String recieve() {
        return "";
    }

    public String getAddress() {
        return address;
    }
    
 
}
