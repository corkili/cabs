package org.neu.cabs.dto;

import lombok.Data;
import org.neu.cabs.orm.Airplane;

import java.util.Set;

/**
 * 航空公司查询结果
 * @author 李浩然
 */
@Data
public class AirlineCompanyResult extends ResponseResult {

    /**
     * 公司ID
     */
    private long id;

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

    /**
     * 飞机数量
     */
    private int airplaneNumber;

    /**
     * 拥有飞机
     */
    private Set<Airplane> airplanes;

    public AirlineCompanyResult(String result, String msg, long id, String companyName, String companyCode,
                                String establishTime, int airplaneNumber, Set<Airplane> airplanes) {
        super(result, msg);
        this.id = id;
        this.companyName = companyName;
        this.companyCode = companyCode;
        this.establishTime = establishTime;
        this.airplaneNumber = airplaneNumber;
        this.airplanes = airplanes;
    }

    public AirlineCompanyResult(String result, String msg) {
        super(result, msg);
    }
}
