package top.javarem.infrastructure.dao.po;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 人群标签明细
 * @TableName crowd_tags_detail
 */
@Data
public class CrowdTagsDetail implements Serializable {
    /**
     * 自增ID
     */
    private Integer id;

    /**
     * 人群标签ID
     */
    private String tagId;

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    private static final long serialVersionUID = 1L;
}