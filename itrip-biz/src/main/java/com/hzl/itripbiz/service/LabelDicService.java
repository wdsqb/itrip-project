package com.hzl.itripbiz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hzl.common.vo.LabelDicVO;
import com.hzl.entity.LabelDic;

import java.util.List;

public interface LabelDicService extends IService<LabelDic> {

    /**
     * 查询酒店特色
     * @return
     */
    List<LabelDicVO> findLabelDicVO();

    /**
     * 查询所有的床型
     * @return
     */
    List<LabelDicVO> findBedTypeLabelDicVO();

}


