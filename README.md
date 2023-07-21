## Description
metaframework is a framework to adapt to spring native.

## Build

```shell
./build.sh
```

## Run
```shell
meta-starter-web/target/meta-starter-web --apollo.bootstrap.enabled=true --spring.profiles.active=dev
```

## 注意

1. @Resource 不能在Spring Native中使用
2. @RefreshScope 不能在Spring Native中使用
3. 使用 Gson 或 Jackson 反序列化时可能存在问题，此时有两种方案
   a. 将反序列化的类加到 @RegisterReflectionForBinding(classes = {}) 注解中 （此种方法不推荐）
   b. 并且在 `reflect-config.json` 中配置类的全路径（见样例reflect.json）（推荐使用）
4. 编译时若报 `java.lang.IllegalArgumentException: Code generation does not support`异常
   是因为 BeanDefinitionBuilder#addConstructorArgValue 方法传递构造方法的参数中有不支持的类型，此时只能使用 addConstructorArgReference 。

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