FROM registry.agri-rd.com/new/yanfa_mvn-centos:v1 AS code-Build
COPY ./  /opt
WORKDIR /opt
RUN  mvn clean package -Dmaven.test.skip \
     && cp -f APP_ENV-assemble/target/APP_ENV*.war  APP_ENV-deploy/webapp/ROOT.war  

FROM  registry.agri-rd.com/new/yanfa_java_run-centos:v1
#USER newhope
COPY --from=code-Build /opt/APP_ENV-deploy   /opt/newhope/app
ENV JAVA_OPTION="-Xmx2048m -Xms2048m -XX:NewRatio=2 -XX:+UseParNewGC -XX:ParallelGCThreads=2 -XX:+UseConcMarkSweepGC " 
ENV JAVA_OPTIONS="$JAVA_OPTION -verbose:gc -XX:+PrintGCTimeStamps -XX:+PrintGCDetails -Xloggc:/var/log/webapps/APP_ENV/jvm.log"

WORKDIR /opt/newhope/app

ENTRYPOINT ["/bin/sh", "-c", "java -jar $JAVA_OPTIONS -Dspring.cloud.config.label=$NEWHOPE_PROFILE_GROUP ./webapp/ROOT.war profile=$NEWHOPE_PROFILE"]