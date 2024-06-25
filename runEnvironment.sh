docker network create kafka-network
# kafka server
docker build -t kafka-quickstart Dockerfile-kafka-server
docker run -p 9092:9092 --name kafka-quickstart-server --network kafka-network -d kafka-quickstart
#kafka UI
docker pull provectuslabs/kafka-ui
docker run -it -p 8080:8080 --name kafka-ui --network kafka-network -e DYNAMIC_CONFIG_ENABLED=true provectuslabs/kafka-ui
# spring boot app
docker build -t kafka-spring-app Dockerfile-spring-app
docker run -it -p 8181:8181 --name spring-app --network kafka-network -d kafka-spring-app
