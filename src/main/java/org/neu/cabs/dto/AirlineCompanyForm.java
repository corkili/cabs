package org.neu.cabs.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 航空公司表单
 * @author 李浩然
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AirlineCompanyForm {
    /**
     * 公司ID
     */
    private Integer id;

    /**
     * 公司名称
     */
    private String companyName;

    /**
     * 公司代码
     */
    private String companyCode;

    /**
     * 成立时间
     */
    private String establishTime;
}
