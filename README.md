# Quickly-Use-Jetpack-Compose
简单易用的 Jetpack Compose 快速开发框架.

# 架构
Quickly-Use-Jetpack-Compose 使用了大部分架构组件,但并没有完全遵守[官方架构指南](https://developer.android.com/topic/architecture?hl=zh-cn).
## 架构组件
+ 模块化,多module,插件化管理依赖.
+ 依赖注入,使用hilt进行依赖注入.
+ 单Activity架构,使用Navigation进行导航.
+ Compose+ViewModel+Flow(MVI),使用协程进行异步操作的反应式ui.

# 开发/发布环境
保证最新版Android Studio可用,但不保证旧版可用.
<br/>在Android Studio更新时,项目也会更新,我会持续把学到的新知识更新到本仓库.

## 运行
确保切换到app,点击运行按钮.
![Run App](docs/images/RunApp.png)
## 密钥
密钥文件存放在根目录的**keystore**目录中.
<br/>密钥配置在**ApplicationConventionPlugin.kt**文件中
## 打包
bundleRelease打包aab.
<br/>assembleRelease打包apk.

# Module目录简介
+ app: 程序的入口,把各个module关联起来.
+ build-logic: 自定义的插件都放在这里,统一管理依赖.
