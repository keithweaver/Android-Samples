package com.weaverprojects.shuttle.Model;

/**
 * Created by Keith on 2015-10-25.
 */
public class Message {
    private int messageId;
    private String message;
    private String owner;
    private String timestamp;
    private boolean isOwnerOfMessage;

    public Message(int messageId, String message, String owner, String timestamp, boolean isOwnerOfMessage) {
        this.messageId = messageId;
        this.message = message;
        this.owner = owner;
        this.timestamp = timestamp;
        this.isOwnerOfMessage = isOwnerOfMessage;
    }
    public boolean isOwner(){
        return isOwnerOfMessage;
    }
    public void setOwnerOfMessage(boolean b){
        this.isOwnerOfMessage = b;
    }
    public int getMessageId() {
        return messageId;
    }

    public String getMessage() {
        return message;
    }

    public String getOwner() {
        return owner;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
