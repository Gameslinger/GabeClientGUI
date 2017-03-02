/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Communication;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;

/**
 *
 * @author Gabriel.Maxfield
 */
public class SubThread implements Runnable{
    Jedis jedis;
    JedisPubSub pubSub;
    public SubThread(Jedis jedis, JedisPubSub pubSub){
        this.jedis = jedis;
        this.pubSub = pubSub;
    }
    @Override
    public void run() {
        jedis.subscribe(pubSub, "test");
    }
    
}
