/**
 * Created by lenovo on 2017/6/2.
 */

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.*;
import java.net.URLEncoder;
import java.security.*;
import java.util.Date;

public enum  JavaHttps {

    INSTANCE;

    public  String httpMethod(JSONObject param, String path,String type) throws Exception {
        String appKey = "99shijithird2";
        String appSecret = "d3358a4240b36874cd9ee7d5d9a1842a";
        String timestamp = DateFormatUtils.format(new Date(), "yyyyMMddHHmmss");
        System.out.println("appkey=" + appKey + "&appsecret=" + appSecret + "&param=" + param.toJSONString() + "&timestamp=" + timestamp);
        String sign = MD5("appkey=" + appKey + "&appsecret=" + appSecret + "&param=" + param.toJSONString() + "&timestamp=" + timestamp);
        String httpsURL = "https://ad.99shiji" +
                ".com/" + path + "?appkey=" + appKey + "&sign=" + sign + "&param=" + URLEncoder.encode(param
                .toJSONString(), "utf-8") + "&timestamp=" + timestamp;
        System.out.println(httpsURL);
        HttpClient httpClient = new DefaultHttpClient();
        try {
            httpClient = wrapClient(httpClient);
        } catch (KeyManagementException | NoSuchAlgorithmException e1) {
            e1.printStackTrace();
        }
        if (type.equals("post")){
            HttpPost httpPost=new HttpPost(httpsURL);
            httpPost.addHeader("Content-Type", "application/json");
            HttpResponse response = httpClient.execute(httpPost);
            return EntityUtils.toString(response.getEntity());
        }else {
            HttpGet httpGet = new HttpGet(httpsURL);
            httpGet.addHeader("Content-Type", "application/json");
            HttpResponse response = httpClient.execute(httpGet);
            return EntityUtils.toString(response.getEntity());
        }
    }

    public  String MD5(String s) {
        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        try {
            byte[] btInput = s.getBytes();
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            return null;
        }
    }

    public  HttpClient wrapClient(HttpClient base) throws KeyManagementException, NoSuchAlgorithmException {
        SSLContext ctx = SSLContext.getInstance("TLS");
        X509TrustManager tm = new X509TrustManager() {
            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                return null;
            }

            @Override
            public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType)
                    throws java.security.cert.CertificateException {

            }

            @Override
            public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType)
                    throws java.security.cert.CertificateException {

            }
        };
        ctx.init(null, new TrustManager[]{tm}, null);
        SSLSocketFactory ssf = new SSLSocketFactory(ctx);
        ssf.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
        ClientConnectionManager ccm = base.getConnectionManager();
        SchemeRegistry registry = ccm.getSchemeRegistry();
        registry.register(new Scheme("https", ssf, 443));
        return new DefaultHttpClient(ccm, base.getParams());
    }
}
