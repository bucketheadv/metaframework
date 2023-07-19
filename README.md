## Description
metaframework is a framework to adapt to spring native.

## Build

```shell
./build.sh
```

## Run
```shell
meta-starter-web/target/meta-starter-web -Denv=dev
```

## 注意

1. @Resource 不能在Spring Native中使用
2. @RefreshScope 不能在Spring Native中使用
3. 使用 Gson 或 Jackson 反序列化时，需要将反序列化的类加到 @RegisterReflectionForBinding(classes = {}) 注解中，否则将反序列化可能会出现异常
4. 编译时若报 `java.lang.IllegalArgumentException: Code generation does not support`异常
   是因为 BeanDefinitionBuilder#addConstructorArgValue 方法传递构造方法的参数中有不支持的类型，此时只能使用 addConstructorArgReference 。