package top.javarem.infrastructure.dao.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.javarem.infrastructure.dao.mapper.NotifyTaskMapper;
import top.javarem.infrastructure.dao.po.NotifyTask;
import top.javarem.infrastructure.dao.service.NotifyTaskService;

/**
* @author aaa
* @description 针对表【notify_task(任务回调表)】的数据库操作Service实现
* @createDate 2025-03-13 21:28:48
*/
@Service
public class NotifyTaskServiceImpl extends ServiceImpl<NotifyTaskMapper, NotifyTask>
implements NotifyTaskService {

}
