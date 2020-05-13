package top.yxf.demo.entity;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name="goods")
@Table
@Data
public class Goods {

    @Id
    private Long goodsId;

    private String goodsName;

    private Long goodsType;

}
