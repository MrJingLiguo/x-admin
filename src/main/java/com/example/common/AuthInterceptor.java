package com.example.common;

import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.entity.Permission;
import com.example.entity.User;
import com.example.exception.CustomException;
import com.example.service.UserService;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * 拦截器
 */
public class AuthInterceptor implements HandlerInterceptor {

    @Resource
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        String token = request.getHeader("token");
        if (StrUtil.isBlank(token)) {
            throw new CustomException("401", "未获取到token, 请重新登录");
        }
        String username;
        try {
            username = JWT.decode(token).getAudience().get(0);
        } catch (JWTDecodeException j) {
            throw new CustomException("401", "权限验证失败, 请重新登录");
        }
        User user = userService.getbyUsername(username);
        if (user == null) {
            throw new CustomException("401", "用户不存在, 请重新登录");
        }
        // 验证 token
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(user.getPassword())).build();
        try {
            jwtVerifier.verify(token);
        } catch (JWTVerificationException e) {
            throw new CustomException("401", "token不合法, 请重新登录");
        }

//        / 校验权限菜单
        String servletPath = request.getServletPath();
        List<Permission> permissions = user.getPermission();
        boolean isOk = false;
        for (Object obj : permissions) {
            LinkedHashMap map = (LinkedHashMap) obj;
            String flag = (String) map.get("flag");
            if (servletPath.contains(flag)) {
                isOk = true;
                break;
            }
        }
        if (!isOk) {
            throw new CustomException("402", "权限验证失败");
        }

        return true;
    }

}
