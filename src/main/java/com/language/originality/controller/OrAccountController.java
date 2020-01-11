package com.language.originality.controller;


import com.language.originality.commons.ResultModel;
import com.language.originality.commons.ValidatorParamUtil;
import com.language.originality.entity.OrAccount;
import com.language.originality.service.IOrAccountService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author ZHAIKAIXUAN
 * @since 2020-01-10
 */
@RestController
@RequestMapping("/auth/or-account")
@Api("账户管理")
public class OrAccountController {


    @Autowired
    private IOrAccountService orAccountService;


    @ApiOperation("新增账户")
    @PostMapping("/save")
    public ResultModel saveAccount(@RequestBody @Valid OrAccount orAccount, BindingResult bindResult) {
        ValidatorParamUtil.validatorData(bindResult);
        orAccountService.save(orAccount);
        return ResultModel.success();
    }
}
