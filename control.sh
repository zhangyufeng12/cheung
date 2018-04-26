VA_HOME="/home/xxxx/tools/jdk1.8.0_144"
CLASSPATH="${JAVA_HOME}/lib/dt.jar:${JAVA_HOME}/lib/tools.jar"
PATH="${JAVA_HOME}/bin:${PATH}"
export JAVA_HOME CLASSPATH PATH

datetimestamp="$(date +%Y%m%d%H%M%S)"
program_name="Manhattan-zhangyufeng"
command_name="$1"
profile_name="$2"
PORT=8018

## kill the process
ps -ef |grep $program_name | grep -v grep | awk '{print $2}' | sed -e 's/^/kill -9 /g' | sh

nohup java -jar ${program_name}-0.0.1-SNAPSHOT.jar --spring.profiles.active=test &

#5.验证是否启动成功
sleep 10s
netstat -anlp  |   grep  $PORT
if [ $? != 0 ] ; then
	echo "start failed....."
	exit -1
fi

echo "start done!"

