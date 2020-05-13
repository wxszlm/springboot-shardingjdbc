package top.yxf.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import top.yxf.demo.entity.Goods;

import java.util.List;

@Repository
public interface GoodsRepository extends JpaRepository<Goods,Long> {

    List<Goods> findAllByGoodsIdBetween(Long goodsId1,Long goodsId2);

    List<Goods> findAllByGoodsIdIn(List<Long> goodsIds);

    List<Goods> findAllByGoodsName(String goodName);

    List<Goods> findTopByGoodsIdIsAfterOrderByGoodsIdAsc(Long goodIds);

    List<Goods> findTopByGoodsIdOrderByGoodsIdAsc(Long goodIds);



}
