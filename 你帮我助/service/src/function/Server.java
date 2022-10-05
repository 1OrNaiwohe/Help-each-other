package function;


import common.Message;
import common.MessageType;
import common.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/*
 * @author      :1OrNaiwohe
 * @date        :2022/9/30 19:45
 * @description :
 */
public class Server {
    private ServerSocket serverSocket = null;

    public static void main(String[] args){
        new Server();
    }

    public Server(){
        try {
            serverSocket = new ServerSocket(9999);
            while (true){
                Socket socket = serverSocket.accept();
                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                Message message = new Message();
                User user = (User) ois.readObject();
                if (userCheck(user.getUserID(),user.getUserPWD())){
                    message.setMessageType(MessageType.MESSAGE_LOGIN_SUCCEED);
                    oos.writeObject(message);
                    ClientThread clientThread = new ClientThread(user.getUserID(),socket);
                    clientThread.start();

                }
                else {
                    message.setMessageType(MessageType.MESSAGE_LOGIN_FAIL);
                    oos.writeObject(message);
                    socket.close();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private boolean userCheck(String userID,String userPSD){
        return true;
    }
}
