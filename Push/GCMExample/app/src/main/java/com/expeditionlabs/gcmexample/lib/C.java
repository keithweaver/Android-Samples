package com.expeditionlabs.gcmexample.lib;

/**
 * Created by Keith on 2015-08-24.
 */
public class C {
    public static class register {
        public static final String ACCOUNT = "gcmexample2account";
        public static final String KEY_REG_ID = "gcm_example_key_reg_id";
    }
    public static class google{
        public static final String GOOGLE_PROJECT_ID = "";//This is your project code
        public static final String SENDER_ID = GOOGLE_PROJECT_ID;
    }
    public static class extras{
        public static final String USERNAME = "USERNAME";
        public static final String REG_ID = "REGID";
    }
    public static class db{
        public static final String MAIN_LINK = "YOUR_URL";
        public static class RegisterUserName{
            public static final String PAGE = "register.php";
            public static final String USERNAME_KEY = "username";
            public static final String REG_ID_KEY = "regid";
        }
        public static class SendMessage{
            public static final String PAGE = "send.php";
            public static final String USERNAME_KEY = "username";
            public static final String MESSAGE_KEY = "message";
        }
        public static class local{
            public static String DB_NAME = "pushdb";
            public static String TABLE = "pushtable";
            public static class column {
                public static final String KEY = "id";
                public static final String PUSH_CODE = "pushcode";
                public static final String PUSH_MESSAGE = "message";
            }
        }
    }

}
