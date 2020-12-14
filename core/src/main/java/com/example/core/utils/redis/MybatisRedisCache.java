package com.example.core.utils.redis;

import com.example.core.utils.holder.SpringContextHolder;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.ibatis.cache.Cache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author 10696
 */

public class MybatisRedisCache implements Cache {

    private static final Logger logger = LoggerFactory.getLogger(MybatisRedisCache.class);

    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock(true);

    private RedisTemplate<String, Object> redisTemplate = null;

    private final String id;

    public MybatisRedisCache(final String id) {
        if (id == null) {
            throw new IllegalArgumentException("Cache instances require an ID");
        }
        this.id = id;
        logger.debug("MybatisRedis id {}", this.id);
    }

    private RedisTemplate<String, Object> getRedisTemplate() {
        if (redisTemplate == null) {
            redisTemplate = SpringContextHolder.getBean("redisTemplate");
        }
        return redisTemplate;
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public int getSize() {
        try {
            Long size = getRedisTemplate().opsForHash().size(this.id);
            logger.debug("MybatisRedis.getSize: {}->{}", id, size);
            return size.intValue();
        } catch (Exception e) {
            logger.error("MybatisRedis.getSize: {}, {}", id, e);
        }
        return 0;
    }

    @Override
    public void putObject(final Object key, final Object value) {
        String md5Key = DigestUtils.md5Hex(key.toString());
        try {
            logger.debug("MybatisRedis.putObject: {}->{}", id, md5Key);
            getRedisTemplate().opsForHash().put(this.id, md5Key, value);
        } catch (Exception e) {
            logger.error("MybatisRedis.putObject.error: {}->{}, {}", id, md5Key, e);
        }
    }

    @Override
    public Object getObject(final Object key) {
        String md5Key = DigestUtils.md5Hex(key.toString());
        try {
            Object hashVal = getRedisTemplate().opsForHash().get(this.id, md5Key);
            logger.debug("MybatisRedis.getObject: {}->{}", id, md5Key);
            return hashVal;
        } catch (Exception e) {
            logger.error("MybatisRedis.getObject.error: {}->{}, {}", id, md5Key, e);
            return null;
        }
    }

    @Override
    public Object removeObject(final Object key) {
        String md5Key = DigestUtils.md5Hex(key.toString());
        try {
            getRedisTemplate().opsForHash().delete(this.id, md5Key);
            logger.debug("MybatisRedis.removeObject: {}->{}", id, md5Key);
        } catch (Exception e) {
            logger.error("MybatisRedis.removeObject.error: {}->{}, {}", id, md5Key, e);
        }
        return null;
    }

    @Override
    public void clear() {
        try {
            getRedisTemplate().delete(this.id);
            logger.debug("MybatisRedis.clear: {}", id);
        } catch (Exception e) {
            logger.error("MybatisRedis.clear.error: {}, {}", id, e);
        }
    }

    @Override
    public ReadWriteLock getReadWriteLock() {
        return this.readWriteLock;
    }

}
