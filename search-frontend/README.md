# BFL聚合搜索平台

# 项目介绍

基于Spring Boot + Elastic Stack + Vue3的一站式信息聚合搜索平台，用户可在同一页面集中搜索出不同来源、不同类型的内容，提升搜索体验。

# 主要工作

## 前端

1. 基于Vue 3 + Ant Design Vue 4.0.0 实现响应式页面开发，使用Tab组件 + Vue Router 动态路由实现统一页面布局，通过用户点击Tab时更改路由来切换各类数据（文章、图片、用户）的搜索结果，并选用不同的组件进行展示。
2. 为解决刷新页面后搜索结果丢失的问题，定义searchParams响应式变量来集中保存当前的搜索条件（比如关键词、搜索类别、分页），并通过Vue Router的query参数将搜索条件同步到url的querystring中。
3. 使用masonry插件，固定每张图片的宽度，高度自适应，实现图片的瀑布流布局。
4. 在tab为post时，监听搜索内容，实现搜索建议。

## 后端
https://gitee.com/lacy-lin/butterfly-search-backend/tree/master
1. 基于Spring Boot框架 + MyBatis X 插件，快速生成基本数据源的增删改查（比如用户、文章）。
2. 数据源获取：
   - 使用HttpClient请求离线获取外部网站的文章，并使用Hutool的JSONUtil解析和预处理文章，最终入库。
   - 使用jsoup实时请求bing搜索接口获取图片，并使用CSS Selector 语法解析和预处理图片信息，最终返回给前端。
3. 搜索词高亮：使用Elasticsearch的highligh语法实现搜索词高亮。
4. 数据同步：使用Spring Scheduler 定时同步近5分钟内发生更新的MySQL的文章数据到ES，通过唯一id来保证每条数据的准确性。
5. 模糊查询：为解决文章搜不出的问题，自主搭建 Elasticsearch 来代替MySQL 的模糊查询，并通过索引绑定ik分词器实现了更灵活的分词搜索。
6. 开发搜索功能时，使用Kibana DevTools + DSL 调试 ES 的搜索效果，并使用Spring Data Elasticsearch 的 QueryBuilder 组合查询条件，实现对 ES 内文章的灵活查询（比如同时查询标题和文章中带有指定关键字的内容）。
7. 搜索建议：使用Elasticsearch的suggest语法实现搜索建议。

## 环境配置：

前端：vue3 + ant design vue 4.0

后端：SpringBoot 2.7 + ElasticSearch 7.17.12 + Kibana 7.17.12 + MySQL 5.7

部署：centos7 + python3 + docker + docker-compose + jdk8 + nginx



## 部署总结

1. 安装docker
2. 安装python3
3. 安装docker-compose
4. 安装mysql
5. docker部署elasticserach
6. docker部署kibana
7. 引入ik插件
8. nginx部署前端
9. docker部署后端SpringBoot



# 效果展示

在线访问：http://chuhuan.icu/

![3.png](images%2F3.png)

![4.png](images%2F4.png)

![2.png](images%2F2.png)

![1.png](images%2F1.png)
