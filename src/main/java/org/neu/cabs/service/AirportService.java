package org.neu.cabs.service;

import org.springframework.transaction.annotation.Transactional;

/**
 * 系统核心逻辑，机场服务接口
 * @author 李浩然
 */
@Transactional(rollbackFor = Exception.class)
public interface AirportService {

}
