package com.hzl.itripbiz.contoller;

import com.hzl.common.constants.ErrorCodeEnum;
import com.hzl.common.vo.*;
import com.hzl.itripbiz.service.AreaDicService;
import com.hzl.itripbiz.service.HotelService;
import com.hzl.itripbiz.service.ImageService;
import com.hzl.itripbiz.service.LabelDicService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author : hzl
 * @version : 4.0
 * @project : itrip-project
 * @description : 酒店控制器
 * @date : 2020-11-21 10:31
 */
@Slf4j
@RestController
@RequestMapping(value = "/api/hotel")
@Api(tags = "酒店控制器")
public class HotelController {

    @Resource
    private HotelService hotelService;

    @Resource
    private AreaDicService areaDicService;

    @Resource
    private LabelDicService labelDicService;

    @Resource
    private ImageService imageService;

    /**
     * 查询热门城市接口
     * @param type
     * @return
     */
    @ApiOperation(value = "查询热门城市", httpMethod = "GET",
            protocols = "HTTP", produces = "application/json",
            response = ReturnResult.class, notes = "查询国内、国外的热门城市(1:国内 2:国外)" +
            "<p>成功：success = ‘true’ | 失败：success = ‘false’ 并返回错误码，如下： </p>" +
            "<p>错误码：</p>" +
            "<p>10201 : hotelId不能为空 </p>" +
            "<p>10202 : 系统异常,获取失败</p>")
    @RequestMapping(value = "/queryhotcity/{type}",
            produces = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.GET)
    public ReturnResult queryHotCity(@PathVariable("type") Integer type) {
        if (StringUtils.isEmpty(type)) {
            //参数为空
            return ReturnResult.error(ErrorCodeEnum.AUTH_PARAMETER_IS_EMPTY);
        }
        List<AreaDicVO> areaDicVOList = areaDicService.findAreaDicVOByType(type);
        if (CollectionUtils.isEmpty(areaDicVOList)) {
            //数据为空
            return ReturnResult.error(ErrorCodeEnum.BIZ_DATA_NULL);
        }
        return ReturnResult.ok(areaDicVOList);
    }

    /**
     * 查询商圈接口
     * @param cityId
     * @return
     */
    @ApiOperation(value = "查询商圈", httpMethod = "GET",
            protocols = "HTTP", produces = "application/json",
            response = ReturnResult.class, notes = "根据城市查询商圈" +
            "<p>成功：success = ‘true’ | 失败：success = ‘false’ 并返回错误码，如下：</p>"
            +
            "<p>错误码：</p>" +
            "<p>10203 : cityId不能为空 </p>" +
            "<p>10204 : 系统异常,获取失败</p>")
    @RequestMapping(value = "/querytradearea/{cityId}" +
            "", produces = "application/json", method = RequestMethod.GET)
    @ResponseBody
    public ReturnResult queryTradeArea(@PathVariable Long cityId) {
        if (StringUtils.isEmpty(cityId)) {
            //如果参数为空
            return ReturnResult.error(ErrorCodeEnum.AUTH_PARAMETER_IS_EMPTY);
        }
        List<AreaDicVO> areaDicVOList = areaDicService.findAreaDicVOByCityId(cityId);
        if (CollectionUtils.isEmpty(areaDicVOList)) {
            //数据为空
            return ReturnResult.error(ErrorCodeEnum.BIZ_DATA_NULL);
        }
        return ReturnResult.ok(areaDicVOList);
    }

    /***
     * 查询酒店特色列表
     */
    @ApiOperation(value = "查询酒店特色列表", httpMethod = "GET",
            protocols = "HTTP", produces = "application/json",
            response = ReturnResult.class, notes = "获取酒店特色(用于查询页列表)" +
            "<p>成功：success = ‘true’ | 失败：success = ‘false’ 并返回错误码，如下：</p>"
            +
            "<p>错误码: </p>" +
            "<p>10205: 系统异常,获取失败</p>")
    @RequestMapping(value = "/queryhotelfeature", produces =
            MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.GET)
    public ReturnResult queryHotelFeature() {
        List<LabelDicVO> labelDicVOList = labelDicService.findLabelDicVO();
        if (CollectionUtils.isEmpty(labelDicVOList)) {
            return ReturnResult.error(ErrorCodeEnum.BIZ_DATA_NULL);
        }
        return ReturnResult.ok(labelDicVOList);
    }

    /**
     * 根据酒店Id查询酒店设施
     * @param id
     * @return
     */
    @ApiOperation(value = "根据酒店id查询酒店设施", httpMethod = "GET",
            protocols = "HTTP", produces = "application/json",
            response = ReturnResult.class, notes = "根据酒店id查询酒店设施")
    @ApiImplicitParam(required = true, name = "id", value = "酒店ID",paramType =
            "path")
    @RequestMapping(value = "/queryhotelfacilities/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public ReturnResult queryHotelFacilities(
            @PathVariable Long id) {
        if (StringUtils.isEmpty(id)) {
            return ReturnResult.error(ErrorCodeEnum.AUTH_PARAMETER_IS_EMPTY);
        }
        SearchFacilitiesHotelVO facilitiesHotelVO =
                hotelService.getHotelFacilitiesById(id);
        if (StringUtils.isEmpty(facilitiesHotelVO)) {
            return ReturnResult.error(ErrorCodeEnum.BIZ_DATA_NULL);
        }
        return ReturnResult.ok(facilitiesHotelVO);
    }

    /***
     * 根据酒店id查询酒店特色和介绍
     */
    @ApiOperation(value = "根据酒店id查询酒店特色和介绍", httpMethod = "GET",
            protocols = "HTTP", produces = "application/json",
            response = ReturnResult.class, notes = "根据酒店id查询酒店特色和介绍")
    @RequestMapping(value = "/queryhoteldetails/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.GET)
    @ApiImplicitParam(required = true, name = "id", value = "酒店ID",paramType =
            "path")
    public ReturnResult queryHotelDetails(@PathVariable Long id) {
        if (StringUtils.isEmpty(id)) {
            return ReturnResult.error(ErrorCodeEnum.AUTH_PARAMETER_IS_EMPTY);
        }
        List<SearchDetailHotelVO> detailHotelVOList =
                hotelService.getHotelDetailVOById(id);
        if (CollectionUtils.isEmpty(detailHotelVOList)) {
            return ReturnResult.error(ErrorCodeEnum.BIZ_DATA_NULL);
        }
        return ReturnResult.ok(detailHotelVOList);
    }

    /**
     * 根据targetId查询酒店图片
     * @param targetId
     * @return
     */
    @ApiOperation(value = "根据targetId查询酒店图片(type=0)", httpMethod = "GET",
            protocols = "HTTP", produces = "application/json",
            response = ReturnResult.class, notes = "根据酒店ID查询酒店图片")
    @RequestMapping(value = "/getimg/{targetId}", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParam(required = true, name = "targetId", value = "酒店ID",paramType
            = "path")
    public ReturnResult getImgByTargetId(@PathVariable Long targetId) {
        if (StringUtils.isEmpty(targetId)) {
            return ReturnResult.error(ErrorCodeEnum.AUTH_PARAMETER_IS_EMPTY);
        }
        List<ImageVO> imageVOList = imageService.getImageVOByHotelId(targetId);
        if (CollectionUtils.isEmpty(imageVOList)) {
            return ReturnResult.error(ErrorCodeEnum.BIZ_DATA_NULL);
        }
        return ReturnResult.ok(imageVOList);
    }

}
