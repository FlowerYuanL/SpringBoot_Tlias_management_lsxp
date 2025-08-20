package com.lsxp.pojo;

public class UserContextHoler {
    //创建一个ThreadLocal对象，用于保存用户信息
    private static final ThreadLocal<UserDTO> userThreadLocal = new ThreadLocal<>();

    /**
     * 存放用户信息到当前线程
     * @param user
     */
    public static void setUser(UserDTO user) {
        userThreadLocal.set(user);
    }


    /**
     * 取出用户信息到当前线程
     * @return 用户信息
     */
    public static UserDTO getUser() {
        return userThreadLocal.get();
    }

    /**
     * 清理信息，否则可能会造成内存泄露
     */
    public static void clear() {
        userThreadLocal.remove();
    }
}
