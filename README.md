# AI No-Code 应用生成平台

这是一个基于自然语言生成 Web 应用的全栈项目。用户可以输入需求，与 AI 实时对话，生成 HTML 或 Vue 多文件应用，并预览、管理和部署生成结果。

## 主要功能

- 自然语言生成 HTML 或 Vue 应用
- SSE 流式 AI 对话与实时生成反馈
- 代码质量检查、代码解析和多文件保存
- 应用创建、编辑、查询、删除和精选管理
- 生成结果实时预览与应用部署
- 普通用户和管理员权限隔离
- 图片、图标和图表素材收集工作流

## 技术栈

后端使用 Java 21、Spring Boot 3、LangChain4j、LangGraph4j、MySQL、Redis、Redisson、MyBatis、SSE 和 Maven。前端使用 Vue 3、TypeScript、Vite、Ant Design Vue、Vue Router、Pinia 和 Axios。

## 项目结构

    src/          Spring Boot 后端源码、配置和提示词
    frontend/     Vue 3 前端源码
    pom.xml       Maven 配置
    mvnw*         Maven Wrapper

demos/ 和 sql/ 目录仅用于本地示例或数据库脚本，已加入 Git 忽略规则，不会上传到 GitHub。

## 环境要求

- JDK 21
- Maven 3.9+（或使用项目内 Maven Wrapper）
- Node.js 20+ 和 npm
- MySQL 8+、Redis 7+
- AI、对象存储和图片搜索服务密钥

## 本地运行

先创建 src/main/resources/application-local.yml，填写 MySQL、Redis、AI、对象存储和图片服务配置。该文件已被忽略，不能提交真实密钥。

启动后端：

    ./mvnw spring-boot:run

Windows PowerShell：

    .\\mvnw.cmd spring-boot:run

后端默认地址为 http://localhost:8123/api。启动前端：

    cd frontend
    npm install
    npm run dev

构建检查：后端执行 ./mvnw test，前端在 frontend 目录执行 npm run build。

