package function;

import common.Message;
import common.MessageType;
import dataBase.XlsTable;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashMap;

/*
 * @author      :1OrNaiwohe
 * @date        :2022/9/30 20:59
 * @description :
 */
public class ClientThread extends Thread {
    Socket socket = null;
    String userID = null;
    boolean keep = true;

    public ClientThread(String userID, Socket socket) {
        this.socket = socket;
        this.userID = userID;
    }

    @Override
    public void run(){
        while (keep){
            System.out.println("用户"+userID+"保持连接");
            try {
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                Message message1  = (Message) ois.readObject();
                if (message1.getMessageType().equals(MessageType.MESSAGE_ADD)){
                    System.out.println("用户"+userID+"执行了增加操作");//日志

                    String s = XlsTable.add(message1.getContent(),message1.getNum());
                    Message message2 = new Message();
                    message2.setMessageType(MessageType.MESSAGE_ADD);
                    message2.setContent(s);
                    ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                    oos.writeObject(message2);
                }
                if (message1.getMessageType().equals(MessageType.MESSAGE_DEL)){
                    System.out.println("用户"+userID+"执行了删除操作");//日志

                    String s = XlsTable.del(message1.getContent(),message1.getNum());
                    Message message2 = new Message();
                    message2.setMessageType(MessageType.MESSAGE_DEL);
                    message2.setContent(s);
                    ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                    oos.writeObject(message2);
                }
                if (message1.getMessageType().equals(MessageType.MESSAGE_VIEW)){

                    HashMap<String,Integer> hs = XlsTable.viewList();
                    StringBuilder sb = new StringBuilder();
                    for (String goods : hs.keySet()) {
                        String key = goods;
                        int value = hs.get(goods);
                        sb.append(key+" "+value + "\n");
                    }

                    Message message2 = new Message();
                    message2.setMessageType(MessageType.MESSAGE_VIEW);
                    message2.setContent(sb.toString());
                    ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                    oos.writeObject(message2);
                }
                if (message1.getMessageType().equals(MessageType.MESSAGE_SEARCH)){
                    int num = XlsTable.search(message1.getContent().trim());

                    Message message2 = new Message();
                    message2.setMessageType(MessageType.MESSAGE_SEARCH);
                    message2.setContent(message1.getContent().trim()+"目前数量为 ");
                    message2.setNum(num);
                    ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                    oos.writeObject(message2);
                }

            } catch (Exception e) {
                System.out.println("用户"+userID+"退出");
                try {
                    socket.close();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                keep = false;

            }
        }

    }

}
