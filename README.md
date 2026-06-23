<p align="center">
  <img src="https://img.shields.io/badge/Java-8+-orange" alt="Java Version">
  <img src="https://img.shields.io/badge/Spring%20Boot-2.7.12-brightgreen" alt="Spring Boot Version">
  <img src="https://img.shields.io/badge/Dubbo-3.0.9-red" alt="Dubbo Version">
  <img src="https://img.shields.io/badge/License-MIT-blue" alt="License">
</p>

<h1 align="center">🛒 Group-Buying Platform</h1>
<p align="center"><strong>A production-grade group-buying &amp; marketplace platform built with DDD architecture</strong></p>

---

## 📋 Overview

**Group-Buying Platform** is a full-featured, domain-driven e-commerce platform that supports group-buying activities, dynamic discount pricing, crowd-based targeting, and multi-channel settlement. Designed with clean architecture and enterprise-grade patterns, it serves as both a learn-by-doing project and a solid foundation for real-world marketplace systems.

The platform integrates with external mini-mall systems via Dubbo RPC, processes asynchronous callbacks through RabbitMQ, and dynamically adjusts configuration at runtime via Redis-based DCC (Dynamic Configuration Center).

---

## ✨ Features

| Feature | Description |
|---------|-------------|
| **Group-Buying Activities** | Create and manage group-buying campaigns with configurable rules |
| **Dynamic Discount Engine** | Multiple discount types: percentage off (ZK), fixed reduction (ZJ), amount off (MJ), straight discount (N) |
| **Crowd Tag Filtering** | Restrict activity participation based on user crowd tags |
| **Rule Chain (Responsibility Chain)** | Extensible trade rule filtering using Chain of Responsibility pattern |
| **Multi-Threaded Strategy Router** | Concurrent query aggregation with decision tree routing |
| **DCC (Dynamic Configuration Center)** | Runtime configuration updates via Redis pub/sub without restart |
| **Multi-Channel Settlement** | Strategy-pattern based settlement callbacks: HTTP, MQ, RPC |
| **Distributed Message Delivery** | Reliable async message processing with RabbitMQ |
| **Dubbo RPC API** | Expose group-buying services as Dubbo RPC interfaces |
| **Team Formation & Settlement** | Group-buying team creation, progress tracking, and settlement |

---

## 🏗️ Architecture

```
┌──────────────────────────────────────────────────────────────────────┐
│                        Trigger Layer                                │
│  ┌──────────────┐  ┌───────────────┐  ┌──────────────────────────┐  │
│  │  HTTP Controllers │  │  MQ Listeners  │  │  Scheduled Jobs        │  │
│  └──────────────┘  └───────────────┘  └──────────────────────────┘  │
├──────────────────────────────────────────────────────────────────────┤
│                        Domain Layer                                 │
│  ┌─────────────────────┐  ┌──────────────────┐  ┌────────────────┐ │
│  │  Activity (Discount) │  │  Trade (Order)    │  │  Tag (Crowd)   │ │
│  │  • Discount Strategy │  │  • Rule Filter    │  │  • Tag Check   │ │
│  │  • Trial Balance     │  │  • Lock/Settle    │  │  • Job Process │ │
│  │  • Group-Buying      │  │  • Team Progress  │  │                │ │
│  └─────────────────────┘  └──────────────────┘  └────────────────┘ │
│  ┌──────────────────────────────────────────────────────────────┐   │
│  │  Message (Distribution)                                      │   │
│  │  • Callback Strategies (HTTP / MQ / RPC)                     │   │
│  └──────────────────────────────────────────────────────────────┘   │
├──────────────────────────────────────────────────────────────────────┤
│                     Infrastructure Layer                            │
│  ┌──────────┐ ┌──────────┐ ┌────────┐ ┌────────┐ ┌──────────────┐  │
│  │  MySQL    │ │  Redis   │ │RabbitMQ│ │ Nacos  │ │  Dubbo RPC   │  │
│  │(MyBatis+) │ │(Redisson)│ │        │ │        │ │              │  │
│  └──────────┘ └──────────┘ └────────┘ └────────┘ └──────────────┘  │
├──────────────────────────────────────────────────────────────────────┤
│                            API Layer                                │
│           Dubbo RPC Interfaces & DTOs (top.javarem.api)            │
├──────────────────────────────────────────────────────────────────────┤
│                         Types Layer                                 │
│   Annotations, Enums, Exceptions, Design Framework, Utilities       │
├──────────────────────────────────────────────────────────────────────┤
│                         App Layer                                   │
│         Spring Boot Application Entry & Configuration               │
└──────────────────────────────────────────────────────────────────────┘
```

---

## 🧩 Module Structure

| Module | Responsibility |
|--------|---------------|
| `group-buying-platform-api` | Dubbo RPC service interfaces, request/response DTOs |
| `group-buying-platform-app` | Spring Boot application entry, config (Redis, ThreadPool, OKHttp, DCC) |
| `group-buying-platform-domain` | Core business logic — activity, trade, tag, message domains |
| `group-buying-platform-infrastructure` | Data persistence (MyBatis-Plus), Redis, DCC, event publishing, gateway |
| `group-buying-platform-trigger` | HTTP controllers, MQ listeners, scheduled cron jobs |
| `group-buying-platform-types` | Common types, custom annotations, design pattern framework, enums, exceptions |

---

## 🛠️ Tech Stack

| Technology | Purpose |
|-----------|---------|
| **Java 8** | Runtime |
| **Spring Boot 2.7.12** | Application framework |
| **Apache Dubbo 3.0.9** | RPC service exposure & invocation |
| **Nacos** | Service discovery & configuration |
| **RabbitMQ** | Async message & event processing |
| **Redis / Redisson** | Caching, distributed locks, DCC |
| **MySQL + MyBatis-Plus** | Data persistence |
| **Nginx** | Reverse proxy & static resources |
| **Maven** | Build & dependency management |
| **JWT** | Authentication (login) |

### Design Patterns Applied

- **Strategy Pattern** — Discount calculation (ZK, ZJ, MJ, N), settlement callbacks (HTTP, MQ, RPC)
- **Chain of Responsibility** — Trade rule filtering pipeline (3 model variants)
- **Factory Pattern** — Rule filter & settlement strategy creation
- **Template Method** — Abstract discount calculation workflow
- **Strategy Router (Decision Tree)** — Multi-threaded concurrent query aggregation
- **AOP + Annotation** — Distributed message publishing via `@DistributeMessage`

---

## 🚀 Quick Start

### Prerequisites

- JDK 1.8+
- MySQL 8.0+
- Redis (with Redisson)
- RabbitMQ
- Nacos 2.x
- Maven 3.6+

### Steps

```bash
# 1. Clone the repository
git clone https://github.com/lainXXX/group-buying-platform.git

# 2. Import database schema
mysql -u root -p < docs/dev-ops/mysql/sql/20250328-distribute-message-group_buying_platform.sql

# 3. Configure application
# Edit group-buying-platform-app/src/main/resources/application-dev.yml
# Set your MySQL, Redis, RabbitMQ, and Nacos connection info

# 4. Build the project
cd group-buying-platform
mvn clean install -DskipTests

# 5. Run
java -jar group-buying-platform-app/target/group-buying-platform-app-1.0-SNAPSHOT.jar
```

---

## 🔌 API Exposed (Dubbo RPC)

| Interface | Description |
|-----------|-------------|
| `IMarketIndexService` | Market index & product inquiry |
| `IMarketTradeService` | Trade-related operations (lock order, settle, notify) |
| `DCCService` | Dynamic configuration center operations |

---

## 📄 License

This project is licensed under the **MIT License** — see the [LICENSE](./LICENSE) file for details.

---

<p align="center">
  Built with ❤️ for learning and sharing
</p>
