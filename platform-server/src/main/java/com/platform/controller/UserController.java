package com.platform.controller;

import com.platform.constant.JwtClaimsConstant;
import com.platform.dto.employees.EmployeeLoginDTO;
import com.platform.properties.JwtProperties;
import com.platform.result.Result;
import com.platform.service.UserService;
import com.platform.utils.JwtUtil;
import com.platform.vo.EmployeeLoginVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("api/user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtProperties jwtProperties;

    @PostMapping("/login")
    public Result<EmployeeLoginVO> login(@RequestBody EmployeeLoginDTO employeeLoginDTO) {
        log.info("用户登录:{}", employeeLoginDTO);
        EmployeeLoginVO vo = userService.login(employeeLoginDTO);

        Map<String, Object> claims = new HashMap<>();
        claims.put(JwtClaimsConstant.USER_ID, vo.getId());
        claims.put(JwtClaimsConstant.EMP_ID, vo.getEmployeeId());
        claims.put(JwtClaimsConstant.USERNAME, vo.getUsername());
        claims.put(JwtClaimsConstant.NAME, vo.getName());

        String token = JwtUtil.createJWT(jwtProperties.getAdminSecretKey(),
                jwtProperties.getAdminTtl(), claims);

        vo.setToken(token);

        return Result.success(vo);

    }
}
