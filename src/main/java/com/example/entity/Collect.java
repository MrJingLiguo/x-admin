package com.example.entity;

import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;


@Data
@TableName("t_collect")
public class Collect extends Model<Collect> {
    /**
      * 主键
      */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
      * 收藏品名称 
      */
    private String name;

    /**
      * 收藏品链接 
      */
    private String url;

    /**
      * 收藏人 
      */
    private String username;

    /**
      * 收藏时间 
      */
    private String time;

    /**
      * 收藏品id 
      */
    private Long foreignId;

}