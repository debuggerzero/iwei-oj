package com.zero.iweiojbackend.repo;

import com.zero.iweiojbackend.model.po.SystemResource;

import java.util.List;

/**
 * SystemResourceRepo
 *
 * @author ZERO
 * @date 2023/6/18
 */
public interface SystemResourceRepo {

    /**
     * 查询系统资源列表
     * @return 系统资源列表
     */
    List<SystemResource> querySystemResourceList();

}
