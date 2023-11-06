package com.platform.context;

import com.platform.vo.EmployeeLoginVO;

public class BaseContext {
    public static ThreadLocal<EmployeeLoginVO> threadLocal = new ThreadLocal<>();

    public static void setThreadLocal(EmployeeLoginVO vo) {
        threadLocal.set(vo);
    }

    public static EmployeeLoginVO getCurrentId() {
        return threadLocal.get();
    }

    public static void removeCurrent() {
        threadLocal.remove();
    }
}
