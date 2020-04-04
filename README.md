## 基于Spring-boot个人博客系统:bulb:
#### 采用**springboot2.1.7+mybatis-plus**
## 本地开发  
- **第一步：** 把数据库文件导入本地，已上传  
- **第二步：** 修改application一系列配置文件(redis配置文件，数据库配置文件)，如不知道application存在位置，可查看下面的项目架构  
- **第三步：** 默认账号：18379461836  密码：11111111  
- **第四步：** 在网页打开： localhost:8080  

## 项目架构
```java
|--- pom                                        // vblog配置文件
|--- blog-plus.sql                              // vblog数据库设计
|--- src                                        // 源代码
|--- |--- main                                  // 代码页
          |--- Java                             // 后台代码
             |--- common                        // 公共类
                   |--- config                  // 配置类(以.config后缀结尾)
                   |--- utils                   // 工具类
         |--- modules                           // 服务端代码
                  |--- controller               // 表现层
                  |--- dao                      // 持久层
                  |--- entity                   // 实体层
                  |--- service                  // 业务逻辑层
                  |--- shiro                    // shiro配置类
                  |--- VblogApplication        // spring boot启动类
|--- |--- resources                             // 资源
              |--- mappering                    // 持久层xml文件
              |--- static                       // 静态文件
              |--- templates                    // 前端页面
              |--- application.properties       // 全局配置类
              |--- application.yml              // 全局配置类
              |--- application-dev.yml          // 全局配置类(开发者模式)
              |--- application-test.yml         // 全局配置类(测试者模式)
              |--- application-prod.yml         // 全局配置类(生产者模式)
```

## 技术能力
 我是一个后台开发人（Java）,对前台不是那么的了解，单单只是能搭出来，效果的话，我已经尽我洪荒之力了，就搭出这个水平，希望不要见怪:sleepy:。
### 项目介绍
1. 博客项目对于初学spring boot的人是个不错的练手项目，即不容易，又不复杂，体验一下做项目的感受。  
2. 该网站已完成基本功能，后续不断更新修改。  
3. 在文章，评论等处添加缓存，提高性能。  
4. 待部署。  
## 技术展示
### 后台：
:seedling:项目构建：Maven  
:seedling:web框架： spring boot  
:seedling:数据持久层： mybatis-plus  
:seedling:安全框架： shiro (暂定，后续可能采用jwt) 
:seedling:搜索引擎： elasticSearch  
:seedling:缓存：redis  
:seedling:数据库：Mysql  

### 前台
:seedling:前台框架：[layui框架](https://www.layui.com/ "layui框架") [amazeui框架](https://amazeui.clouddeep.cn/ "amazeui框架")  
:seedling:前端模板： thymeleaf  


#### 一些细小的框架，就不一一列举了

## 页面展示

## 最后
  博客项目对于spring boot初学者来说，是一个不错的项目体验，从零搭建项目，从无到有，体验项目的完整性，锻炼自己做项目的能力，全面发展。

