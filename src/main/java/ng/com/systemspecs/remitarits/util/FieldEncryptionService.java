package ng.com.systemspecs.remitarits.util;

import ng.com.systemspecs.remitarits.bulkpayment.BulkPaymentInfo;
import ng.com.systemspecs.remitarits.bulkpayment.BulkPaymentRequest;
import ng.com.systemspecs.remitarits.bulkpayment.PaymentDetails;
import ng.com.systemspecs.remitarits.configuration.Credentials;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.ArrayList;
import java.util.List;


/**
 * A configuration class
 *
 *
 * @author Ilesanmi Omoniyi
 *
 * @since 1.0.0
 */
public class FieldEncryptionService {

    private static String ENCODING = "UTF-8";

    final  static String ALGORITHM = "AES";

    final static String CIPHER = "AES/CBC/PKCS5PADDING";



    static String encrypt(String plainText, String initVector, String secretKey) {
        String encryptedText = null;
        try {
            IvParameterSpec iv = new IvParameterSpec(initVector.getBytes(ENCODING));
            SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes(ENCODING), ALGORITHM);
            Cipher cipher = Cipher.getInstance(CIPHER);
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, iv);
            byte[] encrypted = cipher.doFinal(plainText.getBytes(ENCODING));
            encryptedText = new String(Base64.encodeBase64(encrypted), ENCODING);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        return encryptedText;
    }


    public static <T> T encryptFields(T bean, final String initVector, final String secretKey) {
        ReflectionUtils.doWithFields(bean.getClass(), field -> {
            try {
                String currentValue = BeanUtils.getProperty(bean, field.getName());
                if (currentValue != null && field.getType().equals(String.class)) {
                    String newValue = encrypt(currentValue, initVector, secretKey);
                    BeanUtils.setProperty(bean, field.getName(), newValue);
                }
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        });
        return bean;
    }

    public static BulkPaymentRequest encyrptBulkFields(BulkPaymentRequest paymentRequest, Credentials credentials) {

        BulkPaymentInfo bulkPaymentInfo = paymentRequest.getBulkPaymentInfo();
        bulkPaymentInfo = encryptFields(bulkPaymentInfo, credentials.getSecretKeyIv(), credentials.getSecretKey());
        paymentRequest.setBulkPaymentInfo(bulkPaymentInfo);
        List<PaymentDetails> paymentDetails = paymentRequest.getPaymentDetails();
        List<PaymentDetails> encryptedPaymentDetails = new ArrayList<>();
        for (PaymentDetails eachPaymentDetails : paymentDetails) {
            PaymentDetails details = encryptFields(eachPaymentDetails, credentials.getSecretKeyIv(), credentials.getSecretKey());
            encryptedPaymentDetails.add(details);
        }
        paymentRequest.setPaymentDetails(encryptedPaymentDetails);
        return paymentRequest;
    }
}
