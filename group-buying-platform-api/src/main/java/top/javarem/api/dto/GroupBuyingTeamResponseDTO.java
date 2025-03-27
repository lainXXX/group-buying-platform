package top.javarem.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author: rem
 * @Date: 2025/03/25/23:27
 * @Description: 没有拼团完成的组队集合
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GroupBuyingTeamResponseDTO {

    private List<Team> teamList;

    public static class Team {
        // 用户ID
        private String userId;
        // 拼单组队ID
        private String teamId;
    }

}
