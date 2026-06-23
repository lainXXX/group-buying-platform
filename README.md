<p align="center">
  <img src="https://img.shields.io/badge/Java-8+-orange" alt="Java Version">
  <img src="https://img.shields.io/badge/Spring%20Boot-2.7.12-brightgreen" alt="Spring Boot Version">
  <img src="https://img.shields.io/badge/Dubbo-3.0.9-red" alt="Dubbo Version">
  <img src="https://img.shields.io/badge/License-MIT-blue" alt="License">
</p>

<h1 align="center">🛒 拼团平台 Group-Buying Platform</h1>
<p align="center"><strong>基于 DDD 领域驱动设计的企业级拼团交易系统</strong></p>

---

## 📋 项目简介

**拼团平台** 是一个完整的企业级电商拼团交易系统，基于 DDD（领域驱动设计）分层架构实现。支持灵活配置的拼团活动、动态折扣计算引擎、人群标签风控过滤、以及多渠道结算回调。

项目通过 Dubbo RPC 对外提供拼团服务，可与外部商城系统无缝对接；采用 RabbitMQ 处理异步消息与事件分发；基于 Redis 实现运行时动态配置中心（DCC），无需重启即可调整业务参数。

---

## ✨ 核心功能

| 功能 | 描述 |
|------|------|
| **拼团活动管理** | 创建和管理拼团活动，支持灵活的规则配置 |
| **动态折扣引擎** | 四种折扣类型：百分比折扣（ZK）、直降（ZJ）、满减（MJ）、直减（N） |
| **人群标签过滤** | 基于用户标签进行活动准入控制，精准营销 |
| **责任链规则过滤** | 可扩展的交易规则过滤管道，支持灵活编排 |
| **多线程策略路由** | 基于决策树的多线程并发查询汇聚，提升性能 |
| **动态配置中心（DCC）** | 基于 Redis 发布订阅，运行时动态调整配置 |
| **多渠道结算** | 策略模式结算回调：HTTP、MQ、RPC 三种渠道 |
| **分布式消息** | 基于 RabbitMQ 的可靠异步消息处理 |
| **Dubbo RPC 接口** | 以 Dubbo 协议对外暴露拼团服务 |
| **拼团组队结算** | 成团进度追踪、组队统计、自动结算 |

---

## 🏗️ 系统架构

```
┌──────────────────────────────────────────────────────────────────────┐
│                         Trigger 触发层                                │
│  ┌──────────────┐  ┌───────────────┐  ┌──────────────────────────┐  │
│  │  HTTP 控制器  │  │  MQ 消息监听  │  │  定时任务                  │  │
│  └──────────────┘  └───────────────┘  └──────────────────────────┘  │
├──────────────────────────────────────────────────────────────────────┤
│                         Domain 领域层                                 │
│  ┌─────────────────────┐  ┌──────────────────┐  ┌────────────────┐ │
│  │  活动（折扣）         │  │  交易（订单）      │  │  标签（人群）    │ │
│  │  • 折扣策略计算      │  │  • 规则过滤       │  │  • 标签校验    │ │
│  │  • 试算平衡          │  │  • 锁单/结算      │  │  • 标签任务处理 │ │
│  │  • 拼团信息          │  │  • 成团进度追踪   │  │                │ │
│  └─────────────────────┘  └──────────────────┘  └────────────────┘ │
│  ┌──────────────────────────────────────────────────────────────┐   │
│  │  消息（分发）                                                  │   │
│  │  • 回调策略（HTTP / MQ / RPC）                                │   │
│  └──────────────────────────────────────────────────────────────┘   │
├──────────────────────────────────────────────────────────────────────┤
│                     Infrastructure 基础设施层                         │
│  ┌──────────┐ ┌──────────┐ ┌────────┐ ┌────────┐ ┌──────────────┐  │
│  │  MySQL    │ │  Redis   │ │RabbitMQ│ │ Nacos  │ │  Dubbo RPC   │  │
│  │(MyBatis+) │ │(Redisson)│ │        │ │        │ │              │  │
│  └──────────┘ └──────────┘ └────────┘ └────────┘ └──────────────┘  │
├──────────────────────────────────────────────────────────────────────┤
│                          API 层                                      │
│              Dubbo RPC 接口 & DTO（top.javarem.api）                 │
├──────────────────────────────────────────────────────────────────────┤
│                         Types 基础类型层                              │
│           注解、枚举、异常、设计模式框架、工具类                       │
├──────────────────────────────────────────────────────────────────────┤
│                         App 应用层                                    │
│                  Spring Boot 启动入口 & 配置                          │
└──────────────────────────────────────────────────────────────────────┘
```

---

## 🧩 模块说明

| 模块 | 职责 |
|------|------|
| `group-buying-platform-api` | Dubbo RPC 服务接口、请求/响应 DTO |
| `group-buying-platform-app` | Spring Boot 启动入口、配置（Redis、线程池、OKHttp、DCC） |
| `group-buying-platform-domain` | 核心业务逻辑 — 活动、交易、标签、消息四大领域 |
| `group-buying-platform-infrastructure` | 数据持久化（MyBatis-Plus）、Redis 缓存、DCC、事件发布、外部网关 |
| `group-buying-platform-trigger` | HTTP 控制器、MQ 消息监听器、定时任务 |
| `group-buying-platform-types` | 公共类型、自定义注解、设计模式框架（责任链/决策树）、枚举、异常 |

---

## 🛠️ 技术栈

| 技术 | 用途 |
|------|------|
| **Java 8** | 运行环境 |
| **Spring Boot 2.7.12** | 应用框架 |
| **Apache Dubbo 3.0.9** | RPC 远程调用 |
| **Nacos** | 服务发现与配置管理 |
| **RabbitMQ** | 异步消息与事件处理 |
| **Redis / Redisson** | 缓存、分布式锁、动态配置中心 |
| **MySQL + MyBatis-Plus** | 数据持久化 |
| **Nginx** | 反向代理与静态资源 |
| **Maven** | 构建与依赖管理 |
| **JWT** | 身份认证 |

### 运用到的设计模式

- **策略模式** — 折扣计算（ZK、ZJ、MJ、N）、结算回调（HTTP、MQ、RPC）
- **责任链模式** — 交易规则过滤管道（内置 3 种模型变体）
- **工厂模式** — 规则过滤器与结算策略的创建
- **模板方法** — 抽象折扣计算流程
- **策略路由（决策树）** — 多线程并发查询汇聚
- **AOP + 注解** — 通过 `@DistributeMessage` 实现分布式消息发布

---

## 🚀 快速开始

### 环境要求

- JDK 1.8+
- MySQL 8.0+
- Redis（配合 Redisson）
- RabbitMQ
- Nacos 2.x
- Maven 3.6+

### 启动步骤

```bash
# 1. 克隆项目
git clone https://github.com/lainXXX/group-buying-platform.git

# 2. 导入数据库
mysql -u root -p < docs/dev-ops/mysql/sql/20250328-distribute-message-group_buying_platform.sql

# 3. 修改配置
# 编辑 group-buying-platform-app/src/main/resources/application-dev.yml
# 配置 MySQL、Redis、RabbitMQ、Nacos 等连接信息

# 4. 构建项目
cd group-buying-platform
mvn clean install -DskipTests

# 5. 启动
java -jar group-buying-platform-app/target/group-buying-platform-app-1.0-SNAPSHOT.jar
```

---

## 🔌 Dubbo RPC 接口

| 接口 | 说明 |
|------|------|
| `IMarketIndexService` | 首页营销及商品查询 |
| `IMarketTradeService` | 交易相关操作（锁单、结算、通知） |
| `DCCService` | 动态配置中心操作 |
| `IMallTestService` | 商城测试接口 |

---

## 📄 开源协议

本项目基于 **MIT License** 开源，详情请查看 [LICENSE](./LICENSE) 文件。

---

<p align="center">
  一个学习与分享的项目 ❤️
</p>
