package com.example.didi.domain.entity;
/*
*@author  zhangyufeng
*@data 2019/4/18 22:29
*/

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

//@Date   lombok自动注解get&set
@Data
public class TradeEntity {

    /**
     * 订单ID（主键）
     */

    private Long orderId;

    /**
     * 业务线ID
     */

    private Long businessId;

    /**
     * 品类ID
     */

    private Long categoryId;

    /**
     * 服务项ID
     */

    private Long serviceId;

    /**
     * 用户ID
     */

    private Long userId;

    /**
     * 服务者ID
     */

    private Long sellerId;

    /**
     * 城市ID
     */

    private Long cityId;

    /**
     * 交易状态（1:未支付 2:部分支付 3:已付清 4:超额支付 5:待后付 6:已确认 7:已退款 8:已作废）
     */

    private Short tradeStatus;

    /**
     * 订单原价
     */

    private BigDecimal orderPrice;

    /**
     * 订单结算价
     */

    private BigDecimal settlePrice;

    /**
     * 订单应收总额 = 订单总额 - 除优惠券以外的所有优惠（业务线计算）
     */

    private BigDecimal receivablePrice;

    /**
     * 还需支付金额 = 订单应收总额 - 优惠券优惠金额 - 上单结余（由交易计算）
     */

    private BigDecimal needPrice;

    /**
     * 订单服务开始时间
     */

    private Date serviceTime;

    /**
     * 服务总金额（存在活动价时，等于活动价+活动优惠+附加项费用；不存在活动价时，等于订单总额+附加项费用）
     */

    private BigDecimal servicePrice;

    /**
     * 附加项费用
     */

    private BigDecimal additionPrice;

    /**
     * 预付比例
     */

    private Short advanceRatio;

    /**
     * 已支付金额(预付支付金额)
     */

    private BigDecimal hasPaidAmount;

    /**
     * 现金支付金额
     */

    private BigDecimal cashPaidAmount;

    /**
     * 线上支付金额
     */

    private BigDecimal onlinePaidAmount;

    /**
     * 优惠金额（活动优惠+优惠券金额+优惠券券码+次卡购卡优惠+折扣卡优惠）
     */

    private BigDecimal discountAmount;

    /**
     * 退款金额
     */

    private BigDecimal refundAmount;

    /**
     * 罚款金额
     */

    private BigDecimal fineAmount;

    /**
     * 扩展参数
     */

    private String extendParam;

    /**
     * 更新时间
     */

    private Date updateTime;

    /**
     * 创建时间
     */

    private Date createTime;
}
