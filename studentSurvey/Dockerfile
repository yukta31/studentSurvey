# Use an official OpenJDK runtime as a parent image. 
# Since you're using Java 21, use the appropriate tag. If not available, check for "eclipse-temurin:21-jdk-alpine" or similar.
FROM eclipse-temurin:21-jdk-alpine

# Set the working directory in the container
WORKDIR /app

# Copy the packaged JAR file into the container. 
# Replace the JAR_FILE value with your JAR file name.
ARG JAR_FILE=target/studentSurvey-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar

# Expose port 8080 (or the port your app uses)
EXPOSE 8080

# Run the JAR file 
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
