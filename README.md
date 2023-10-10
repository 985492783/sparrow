# sparrow

> 包含`log-gather`, `thread pool agent`等强大功能  
强依赖`nacos`, `dubbo`作为分布式注册中心以及rpc使用。

`sparrow-common` 公共包

`sparrow-bootstrap` bootstrap包 
`sparrow-client` 客户端-->bootstrap  
`sparrow-agent` agent包-->client  
`sparrow-spring-boot-starter` dubbo,nacos自动装配-->client

`sparrow-plugin` 插件化设计-->common  
`sparrow-core` 核心包-->plugin  
`sparrow-console` 控制台-->core  


## 快速使用

### 1. 通过git拉取最新源码到本地

```shell
git clone git@github.com:985492783/sparrow.git
```

### 2. 编译打包项目

```shell
maven clean install -DskipTests
```

### 3. 通过agent注入到本地项目中

VM options
>`${SPARROW_AGENT_HOME}`和`${SPARROW_BOOTSTRAP_HOME}`为真实的包位置
```text
-javaagent:${SPARROW_AGENT_HOME}\sparrow-agent.jar
-Xbootclasspath/a:${SPARROW_BOOTSTRAP_HOME}\sparrow-bootstrap.jar
```
