package top.javarem.domain.message.model.entity;

import lombok.*;

import java.util.List;

/**
 * @Author: rem
 * @Date: 2025/03/28/21:12
 * @Description:
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MessageContext {

    private String notifyUrl;

    private String msgJson;

}
