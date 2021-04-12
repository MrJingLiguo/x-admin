package com.example.entity;

import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;


@Data
@TableName("t_comment")
public class Comment extends Model<Comment> {
    /**
      * 主键
      */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
      * 评论内容 
      */
    private String content;

    /**
      * 评论时间 
      */
    private String time;

    /**
      * 评论人 
      */
    private String username;

    /**
      * 评论人头像 
      */
    private String avatar;

    /**
      * 父评论 
      */
    private Long parentId;

}