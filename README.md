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