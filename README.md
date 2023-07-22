## Description
metaframework is a framework to adapt to spring native.

## Build

```shell
./build.sh
```

## Run
```shell
meta-starter-web/target/meta-starter-web -Denv=dev --spring.profiles.active=dev
```

## 注意

1. @Resource 不能在Spring Native中使用
2. @RefreshScope 不能在Spring Native中使用
3. 使用 Gson 或 Jackson 反序列化时可能存在问题，此时有两种方案
   a. 将反序列化的类加到 @RegisterReflectionForBinding(classes = {}) 注解中 （此种方法不推荐）
   b. 并且在 `reflect-config.json` 中配置类的全路径（见样例`reflect.json`）（推荐使用）
4. 编译时若报 `java.lang.IllegalArgumentException: Code generation does not support`异常
   是因为 BeanDefinitionBuilder#addConstructorArgValue 方法传递构造方法的参数中有不支持的类型，此时只能使用 addConstructorArgReference 。
5. 打包时会将相关配置一并打包，因此如果有 @Value 和 @ConfigurationProperties 对应的配置，必须在打包环境的配置环境中设置好对应的key,
   @Qualifier 对应的 Bean 的配置也必须在打包环境中设置，若同一类型的Bean有多个并存在 @Qualifier 注解时，打包时必须至少存在一个该类型的Bean的配置。
   如 Redis 配置存在多个配置时（如存在两个实例分别名为 default 和 main ），至少要配置一个 Redis 实例，见样例 `redis.yml`。

样例redis.yml
```yaml
meta:
  redis:
    default:
      host: localhost
      port: 6379
```

样例reflect-config.json
```json
[
    {
        "name" : "com.ctrip.framework.foundation.internals.DefaultProviderManager",
        "allDeclaredMethods": true,
        "allDeclaredFields": true,
        "allDeclaredConstructors": true,
        "allPublicMethods": true,
        "allPublicFields": true,
        "allPublicConstructors": true,
        "queryAllDeclaredMethods": true,
        "queryAllDeclaredConstructors": true,
        "queryAllPublicMethods": true,
        "queryAllPublicConstructors": true,
        "unsafeAllocated": true
    }
]
```