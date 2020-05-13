package top.yxf.demo.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import top.yxf.demo.entity.OrderEntity;

/**
 * @author DELL
 */
@Repository
public interface OrderDao extends JpaRepository<OrderEntity,Long> {
}
