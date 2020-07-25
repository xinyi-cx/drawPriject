package com.tt.base.cotent;/**
                                  * Created by ouzaihui on 2019/4/19.
                                  */

/**
 * @Description //TODO 一句话说明
 * @ClassName Const
 * @Author ouzaihui
 * @CreateDate 2019/4/19 13:29
 * @UpdateDate 2019/4/19 13:29
 * @Version 1.0
 **/
public class Const {

    public final static String CHARSET                           = "UTF-8";
    public static final long   TOKEN_EXPIRE_TIME                 = 30 * 60 * 1000L;                // H_TOKENE有效时间
    public static final long   SERVER_PUBLIC_KEY_EXPIRE_TIME     = 1 * 60 * 60 * 1000L;            // 服务器公钥有效时间
    public static final long   TIMESTAMP_EXPIRE_TIME             = 3 * 60 * 1000L;                 // 时间序号过期时间
    public final static String KEY_REDIS_DISABLED_USER           = "userAccountDisabled";
    public final static String KEY_REDIS_DISABLED_TOKEN          = "tokenDisabled";
    public final static String KEY_REDIS_USER_PUBLIC_KEY         = "userPublicKey";
    public final static String KEY_REDIS_SERVER_USER_PRIVATE_KEY = "serverUserPrivateKey";
    public final static String KEY_REDIS_USER_TOKEN_KEY          = "userTokenKey";
    public final static String KEY_REDIS_INSIDE_API_CONFIG       = "datastation:db:tdinsideapi";

    public final static String DEFAULT_TIMEZONE                  = "GMT+8";
    public final static String DEFAULT_DATA_FORMAT_PATTERN       = "yyyy-MM-dd HH:mm:ss";

//    public final static String DATA_DBSERVER_URL                 = "http://databank.dbserver:8762";
    // public final static String DATA_DBSERVER_URL = "http://localhost:8762/databank-dbserver";

    public final static String TOKEN_FAIL_IP_ADDR                = "0.0.0.0";
    public final static int    TOKEN_FAIL_USER_ID                = -1;
    public final static String TOKEN_FAIL_ACCOUNT                = "undefined account";

    // 日志定义
    public enum LogTag {
                        ADD(1, "1"), DEL(2, "2"), PUT(3, "3"), GET(4, "4");

        private final String value;
        private final int    val;

        private LogTag(int val, String value){
            this.val = val;
            this.value = value;
        }

        public LogTag valueOf(int val) {
            switch (val) {
                case 1:
                    return LogTag.ADD;
                case 2:
                    return LogTag.DEL;
                case 3:
                    return LogTag.PUT;
                case 4:
                    return LogTag.GET;
                default:
                    return null;
            }
        }

        public String getValue() {
            return this.value;
        }

        /**
         * @return
         */
        public int getVal() {
            // TODO Auto-generated method stub
            return this.val;
        }
    }

    public enum LogOperatorStatus {
                                   SUCCESS(1, "1"), FAIL(0, "0");

        private final int    val;
        private final String value;

        private LogOperatorStatus(int val, String value){
            this.val = val;
            this.value = value;
        }

        public LogOperatorStatus valueOf(int val) {
            switch (val) {
                case 1:
                    return LogOperatorStatus.SUCCESS;
                case 0:
                    return LogOperatorStatus.FAIL;
                default:
                    return null;
            }
        }

        public int getVal() {
            return val;
        }

        public String getValue() {
            return value;
        }
    }

    public final static class LogTitle{
        public final static String FILE_QUERY_LIST                  = " 文件管理--》查询文件列表";
        public final static String FILE_QUERY_MAX_FILE_VERSION      = " 文件管理--》查询文件当前最大版本";
        public final static String FILE_UPLOAD_FILE                 = " 文件管理--》上传文件";
        public final static String FILE_DOWNLOAD_FILE               = " 文件管理--》下载文件";
        public final static String FILE_DELETE_FILE                 = " 文件管理--》删除文件";
        public final static String FILE_ADD_RESOURCE                = " 文件管理--》添加到文件管理";
    }

}
