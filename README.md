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

1. @Resource不能在spring native中使用
2. @RefreshScope不能在spring native中使用
3. 不能使用Gson，因此apollo-client中的Gson必须替换为jackson或fastjson