your-project-name/
├── src/
│   └── main/
│       ├── java/
│       │   └── com/
│       │       └── company/
│       │           └── project/
│       │               ├── Application.java               # 项目启动类（根包）
│       │               ├── config/                        # 配置类
│       │               ├── constant/                      # 常量定义
│       │               ├── controller/                    # 接口控制层（API入口）
│       │               ├── service/                       # 业务逻辑层
│       │               │   ├── impl/                      # 业务实现类
│       │               │   └── UserService.java           # 业务接口
│       │               ├── mapper/                        # 数据访问层（MyBatis/MyBatis-Plus）
│       │               ├── entity/                        # 数据库实体对象
│       │               │   ├── po/                        # 持久化对象（对应数据库表）
│       │               │   ├── dto/                       # 数据传输对象（接口入参）
│       │               │   ├── vo/                        # 视图对象（接口返回值）
│       │               │   └── bo/                        # 业务对象（Service 内部用）
│       │               ├── common/                        # 公共模块
│       │               │   ├── result/                    # 统一返回结果
│       │               │   ├── exception/                 # 全局异常
│       │               │   └── base/                      # 基类
│       │               ├── util/                          # 工具类
│       │               └── enums/                         # 枚举类
│       └── resources/
│           ├── application.yml                           # 主配置文件
│           ├── application-dev.yml                       # 开发环境配置
│           ├── application-prod.yml                      # 生产环境配置
│           └── mapper/                                   # MyBatis XML 文件
├── pom.xml                                               # Maven 依赖
└── README.md                                             # 项目说明