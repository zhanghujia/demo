package com.example.business.service;

import com.example.business.entity.Users;
import org.springframework.stereotype.Component;
import java.util.List;

/**
 * (Users)表服务接口
 *
 * @author makejava
 * @since 2020-05-21 14:56:18
 */
 
@Component 
public interface UsersService {

    /**
     * 通过ID查询单条数据
     *
     * @param userId 主键
     * @return 实例对象
     */
    Users queryById(Integer userId);

    /**
     * 新增数据
     *
     * @param users 实例对象
     * @return 实例对象
     */
    Users insert(Users users);

    /**
     * 修改数据
     *
     * @param users 实例对象
     * @return 实例对象
     */
    Users update(Users users);

    /**
     * 通过主键删除数据
     *
     * @param userId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer userId);
    
    /**
     * 分页查询所有数据
     *
     * @return 实例对象数组
     */
    List<Users> queryPageAll();

}