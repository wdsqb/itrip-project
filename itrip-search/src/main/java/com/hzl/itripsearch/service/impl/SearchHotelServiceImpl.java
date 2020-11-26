package com.hzl.itripsearch.service.impl;


import com.hzl.common.constants.ErrorCodeEnum;
import com.hzl.exception.ServiceException;
import com.hzl.itripsearch.condition.SearchHotCityCondition;
import com.hzl.itripsearch.dao.SearchHotelRepository;
import com.hzl.itripsearch.service.SearchHotelService;
import com.hzl.itripsearch.vo.HotelVO;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;


/**
 * @author : hzl
 * @version : 4.0
 * @project : itrip-project
 * @description :搜索酒店Service层实现
 * @date : 2020-11-21 15:57
 */
@Service
public class SearchHotelServiceImpl implements SearchHotelService {

    private static final Logger log = LoggerFactory.getLogger(SearchHotelServiceImpl.class);

    @Resource
    private SearchHotelRepository hotelRepository;

    @Override
    public List<HotelVO> searchHotelByHotCity(Long cityId, Long pageSize) {
        List<HotelVO> hotelVOList = hotelRepository.findByCityId(cityId)
                .stream().limit(pageSize).collect(Collectors.toList());
        if (CollectionUtils.isEmpty(hotelVOList)) {
            throw new ServiceException(ErrorCodeEnum.BIZ_DATA_NULL);
        }
        return hotelVOList;
    }

    @Override
    public Page<HotelVO> searchHotelPage(SearchHotCityCondition condition) {
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        //将所有的条件动态封装到BoolQueryBuilder对象中
        BoolQueryBuilder builder = QueryBuilders.boolQuery();
        //判断条件是否为空，来动态拼接条件
        if (StringUtils.hasText(condition.getDestination())) {
        //详情
            builder.must(QueryBuilders.matchQuery("redundantCityName",condition.getDestination()));
        }
        if (!StringUtils.isEmpty(condition.getHotelLevel())) {
        //酒店级别
            builder.must(QueryBuilders.matchQuery("hotelLevel",
                    condition.getHotelLevel()));
        }
        if (StringUtils.hasText(condition.getKeywords())) {
        //关键词，全文搜索
            builder.must(QueryBuilders.queryStringQuery(condition.getKeywords()));
        }
        if (!StringUtils.isEmpty(condition.getMinPrice())) {
        //最低价
            builder.filter(QueryBuilders.rangeQuery("minPrice").gt(condition.getMinPrice())
            );
        }
        if (!StringUtils.isEmpty(condition.getMaxPrice())) {
        //最高价
            builder.filter(QueryBuilders.rangeQuery("maxPrice").lt(condition.getMaxPrice()));
        }
        if (StringUtils.hasText(condition.getFeatureIds())) {
            //酒店特色
            builder.must(QueryBuilders.termQuery("featureId",condition.getFeatureIds()));
        }
        //指定排序
        Sort.Order order = null;
        if (!StringUtils.isEmpty(condition.getAscSort())) {
            order = Sort.Order.asc(condition.getAscSort());
        }
        if (!StringUtils.isEmpty(condition.getDescSort())) {
            order = Sort.Order.desc(condition.getDescSort());
        }
        PageRequest pageable = PageRequest.of(condition.getPageNo(),
                condition.getPageSize());
        if (order != null) {
            pageable = PageRequest.of(condition.getPageNo(),
                    condition.getPageSize(), Sort.by(order));
        }
        queryBuilder.withQuery(builder);
        queryBuilder.withPageable(pageable);
        Page<HotelVO> hotelVOPage =
                hotelRepository.search(queryBuilder.build());
        return hotelVOPage;
    }

}
