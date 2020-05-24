package com.example.business.service.impl;

import com.example.business.entity.Users;
import com.example.business.mapper.UsersMapper;
import com.example.business.service.UsersService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * (Users)表服务实现类
 *
 * @author makejava
 * @since 2020-05-21 14:56:18
 */
 
@Service("usersService")
public class UsersServiceImpl implements UsersService {
    
    private final UsersMapper usersMapper;

    public UsersServiceImpl(UsersMapper usersMapper) {
        this.usersMapper = usersMapper;
    }

    /**
     * 通过ID查询单条数据
     *
     * @param userId 主键
     * @return 实例对象
     */
    @Override
    @Cacheable(cacheNames = "Users", key = "'List'+'-'+#userId", unless = "#result == null")
    public Users queryById(Integer userId) {
        return usersMapper.selectByPrimaryKey(userId);
    }

    /**
     * 新增数据
     *
     * @param users 实例对象
     * @return 实例对象
     */
    @Override
    @CacheEvict(cacheNames = "Users", key = "'List'")
    public Users insert(Users users) {
        usersMapper.insert(users);
        return users;
    }

    /**
     * 修改数据
     *
     * @param users 实例对象
     * @return 实例对象
     */
    @Override
    @CachePut(cacheNames = "Users", key = "'List'+'-'+#users.userId", unless = "#result == null")
    @CacheEvict(cacheNames = "Users", key = "'List'")
    public Users update(Users users) {
        usersMapper.updateByPrimaryKeySelective(users);
        return this.queryById(users.getUserId());
    }

    /**
     * 通过主键删除数据
     *
     * @param userId 主键
     * @return 是否成功
     */
    @Override
    @CacheEvict(cacheNames = "Users", key = "'List'", allEntries = true)
    public boolean deleteById(Integer userId) {
        return usersMapper.deleteByPrimaryKey(userId) > 0;
    }
    
    /**
     * 分页查询所有数据
     *
     * @return 实例对象数组
     */
    @Override
    @Cacheable(cacheNames = "Users", key = "'List'", unless = "#result == null")
    public List<Users> queryPageAll() {
        return usersMapper.selectAll();
    }
    
}