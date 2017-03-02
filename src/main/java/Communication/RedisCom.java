/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Communication;

import javafx.scene.control.ListView;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;

/**
 *
 * @author Gabe
 */
public class RedisCom implements ICommunication{
    Jedis jd;
    ListView<String> messages;
    //SubThread subThread;
    public RedisCom(ListView<String> messages){
        this.messages = messages;
    }
    @Override
    public void connect(String address) {
        System.out.println("-"+address+"-");
        jd = new Jedis(address);
        
        new SubThread(jd,new JedisPubSub(){
        @Override
	public void onMessage(String channel, String message) {
            messages.getItems().add(message);
	}
    }).run();
    }

    @Override
    public void send(String msg) {
        jd.set("msg",msg);
    }

    @Override
    public String recieve() {
        return jd.get("msg");
           
    }
    
 
}
