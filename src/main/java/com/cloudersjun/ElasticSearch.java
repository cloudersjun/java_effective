package com.cloudersjun;

import java.util.UUID;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * Created by lenovo on 2017/6/15.
 */
public class ElasticSearch {
    public static  void main(String[] args) {
        JSONArray array=new JSONArray();
        JSONObject js = new JSONObject();
        js.put("array",array);
        JSONArray newArray = js.getJSONArray("array");
        newArray.add(1);
        System.out.print(js.toJSONString());

        System.out.println(UUID.randomUUID().toString().replace("-",""));
        System.out.println(UUID.randomUUID().toString().replace("-",""));

        js.toJSONString().contains("as");
    }
    public static void test(){
        String url = "asda";
        System.out.println("url:"+url);
    }
}
