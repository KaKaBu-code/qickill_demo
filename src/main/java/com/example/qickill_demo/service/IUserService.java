package com.example.qickill_demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.qickill_demo.entity.User;
import com.example.qickill_demo.vo.LoginVo;
import com.example.qickill_demo.vo.RespBean;
import org.springframework.http.HttpRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author jobob
 * @since 2022-08-02
 */
public interface IUserService extends IService<User> {
    /**
     * 登陆功能
     * @param loginVo
     * @return
     */
    RespBean doLogin(LoginVo loginVo, HttpServletRequest request, HttpServletResponse response);
}
