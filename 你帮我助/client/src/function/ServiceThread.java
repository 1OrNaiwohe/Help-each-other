package function;

import common.Message;
import common.MessageType;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

/*
 * @author      :1OrNaiwohe
 * @date        :2022/10/1 19:13
 * @description :用于接收服务器端的信息
 */
public class ServiceThread extends Thread {
    Socket socket = null;
    boolean keep = true;

    public ServiceThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run(){
        while (keep) {
            try {
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                Message message = (Message) ois.readObject();
                //
                //添加物品
                if(message.getMessageType().equals(MessageType.MESSAGE_ADD)){
                    System.out.println(message.getContent());
                }
                //
                //删除物品
                if(message.getMessageType().equals(MessageType.MESSAGE_DEL)){
                    System.out.println(message.getContent());
                }
                //
                //查询物品
                if (message.getMessageType().equals(MessageType.MESSAGE_VIEW)){
                    System.out.println("现有物品如下：");
                    System.out.println(message.getContent());
                }
                //
                //显示物品列表
                if(message.getMessageType().equals(MessageType.MESSAGE_SEARCH)){
                    System.out.println(message.getContent()+message.getNum());
                }

            } catch (Exception e) {
                keep = false;
                System.out.println("与服务器连接断开");
                System.exit(0);
                //e.printStackTrace();
            }
        }
    }
}
