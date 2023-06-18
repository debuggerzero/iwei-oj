package com.zero.acskybackend.config;

import com.zero.acskybackend.common.AcSkyFilter;
import com.zero.acskybackend.common.AcSkyRealm;
import com.zero.acskybackend.model.po.SystemResource;
import com.zero.acskybackend.repo.SystemResourceRepo;
import lombok.RequiredArgsConstructor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Shiro 框架配置类
 *
 * @author ZERO
 * @date 2023/6/18
 */
@Configuration
@RequiredArgsConstructor
public class ShiroConfig {

    private final AcSkyRealm acSkyRealm;

    @Resource(name = "systemResourceRepoImpl")
    private final SystemResourceRepo systemResourceRepo;

    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("manager") DefaultWebSecurityManager manager) {
        ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();
        factoryBean.setSecurityManager(manager);

        List<SystemResource> systemResoureList = systemResourceRepo.querySystemResourceList();
        Map<String, String> map = systemResoureList.stream()
                .collect(Collectors.toMap(SystemResource::getUrl, SystemResource::getIdentity));

        factoryBean.setFilterChainDefinitionMap(map);

        factoryBean.setFilters(new LinkedHashMap<String, Filter>() {{
            put("perms", new AcSkyFilter());
        }});
        return factoryBean;
    }

    @Bean(name = "manager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager() {
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        manager.setRealm(acSkyRealm);
        return manager;
    }

}
