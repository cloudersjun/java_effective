import javax.crypto.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

/**
 * Created by lenovo on 2017/7/6.
 */
public class RSATest {
    public String encrypt(RSAPublicKey publicKey, String plain) throws NoSuchPaddingException, NoSuchAlgorithmException,
            InvalidKeyException, BadPaddingException, IllegalBlockSizeException, IOException {
        byte[] b=plain.getBytes(Charset.forName("utf-8"));
        Cipher cipher = null;
        cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        int len=b.length;
        ByteArrayOutputStream out= new ByteArrayOutputStream();
        int offSet=0;
        byte[] cache;
        int i=0;
        while (len-offSet>0){
            if (len - offSet > 32) {
                cache = cipher.doFinal(b, offSet, 32);
            } else {
                cache = cipher.doFinal(b, offSet, len - offSet);
            }
            out.write(cache,0,cache.length);
            i++;
            offSet=i*32;
        }
        out.close();
        return new String(out.toByteArray(), Charset.forName("UTF-8"));
    }

    public String decrypt(RSAPrivateKey privateKey, String cipherData) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException, IOException {
        byte[] b=cipherData.getBytes(Charset.forName("utf-8"));
        Cipher cipher = null;
        cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        int len=b.length;
        ByteArrayOutputStream out= new ByteArrayOutputStream();
        int offSet=0;
        byte[] cache;
        int i=0;
        while (len-offSet>0){
            if (len - offSet > 32) {
                cache = cipher.doFinal(b, offSet, 32);
            } else {
                cache = cipher.doFinal(b, offSet, len - offSet);
            }
            out.write(cache,0,cache.length);
            i++;
            offSet=i*32;
        }
        out.close();
        return new String(out.toByteArray(),Charset.forName("UTF-8"));
    }
}
