package face;

import function.ClientFunction;
import function.ServiceThread;
import utility.Utility;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Vector;

/*
 * @author      :1OrNaiwohe
 * @date        :2022/9/30 20:23
 * @description :
 */
public class View {
    private boolean loop = true;//循环条件
    ClientFunction function = new ClientFunction();

    public static void main(String[] args) {
        new View().mainMenu();
    }
    private void mainMenu(){
        while (loop){
            System.out.println("=============你帮我助=============");
            System.out.println("");
            System.out.println("\t\t 1 登录");
            System.out.println("\t\t 2 退出");
            System.out.println("");
            System.out.println("");
            System.out.println("=============扣1登录=============");

            String key = Utility.readString(1);
            switch (key){
                case "1"://选择登录
                    System.out.println("=============你帮我助=============");
                    System.out.println("");
                    System.out.println("输入用户名：");
                    String userID = Utility.readString(50);
                    System.out.println("输入密码：");
                    String userPSD = Utility.readString(50);

                    if (function.log(userID,userPSD)){
                        System.out.println("");
                        System.out.println("");
                        System.out.println("=============登陆成功=============");
                        System.out.println("");
                        System.out.println("请选择对应操作序号");
                        System.out.println("1、  增加物品");
                        System.out.println("2、  删除物品");
                        System.out.println("3、 显示物品列表");
                        System.out.println("4、  查找物品");
                        System.out.println("9、   退出");
                        while (loop){
                            String key2 = Utility.readString(10);

                            if (key2.equals("1")){
                                System.out.println("要增加的物品：");
                                String goods = Utility.readString(50);
                                System.out.println("数量: ");
                                int num = Utility.readInt();
                                function.add(goods,num);
                            }

                            if (key2.equals("2")){
                                System.out.println("要删除的物品: ");
                                String goods = Utility.readString(50);
                                System.out.println("要删除的数目: ");
                                int num = Utility.readInt();
                                function.del(goods,num);
                            }
                            if(key2.equals("4")){
                                System.out.println("请输入要查询的物品");
                                String goods = Utility.readString(50);
                                function.search(goods);
                            }

                            if (key2.equals("3")){
                                function.view();
                            }
                            if (key2.equals("9")){
                                function.log_out();
                            }

                        }


                    }
                case "2":
                    loop = false;
                    break;
            }

        }
    }

}
