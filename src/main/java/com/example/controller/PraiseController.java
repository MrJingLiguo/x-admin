package com.example.controller;

import com.example.common.Result;
import com.example.entity.Praise;
import com.example.service.PraiseService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api/praise")
public class PraiseController {
    @Resource
    private PraiseService praiseService;

    @PostMapping
    public Result<?> save(@RequestBody Praise praise) {
        return Result.success(praiseService.save(praise));
    }

    @PutMapping
    public Result<?> update(@RequestBody Praise praise) {
        return Result.success(praiseService.updateById(praise));
    }

    @DeleteMapping("/{id}")
    public Result<?> delete(@PathVariable Long id) {
        praiseService.removeById(id);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result<Praise> findById(@PathVariable Long id) {
        return Result.success(praiseService.getById(id));
    }

    @GetMapping
    public Result<List<Praise>> findAll() {
        return Result.success(praiseService.list());
    }

    @GetMapping("/page")
    public Result<IPage<Praise>> findPage(@RequestParam(required = false, defaultValue = "") String name,
                                                @RequestParam(required = false, defaultValue = "1") Integer pageNum,
                                                @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
        return Result.success(praiseService.page(new Page<>(pageNum, pageSize), Wrappers.<Praise>lambdaQuery().like(Praise::getName, name)));
    }

}
