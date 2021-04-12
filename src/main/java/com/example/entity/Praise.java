package com.example.entity;

import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;


@Data
@TableName("t_praise")
public class Praise extends Model<Praise> {
    /**
      * 主键
      */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
      * 点赞名称 
      */
    private String name;

    /**
      * 点赞人 
      */
    private String username;

    /**
      * 点赞时间 
      */
    private String time;

    /**
      * 点赞模块id 
      */
    private Long foreignId;

}