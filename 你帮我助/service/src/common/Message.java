package common;

import java.io.Serializable;

/*
 * @author      :1OrNaiwohe
 * @date        :2022/9/29 19:20
 * @description :
 */
public class Message implements Serializable {
    private String sendID;
    private String content;
    private String sendTime;
    private String messageType;
    private int num;//物品的数量

    public void setNum(int num) {
        this.num = num;
    }

    public int getNum() {
        return num;
    }

    public void setSendID(String sendID) {
        this.sendID = sendID;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public String getSendID() {
        return sendID;
    }

    public String getContent() {
        return content;
    }

    public String getSendTime() {
        return sendTime;
    }

    public String getMessageType() {
        return messageType;
    }
}
