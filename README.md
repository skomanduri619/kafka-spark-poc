# kafka-spark-poc
This is a sample demo project, developed the understanding of apache kafka and apache spark functionalities

# Technologies
1. Springboot
2. Gradle
3. Apache-Kafka
4. Apache-Spark
5. lombok

Application reads the flights.csv file and streams through each line and push the messages to kafka topic(poc-events).
Kafka Consumer reads the messages from the topic and by using the apche spark java api to process the messages.

# pre-requicites to run the application
Need to run kafka broker and server running locally,
please follow below link to setup the kafka
https://kafka.apache.org/quickstart


