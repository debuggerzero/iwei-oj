package com.zero.acskybackend.common;

import com.zero.acskybackend.model.po.SystemRole;
import com.zero.acskybackend.model.po.UserInfo;
import com.zero.acskybackend.repo.SystemRoleRepo;
import com.zero.acskybackend.repo.UserInfoRepo;
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
 * @date 2023/6/18
 */
@Component
@RequiredArgsConstructor
public class AcSkyRealm extends AuthorizingRealm {

    @Resource(name = "userInfoRepoImpl")
    private final UserInfoRepo userInfoRepo;

    @Resource(name = "systemRoleRepoImpl")
    private final SystemRoleRepo systemRoleRepo;

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

        UserInfo userInfo = userInfoRepo.queryUserInfo(account);
        if (Objects.isNull(userInfo)) {
            return null;
        }
        String password = userInfo.getPassword();

        return new SimpleAuthenticationInfo(userInfo, password, "");
    }
}
