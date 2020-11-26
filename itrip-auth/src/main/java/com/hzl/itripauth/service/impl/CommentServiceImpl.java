package com.hzl.itripauth.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hzl.entity.Comment;
import com.hzl.itripauth.service.CommentService;
import com.hzl.mapper.CommentMapper;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

}


