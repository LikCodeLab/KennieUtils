# KennieUtils

## 如何使用

### 1. 项目根目录的 build.gradle 的 buildscript 添加
```
allprojects {
     repositories {
        ...
        maven { url "https://jitpack.io" }
    }
}
```

### 2.然后在 dependencies 添加

Latest Version：[![](https://jitpack.io/v/kennielab/KennieUtils.svg)](https://jitpack.io/#kennielab/KennieUtils)

```gradle
dependencies {
  implementation 'com.github.kennielab:KennieUtils:{Latest Version}'
}
