package com.cheng.test.util;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.apache.ibatis.cache.Cache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

public class RedisCache implements Cache{
	
	private static final Logger logger = LoggerFactory.getLogger(RedisCache.class);
    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private final String id; // cache instance id
    private RedisTemplate redisTemplate;
    private static final long EXPIRE_TIME_IN_MINUTES = 30; // redis过期时间
    public RedisCache(String id) {
        if (id == null) {
            throw new IllegalArgumentException("Cache instances require an ID");
        }
        this.id = id;
    }

    /**
	 * 发生更新时，清除缓存。
	 */
	@Override
	public void clear() {
		RedisTemplate redisTemplate = getRedisTemplate();
        redisTemplate.execute((RedisCallback) connection -> {
            connection.flushDb();
            return null;
        });
        logger.debug("Clear all the cached query result from redis");
	}

	/**
	 * mybatis缓存操作对象的标识符。一个mapper对应一个mybatis的缓存操作对象。
	 */
	@Override
	public String getId() {
		// TODO Auto-generated method stub
		System.out.println(id);
		return id;
	}

	/**
	 * 从缓存中获取被缓存的查询结果。
	 */
	@Override
	public Object getObject(Object key) {
		RedisTemplate redisTemplate = getRedisTemplate();
        ValueOperations opsForValue = redisTemplate.opsForValue();
        logger.debug("Get cached query result from redis");
        return opsForValue.get(key);
	}

	/**
	 * 可选实现。用于实现原子性的缓存操作。
	 */
	@Override
	public ReadWriteLock getReadWriteLock() {
		return readWriteLock;
	}

	/**
	 * 可选实现。返回缓存的数量。
	 */
	@Override
	public int getSize() {
		return 0;
	}

	/**
	 * 将查询结果塞入缓存。
	 */
	@Override
	@SuppressWarnings("unchecked")
	public void putObject(Object key, Object value) {
		// TODO Auto-generated method stub
		RedisTemplate redisTemplate = getRedisTemplate();
        ValueOperations opsForValue = redisTemplate.opsForValue();
        opsForValue.set(key, value, EXPIRE_TIME_IN_MINUTES, TimeUnit.MINUTES);
        logger.debug("Put query result to redis");
	}

	/**
	 * 从缓存中删除对应的key、value。只有在回滚时触发。
	 * 一般我们也可以不用实现，具体使用方式请参考：org.apache.ibatis.cache.decorators.TransactionalCache。
	 */
	@Override
	@SuppressWarnings("unchecked")
	public Object removeObject(Object key) {
		// TODO Auto-generated method stub
		RedisTemplate redisTemplate = getRedisTemplate();
        redisTemplate.delete(key);
        logger.debug("Remove cached query result from redis");
        return null;
	}
	
	private RedisTemplate getRedisTemplate() {
        if (redisTemplate == null) {
            redisTemplate = ApplicationContextHolder.getBean("redisTemplate");
        }
        return redisTemplate;
    }

}
