package com.hzl.itripbiz.contoller;

import com.hzl.common.condition.HotelRoomCondition;
import com.hzl.common.constants.ErrorCodeEnum;
import com.hzl.common.vo.HotelRoomVO;
import com.hzl.common.vo.ImageVO;
import com.hzl.common.vo.LabelDicVO;
import com.hzl.common.vo.ReturnResult;
import com.hzl.itripbiz.service.HotelRoomService;
import com.hzl.itripbiz.service.ImageService;
import com.hzl.itripbiz.service.LabelDicService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.hibernate.validator.constraints.pl.REGON;
import org.springframework.http.MediaType;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author : hzl
 * @version : 4.0
 * @project : itrip-project
 * @description : 酒店户型控制类
 * @date : 2020-11-27 09:26
 */
@Api(tags = "酒店户型控制器")
@RestController
@RequestMapping(value = "/api/hotelroom")
public class HotelRoomController {

    @Resource
    private HotelRoomService hotelRoomService;

    @Resource
    private LabelDicService labelDicService;

    @Resource
    private ImageService imageService;

    @ApiOperation(value = "查询酒店房间列表", httpMethod = "POST",
            protocols = "HTTP", produces = "application/json",
            response = ReturnResult.class, notes = "查询酒店房间列表")
    @RequestMapping(value = "/queryhotelroombyhotel", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ReturnResult queryHotelRoomHotel(@RequestHeader HotelRoomCondition condition) {
        //判断条件是否满足要求
        if (StringUtils.isEmpty(condition.getHotelId())) {
            return ReturnResult.error(ErrorCodeEnum.AUTH_PARAMETER_IS_EMPTY);
        }
        if
        (StringUtils.isEmpty(condition.getStartDate())||StringUtils.isEmpty(condition.getEndDate())) {
            return
                    ReturnResult.error(ErrorCodeEnum.AUTH_PARAMETER_IS_EMPTY);
        }
        //使用了localDateTime 中的 判断先后的API
        if (condition.getStartDate().isAfter(condition.getEndDate())){
            return
                    ReturnResult.error(ErrorCodeEnum.AUTH_PARAMETER_IS_EMPTY);
        }
        List<HotelRoomVO> originalRoomList = hotelRoomService.getHotelRoomVOByCondition(condition);
        List<List<HotelRoomVO>> hotelRoomList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(originalRoomList)) {
        //因为前端结构的原因，所以要多包一层，这里表示没看懂为什么
            originalRoomList.forEach(hotelRoomVO -> {
                List<HotelRoomVO> tempList = new ArrayList<>();
                tempList.add(hotelRoomVO);
                hotelRoomList.add(tempList);
            });
        }
        return ReturnResult.ok(hotelRoomList);
    }

    @ApiOperation(value = "查询酒店房间床型列表", httpMethod = "GET",
            protocols = "HTTP", produces = "application/json",
            response = ReturnResult.class, notes = "查询酒店床型列表")
    @RequestMapping(value = "/queryhotelroombed", method = RequestMethod.GET,
            produces = "application/json")
    public ReturnResult queryHotelRoomBed() {
        List<LabelDicVO> bedTypeLabelDicVOList =
                labelDicService.findBedTypeLabelDicVO();
        return ReturnResult.ok(bedTypeLabelDicVOList);
    }

    @ApiOperation(value = "根据targetId查询酒店房型图片(type=1)", httpMethod = "GET",
            protocols = "HTTP", produces = "application/json",
            response = ReturnResult.class, notes = "根据酒店房型ID查询酒店房型图片")
    @RequestMapping(value = "/getimg/{targetId}", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ReturnResult getImgByTargetId(@ApiParam(required = true, name =
            "targetId", value = "酒店房型ID") @PathVariable Long targetId) {
        if (StringUtils.isEmpty(targetId)) {
            return ReturnResult.error(ErrorCodeEnum.AUTH_PARAMETER_IS_EMPTY);
        }
        List<ImageVO> imageVOList =
                imageService.getImageVOByTypeAndTarget(1,targetId);
        if (CollectionUtils.isEmpty(imageVOList)) {
            return ReturnResult.error(ErrorCodeEnum.BIZ_DATA_NULL);
        }
        return ReturnResult.ok(imageVOList);
    }
}
