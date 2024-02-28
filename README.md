<h1 align="center">OpenApi 接口开放平台</h1>
<p align="center"><strong>OpenApi 接口开放平台是一个为用户和开发者提供全面API接口调用服务的平台 🛠</strong></p>
<div align="center">
    <img alt="" src="https://raster.shields.io/badge/Maven-3.9.5-red.svg"/>
    <a target="_blank" href="https://www.oracle.com/technetwork/java/javase/downloads/index.html">
        <img alt="" src="https://img.shields.io/badge/JDK-17-green.svg"/>
    </a>
    <img alt="" src="https://raster.shields.io/badge/SpringBoot-3.2.2-green.svg"/>
</div>






## 项目介绍 🙋

**😀 作为用户您可以通过注册登录账户，获取接口调用权限，并根据自己的需求浏览和选择适合的接口。您可以在线进行接口调试，快速验证接口的功能和效果。** 

💻 **本项目为java后端面试项目，所以前端会有些简陋，非必要功能就没用去做了，比如接口的展示方式（现在这个样确实丑陋orz）**

# 已实现接口见文档

- [**Swagger文档**️](http://localhost:1111/doc.html#/home)

## 网站导航 🧭

- [**OpenApI 前端 🏘**️](https://github.com/ChangeTTTO/OpenApi_frontend)

## 目录结构 📑


| 目录                                                     | 描述               |
|--------------------------------------------------------| ------------------ |
| **🏘️ [OpenApi](./)**             | OpenApI后端父模块 |
| **🏘️ [service](/service)**               | 业务模块       |
| **🕸️ [gateway](/gateway)**             | 网关模块           |
| **🔗 [api](/api)**          | 目标接口模块           |
|   **🔗 [feign](/feign)**                        | 远程调用模块 |

## 项目流程 🗺️

## 快速启动 🚀

### 前端

环境要求：Node.js >= 18

安装依赖：

```bash
yarn or  npm install
```

启动：

```bash
yarn run dev or npm run start:dev
```

部署：

```bash
yarn build or npm run build
```

### 后端

执行sql目录下ddl.sql

## 项目选型 🎯

### **后端**

- Spring Boot 
- Spring MVC
- MySQL 数据库
- Redis
- Spring Cloud OpenFeign远程调用
- Spring Cloud Gateway 微服务网关
- Spring Cloud Nacos注册中心
- RabbitMQ
- Swagger + Knife4j 接口文档
- [Sa-Token](https://sa-token.cc/)
- MyBatis
- Hutool、Apache Common Utils、Gson 等工具库

### 前端

- Vue3
- element-plus组件库

## 功能展示 ✨

### 首页

### 免费接口

<img src="https://rxbby.oss-cn-guangzhou.aliyuncs.com/Picture/image-20240228170817987.png" alt="image-20240228170817987" style="zoom:80%;" />

### 会员接口

<img src="https://rxbby.oss-cn-guangzhou.aliyuncs.com/Picture/image-20240228170825683.png" alt="image-20240228170825683" style="zoom:80%;" />

### 会员服务

<img src="https://rxbby.oss-cn-guangzhou.aliyuncs.com/Picture/image-20240228160958326.png" alt="image-20240228160958326" style="zoom:80%;" />

### 接口描述

#### **接口信息以及在线调试**

![image-20240228154407288](https://rxbby.oss-cn-guangzhou.aliyuncs.com/Picture/image-20240228154407288.png)





