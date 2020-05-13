package top.yxf.demo.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.GeneratorType;

import javax.persistence.*;
import java.io.Serializable;


@Entity(name = "t_order")
@Table
@JsonIgnoreProperties(value = { "hibernateLazyInitializer"})
public class OrderEntity implements Serializable {

    @Id
    //    @GeneratedValue(strategy = GenerationType.AUTO)
    private long orderId;
    private long userId;
    private String status;



    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
