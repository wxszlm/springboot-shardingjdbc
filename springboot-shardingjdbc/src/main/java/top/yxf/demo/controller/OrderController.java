package top.yxf.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import top.yxf.demo.entity.OrderEntity;
import top.yxf.demo.service.OrderService;
import top.yxf.demo.util.SnowFlakeUtils;

import java.util.Random;

@RestController
@RequestMapping(value = "/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    SnowFlakeUtils snowFlake = new SnowFlakeUtils(5, 6);

    @RequestMapping(value = "/add")
    public void add(){
        Random random = new Random();
        for (int i = 0; i < 1000 ; i++) {
            int userId = random.nextInt(1000000000);
            int orderId = random.nextInt(1000000000);
            OrderEntity entity = new OrderEntity();
            entity.setOrderId(snowFlake.nextId(true));
            entity.setStatus("1");
            entity.setUserId(userId);

            orderService.add(entity);

        }


    }

    @RequestMapping(value = "/get/{orderId}",method = RequestMethod.GET)
    public OrderEntity get(@PathVariable(value = "orderId") Long orderId){
        return orderService.getOrderByPrimaryKey(orderId);
    }

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public Integer listOrder(){
        return orderService.listOrder().size();
    }

}
