#!/bin/bash

# Check if the CONTAINER_NAME argument is provided
if [ $# -ne 1 ]; then
  echo "Usage: $0 <CONTAINER_NAME>"
  exit 1
fi

# Get the container name from the first argument
CONTAINER_NAME=$1

# Run the Docker container with the specified name
docker run -d --name "$CONTAINER_NAME" -p 8080:8080 keycloak/resource-server:1.0

echo "Docker container $CONTAINER_NAME is running."
