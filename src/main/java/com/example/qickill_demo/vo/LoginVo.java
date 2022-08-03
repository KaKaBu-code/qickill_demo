package com.example.qickill_demo.vo;

import com.example.qickill_demo.validator.IsMobile;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class LoginVo {
    @NotNull
    @IsMobile
    private String mobile;
    @NotNull
    private String password;

}
