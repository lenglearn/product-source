package com.briup.product_source.dao.ext;

import com.briup.product_source.dao.basic.HurdlesMapper;

/**
 * 栏圈扩展映射接口
 */
public interface HurdlesExtMapper extends HurdlesMapper {
    /**
     * 将指定的栏圈栏舍编号设置为null
     * @param fenceId
     */
    void updateFenceId(String fenceId);

}
