package com.hzl.itripbiz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hzl.common.constants.ErrorCodeEnum;
import com.hzl.common.vo.AreaDicVO;
import com.hzl.entity.AreaDic;
import com.hzl.exception.ServiceException;
import com.hzl.itripbiz.service.AreaDicService;
import com.hzl.mapper.AreaDicMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author : hzl
 * @version : 4.0
 * @project : itrip-project
 * @description :
 * @date : 2020-11-21 10:23
 */
@Service
public class AreaDicServiceImpl  extends ServiceImpl<AreaDicMapper, AreaDic> implements AreaDicService {

    @Override
    public List<AreaDicVO> findAreaDicVOByType(Integer type) {
        QueryWrapper<AreaDic> areaDicQueryWrapper = new QueryWrapper<AreaDic>();
        areaDicQueryWrapper.select("id", "name")
                .eq("is_hot", 1)
                .eq("is_china", type);
        List<AreaDic> areaDicList = this.list(areaDicQueryWrapper);
        if (CollectionUtils.isEmpty(areaDicList)) {
            throw new ServiceException(ErrorCodeEnum.BIZ_DATA_NULL);
        }
        List<AreaDicVO> areaDicVOList= areaDicList.stream().map(areaDic -> {
            AreaDicVO areaDicVO = new AreaDicVO();
            BeanUtils.copyProperties(areaDic, areaDicVO);
            return areaDicVO;
        }).collect(Collectors.toList());
        return areaDicVOList;
    }

    @Override
    public List<AreaDicVO> findAreaDicVOByCityId(Long cityId) {
        QueryWrapper<AreaDic> areaDicQueryWrapper = new QueryWrapper<>();
        areaDicQueryWrapper.select("id", "name")
                .eq("is_trading_area", 1)
                .eq("parent", cityId);
        List<AreaDic> areaDicList = this.list(areaDicQueryWrapper);
        if (CollectionUtils.isEmpty(areaDicList)) {
            throw new ServiceException(ErrorCodeEnum.BIZ_DATA_NULL);
        }
        //调用公共方法，完成类型转换
        List<AreaDicVO> areaDicVOList = areaDicToAreaDicVO(areaDicList);
        return areaDicVOList;
    }

    /**
     * 将List<AreaDic>类型的数据转换为List<AreaDicVO>
     * @param areaDicList
     * @return
     */
    private List<AreaDicVO> areaDicToAreaDicVO(List<AreaDic> areaDicList) {
        //完成类型转换
        return areaDicList.stream().map(areaDic -> {
            AreaDicVO areaDicVO = new AreaDicVO();
            BeanUtils.copyProperties(areaDic, areaDicVO);
            return areaDicVO;
        }).collect(Collectors.toList());
    }

}
