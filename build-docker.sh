#!/bin/bash

# Check if the JAR_FILE argument is provided
if [ $# -ne 1 ]; then
  echo "Usage: $0 <JAR_FILE_PATH>"
  exit 1
fi

# Get the JAR file path from the first argument
JAR_FILE=$1

# Check if the JAR file exists
if [ ! -f "$JAR_FILE" ]; then
  echo "Error: JAR file not found at $JAR_FILE"
  exit 1
fi

# Build the Docker image with the provided JAR file
docker build --build-arg JAR_FILE="$JAR_FILE" -t keycloak/resource-server:1.0 .

echo "Docker image built successfully."

