package com.example.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.RuntimeUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.common.Result;
import com.example.entity.User;
import com.example.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("/api/user")
public class UserController {
    public static final ConcurrentHashMap<String, User> MAP = new ConcurrentHashMap<>();
    // 60分钟过期
    public static final int EXPIRE_MIN = 60;

    @Resource
    private UserService userService;


    /**
     * 登录
     *
     * @param user
     * @return
     */
    @PostMapping("/login")
    public Result<User> login(@RequestBody User user) {
        User res = userService.login(user);
        // 生成token
        String token = JWT.create().withAudience(res.getUsername()).withExpiresAt(DateUtil.offset(new Date(), DateField.MINUTE, EXPIRE_MIN))
                .sign(Algorithm.HMAC256(res.getPassword()));
        res.setToken(token);

        // 保存用户到缓存
        MAP.put(res.getUsername(), res);
        return Result.success(res);
    }

    /**
     * 注册
     *
     * @param user
     * @return
     */
    @PostMapping("/register")
    public Result<User> register(@RequestBody User user) {
        if (user.getPassword() == null) {
            user.setPassword("123456");
        }
        User dbUser = userService.register(user);
        return Result.success(dbUser);
    }

    @GetMapping("/logout/{username}")
    public Result<?> logout(@PathVariable String username) {
        MAP.remove(username);
        return Result.success();
    }

    @GetMapping("/online")
    public Result<Collection<User>> online() {
        return Result.success(MAP.values());
    }

    @PostMapping
    public Result<?> save(@RequestBody User user) {
        if (user.getPassword() == null) {
            user.setPassword("123456");
        }
        return Result.success(userService.save(user));
    }

    @PutMapping
    public Result<?> update(@RequestBody User user) {
        return Result.success(userService.updateById(user));
    }

    @DeleteMapping("/{id}")
    public Result<?> delete(@PathVariable Long id) {
        userService.removeById(id);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result<User> findById(@PathVariable Long id) {
        return Result.success(userService.getById(id));
    }

    @GetMapping("/detail/{username}")
    public Result<User> findByUsername(@PathVariable String username) {
        return Result.success(userService.getbyUsername(username));
    }

    @GetMapping
    public Result<List<User>> findAll() {
        return Result.success(userService.list());
    }

    @GetMapping("/page")
    public Result<IPage<User>> findPage(@RequestParam(required = false, defaultValue = "") String name,
                                        @RequestParam(required = false, defaultValue = "1") Integer pageNum,
                                        @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
        return Result.success(userService.page(new Page<>(pageNum, pageSize), Wrappers.<User>lambdaQuery().like(User::getUsername, name).orderByDesc(User::getId)));
    }

    @GetMapping("/export")
    public void export(HttpServletResponse response) throws IOException {

        List<Map<String, Object>> list = CollUtil.newArrayList();

        List<User> all = userService.list();
        for (User user : all) {
            Map<String, Object> row1 = new LinkedHashMap<>();
            row1.put("名称", user.getUsername());
            row1.put("手机", user.getPhone());
            row1.put("邮箱", user.getEmail());
            list.add(row1);
        }

        // 2. 写excel
        ExcelWriter writer = ExcelUtil.getWriter(true);
        writer.write(list, true);

        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        String fileName = URLEncoder.encode("用户信息", "UTF-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName + ".xlsx");

        ServletOutputStream out = response.getOutputStream();
        writer.flush(out, true);
        writer.close();
        IoUtil.close(System.out);
    }

}
