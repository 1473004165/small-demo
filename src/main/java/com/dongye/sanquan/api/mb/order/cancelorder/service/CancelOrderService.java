package com.dongye.sanquan.api.mb.order.cancelorder.service;

import org.springframework.stereotype.Service;

@Service
public interface CancelOrderService {
    /**
     * 修改订单状态
     */
    void updateOrderStatus(Integer orderId);
}
