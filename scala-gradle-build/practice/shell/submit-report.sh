#!/usr/bin/env sh

LIB="lib"

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


spark-submit --class chobit.practice.TypeQueryDriver \
--master yarn-cluster \
--name my-practice \
--num-executors 32 \
--driver-memory 4g \
--executor-memory 4g \
--executor-cores 3 \
--conf spark.executor.extraJavaOptions="-XX:+UseParNewGC -verbose:gc -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -Dfile.encoding=UTF-8" \
--jars ${jars} report-practice.jar # $1 $2 $3

# $1 topicName
# $2 fromTime
# $3 toTime