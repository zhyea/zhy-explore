#!/usr/bin/env sh

LIB="libs"

jars=""
for f in ${LIB}/*
  do
   if [[ ${jars} != "" ]];then
      jars=${jars},${f}
   else
      jars=${f}
   fi
done

echo ${jars}

echo "$1"

spark-submit --class chobit.MyDriver \
--master yarn-cluster \
--queue portrait \
--name my-driver \
--num-executors 1 \
--driver-memory 6g \
--executor-memory 6g \
--executor-cores 1 \
--conf spark.executor.extraJavaOptions="-verbose:gc -Xss80M -XX:+UseG1GC -XX:-UseGCOverheadLimit -XX:+HeapDumpOnOutOfMemoryError  -XX:HeapDumpPath=/tmp -XX:NewRatio=1 -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -XX:+PrintTenuringDistribution -Dfile.encoding=UTF-8" \
--jars ${jars} myApp.jar "chobit" "zhyea" "robin"
