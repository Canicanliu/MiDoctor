package com.can.minidoctor.core.service.common;

import com.fasterxml.jackson.core.JsonParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.SerializationUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;

import java.io.IOException;
import java.util.List;
import java.util.Set;

/**
 * @Author: ltm
 * @Descripion:
 * @Date: Created in 22:12 2018/12/18
 */
@Component
public class JedisClusterService {

    private static final Logger LOGGER = LoggerFactory.getLogger(JedisClusterService.class);

    @Autowired
    private JedisCluster jedisCluster;
    private int timeOut = 60*60*24*30;//默认超时时间


    public JedisClusterService(){
    }

    public void setTimeOut(int timeout) {
        this.timeOut = timeout;
    }

    public void saveOrUpdate(String key,Object obj,int timeout) {
        jedisCluster.setex(key.getBytes(), timeout, SerializationUtils.serialize(obj));
    }
    /**
     * 当不仅仅只是为了加速才缓存，而是还有其他依赖调用必须用到时，就调用这个方法
     * @param key
     * @param obj
     * @param timeout
     */
    public void saveOrUpdateEssential(String key,Object obj,int timeout) {
        jedisCluster.setex(key.getBytes(), timeout, SerializationUtils.serialize(obj));
    }

    public void saveOrUpdate(String key,Object obj) {
        jedisCluster.setex(key.getBytes(), this.timeOut ,SerializationUtils.serialize(obj));
        //jedisCluster.setex(key.getBytes(), this.timeOut ,SerializationUtil.serialize(obj));
    }

    public void saveOrUpdateEssential(String key,Object obj) {
        jedisCluster.setex(key.getBytes(), this.timeOut , SerializationUtils.serialize(obj));
        //jedisCluster.setex(key.getBytes(), this.timeOut , SerializationUtil.serialize(obj));
    }

    public void saveOrUpdate(String key,String value,int timeout) {
        jedisCluster.setex(key, timeout, value);
    }
    /**
     * 当不仅仅只是为了加速才缓存，而是还有其他依赖调用必须用到时，就调用这个方法
     * @param key
     * @param value
     * @param timeout
     */
    public void saveOrUpdateEssential(String key,String value,int timeout) {
        jedisCluster.setex(key, timeout, value);
    }
    /**
     * 当不仅仅只是为了加速才缓存，而是还有其他依赖调用必须用到时，就调用这个方法
     * @param key
     * @param value
     */
    public void saveOrUpdateEssential(String key,String value) {
        jedisCluster.setex(key,this.timeOut , value);
    }
    public void saveOrUpdate(String key,String value) {
        try{
            jedisCluster.setex(key,this.timeOut , value);
        }catch (Exception e){
            LOGGER.error("save exception",e);
        }

    }

    public String saveOrUpdate_Str(String key,String value) {
        return jedisCluster.setex(key,this.timeOut , value);
    }

    public long incr(String key){
        return jedisCluster.incr(key);
    }

    public Long decr(String key){
        return jedisCluster.decr(key);
    }

    public void expire(String key, int unixTime){
        jedisCluster.expire(key, unixTime);
    }

    public void setnx(String key, String value){
        jedisCluster.setnx(key, value);
    }

    public void hset(String key, String field, String value){
        jedisCluster.hset(key, field, value);
    }

    public Boolean hexists(String key, String field){
        return jedisCluster.hexists(key, field);
    }

    public boolean exists(String key){
        return jedisCluster.exists(key);
    }

    @SuppressWarnings("unchecked")
    public <T> T getValue(String key ,Class<T> clazz){
        //为了兼容旧版的redis，取值String类型
        if(clazz.isAssignableFrom(String.class)){
            return (T) jedisCluster.get(key);
        }
        return (T) SerializationUtils.deserialize(getValue(key.getBytes()));
        //return (T) SerializationUtil.deserialize(getValue(key.getBytes()));
    }
    /**
     * 当要拿的值不仅仅是为了加速，而是有其他依赖时，调此方法，在redis异常时，会保存到本地缓存
     * @param key
     * @param clazz
     * @return
     */
    @SuppressWarnings("unchecked")
    public <T> T getValueEssential(String key ,Class<T> clazz){
        //为了兼容旧版的redis，取值String类型
        if(clazz.isAssignableFrom(String.class)){
            return (T) jedisCluster.get(key);
        }
        return (T) SerializationUtils.deserialize(getValue(key.getBytes()));
        //return (T) SerializationUtil.deserialize(getValue(key.getBytes()));
    }

    public byte[] getValue(byte[] key){
        return jedisCluster.get(key);
    }

    public void set(byte[] key, byte[] value){
        jedisCluster.set(key,value);
    }

    public void set(String key, String value){
        jedisCluster.set(key, value);
    }

    /**
     * 当要拿的值不仅仅是为了加速，而是有其他依赖时，调此方法，在redis异常时，会保存到本地缓存
     * @param key
     * @return
     */
    public String getEssential(String key){
        return jedisCluster.get(key);
    }

    public String get(String key){
        return jedisCluster.get(key);
    }

    public void del(String key){
        jedisCluster.del(key);
    }

    public void sadd(String key,String id){
        jedisCluster.sadd(key, id);
    }

    public Set<String> smembers(String key){
        return jedisCluster.smembers(key);
    }

    public void srem(String key,String info){
        jedisCluster.srem(key, info);
    }

    public long ttl(String key){
        return jedisCluster.ttl(key);
    }

    public List<String> lrange(String key, long start, long end){
        return jedisCluster.lrange(key, start, end);
    }

    public long lpush(String key,String value){
        return jedisCluster.lpush(key, value);
    }

    public List<String> blpop(String key,String time){
        return jedisCluster.blpop(Integer.parseInt(time), key);
    }

    public String lpop(String key){
        return jedisCluster.lpop(key);
    }

    public long llen(String key){
        return jedisCluster.llen(key);
    }

}
