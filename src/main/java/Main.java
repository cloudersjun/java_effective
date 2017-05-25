import java.util.*;

/**
 * Created by lenovo on 2017/5/25.
 */
public class Main {
    public static void main(String []args){
        Map<String,List<Integer>> map=new HashMap<>();
        List<Integer> l= new LinkedList();
        l.add(12);
        map.put("1",l);
        String s="1";
        String s2=s.toString();
        s="3";
        System.out.print(s2);
    }
}
