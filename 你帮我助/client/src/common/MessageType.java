package common;

public interface MessageType {
    String MESSAGE_LOGIN_SUCCEED = "1";//服务器回应，登录成功
    String MESSAGE_LOGIN_FAIL = "2";//服务器回应，登录成功
    String MESSAGE_ADD = "add";//增加物品
    String MESSAGE_DEL = "DEL";//删除物品
    String MESSAGE_VIEW = "VIEW";//显示物品列表
    String MESSAGE_SEARCH = "SEARCH";//查找物品
    String MESSAGE_LOG_OUT = "OUT";//注销

}
