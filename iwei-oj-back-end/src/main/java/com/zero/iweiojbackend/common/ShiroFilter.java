package com.zero.iweiojbackend.common;

import com.zero.iweiojbackend.exception.AssertionException;
import com.zero.iweiojbackend.model.common.ErrorCode;
import com.zero.iweiojbackend.utils.ResultUtils;
import com.zero.iweiojbackend.utils.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;

import static com.zero.iweiojbackend.model.common.UserConstant.USER_LOGIN_STATE;

/**
 * BlogFilter
 *
 * @author ZERO
 * @date 2023/12/24
 */
@Slf4j
public class ShiroFilter extends AuthorizationFilter {

    @Override
    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object o) {
        Subject subject = getSubject(servletRequest, servletResponse);
        // 用户未登录
        if (Objects.isNull(subject)) {
            return false;
        }
        String[] rolesArray = (String[]) o;
        if (Objects.isNull(rolesArray) || rolesArray.length == 0) {
            return true;
        }
        for (String perm : rolesArray) {
            if (subject.isPermitted(perm)) {
                return true;
            }
        }
        return false;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws IOException {
        HttpServletResponse servletResponse = (HttpServletResponse) response;
        Subject subject = getSubject(request, servletResponse);
        servletResponse.setCharacterEncoding("UTF-8");
        servletResponse.setContentType("text/html;charset=UTF-8");
        servletResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);

        PrintWriter writer = servletResponse.getWriter();
        String jsonString;
        ErrorCode code;
        if (Objects.isNull(subject)) {
            code = ErrorCode.NOT_LOGIN_ERROR;
        } else {
            code = ErrorCode.NO_AUTH_ERROR;
        }
        AssertionException exception = new AssertionException(code);
        log.info("AssertionException", exception);
        jsonString = JsonUtil.toJsonString(ResultUtils.error(code));
        writer.print(jsonString);
        writer.flush();
        writer.close();

        return false;
    }
}
