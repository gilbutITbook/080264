docker run -d -p 3306:3306 -e MYSQL_ROOT_PASSWORD=1q2w3e4r \
-v $HOME/DockerData/spring-tour-mysql:/var/lib/mysql \
--name spring-tour-mysql mysql:latest \
--character-set-server=utf8mb4 \
--collation-server=utf8mb4_unicode_c

docker exec -it spring-tour-mysql bash
