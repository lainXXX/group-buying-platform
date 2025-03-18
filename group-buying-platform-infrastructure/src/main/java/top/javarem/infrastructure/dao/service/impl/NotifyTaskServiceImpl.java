package top.javarem.infrastructure.dao.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.javarem.infrastructure.dao.mapper.NotifyTaskMapper;
import top.javarem.infrastructure.dao.po.NotifyTask;
import top.javarem.infrastructure.dao.service.NotifyTaskService;

import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * @author aaa
 * @description 针对表【notify_task(任务回调表)】的数据库操作Service实现
 * @createDate 2025-03-13 21:28:48
 */
@Service
public class NotifyTaskServiceImpl extends ServiceImpl<NotifyTaskMapper, NotifyTask>
        implements NotifyTaskService {

    @Override
    public List<NotifyTask> queryUnExecutedNotifyTask() {

        return lambdaQuery()
                .select(NotifyTask::getTeamId, NotifyTask::getNotifyUrl, NotifyTask::getNotifyCount, NotifyTask::getNotifyStatus, NotifyTask::getParameterJson)
                .in(NotifyTask::getNotifyStatus, 0, 2)
                .last("limit 50")
                .list();

    }

    @Override
    public int updateNotifyTaskStatusSuccess(String teamId) {

        return this.baseMapper.update(null, new LambdaUpdateWrapper<NotifyTask>()
                .setSql("notify_count = notify_count + 1")
                .set(NotifyTask::getNotifyStatus, 1)
                .set(NotifyTask::getUpdateTime, new Date())
                .eq(NotifyTask::getTeamId, teamId)
        );

    }

    @Override
    public int updateNotifyTaskStatusError(String teamId) {

        return this.baseMapper.update(null, new LambdaUpdateWrapper<NotifyTask>()
                .setSql("notify_count = notify_count + 1")
                .set(NotifyTask::getNotifyStatus, 3)
                .set(NotifyTask::getUpdateTime, new Date())
                .eq(NotifyTask::getTeamId, teamId)
        );

    }

    @Override
    public int updateNotifyTaskStatusRetry(String teamId) {

        return this.baseMapper.update(null, new LambdaUpdateWrapper<NotifyTask>()
                .setSql("notify_count = notify_count + 1")
                .set(NotifyTask::getNotifyStatus, 2)
                .set(NotifyTask::getUpdateTime, new Date())
                .eq(NotifyTask::getTeamId, teamId)
        );

    }

    @Override
    public List<NotifyTask> queryUnExecutedNotifyTask(String teamId) {
        return lambdaQuery()
                .select(NotifyTask::getTeamId, NotifyTask::getNotifyUrl, NotifyTask::getNotifyCount, NotifyTask::getNotifyStatus, NotifyTask::getParameterJson)
                .eq(NotifyTask::getTeamId, teamId)
                .in(NotifyTask::getNotifyStatus, 0, 2)
                .last("limit 50")
                .list();
    }
}
