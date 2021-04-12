package com.example.controller;

import com.example.common.Result;
import com.example.entity.Collect;
import com.example.service.CollectService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api/collect")
public class CollectController {
    @Resource
    private CollectService collectService;

    @PostMapping
    public Result<?> save(@RequestBody Collect collect) {
        return Result.success(collectService.save(collect));
    }

    @PutMapping
    public Result<?> update(@RequestBody Collect collect) {
        return Result.success(collectService.updateById(collect));
    }

    @DeleteMapping("/{id}")
    public Result<?> delete(@PathVariable Long id) {
        collectService.removeById(id);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result<Collect> findById(@PathVariable Long id) {
        return Result.success(collectService.getById(id));
    }

    @GetMapping
    public Result<List<Collect>> findAll() {
        return Result.success(collectService.list());
    }

    @GetMapping("/page")
    public Result<IPage<Collect>> findPage(@RequestParam(required = false, defaultValue = "") String name,
                                                @RequestParam(required = false, defaultValue = "1") Integer pageNum,
                                                @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
        return Result.success(collectService.page(new Page<>(pageNum, pageSize), Wrappers.<Collect>lambdaQuery().like(Collect::getName, name)));
    }

}
