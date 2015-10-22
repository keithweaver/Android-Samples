package com.weaverprojects.shuttle.Controller;

/**
 * Created by Keith on 2015-10-21.
 */
public class C {
    public class local{
        public static final String DB_NAME = "SHUTTLE_DB";
        public class saved_friends_data{
            public static final String TABLE_NAME = "save_friends_data";
            public static final String KEY = "id";
            public static final String USER_NAME = "user_name";
            public static final String NAME = "name";
        }
        public class on_going_chats{
            public static final String TABLE_NAME = "on_going_chats";
            public static final String KEY = "id";
            public static final String USER_NAME = "user_name";
            public static final String LAST_MESSAGE = "last_message";
            public static final String DEVICE_ID = "device_id";
            public static final String IMG_LINK = "img_link";
        }
    }
}
