package top.yxf.demo.controller;


import com.dangdang.ddframe.rdb.sharding.keygen.KeyGenerator;
import org.hibernate.criterion.Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import top.yxf.demo.dao.GoodsRepository;
import top.yxf.demo.entity.Goods;

import java.util.ArrayList;
import java.util.List;

@RestController
public class GoodsController {

    @Autowired
    private KeyGenerator keyGenerator;

    @Autowired
    private GoodsRepository goodsRepository;

    @GetMapping("save")
    public String save(){
        for(int i= 1 ; i <= 1000 ; i ++){
            System.out.println(keyGenerator.generateKey().longValue());
            Goods goods = new Goods();
            goods.setGoodsId(keyGenerator.generateKey().longValue());
            goods.setGoodsName( "shangpin" + i);
            goods.setGoodsType((long) (i+1));
            goodsRepository.save(goods);
        }
        return "success";
    }

    @GetMapping("select")
    public String select(){
        return goodsRepository.findAll().toString();
    }

    @GetMapping("delete")
    public void delete(){
        goodsRepository.deleteAll();
    }

    @GetMapping("query1")
    public Object query1(){
        return goodsRepository.findAllByGoodsIdBetween(10L, 30L);
    }



    @GetMapping("query2")
    public Object query2(){
        List<Long> goodsIds = new ArrayList<>();
        goodsIds.add(10L);
        goodsIds.add(15L);
        goodsIds.add(20L);
        goodsIds.add(25L);
        return goodsRepository.findAllByGoodsIdIn(goodsIds);
    }
    @GetMapping("query3")
    public Object query3(String goodName){

//        Example example = Example.create(Goods.class);
//        example.excludeProperty(goodName);
//
//        return goodsRepository.findOne(example);
        return goodsRepository.findAllByGoodsName(goodName);
    }


    @GetMapping("query4")
    public Object query4(Long goodId){

        return goodsRepository.findTopByGoodsIdIsAfterOrderByGoodsIdAsc(goodId);
    }

    @GetMapping("query5")
    public Object query5(Long goodId){

        return goodsRepository.findTopByGoodsIdOrderByGoodsIdAsc(goodId);
    }


}
