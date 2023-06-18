package com.zero.acskybackend.common;

import com.zero.acskybackend.exception.AssertionException;
import com.zero.acskybackend.utils.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.Objects;

/**
 * BlogFilter
 *
 * @author ZERO
 * @date 2023/6/18
 */
@Slf4j
public class AcSkyFilter extends AuthorizationFilter {

    @Override
    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object o) throws Exception {
        Subject subject = getSubject(servletRequest, servletResponse);
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
        if (Objects.isNull(subject)) {
            AssertionException exception = new AssertionException(500005, "账号未登录，无权限访问");
            log.info("global exception handle, code: {}, message: {} ", exception.getCode(), exception.getMessage());
            jsonString = JsonUtil.toJsonString(new LinkedHashMap<String, Object>() {{
                put("code", exception.getCode());
                put("message", exception.getMessage());
            }});
        }
        else {
            AssertionException exception = new AssertionException(500005, "无访问权限");
            log.info("global exception handle, code: {}, message: {}", exception.getCode(), exception.getMessage());
            jsonString = JsonUtil.toJsonString(new LinkedHashMap<String, Object>() {{
                put("code", exception.getCode());
                put("message", exception.getMessage());
            }});
        }
        writer.print(jsonString);
        writer.flush();
        writer.close();

        return false;
    }
}
