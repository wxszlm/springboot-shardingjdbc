package top.yxf.demo.config;


import org.apache.shardingsphere.api.config.sharding.KeyGeneratorConfiguration;
import org.apache.shardingsphere.api.config.sharding.ShardingRuleConfiguration;
import org.apache.shardingsphere.api.config.sharding.TableRuleConfiguration;
import org.apache.shardingsphere.api.config.sharding.strategy.InlineShardingStrategyConfiguration;
import org.apache.shardingsphere.api.config.sharding.strategy.StandardShardingStrategyConfiguration;
import org.apache.shardingsphere.shardingjdbc.api.ShardingDataSourceFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import top.yxf.demo.util.DataSourceUtils;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Configuration
public class ShardingJdbcConfig {

    @Bean
    public DataSource getDataSource() throws SQLException {
        return buildDataSource();
    }

    private DataSource buildDataSource() throws SQLException {
        ShardingRuleConfiguration shardingRuleConfiguration = new ShardingRuleConfiguration();
        // 设置分表规则
        shardingRuleConfiguration.getTableRuleConfigs().add(getOrderTableRuleConfiguration());
        // 绑定
        shardingRuleConfiguration.getBindingTableGroups().add("t_order");
        // 设置数据源分片规则
        // shardingColumn 用来指定分库的字段  algorithmExpression 用来指定按什么规则进行分库
        shardingRuleConfiguration.setDefaultDatabaseShardingStrategyConfig(new InlineShardingStrategyConfiguration("user_id", "ds${user_id % 2}"));
        // 设置数据表分片规则
        shardingRuleConfiguration.setDefaultTableShardingStrategyConfig(new StandardShardingStrategyConfiguration("order_id",new PreciseModuloShardingTableAlgorithm()));
        return ShardingDataSourceFactory.createDataSource(createDataSourceMap(),shardingRuleConfiguration,getProperties());
    }

    public static TableRuleConfiguration getOrderTableRuleConfiguration(){
        // 设置逻辑表
        // 2 ： 真实存在的节点，由数据源 + 表明组成， ds${0..1} 代表 数据库选择 ds 后缀为 0 - 1 之间，t_order 代表数据表 t_order 后缀 0 - 1 之间
        // logicTable 逻辑表名
        // actualDataNodes 实际上存在的表。有数据源和表名组成ds${0..1}指数据库选择后缀为0-1之间的数据库 t_order_${0..1}指的是我实际存在的表（t_order_0,t_order_1
        TableRuleConfiguration result = new TableRuleConfiguration("t_order","ds${0..1}.t_order_${0..1}");
        result.setKeyGeneratorConfig(getKeyGeneratorConfiguration());
        return result;

    }

    public static KeyGeneratorConfiguration getKeyGeneratorConfiguration(){
        // 算法，字段名
        // 主键生成列
        KeyGeneratorConfiguration result = new KeyGeneratorConfiguration("SNOWFLAKE","order_id",getProperties());
        return result;

    }

    public static Properties getProperties(){
        Properties result = new Properties();
        result.setProperty("worker.id","123");
        return result;
    }

    Map<String,DataSource> createDataSourceMap(){
        DataSource ds0 = DataSourceUtils.createDataSource("ds0");
        DataSource ds1 = DataSourceUtils.createDataSource("ds1");
        Map<String,DataSource> map = new HashMap<>(2);
        map.put("ds0",ds0);
        map.put("ds1",ds1);
        return map;
    }



}
