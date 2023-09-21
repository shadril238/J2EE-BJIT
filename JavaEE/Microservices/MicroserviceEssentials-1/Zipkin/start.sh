kill -9 $(lsof -t -i:9411)
java -jar zipkin-server-2.12.9-exec.jar