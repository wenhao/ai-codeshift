# ai-codeshift

Spec将取代code变为唯一可信源（Single Source of Truth），零行手写从根本上改变了软件工程，围绕编程相关的实践将逐渐淡出我们的视线，Spec工程才是未来。

1. 渐进式披露：`AGENTS`的重要性，定义项目的大边界，约定俗成的规范。
2. 上下文工程：上下文完整性与上下文隔离性是控制大模型幻觉的重要原则。
3. 架构风格：是AI适应架构还是架构顺应AI，值得探索。

Harness Engineering

```
specs
├── AGENTS.md                         # 项目简介、项目目录结构、技术栈、功能代码生成规范
├── features                          # 业务领域集
│   └── feature                       # 业务领域
│       ├── entity
│       │   └── entity.md             # 数据库表契约
│       └── functions                 # 业务功能集
│           └── function              # 业务功能，原子化
│               ├── api.md            # 业务功能API契约
│               ├── service.md        # 业务逻辑
│               ├── mapper.md         # 数据库操作
│               └── feginclient.md    # 第三方API契约
└── scaffold                          # 项目脚手架
    └── scaffold.md
```

## RooCode设置

导入`.roo\codeshift.yaml`文件。输入生成功能代码的指令，如`生成功能：业务band的功能create`。
