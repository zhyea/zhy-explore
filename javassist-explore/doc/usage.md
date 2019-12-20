# USAGE

打包命令：

```shell
mvn clean package
```

在执行前调用：

```shell script
java -javaagent:agent_jar_path java_app
```

进入target目录，/target/test-classes， 执行如下命令：

```shell script
java -javaagent:../myAgent.jar org.chobit.javassist.MyTest
```