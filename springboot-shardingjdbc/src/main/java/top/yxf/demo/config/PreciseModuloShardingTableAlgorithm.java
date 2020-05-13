package top.yxf.demo.config;

import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;

import java.util.Collection;

public class PreciseModuloShardingTableAlgorithm implements PreciseShardingAlgorithm<Long> {


    /**
     *
     * @param collection 用来保存需要分表的所有表格
     * @param preciseShardingValue 用来指定分表的参数
     * @return
     */
    @Override
    public String doSharding(Collection<String> collection, PreciseShardingValue<Long> preciseShardingValue) {


        for (String each : collection) {
            if (each.endsWith(preciseShardingValue.getValue() % 2 + "")) {
                System.out.println(preciseShardingValue.getValue());
                return each;
            }
        }
        System.out.println(preciseShardingValue.getValue());
        throw new UnsupportedOperationException();
    }


}
