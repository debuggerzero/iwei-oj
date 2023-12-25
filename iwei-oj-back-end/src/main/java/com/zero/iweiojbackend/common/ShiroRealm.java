package com.zero.iweiojbackend.common;

import com.zero.iweiojbackend.model.query.UserInfoQuery;
import com.zero.iweiojbackend.model.po.SystemRole;
import com.zero.iweiojbackend.model.po.UserInfo;
import com.zero.iweiojbackend.repo.SystemRoleRepo;
import com.zero.iweiojbackend.repo.UserInfoRepo;
import lombok.RequiredArgsConstructor;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * 自定义 Realm
 *
 * @author ZERO
 * @date 2023/12/24
 */
@Component
@RequiredArgsConstructor
public class ShiroRealm extends AuthorizingRealm {

    @Resource(name = "userInfoRepoImpl")
    private UserInfoRepo userInfoRepo;

    @Resource(name = "systemRoleRepoImpl")
    private SystemRoleRepo systemRoleRepo;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        Subject subject = SecurityUtils.getSubject();
        UserInfo user = (UserInfo) subject.getPrincipal();
        SystemRole systemRole = systemRoleRepo.querySystemRole(user.getAccount());
        authorizationInfo.addStringPermission(systemRole.getName());

        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String account = token.getUsername();
        UserInfo userInfo = userInfoRepo.getOne(UserInfoQuery.builder().account(account).build());
        if (Objects.isNull(userInfo)) {
            return null;
        }
        String password = userInfo.getPassword();

        return new SimpleAuthenticationInfo(userInfo, password, "");
    }
}
