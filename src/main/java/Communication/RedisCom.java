/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Communication;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.exceptions.JedisException;

/**
 *
 * @author Gabe
 */
public class RedisCom implements ICommunication{
    static JedisPool jp;
    
    @Override
    public void connect(String address) {
        jp = new JedisPool(address, 6379);
        
    }

    @Override
    public void send(String msg) {
        Jedis jedis = jp.getResource();
            try {
                jedis.set("message",msg);
        } catch (JedisException e) {
            //if something wrong happen, return it back to the pool
            if (null != jedis) {
                jp.returnBrokenResource(jedis);
                jedis = null;
            }
        } finally {
            ///it's important to return the Jedis instance to the pool once you've finished using it
            if (null != jedis)
                jp.returnResource(jedis);
        }
       
    }

    @Override
    public String recieve() {
        Jedis jedis = jp.getResource();
            try {
                return jedis.get("message");
        } catch (JedisException e) {
            //if something wrong happen, return it back to the pool
            if (null != jedis) {
                jp.returnBrokenResource(jedis);
                jedis = null;
            }
        } finally {
            ///it's important to return the Jedis instance to the pool once you've finished using it
            if (null != jedis)
                jp.returnResource(jedis);
        }
     return null;   
    }
    
 
}
