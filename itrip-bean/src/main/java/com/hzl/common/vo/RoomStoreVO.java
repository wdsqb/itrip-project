package com.hzl.common.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @author : hzl
 * @version : 4.0
 * @project : itrip-project
 * @description : 房间库存展示实体类
 * @date : 2020-11-27 09:42
 */
public class RoomStoreVO implements Serializable {

    private static final long serialVersionUID = 1893635618597692163L;
    private Long hotelId;
    private Long roomId;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private Integer count;
    private String hotelName;
    private Integer store;
    private BigDecimal price;
}
