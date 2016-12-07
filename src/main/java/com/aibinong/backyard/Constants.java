package com.aibinong.backyard;

/**
 * Created by ouwa on 16/6/28.
 */
public class Constants {
    public static final int DEFAULT_PAGE_SIZE = 15;
  //存储服务key
	public static final String MEMORY_SERVER_ACCESSKEYID="jggc2fKoaQgmcfTm";
	//存储服务secret
	public static final String MEMORY_SERVER_ACCESSKEYSECRET="YsdVOKFJ7ZvkWKO5P694cldW8donNb";
	
	public static final String MEMORY_SERVER_ENDPOINT="http://oss-cn-beijing.aliyuncs.com";
	
	public static final String MEMORY_SERVER_BUCKETNAME="new-duobao-oss";
	
	
	public static final String DUOBAO_ADS_EVENT_CLICK_LOCATION="9";
	
	public static final String DUOBAO_ADS_EVENT_CLICK_DETAI="37";
	//一元购
	public static final int DUOBAO_PRODUCT_TYPE_ONE_BUY=1;
	//十元购
	public static final int DUOBAO_PRODUCT_TYPE_TEN_BUY=10;
	//百元购
	public static final int DUOBAO_PROCUT_TYPE_BAIYUAN_BUY=100;
	
	public static final String COOKIE_SESSION_ID = "DUOBAO_SESSION_ID";
	
	public static final int PERIOD_CODE_BASE = 10000001;    //期次夺宝号的基数
	
	public static final String REDIS_KEY_PERIOD_CODE_LIST_PREFIX ="period_code_list_"; //期次的夺宝号
	
	public static final int PERIOD_STATUS_NORMAL = 0;       //期次状态：进行中
	//数据库密码加密证书
	public final static String DB_ENCRYPT_PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCszzwyEN2GIhJ46Kg8x47xjwKlghsp/Dz1mSOGXbdNN4/SnZs3pbZ6mOtWGV7NDOfn9RXlHOANV9q5GMWVVAnv9uf4PSABgwQ9Ou3AYx3b/EDiWm3eH7z/u0hXduR9JVg9PqbaLPav/D4884oAVJZhwlE863QCOKhBbYVzacUQOQIDAQAB";
	
	public final static String DB_ENCRYPT_PRIVATE_KEY="MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAKzPPDIQ3YYiEnjoqDzHjvGPAqWCGyn8PPWZI4Zdt003j9KdmzeltnqY61YZXs0M5+f1FeUc4A1X2rkYxZVUCe/25/g9IAGDBD067cBjHdv8QOJabd4fvP+7SFd25H0lWD0+ptos9q/8PjzzigBUlmHCUTzrdAI4qEFthXNpxRA5AgMBAAECgYBUc8KbRRq9npEgfbMaYPw+mQFWGUtlLVwtOnXeCJa4KdsjttpQS6/uIiWAok1uMEx1U5UrrvzgCHtf0fddjwB7c7Di8Vk+u9oiLldB0KB30CbGNyDfs2PFCNovcfo9Swgpiy56MhkL/iqlwvyA4qo+7PbOiZFbtwDxNvC0d9JjwQJBANcUKiYbJW4gxpGI7iYdKhJhWjCOsq7gVf+OhDrJh+YTsLA1ZKSRgYE14dS31HLGUCWFEBjHdgAueV/KqFG8n3UCQQDNsENYbqeK9EvpS17Jyg0f8vvjF80va2H7rRkPdXoETLqygenoB5UuV7YJKGanPzkKUeLWMRfvenN9Eqg9ezk1AkBnukLlo9v2c/owQEDSwugCe08a8gc9DdaL7Lja/Uz8amyhXu7N65rwrQLgK1RbkkcfAUCVIsVHKyLATyXEXdrtAkA0ao+u8Hv3uCvEJvXf3IKdurEf/QfH5siQd1skil5uzBLjvKGcBJqqtaVpDgei5USm8Zkp1lbwKzdVDzUafdVlAkEArE8y15kkAb9ghmRMfhmHhvzOvOSIeR8sHi5w0z/M5XQfIgW6CL8m3zbxKy5wtrxqV3u6SBfH514DYs5rTmCUJg==";
	
	public final static String SYS_CONFIG_SMARTAI_ARGS="smartAiArgs";
	
	public final static int NEED_LOGIN_TARGET =0;
	
	public final static String PARAM_SYS_DEFAULT_VALUE="-1";
	
	public final static int BACKYARD_DEFAULT_WEIGHT=0;//默认权重
	
	public final static String LETTER_DEFAULT_SEND_TYPE="common";//默认发送对象
	
	public final static int DEFAULT_STATUS=0;//默认状态
	
	public final static int PERIOD_DEFAULT_CUR_COUNT=0;//期次默认当前数量
	
	public final static int ROLE_DEFAULT_STATUS_VALUE=1;
	
	public final static int PERIOD_TYPE_OBJECT_VALUE=0;//实物期次
	
	public final static int PERIOD_TYPE_RECHARGE_VALUE=1;//虚拟充值期次
	
	public final static int RECHARGE_RECORD_STATUS_COLSED=9;//客服手动关闭 
	
	public final static int RECHARGE_RECORD_STATUS_CANNOT_RECOVER=-9;//-9不可恢复的充值失败
	
	public final static int RECHARGE_RECORD_STATUS_INIT=0;//初始化
	
	public final static int RECHARGE_RECORD_STATUS_CREATE_ORDER=1;//创建订单
	
	public final static int RECHARGE_RECORD_STATUS_SUCCESS=1;//充值成功
	
	public final static int RECHARGE_RECORD_STATUS_CAN_RECOVER=-1;//可恢复的充值失败
	
	public final static int ORDER_EXPRESS_DEFAULT_STATUS=0;//物流订单获得奖品状态
	
	public final static int DUOBAO_ADS_DELETE_STATUS=1;//广告删除状态
	
	public final static int DUOBAO_ADS_NORMAL_STATUS=0;//广告正常状态
	
	public final static  int DUOBAO_ROLED_DELETED_STATUS=0;// 角色删除状态
	
	public final static int DUOBAO_ROLE_NORMAL_STATUS=1;//角色正常状态
	
	public final static int DUOBAO_ACCOUNT_DELETED_STATUS=0;//用户删除状态
	
	public final static int DUOBAO_ACCOUNT_NORMAL_STAUTS=1;//用户正常状态
	
	public final static int DUOBAO_AI_STATUS=1;//AI用户
	
	public final static int DUOBAO_NOTAI_STATUS=0;//非AI用户
	
	public final static int DUOBAO_CUSTOMER_LOGS_STATUS_CONFIRM=0;//实物重新确认
	
	public final static int DUOBAO_CUSTOMER_LOGS_STATUS_CONFIRM_MOBILE=1;//充值卡确认手机号
	
	public final static int DUOBAO_CUSTOMER_LOGS_STATUS_REFUND=2;//退款
	
	public final static int DUOBAO_CUSTOMER_LOGS_STATUS_SHARE_CALLBACK=3;//晒单回访
	
	public final static int DUOBAO_PUSH_MANY_CLIENT_ID_TYPE=3;//客户端推送
	
	public final static int DUOBAO_ACCOUNT_NORMAL_STATUS=1;//用户默认
}
