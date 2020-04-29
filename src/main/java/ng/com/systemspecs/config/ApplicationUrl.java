package ng.com.systemspecs.config;

public class ApplicationUrl {

    public static String algorithm = "AES";

    public static String cipher = "AES/CBC/PKCS5PADDING";

    final static String DEMO_HOST = "https://remitademo.net/remita/exapp/api/v1/send/api/rpgsvc/rpg/api/v2";

    final static String LIVE_HOST = "https://login.remita.net/remita/exapp/api/v1/send/api/rpgsvc/rpg/api/v2";

    public static String DEMO_BULK_PAYMENT = DEMO_HOST + "/merc/bulk/payment/send";

    public static String LIVE_BULK_PAYMENT = LIVE_HOST + "/merc/bulk/payment/send";

    public static String DEMO_GET_ACTIVE_BANKS = DEMO_HOST + "/fi/banks";

    public static String LIVE_GET_ACTIVE_BANKS = LIVE_HOST + "/fi/banks";

    public static String DEMO_SINGLE_PAYMENT = DEMO_HOST + "/merc/payment/singlePayment.json";

    public static String LIVE_SINGLE_PAYMENT = LIVE_HOST + "/merc/payment/singlePayment.json";

    public static String DEMO_URL_ACCOUNT_ENQUIRY = DEMO_HOST + "/merc/fi/account/lookup";

    public static String LIVE_URL_ACCOUNT_ENQUIRY = LIVE_HOST + "/merc/fi/account/lookup";

    public static String DEMO_URL_ADD_ACCOUNT = DEMO_HOST + "/merc/account/token/init";

    public static String LIVE_URL_ADD_ACCOUNT = LIVE_HOST + "/merc/account/token/init";

    public static String DEMO_URL_BULK_PAYMENT_STATUS = DEMO_HOST + "/merc/bulk/payment/status";

    public static String LIVE_URL_BULK_PAYMENT_STATUS = LIVE_HOST + "/merc/bulk/payment/status";

    public static String DEMO_URL_PAYMENT_STATUS = DEMO_HOST + "/merc/payment/status";

    public static String LIVE_URL_PAYMENT_STATUS = LIVE_HOST + "/merc/payment/status";

    public static String DEMO_URL_VALIDATE_ACCOUNT = DEMO_HOST + "/merc/account/token/validate";

    public static String LIVE_URL_VALIDATE_ACCOUNT = LIVE_HOST + "/merc/account/token/validate";
}
