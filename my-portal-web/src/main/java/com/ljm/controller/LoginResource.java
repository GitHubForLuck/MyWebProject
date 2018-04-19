package com.ljm.controller;

import com.ljm.domain.Role;
import com.ljm.domain.User;
import com.ljm.server.ILoginService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.pam.UnsupportedTokenException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @Project MyWebProject
 * @ClassName LoginResource
 * @Description TODO
 * @Author random
 * @Date Create in 2018/4/18 16:02
 * @Version 1.0
 **/
@Controller
public class LoginResource {

    @Autowired
    private ILoginService loginService;

    //退出的时候是get请求，主要是用于退出
    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String login(){
        return "login";
    }

    //post登录
    /** @requestBody注解常用来处理content-type不是默认的application/x-www-form-urlcoded编码的内容，
     * 比如说：application/json或者是application/xml等。
     * */
    @RequestMapping(value = "/login",method = RequestMethod.POST, produces = "application/x-www-form-urlencoded")
    public String login(User user, Map<String, Object> map){
        //添加用户认证信息
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(
                user.getName(),
                user.getPassword());
        //进行验证，这里可以捕获异常，然后返回对应信息
        try {
            subject.login(usernamePasswordToken);
        } catch (UnsupportedTokenException e) {
            System.out.println("shiro-身份令牌异常，不支持的身份令牌");
            e.printStackTrace();
        } catch (UnknownAccountException e) {
            System.out.println("shiro-未知账户/没找到帐号,登录失败");
            e.printStackTrace();
            map.put("msg", "未知账户/没找到帐号,登录失败");
            return "login";
        } catch (LockedAccountException e) {
            System.out.println("shiro-帐号锁定");
            e.printStackTrace();
        } catch (DisabledAccountException e) {
            System.out.println("shiro-用户禁用");
            e.printStackTrace();
        } catch (ExcessiveAttemptsException e) {
            System.out.println("shiro-登录重试次数，超限。只允许在一段时间内允许有一定数量的认证尝试");
            e.printStackTrace();
        } catch (ConcurrentAccessException e) {
            System.out.println("shiro-一个用户多次登录异常：不允许多次登录，只能登录一次 。即不允许多处登录");
            e.printStackTrace();
        }
        return "index";
    }

    @RequestMapping(value = "/index")
    public String index(){
        return "index";
    }

    //登出
    @RequestMapping(value = "/logout")
    public String logout(){
        return "logout";
    }

    //错误页面展示
    @RequestMapping(value = "/error",method = RequestMethod.POST)
    public String error(){
        return "error ok!";
    }

    //数据初始化
    @RequestMapping(value = "/addUser")
    public String addUser(@RequestBody Map<String,Object> map){
        User user = loginService.addUser(map);
        return "addUser is ok! \n" + user;
    }

    //角色初始化
    @RequestMapping(value = "/addRole")
    public String addRole(@RequestBody Map<String,Object> map){
        Role role = loginService.addRole(map);
        return "addRole is ok! \n" + role;
    }

    //注解的使用
    @RequiresRoles("admin")
    @RequiresPermissions("create")
    @RequestMapping(value = "/create")
    public String create(){
        return "Create success!";
    }

}
