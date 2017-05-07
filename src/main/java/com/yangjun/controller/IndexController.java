package com.yangjun.controller;

import com.yangjun.dao.mybatis.mapper.AccountInfoMapper;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by admin on 2017/2/21.
 */
@RestController
@RequestMapping(value="/api/index")
public class IndexController {
    @Autowired
    private AccountInfoMapper accountInfoMapper;
    @ApiOperation(value = "入口", notes = "入口测试")
    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String index(){
        System.out.println(accountInfoMapper.selectByPrimaryKey(1));
        return "hello world!";
    }

}
