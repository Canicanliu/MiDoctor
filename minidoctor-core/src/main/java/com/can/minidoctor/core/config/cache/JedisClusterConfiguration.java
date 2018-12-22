package com.can.minidoctor.core.config.cache;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;
import java.util.HashSet;
import java.util.Set;

@Configuration
public class JedisClusterConfiguration {
	private static final Logger LOGGER= LoggerFactory.getLogger(JedisClusterConfiguration.class);

	@Bean
	public Jedis getJedis(){
		Jedis jedis=new Jedis("127.0.0.1",6379);
		return jedis;
	}
	
//	@Bean
//	public JedisCluster getJedisCluster(){
//		LOGGER.info("init jediscluster start");
//		JedisCluster jedisCluster = null;
//		try{
//			// 添加集群的服务节点Set集合
//			Set<HostAndPort> hostAndPortsSet = new HashSet<HostAndPort>();
//			// 添加节点
//			hostAndPortsSet.add(new HostAndPort("127.0.0.1", 6379));
//			jedisCluster = new JedisCluster(hostAndPortsSet);
//		}catch (Exception e){
//			LOGGER.error("init jedis exception",e);
//		}
//
//
////		// Jedis连接池配置
////		JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
////		// 最大空闲连接数, 默认8个
////
////		// 最大连接数, 默认8个
////		jedisPoolConfig.setMaxTotal(500);
////		//最小空闲连接数, 默认0
////		jedisPoolConfig.setMinIdle(0);
////		// 获取连接时的最大等待毫秒数(如果设置为阻塞时BlockWhenExhausted),如果超时就抛异常, 小于零:阻塞不确定的时间,  默认-1
////		jedisPoolConfig.setMaxWaitMillis(2000); // 设置2秒
////		//对拿到的connection进行validateObject校验
////		jedisPoolConfig.setTestOnBorrow(true);
//
//		LOGGER.info("init jediscluster success");
//		return jedisCluster;
//	}
	
}
