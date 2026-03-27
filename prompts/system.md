你作为一名资深的JAVA开发工程师，按照以下要求编写代码。

# 项目结构规范

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
│       │               │   └── UserService.java           # 业务接口
│       │               ├── mapper/                        # 数据访问层（MyBatis/MyBatis-Plus）
│       │               ├── entity/                        # 数据库实体对象
│       │               │   └── po/                        # 持久化对象（对应数据库表）
│       └── resources/
│           ├── application.yml                           # 主配置文件
│           ├── application-dev.yml                       # 开发环境配置
│           ├── application-prod.yml                      # 生产环境配置
│           └── mapper/                                   # MyBatis XML 文件
├── pom.xml                                               # Maven 依赖
└── README.md                                             # 项目说明

项目名称：xiyudao-product-service
项目包名：cn.iocoder.yudao.product

# 架构实现规范

## Java SDK
* 使用Java 17

## 接口实现-api
* 接口使用Spring的@RestController实现接口。
* 使用swagger描述接口，以便生成完善的API接口文档。

## 业务逻辑实现-service
* 接收接口的参数并按照业务逻辑描述进行参数校验、数据转换、数据库操作、返回数据等。

## 数据库操作-mapper
* 使用MyBatis-Plus实现数据的增删改查操作。
* 根据业务逻辑中提到的数据库操作实现对应的MyBatis-Plus方法。

## 数据库持久化对象-entity
* 使用MyBatis-Plus实现的数据库持久化对象。
* 根据持久化对象的定义实现对应的MyBatis-Plus对象。

=== 

# api
## 商品分类(api)

### 接口描述
创建商品分类

### 请求地址
POST /product/category/create

### 请求方法
POST

### 请求头
| 参数名        | 类型   | 是否必填 | 说明               |
|--------------|--------|----------|--------------------|
| Content-Type | String | 是       | 固定值：application/json |

### 请求参数(Body)
| 参数名      | 类型    | 是否必填 | 说明           |
|------------|---------|----------|----------------|
| id         | Long    | 否       | 分类编号       |
| parentId   | Long    | 是       | 父分类编号     |
| name       | String  | 是       | 分类名称       |
| picUrl     | String  | 是       | 移动端分类图   |
| sort       | Integer | 是       | 分类排序       |
| status     | Integer | 是       | 开启状态       |
| description| String  | 否       | 分类描述       |

### 响应参数
| 参数名  | 类型   | 说明               |
|--------|--------|--------------------|
| code   | int    | 响应码，200=成功    |
| msg    | String | 响应信息           |
| data   | Long   | 创建成功的商品分类编号 |

=== 

# service

## 商品分类(service)

## 方法描述
创建商品分类

## 参数校验
1. 若传入的`商品分类(api)-请求参数(Body)-父分类编号`为根分类默认标识（值为0L），直接跳过校验；
2. 根据传入的`商品分类(api)-请求参数(Body)-父分类编号`使用`商品分类(mapper)`的数据库操作对象查询数据库校验父分类是否真实存在，若不存在则抛出“父分类不存在”业务异常，错误码：1_008_001_001；
3. 根据传入的`商品分类(api)-请求参数(Body)-父分类编号`使用`商品分类(mapper)`的数据库操作对象查询数据库校验父分类必须是一级分类（值为0L），若父分类为二级及以下层级，抛出“父分类不能是二级分类”业务异常，错误码：1_008_001_002。

## 数据转换
将传入的`商品分类(api)-请求参数(Body)`，转换为数据库对应的`商品分类(entity)`数据对象。

## 数据库操作
使用`商品分类(mapper)`的数据库操作对象将`商品分类(entity)`数据对象插入数据库。

## 返回数据
返回数据库自动生成的商品`商品分类(entity)-分类编号`。

===

# mapper

## 商品分类(mapper)
* 根据商品分类编号从数据库查询`商品分类(entity)`
* 根据`商品分类(entity)`插入数据库。

===

# entity
商品分类(entity)

## 数据库表名
product_category

## 属性
| 属性名     | 类型          | MyBatis-Plus注解 | 说明 |
|-----------|---------------|------------------|------|
| id        | Long          | @TableId         | 分类编号 |
| parentId  | Long          | 无               | 父分类编号（根分类为0） |
| name      | String        | 无               | 分类名称 |
| picUrl    | String        | 无               | 移动端分类图（建议180*180分辨率） |
| sort      | Integer       | 无               | 分类排序 |
| status    | Integer       | 无               | 开启状态（枚举CommonStatusEnum） |
| createTime| LocalDateTime | @TableField(fill = FieldFill.INSERT) | 创建时间 |
| updateTime| LocalDateTime | @TableField(fill = FieldFill.INSERT_UPDATE) | 最后更新时间 |
| creator   | String        | @TableField(fill = FieldFill.INSERT, jdbcType = JdbcType.VARCHAR) | 创建者（SysUser的id编号） |
| updater   | String        | @TableField(fill = FieldFill.INSERT_UPDATE, jdbcType = JdbcType.VARCHAR) | 更新者（SysUser的id编号） |
| deleted   | Boolean       | @TableLogic      | 是否删除（逻辑删除字段） |
