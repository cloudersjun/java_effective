import com.alibaba.fastjson.JSONObject;
import org.apache.commons.codec.binary.*;
import org.apache.commons.lang3.ArrayUtils;
import sun.misc.BASE64Decoder;

import java.io.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * Created by lenovo on 2017/5/25.
 */
public class Main {
    public static void main(String []args) throws IOException {
//        Map<String,String> map=new TreeMap<>();
//        map.put("a","a");
//        map.put("b","b");
//        map.put("ab","ab");
//        Integer[] a={2,3,4,7,8,5,9};
//        Arrays.sort(a);
//        System.out.print(Arrays.deepToString(a));
//        int i1=1;
//        int i2=Integer.MAX_VALUE;
//        int result=(i1+i2) >>> 1;
//        System.out.print(result);
////        binarySort(a,0,7,1);
//        System.out.print(Arrays.deepToString(a));
//        Set<Integer> set=new HashSet();
//        set.add(3);
//        List<String > list=new ArrayList<>();
//        System.out.print(set.contains(3));
//        String bas4="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";
//        OutputStream out = new FileOutputStream("F://srv//aaaa.png");
//        try {
//            // Base64解码
//            byte[] bytes = org.apache.commons.codec.binary.Base64.decodeBase64(bas4.getBytes());
//            for (int i = 0; i < bytes.length; ++i) {
//                if (bytes[i] < 0) {// 调整异常数据
//                    bytes[i] += 256;
//                }
//            }
//            // 生成jpeg图片
//            out.write(bytes);
//            out.flush();
//        } finally {
//            try {
//                out.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        System.out.print("{\"pram\":[{\"sequence\":[3,2,1],\"proportion\":0.7},{{\"sequence\":[2,3,1]," +
//                "\"proportion\":0.3}]}");
//        JSONObject js =new JSONObject();
//        js.put("2",null);
//        js.put("dd","");
//        System.out.print(js.toJSONString());
//        Map<String,String > map = new ConcurrentHashMap<>();
        List<String> list=new ArrayList<>();
        list.add("2");
        list.add("3");
        Object[] s= list.toArray();
        System.out.print(s.length);
    }
    private static  void binarySort(Integer[] a, int lo, int hi, int start) {
        assert lo <= start && start <= hi;
        if (start == lo)
            start++;
        for ( ; start < hi; start++) {
            Integer pivot = a[start];

            // Set left (and right) to the index where a[start] (pivot) belongs
            int left = lo;
            int right = start;
            assert left <= right;
            /*
             * Invariants:
             *   pivot >= all in [lo, left).
             *   pivot <  all in [right, start).
             */
            while (left < right) {
                int mid = (left + right) >>> 1;
                if (pivot-a[mid] < 0)
                    right = mid;
                else
                    left = mid + 1;
            }
            assert left == right;

            /*
             * The invariants still hold: pivot >= all in [lo, left) and
             * pivot < all in [left, start), so pivot belongs at left.  Note
             * that if there are elements equal to pivot, left points to the
             * first slot after them -- that's why this sort is stable.
             * Slide elements over to make room for pivot.
             */
            int n = start - left;  // The number of elements to move
            // Switch is just an optimization for arraycopy in default case
            switch (n) {
                case 2:  a[left + 2] = a[left + 1];
                case 1:  a[left + 1] = a[left];
                    break;
                default: System.arraycopy(a, left, a, left + 1, n);
            }
            a[left] = pivot;
        }
    }
}
