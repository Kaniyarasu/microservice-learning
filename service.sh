#!/usr/bin/env bash

cd $(dirname $0)

build() {
  mvn clean install
  true
}

run() {
  java -jar target/recruiting-service-*.jar
  sleep 600
}

docker_build() {
  docker build -t heavenhr:recruitingservice .
}

docker_run() {
  docker run --rm -it -p 8090:8090 heavenhr:recruitingservice
}

usage() {
  cat <<EOF
Usage:
  $0 <command>
Local machine commands:
  build        : builds and packages your app
  run          : starts your app in the foreground
Docker commands:
  docker_build     : packages your app into a docker image
  docker_run       : runs your app using a docker image
EOF
}

action=$1
action=${action:-"usage"}
action=${action/help/usage}
shift
if type -t $action >/dev/null; then
  echo "Invoking: $action"
  $action $*
else
  echo "Unknown action: $action"
  usage
  exit 1
fi