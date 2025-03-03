package top.javarem.domain.tag.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import top.javarem.domain.tag.adapter.repository.ITagRepository;
import top.javarem.domain.tag.model.entity.CrowdTagJobEntity;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: rem
 * @Date: 2025/03/03/14:31
 * @Description: 标签服务实现类
 */
@Service
@Slf4j
public class TagService implements ITagService {

    @Resource
    private ITagRepository tagRepository;

    @Override
    public void execCrowdTagJob(String tagId, String batchId) {

        log.info("执行人群标签任务 tagId :{} batchId :{}", tagId, batchId);
//        1. 查询人群标签任务
        CrowdTagJobEntity crowdTagJobEntity = tagRepository.queryCrowdTagJob(tagId, batchId);
//        TODO 2. 采集用户数据 - 这部分需要采集用户的消费类数据，后续有用户发起拼单后再处理。

//        3. 数据写入记录
        List<String> users = new ArrayList<String>() {
            {
                add("rem");
                add("lain");
            }
        };
//        4.遍历插入人群标签明细
        for (String userId : users) {

            tagRepository.addCrowdTagDetail(tagId, userId);

        }
//        5,更新人群标签统计量
        tagRepository.updateCrowdTagStatistics(tagId, users.size());
    }
}
