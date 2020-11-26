package com.hzl.itripbiz.service;

import com.hzl.common.vo.AreaDicVO;

import java.util.List;

/**
 * @author : hzl
 * @version : 4.0
 * @project : itrip-project
 * @description : 城市业务类
 * @date : 2020-11-21 10:22
 */
public interface AreaDicService {

    /**
     * 查询热门城市Id和城市名
     * @param type
     * @return
     */
    List<AreaDicVO> findAreaDicVOByType(Integer type);

    /**
     * 根据城市Id查询商圈
     * @param cityId
     * @return
     */
    List<AreaDicVO> findAreaDicVOByCityId(Long cityId);

}
