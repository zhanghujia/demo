package com.example.business.mapper;

import com.example.business.entity.Users;
import com.example.core.utils.redis.MybatisRedisCache;
import com.example.core.utils.tk.mapper.Mapper;
import org.apache.ibatis.annotations.CacheNamespace;
import org.springframework.stereotype.Repository;


/**
 * (Users)表数据库访问层
 *
 * @author makejava
 * @since 2020-05-21 14:56:18
 */
 
@Repository
@CacheNamespace(implementation = MybatisRedisCache.class,eviction = MybatisRedisCache.class)
public interface UsersMapper extends Mapper<Users> {


}