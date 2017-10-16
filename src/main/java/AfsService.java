import com.alibaba.fastjson.JSONObject;

/**
 * Created by lenovo on 2017/6/7.
 */
public class AfsService {
    public static void main(String[] args) throws Exception {
//        createReplay();
//        queryServiceList();
        queryDetail();
    }
    public static void createReplay() throws Exception {
        String addId="13_1060_3542_51370";
        String addr="山东,德州市,德城区城区,经济开发区新河东路奇瑞汽车4S店";
        String [] array=addId.split("_");
        int province=Integer.valueOf(array[0]);
        int city=Integer.valueOf(array[1]);
        int county=Integer.valueOf(array[2]);
        int village=Integer.valueOf(array[3]);
        JSONObject param = new JSONObject();
        param.put("orderId", "ZMM17101110373617030365089875328");
        param.put("customerExpect",10);
        param.put("questionDesc", "烘焙秤小了");
        JSONObject asCustomerDto=new JSONObject();
        asCustomerDto.put("customerContactName","李兵兵");
        asCustomerDto.put("customerTel","18953433699");
        asCustomerDto.put("customerMobilePhone","18953433699");
        asCustomerDto.put("customerEmail","");
        asCustomerDto.put("customerPostcode","");
        param.put("asCustomerDto",asCustomerDto);
        JSONObject asPickwareDto=new JSONObject();
        asPickwareDto.put("pickwareType",4);
        asPickwareDto.put("pickwareProvince",province);
        asPickwareDto.put("pickwareCity",city);
        asPickwareDto.put("pickwareCounty",county);
        asPickwareDto.put("pickwareVillage",village);
        asPickwareDto.put("pickwareAddress",addr);
        param.put("asPickwareDto",asPickwareDto);
        JSONObject asReturnwareDto=new JSONObject();
        asReturnwareDto.put("returnwareType",10);
        asReturnwareDto.put("returnwareProvince",province);
        asReturnwareDto.put("returnwareCity",city);
        asReturnwareDto.put("returnwareCounty",county);
        asReturnwareDto.put("returnwareVillage",village);
        asReturnwareDto.put("returnwareAddress",addr);
        param.put("asReturnwareDto",asReturnwareDto);
        JSONObject asDetailDto=new JSONObject();
        asDetailDto.put("skuId",782024);
        asDetailDto.put("skuNum",1);
        param.put("asDetailDto",asDetailDto);
        System.out.print(param.toJSONString());
        System.out.print(JavaHttps.INSTANCE.httpMethod(param,"market/1/afs/createAfterService","post"));
    }

    public static void queryServiceList() throws Exception {
        JSONObject param = new JSONObject();
        param.put("pageNum",1);
        param.put("pageSize",50);
        param.put("orderId","ZMM17101110373617030365089875328");
        System.out.print(JavaHttps.INSTANCE.httpMethod(param,"market/1/afs/queryServiceList","post"));
    }
     public static void queryDetail() throws Exception {
        JSONObject param = new JSONObject();
        param.put("afsServiceId",345050003);
        System.out.print(JavaHttps.INSTANCE.httpMethod(param,"market/1/afs/queryServiceDetail","post"));
    }

}
