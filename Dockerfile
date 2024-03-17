# Use the official Java 8 base image
FROM java:8

# Set the image authors information
LABEL authors="Ryan_fzzzy"

# Create a temporary directory inside the container
VOLUME /tmp

# Copy the local Seckill-Mall-1.0-SNAPSHOT.jar file into the container and rename it to Seckill.jar
ADD Seckill-Mall-1.0-SNAPSHOT.jar Seckill.jar

# Use the bash command to execute the 'touch' command, creating an empty file '/Seckill.jar'
RUN bash -c 'touch /Seckill.jar'

# Define the default entry point command for the container, which runs 'java -jar /Seckill.jar'
ENTRYPOINT ["java", "-jar", "/Seckill.jar"]

# Expose port 8080 from the container
EXPOSE 8080