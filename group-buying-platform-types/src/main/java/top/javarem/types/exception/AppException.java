package top.javarem.types.exception;


import lombok.Data;
import top.javarem.types.enums.ResponseCode;

/**
 * @Author: rem
 * @Date: 2025/02/27/17:36
 * @Description:
 */
@Data
public class AppException extends RuntimeException {

    private String info;
    private String code;

    public AppException(String code) {
        this.info = code;
    }

    public AppException(ResponseCode code) {

        this.info = code.getInfo();

        this.code = code.getCode();

    }

    public AppException(String code, String info) {
        this.info = info;
        this.code = code;
    }

    @Override
    public String toString() {
        return "AppException{" +
                "info='" + info + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
