package com.jobCenter.comm.shiro;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jobCenter.domain.UserInfo;
import com.jobCenter.service.LogonService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;


public class MyRealm extends AuthorizingRealm {
    // 设置realm的名称
    @Override
    public void setName(String name) {
        super.setName("myRealm");
    }
    @Autowired
    private LogonService logonService;
    /**
     * 认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
        try {
            String userId = token.getUsername();
            UserInfo userInfoQuery = new UserInfo();
            userInfoQuery.setId(Long.valueOf(userId));
            UserInfo userInfo = logonService.getUserInfo(userInfoQuery);
            AuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(userInfo.getId(), userInfo.getUserPwd(), getName());
            return authenticationInfo;
        }catch (Exception e){
            throw new AuthenticationException("登录失败!",e);
        }
    }

    /**
     * 授权,只有成功通过<span style="font-family: Arial, Helvetica, sans-serif;">doGetAuthenticationInfo方法的认证后才会执行。</span>
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        // 从 principals获取主身份信息
        // 将getPrimaryPrincipal方法返回值转为真实身份类型（在上边的doGetAuthenticationInfo认证通过填充到SimpleAuthenticationInfo中身份类型），
        Long userId = (Long) principals.getPrimaryPrincipal();
        // 查到权限数据，返回授权信息(要包括 上边的permissions)
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        // 从数据库获取到权限数据
        JSONObject obj = null;//logonService.getAuthen(userId);

        obj = new JSONObject();
        JSONArray roleArr = new JSONArray();
        roleArr.add("123");
        obj.put("roles",roleArr);
        JSONArray permissionsArr = new JSONArray();
        permissionsArr.add("1234");
        obj.put("permissions",permissionsArr);

        if (obj != null) {
            // 将上边查询到授权信息填充到simpleAuthorizationInfo对象中
            JSONArray roles = obj.getJSONArray("roles");
            JSONArray permissions = obj.getJSONArray("permissions");
            this.addRoles(simpleAuthorizationInfo, roles);
            this.addPermissions(simpleAuthorizationInfo, permissions);
        }
        return simpleAuthorizationInfo;
    }
    private void addRoles(SimpleAuthorizationInfo simpleAuthorizationInfo, JSONArray roles) {
        Iterator var3 = roles.iterator();
        while (var3.hasNext()) {
            String role = (String) var3.next();
            simpleAuthorizationInfo.addRole(role);
        }
    }
    private void addPermissions(SimpleAuthorizationInfo simpleAuthorizationInfo, JSONArray permissions) {
        Iterator var3 = permissions.iterator();
        while (var3.hasNext()) {
            String permission = (String) var3.next();
            simpleAuthorizationInfo.addStringPermission(permission);
        }
    }
}
