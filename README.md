# 医院装备物联网管理系统

该项目是一个医院装备管理系统，结合了物联网技术，用于对医院装备进行实时监控与管理。系统使用 **Ant Design Vue** 构建前端， **Spring Boot** 构建后端，提供了用户友好的界面和强大的后台服务。

## 功能特点

- **设备实时监控**：基于物联网的设备状态监控，实时反馈设备的工作状态。
- **设备管理**：添加、修改、删除医院装备，并记录每台设备的状态信息。
- **告警功能**：当设备出现故障或状态异常时，系统将发送告警通知。
- **设备维护记录**：提供设备的维修与维护历史，便于设备管理和追踪。
- **报表导出**：支持导出设备状态与使用情况的报表，便于分析和决策。

## 技术栈

### 前端

- **Ant Design Vue**：用于构建响应式的用户界面。
- **Vue.js**：用于构建单页应用（SPA）。
- **Axios**：用于与后端 API 进行交互。

### 后端

- **Spring Boot**：后端框架，提供 RESTful API 服务。
- **MySQL**：关系型数据库，用于存储设备信息和日志数据。
- **MyBatis-Plus**：用于数据库操作的持久层框架。
- **JWT**：用于用户认证和安全。

## 系统架构

```yaml
scss


复制代码
前端 (Ant Design Vue) --> 后端 (Spring Boot) --> 数据库 (MySQL)

```

- 前端负责展示和用户交互，使用 Vue.js 和 Ant Design Vue 实现。
- 后端基于 Spring Boot，负责处理业务逻辑和提供接口。
- MySQL 数据库用于存储医院装备的详细信息和操作日志。

## 项目安装与运行

### 前端

1. 克隆仓库到本地

   ```yaml
   bash


   复制代码
   git clone https://github.com/yourusername/hospital-iot-management-frontend.git

   ```

2. 进入前端项目目录并安装依赖

   ```yaml
   bash


   复制代码
   cd hospital-iot-management-frontend
   npm install

   ```

3. 启动前端服务

   ```yaml
   bash


   复制代码
   npm run serve

   ```

4. 打开浏览器访问 `http://localhost:8080`

### 后端

1. 克隆后端项目仓库

   ```yaml
   bash


   复制代码
   git clone https://github.com/yourusername/hospital-iot-management-backend.git

   ```

2. 使用 IDE 导入项目（例如 IntelliJ IDEA）

3. 配置数据库连接信息 (`application.yml` 或 `application.properties`)

4. 运行 Spring Boot 项目

   ```yaml
   bash


   复制代码
   mvn spring-boot:run

   ```

## 使用指南

1. 访问前端页面并登录。
2. 在系统中添加或查看设备。
3. 监控设备状态，查看设备告警与维护记录。
4. 导出报表并进行分析。

## 贡献

欢迎提交 issue 和 pull request 改进项目。请遵循以下步骤：

1. Fork 本项目。
2. 创建 feature 分支：`git checkout -b feature-branch`
3. 提交修改：`git commit -m 'Add new feature'`
4. Push 到分支：`git push origin feature-branch`
5. 提交 pull request。

## 许可证

该项目基于 MIT 许可证开源。