package top.yxf.demo;


import java.util.*;

public class SpringBootTest {

    public static void main(String[] args) {

        int[] nums = {-1, 0, 1, 2, -1, -4};

        System.out.println(threeSum(nums));

    }

    /**
     * 三数之和
     *
     * @param nums
     * @return
     */
    public static List<List<Integer>> threeSum(int[] nums) {

        // 首先定义两个集合
        List<List<Integer>> lists = new ArrayList<>();

        // 去重
        Map<Integer,Integer> map = new HashMap<Integer, Integer>();

        List<Integer> integers = new ArrayList<>();
        Set<List<Integer>> sets = new HashSet<>();

        for (int i = 0; i < nums.length - 1; i++) {
//
            int num1 = nums[i];
            for (int j = i + 1; j < nums.length - 1; j++) {

                int num3 = nums[j + 1];
                int num2 = nums[j];
                if (num1 + num2 + num3 == 0) {
                    integers.add(num1);
                    integers.add(num2);
                    integers.add(num3);
                    lists.add(integers);
                    continue;
                }
            }
        }

        for (int j = 0; j < lists.size(); j++) {
            sets.add(lists.get(j));
        }

        final Iterator<List<Integer>> iterator = sets.iterator();
        while (iterator.hasNext()) {

            System.out.println(iterator.next() + "------>iterator.next();");
        }

        lists.clear();

        for (int i = 0; i < sets.size() ; i++) {

        }




        return lists;

    }


}
