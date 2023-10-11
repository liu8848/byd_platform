package com.platform.service.impl;

import com.platform.constant.MessageConstant;
import com.platform.constant.StatusConstant;
import com.platform.dto.EmployeeLoginDTO;
import com.platform.entity.User;
import com.platform.exception.AccountLockedException;
import com.platform.exception.AccountNotFoundException;
import com.platform.exception.PasswordErrorException;
import com.platform.mapper.UserMapper;
import com.platform.service.UserService;
import com.platform.vo.EmployeeLoginVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public EmployeeLoginVO login(EmployeeLoginDTO employeeLoginDTO) {
        String username= employeeLoginDTO.getUsername();
        String password = DigestUtils.md5DigestAsHex(employeeLoginDTO.getPassword().getBytes());

        User user = userMapper.getByUsername(username);

        if(user==null){
            throw new AccountNotFoundException(MessageConstant.ACCOUNT_NOT_FOUND);
        }

        if(!password.equals(user.getPassword())){
            throw new PasswordErrorException(MessageConstant.PASSWORD_ERROR);
        }

//        if(user.getStatus().equals(StatusConstant.USER_IN_USED)){
//            throw new AccountLockedException(MessageConstant.ACCOUNT_LOCKED);
//        }
        EmployeeLoginVO vo=new EmployeeLoginVO();
        BeanUtils.copyProperties(user,vo);
        return vo;
    }
}
