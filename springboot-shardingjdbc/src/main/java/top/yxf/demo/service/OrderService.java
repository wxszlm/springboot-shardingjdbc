package top.yxf.demo.service;


import top.yxf.demo.entity.OrderEntity;

import java.util.List;

/**
 * @author DELL
 */
public interface OrderService {

    void add(OrderEntity orderEntity);

    OrderEntity getOrderByPrimaryKey(Long orderId);

    List<OrderEntity> listOrder();

}
