package com.weaverprojects.shuttle.Model;

/**
 * Created by Keith on 2015-10-21.
 */
public class ConvoListItem {
    private String deviceId;
    private String userName;
    private String lastMessage;
    private String imgLink;

    public ConvoListItem(String deviceId, String userName, String lastMessage, String imgLink) {
        this.deviceId = deviceId;
        this.userName = userName;
        this.lastMessage = lastMessage;
        this.imgLink = imgLink;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public String getUserName() {
        return userName;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public String getImgLink() {
        return imgLink;
    }
}
