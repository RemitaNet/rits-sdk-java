package com.systemspecs.paymentinfra.utils;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;


@Component
public class FieldEncryptionService {

    private static String encoding = "UTF-8";


    static String encrypt(String plainText, String initVector, String secretKey, String algorithm, String cipherString, String encoding) {
        String encryptedText = StringUtils.EMPTY;
        try {
            IvParameterSpec iv = new IvParameterSpec(initVector.getBytes(encoding));
            SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes(encoding), algorithm);
            Cipher cipher = Cipher.getInstance(cipherString);
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, iv);
            byte[] encrypted = cipher.doFinal(plainText.getBytes(encoding));
            encryptedText = new String(Base64.encodeBase64(encrypted), encoding);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        return encryptedText;
    }


    public static <T> T encryptFields(T bean, final String algorithm, final String cipherString, final String initVector, final String secretKey) {
        ReflectionUtils.doWithFields(bean.getClass(), field -> {
            try {
                String currentValue = BeanUtils.getProperty(bean, field.getName());
                if (currentValue != null && field.getType().equals(String.class)) {
                    String newValue = encrypt(currentValue, initVector, secretKey, algorithm, cipherString, encoding);
                    BeanUtils.setProperty(bean, field.getName(), newValue);
                }
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        });
        return bean;
    }
}
