package com.example.controller;

import com.example.mapper.SysRoleMapper;
import com.example.mapper.SysUserMapper;
import com.example.pojo.SysRole;
import com.example.pojo.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TestController {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private SysRoleMapper sysRoleMapper;


    @RequestMapping("/test")
    public List<SysUser> test() {
        return sysUserMapper.userList();
    }

    @RequestMapping("/test2")
    public List<SysRole> test2() {
        return sysRoleMapper.roleList();
    }

    @RequestMapping("/test3")
    public SysUser test3(){
        return sysUserMapper.getUser("admin");
    }

//    @RequestMapping("/test4")
//    public List<SysRole> test4() {
//        return sysRoleMapper.getRolesByUid(1);
//    }

}
