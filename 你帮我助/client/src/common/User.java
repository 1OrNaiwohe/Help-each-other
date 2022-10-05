package common;

import java.io.Serializable;

/*
 * @author      :1OrNaiwohe
 * @date        :2022/9/29 19:20
 * @description :
 */
public class User implements Serializable {
    private String userID;
    private String userPWD;

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public void setUserPWD(String userPWD) {
        this.userPWD = userPWD;
    }

    public String getUserID() {
        return userID;
    }

    public String getUserPWD() {
        return userPWD;
    }
}
