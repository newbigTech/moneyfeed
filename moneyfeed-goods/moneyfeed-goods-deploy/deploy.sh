APP_NAME=$1
SPRING_PROFILES=$2
ACTION=$3
CURRENT_DIR=$PWD
MAVEN_HOME=/usr/local/newhope/maven
MAVEN_REPOSITORY=/home/maven/repos

sh ./pre-startup.sh $MAVEN_REPOSITORY

cd ..
CURRENT_DIR=$PWD
echo $CURRENT_DIR
sh $MAVEN_HOME/bin/mvn clean package -Dmaven.test.skip -Dmaven.repo.local=$MAVEN_REPOSITORY

if [ $? -eq 0 ];then 
	LOG_PATH=/var/log/webapps/$APP_NAME
  if [ ! -d "$LOG_PATH" ];then
    mkdir "$LOG_PATH"
  fi
  
  cp -f $APP_NAME-assemble/target/$APP_NAME-assemble-*.war  $APP_NAME-deploy/webapp/ROOT.war
  #unzip to dir
  #rm -Rf $APP_NAME-deploy/jetty/webapps/ROOT
  #mkdir $APP_NAME-deploy/jetty/webapps/ROOT
  #cd $APP_NAME-deploy/jetty/webapps/ROOT 
  #jar -xf $CURRENT_DIR/$APP_NAME-assemble/target/ROOT.war
  
  # restart
  if [ "$ACTION" = "start" ];then
    cd $CURRENT_DIR/$APP_NAME-deploy    
    sh ./start-app.sh $APP_NAME stop
    sh ./start-app.sh $APP_NAME start $SPRING_PROFILES
  fi
fi
