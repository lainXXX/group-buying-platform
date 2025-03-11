package top.javarem.test.types.design.framework.link.model2;

import lombok.Data;

/**
 * @Author: rem
 * @Date: 2025/03/10/20:47
 * @Description:
 */
@Data
public class XResponse {

    private final String response;

    public XResponse(String response) {
        this.response = response;
    }

}
