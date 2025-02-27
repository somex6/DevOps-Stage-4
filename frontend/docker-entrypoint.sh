#!/bin/sh

# Create runtime configuration
echo "window.config = { \
  AUTH_API_ADDRESS: '${AUTH_API_ADDRESS}', \
  TODOS_API_ADDRESS: '${TODOS_API_ADDRESS}' \
}" > /app/dist/config.js

# Start HTTP server
http-server /app/dist -p $PORT