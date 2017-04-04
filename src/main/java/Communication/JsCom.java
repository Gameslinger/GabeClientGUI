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
import javafx.scene.control.TextArea;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import world.drj.drjclass.client.jedis.JedisClient;

/**
 *
 * @author Gabe
 */
public class JsCom implements ICommunication{
final ListView<String> messages;
    JedisClient client;
    Nameable name;
    String lastMessage = "",address;
    int spamDuration = 0;
    ScriptEngine eng;
    
    TextArea sendCode,recCode;
    
    //SubThread subThread;
    public JsCom(Nameable name,ListView<String> messages,TextArea sendCode,TextArea recCode){
        this.sendCode = sendCode;
        this.recCode = recCode;
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
        eng.put("messages", messages.getItems());
        eng.put("self",this);
        client.observe().subscribeOn(Schedulers.io()).observeOn(JavaFxScheduler.platform()).subscribe(s->{
                eng.put("lastMessage",lastMessage);
                eng.put("msg", s);
                eng.eval(recCode.getText());
                //lastMessage = s;
        });
      
    }
    @Override
    public void send(String msg) {
        eng.put("msg", msg);
        eng.put("name", name.getName());
    try {
        eng.eval(sendCode.getText());
    } catch (ScriptException ex) {
        ex.printStackTrace();
    }
    //lastMessage = msg;
    }

    @Override
    public String recieve() {
        return "";
    }
    @Override
    public String getAddress() {
        return address;
    }
    public void setLastMessage(String msg){
        this.lastMessage = msg;
    }
}
