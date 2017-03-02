/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Communication;

import redis.clients.jedis.Jedis;

/**
 *
 * @author Gabe
 */
public class RedisCom implements ICommunication{
    static Jedis jd;
    
    @Override
    public void connect(String address) {
        jd = new Jedis(address);
        
    }

    @Override
    public void send(String msg) {
        jd.set("Message",msg);
    }

    @Override
    public String recieve() {
        return jd.get("Message");
           
    }
    
 
}
