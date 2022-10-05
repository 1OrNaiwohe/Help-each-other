package function;

import common.Message;
import common.MessageType;
import common.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/*
 * @author      :1OrNaiwohe
 * @date        :2022/9/30 18:59
 * @description :
 */
public class ClientFunction {
    private User user = new User();
    Socket socket = null;

    public boolean log(String userID,String userPSD){
        boolean isTrue = false;
        user.setUserID(userID);
        user.setUserPWD(userPSD);

        try {
            socket = new Socket(InetAddress.getLocalHost(),9999);
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(user);
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            Message message = (Message) ois.readObject();
            if (message.getMessageType().equals(MessageType.MESSAGE_LOGIN_SUCCEED)){
                isTrue = true;
                ServiceThread serviceThread = new ServiceThread(socket);
                serviceThread.start();
            }
            else {
                socket.close();
            }

        } catch (Exception e) {
            System.out.println("未连接到服务器");
            //e.printStackTrace();
        }
        return isTrue;
    }
    //增加物品
    public void add(String goods,int num){
        try {
            Message message = new Message();
            message.setMessageType(MessageType.MESSAGE_ADD);
            message.setContent(goods);
            message.setNum(num);
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //删除物品
    public void del(String goods,int num){//要删除的物品以及数量
        Message message = new Message();
        message.setMessageType(MessageType.MESSAGE_DEL);
        message.setContent(goods);
        message.setNum(num);
        try {
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(message);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    //显示列表
    public void view(){
        Message message = new Message();
        message.setMessageType(MessageType.MESSAGE_VIEW);
        try {
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //查询物品
    public void search(String goods){
        Message message = new Message();
        message.setMessageType(MessageType.MESSAGE_SEARCH);
        message.setContent(goods);
        try {
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //用户退出
    public void log_out(){
        System.exit(0);
    }


}
