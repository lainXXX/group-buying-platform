# develop BY rem 学习项目

人群标签节点过滤
首先需要商品和用户Id
再进行折扣
再进行人群标签检测限制 如果该活动商品配置了人群标签限制就是只能在该活动商品配置标签下的用户才能参与

dcc动态配置
首先注册redis监听器 
监听器过程：
1.获取msg 拆分msg msg中包含key和value
2.通过key来判断redis bucket存不存在 不存在则直接返回
3.如果存在则更新bucket的值
4.取出dccObjGroup中的bean对象 判断是否为代理对象 如果是则设置为原始对象
5.设置bean对象的指定对象的值

postProcessAfterInitialization 
目的：获取带有@DCCValue注解的bean 并从注解的value中设置带有注解的字段的值 并装入dccObjGroup
提示.postProcessAfterInitialization是把所有的bean都执行一遍 所以需要判断遍历的当前bean是否带有注解
1.获取bean对象 并判断是否为代理对象
2.判断当前bean是否带有@DCCValue注解
3.通过key获取redis bucket
4.判断bucket是否存在 不存在则设置defaultValue 存在则从bucket中取出value
5.设置字段的值
6.装入group

