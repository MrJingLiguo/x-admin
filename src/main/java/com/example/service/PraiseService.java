package com.example.service;

import com.example.entity.Praise;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.mapper.PraiseMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class PraiseService extends ServiceImpl<PraiseMapper, Praise> {

    @Resource
    private PraiseMapper praiseMapper;

}
