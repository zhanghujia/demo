package com.example.business.entity;

import java.io.Serializable;
import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * (Users)实体类
 *
 * @author makejava
 * @since 2020-05-21 14:56:18
 */
@Data
@Document(indexName = "demo",type = "users")
public class Users implements Serializable {

    private static final long serialVersionUID = -53762884959197042L;
    
    /**
    * 用户主键
    */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;
    /**
    * 用户名
    */    
    private String userName;
    /**
    * 密码
    */    
    private String password;


}