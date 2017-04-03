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
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import world.drj.drjclass.client.jedis.JedisClient;

/**
 *
 * @author Gabe
 */
public class JsCommunication implements ICommunication{
final ListView<String> messages;
    JedisClient client;
    Nameable name;
    String lastMessage = "",address;
    int spamDuration = 0;
    ScriptEngine eng;
    //SubThread subThread;
    public JsCommunication(Nameable name, ListView<String> messages){
        this.messages = messages;
        this.name = name;
        eng = new ScriptEngineManager().getEngineByName("nashorn");
    }
    
    String method = "";
    @Override
    public void connect(String address,String channel) {
        this.address = address;
        System.out.println("-"+address+"-");
        client = new JedisClient(address,channel);
        eng.put("client", client);
        eng.put("messages", messages);
        client.observe().subscribeOn(Schedulers.io()).observeOn(JavaFxScheduler.platform()).subscribe(s->{
                eng.eval(method);
        });
      
    }
    @Override
    public void send(String msg) {
        if(msg.charAt(0)=='.'){
            method = msg.substring(1);
            return;
        }
        eng.put("msg", msg);
    try {
        eng.eval(method);
    } catch (ScriptException ex) {
        ex.printStackTrace();
    }
    }

    @Override
    public String recieve() {
        return "";
    }
    @Override
    public String getAddress() {
        return address;
    }
    
}
