package com.example.controller;

import com.example.common.Result;
import com.example.entity.Comment;
import com.example.service.CommentService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api/comment")
public class CommentController {
    @Resource
    private CommentService commentService;

    @PostMapping
    public Result<?> save(@RequestBody Comment comment) {
        return Result.success(commentService.save(comment));
    }

    @PutMapping
    public Result<?> update(@RequestBody Comment comment) {
        return Result.success(commentService.updateById(comment));
    }

    @DeleteMapping("/{id}")
    public Result<?> delete(@PathVariable Long id) {
        commentService.removeById(id);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result<Comment> findById(@PathVariable Long id) {
        return Result.success(commentService.getById(id));
    }

    @GetMapping
    public Result<List<Comment>> findAll() {
        return Result.success(commentService.list());
    }

    @GetMapping("/page")
    public Result<IPage<Comment>> findPage(@RequestParam(required = false, defaultValue = "") String name,
                                                @RequestParam(required = false, defaultValue = "1") Integer pageNum,
                                                @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
        return Result.success(commentService.page(new Page<>(pageNum, pageSize), Wrappers.<Comment>lambdaQuery().like(Comment::getContent, name)));
    }

}
