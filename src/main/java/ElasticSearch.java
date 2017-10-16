import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sun.org.apache.xml.internal.security.exceptions.Base64DecodingException;
import com.sun.org.apache.xml.internal.security.utils.Base64;

import javax.crypto.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.*;
import java.util.UUID;

/**
 * Created by lenovo on 2017/6/15.
 */
public class ElasticSearch {
    public static  void main(String[] args) throws NoSuchAlgorithmException, Base64DecodingException, InvalidKeySpecException, NoSuchPaddingException, BadPaddingException, IllegalBlockSizeException, InvalidKeyException, IOException {
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
}
