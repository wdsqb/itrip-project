package com.hzl.itripbiz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hzl.common.vo.LabelDicVO;
import com.hzl.entity.LabelDic;
import com.hzl.itripbiz.service.LabelDicService;
import com.hzl.mapper.LabelDicMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LabelDicServiceImpl extends ServiceImpl<LabelDicMapper, LabelDic> implements LabelDicService {

    @Override
    public List<LabelDicVO> findLabelDicVO() {
        QueryWrapper<LabelDic> labelDicQueryWrapper = new QueryWrapper<>();
        labelDicQueryWrapper.select("id", "name", "description", "pic")
                .eq("parent_id", 16);
        List<LabelDic> labelDicList = this.list(labelDicQueryWrapper);
        //类型转换
        List<LabelDicVO> labelDicVOList = labelDicList.stream().map(labelDic -> {
            LabelDicVO labelDicVO = new LabelDicVO();
            BeanUtils.copyProperties(labelDic, labelDicVO);
            return labelDicVO;
        }).collect(Collectors.toList());
        return labelDicVOList;

    }
}


