package org.neu.cabs.dto;

import java.util.HashMap;
import java.util.Map;

/**
 * 描述服务层提供的服务结果
 * @author 李浩然
 */
public class ServiceResult {
    private boolean successful;

    private String message;

    private Map<String, Object> extra;

    public ServiceResult(boolean successful, String message, Object... extras) {
        this.successful = successful;
        this.message = message;
        this.extra = new HashMap<>();
        for (int i = 0; i < extras.length; i += 2) {
            this.extra.put(extras[i].toString(), extras[i + 1]);
        }
    }

    public ServiceResult putExtra(String name, Object value) {
        this.extra.put(name, value);
        return this;
    }

    public ServiceResult putExtras(Object... extras) {
        for (int i = 0; i < extras.length; i += 2) {
            this.extra.put(extras[i].toString(), extras[i + 1]);
        }
        return this;
    }

    public Object getExtra(String name) {
        return extra.get(name);
    }

    public boolean isSuccessful() {
        return this.successful;
    }

    public String getMessage() {
        return this.message;
    }
}
