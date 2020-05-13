package top.yxf.demo.service.impl;

import jdk.nashorn.internal.ir.annotations.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.yxf.demo.dao.OrderDao;
import top.yxf.demo.entity.OrderEntity;
import top.yxf.demo.service.OrderService;

import javax.transaction.Transactional;
import java.util.List;


@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Override
    public void add(OrderEntity orderEntity) {
        orderDao.save(orderEntity);
    }

    @Override
    public OrderEntity getOrderByPrimaryKey(Long orderId) {
        return orderDao.getOne(orderId);
    }

    @Override
    public List<OrderEntity> listOrder() {
        return orderDao.findAll();
    }
}
