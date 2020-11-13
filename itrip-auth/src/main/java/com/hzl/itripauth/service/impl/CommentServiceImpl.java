package com.hzl.itripauth.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hzl.entiy.Comment;
import com.hzl.mapper.CommentMapper;
import com.hzl.itripauth.service.CommentService;

@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

}


