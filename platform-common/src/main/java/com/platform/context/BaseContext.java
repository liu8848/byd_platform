package com.platform.context;


import com.platform.commonModel.LoginData;

public class BaseContext {
    public static ThreadLocal<LoginData> threadLocal = new ThreadLocal<>();

    public static void setThreadLocal(LoginData vo) {
        threadLocal.set(vo);
    }

    public static LoginData getCurrentId() {
        return threadLocal.get();
    }

    public static void removeCurrent() {
        threadLocal.remove();
    }
}
