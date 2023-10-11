package com.platform.interceptor;

import com.platform.constant.JwtClaimsConstant;
import com.platform.context.BaseContext;
import com.platform.exception.BaseException;
import com.platform.properties.JwtProperties;
import com.platform.utils.JwtUtil;
import com.platform.vo.EmployeeLoginVO;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

@Configuration
@Slf4j
public class JwtTokenAdminInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtProperties jwtProperties;

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception
    {
        if(!(handler instanceof HandlerMethod)){
            return true;
        }

        String token= request.getHeader(jwtProperties.getAdminTokenName());
        try{
            log.info("jwt校验:{}",token);
            Claims claims= JwtUtil.parseJWT(jwtProperties.getAdminSecretKey(),token);
            EmployeeLoginVO vo = EmployeeLoginVO.builder()
                    .id(claims.get(JwtClaimsConstant.USER_ID, Long.class))
                    .employeeId(claims.get(JwtClaimsConstant.EMP_ID, Long.class))
                    .name(claims.get(JwtClaimsConstant.NAME, String.class))
                    .username(claims.get(JwtClaimsConstant.USERNAME, String.class))
                    .token(claims.get(JwtClaimsConstant.JWT, String.class)).build();
            log.info("当前登陆员工信息：{}",vo);

            BaseContext.setThreadLocal(vo);
            return true;
        }catch(Exception ex){
            throw new BaseException(ex.getMessage());
        }
    }
}
