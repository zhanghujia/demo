package com.example.web.controller;

import com.example.business.entity.Users;
import com.example.business.service.UsersService;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.ApiOperation;
import com.example.core.utils.result.Result;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * (Users)表控制层
 *
 * @author makejava
 * @since 2020-05-21 14:56:18
 */
 
@RestController
@RequestMapping("/users")
public class UsersController {


    /**
     * 服务对象
     */
    private final UsersService usersService;
    
    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    
    @GetMapping("/{id}")
    @ApiOperation(value = "依据主键获取 Users", response = Result.class)
    public Users selectDetail(@PathVariable("id") Integer id) {
        return usersService.queryById(id);
    }
    
    @PostMapping("/insert")
    @ApiOperation(value = "新增 Users", response = Result.class)
    public Users addUsers(@RequestBody Users users) {
        return usersService.insert(users);
    }
   
    @PutMapping("/update")
    @ApiOperation(value = "修改 Users", response = Result.class)
    public Users updateUsers(@RequestBody Users users) {
        return usersService.update(users);
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "依据主键删除 Users", response = Result.class)
    public Boolean deleteUsers(@PathVariable("id") Integer id) {
        return usersService.deleteById(id);
    }

    @GetMapping("/list")
    @ApiOperation(value = "获取 Users列表(分页)", response = Result.class)
    public PageInfo listUsers(@RequestParam(defaultValue = "0") Integer page,
                              @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<Users> list = usersService.queryPageAll();
        System.out.println(">>>>>>>>>>>>>>>>>>");
        return new PageInfo<>(list);
    }

}