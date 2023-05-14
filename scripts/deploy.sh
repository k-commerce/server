#!/bin/bash

CURRENT_PROFILE=$(curl -s http://localhost/profile)

if [ $CURRENT_PROFILE == prod1 ]
then
  IDLE_PROFILE=prod2
  IDLE_PORT=8082
else
  IDLE_PROFILE=prod1
  IDLE_PORT=8081
fi

docker pull kwondongwook/k-commerce:latest
docker image tag kwondongwook/k-commerce:latest k-commerce:$IDLE_PROFILE
docker rmi kwondongwook/k-commerce:latest

docker run --name $IDLE_PROFILE \
-d \
-e TZ=Asia/Seoul \
-e "SPRING_PROFILES_ACTIVE=$IDLE_PROFILE" \
-p $IDLE_PORT:$IDLE_PORT \
-v /home/ubuntu/app:/config \
k-commerce:$IDLE_PROFILE

sleep 10
for retry_count in {1..5}
do
  response=$(curl -s http://localhost:$IDLE_PORT/actuator/health)
  up_count=$(echo $response | grep 'UP' | wc -l)
  if [ $up_count -ge 1 ]
  then
    echo "> Health check $retry_count 성공"
    break
  elif [ $retry_count -eq 5 ]
  then
    echo "> Health check $retry_count 실패"
    exit 1
  fi
  echo "> Health check $retry_count 재시도"
  sleep 10
done

if [ $CURRENT_PROFILE == prod1 ] || [ $CURRENT_PROFILE == prod2 ]
then
  bash ./app/switch.sh > /dev/null 2>&1
  docker stop $CURRENT_PROFILE
  docker rm $CURRENT_PROFILE
  docker rmi k-commerce:$CURRENT_PROFILE
fi
