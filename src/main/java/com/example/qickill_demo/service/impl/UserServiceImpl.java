package com.example.qickill_demo.service.impl;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.qickill_demo.entity.User;
import com.example.qickill_demo.exception.GlobalException;
import com.example.qickill_demo.mapper.UserMapper;
import com.example.qickill_demo.service.IUserService;
import com.example.qickill_demo.utils.CookieUtil;
import com.example.qickill_demo.utils.MD5Util;
import com.example.qickill_demo.utils.UUIDUtil;
import com.example.qickill_demo.utils.ValidatorUtil;
import com.example.qickill_demo.vo.LoginVo;
import com.example.qickill_demo.vo.RespBean;
import com.example.qickill_demo.vo.RespBeanEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author jobob
 * @since 2022-08-02
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    @Autowired
    UserMapper userMapper;
    @Autowired
    RedisTemplate redisTemplate;
    @Override
    public RespBean doLogin(LoginVo loginVo, HttpServletRequest request, HttpServletResponse response) {
        String mobile= loginVo.getMobile();
        String password= loginVo.getPassword();
//        if(StringUtils.isEmpty(mobile)||StringUtils.isEmpty(password)){
//            return RespBean.error(RespBeanEnum.LOGIN_ERROR);
//        }
//        if(!ValidatorUtil.isMobile(mobile)){
//            return RespBean.error(RespBeanEnum.MOBILE_ERROR);
//        }

        User user=userMapper.selectById(mobile);
        if(null==user){
            throw new GlobalException(RespBeanEnum.LOGIN_ERROR);
        }
        if(!MD5Util.formPassToDBPass(password,user.getSalt()).equals(user.getPassword())){
            throw new GlobalException(RespBeanEnum.LOGIN_ERROR);
        }
        // cookie
        String ticket= UUIDUtil.uuid();
//        request.getSession().setAttribute(ticket,user);
        redisTemplate.opsForValue().set("user:"+ticket,user);
        CookieUtil.setCookie(request,response,"userTicket",ticket);
        return RespBean.success(ticket);
    }

    @Override
    public User getUserByCookie(String userTicket,HttpServletRequest request,HttpServletResponse response) {
        if(StringUtils.isEmpty(userTicket)){
            return null;
        }
        User user=(User) redisTemplate.opsForValue().get("user:"+userTicket);
        if(user!=null){
            CookieUtil.setCookie(request,response,"userTicket",userTicket);
        }
        return user;
    }
}
