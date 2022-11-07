package com.moais.todo.security;

import com.moais.todo.log.ExceptionLog;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.Arrays;

@Component
@PropertySource("classpath:security.properties")
public class AES256SEC {


    @Value("${aes.key}")
    private String ENCODING_KEY;

    public String encrypt(String plainText, String salt) { // AES128 암호화
        String cipherText = "";
        if(plainText==null || plainText.length()==0 ) return cipherText;

        try {
            byte[] keyBytes = ENCODING_KEY.getBytes("UTF-8");
            byte[] plainTextBytes = plainText.getBytes("UTF-8");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

            int bsize = cipher.getBlockSize();

            IvParameterSpec ivspec = new IvParameterSpec(Arrays.copyOfRange(keyBytes, 0, bsize));
            SecretKeySpec secureKey = new SecretKeySpec(keyBytes, "AES");
            cipher.init(Cipher.ENCRYPT_MODE, secureKey, ivspec);

            byte[] encrypted = cipher.doFinal(plainTextBytes);
            cipherText = new String(Base64.encodeBase64(encrypted), "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
            new ExceptionLog(e, "").logging();
        }
        return cipherText;
    }

    public String decrypt(String cipherText) { //AES128 복호화
        String plainText = "";
        if(cipherText==null || cipherText.length()==0) return plainText;

        try {
            byte[] keyBytes = ENCODING_KEY.getBytes("UTF-8");
            byte[] cipherTextBytes = Base64.decodeBase64(cipherText.getBytes("UTF-8"));

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

            int bsize = cipher.getBlockSize();

            IvParameterSpec ivspec = new IvParameterSpec(Arrays.copyOfRange(keyBytes, 0, bsize));
            SecretKeySpec secureKey = new SecretKeySpec(keyBytes, "AES");

            cipher.init(Cipher.DECRYPT_MODE, secureKey, ivspec);
            byte[] decrypted = cipher.doFinal(cipherTextBytes);

            plainText = new String(decrypted, "UTF-8");
        } catch(Exception e) {
            e.printStackTrace();
            new ExceptionLog(e, "").logging();
        }
        return plainText;
    }

    public String getSalt() {
        return null;
    }
}
