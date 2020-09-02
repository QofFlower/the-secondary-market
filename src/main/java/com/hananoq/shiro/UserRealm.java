package com.hananoq.shiro;

import com.hananoq.domain.User;
import com.hananoq.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author :花のQ
 * @since 2020/7/7 9:23
 **/
public class UserRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }


    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
        User user = userService.findByUsername(usernamePasswordToken.getUsername());

        if (user == null) {
            throw new UnknownAccountException("用户名不存在");
        }

        return new SimpleAuthenticationInfo(user, user.getPassword(), "");
    }
}
