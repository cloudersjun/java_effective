import java.util.*;

/**
 * Created by lenovo on 2017/5/25.
 */
public class Main {
    public static void main(String []args){
        Map<String,String> map=new TreeMap<>();
        map.put("a","a");
        map.put("b","b");
        map.put("ab","ab");
        Integer[] a={2,3,4,7,8,5,9};
//        Arrays.sort(a);
        System.out.print(Arrays.deepToString(a));
        int i1=1;
        int i2=Integer.MAX_VALUE;
        int result=(i1+i2) >>> 1;
        System.out.print(result);
        binarySort(a,0,7,1);
        Integer e=0;
        e.compareTo(3);
        System.out.print(Arrays.deepToString(a));
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
