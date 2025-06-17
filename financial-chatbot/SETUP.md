# Financial Chatbot Setup Guide

## Prerequisites

1. Java 17 or higher
2. Maven 3.6 or higher
3. Google Cloud account with Vertex AI API enabled
4. Service account key with Vertex AI User role

## Setup Instructions

1. **Clone the repository**
   ```bash
   git clone <repository-url>
   cd financial-chatbot
   ```

2. **Set up Google Cloud**
   - Go to [Google Cloud Console](https://console.cloud.google.com/)
   - Create a new project or select an existing one
   - Enable the Vertex AI API
   - Create a service account with the "Vertex AI User" role
   - Generate and download a JSON key for the service account

3. **Configure the application**
   - Place the downloaded service account key JSON file in the project root directory
   - Update `src/main/resources/application.properties` with your configuration:
     ```properties
     gemini.api.key-path=path/to/your/service-account-key.json
     gemini.api.project-id=your-project-id
     gemini.api.location=us-central1  # or your preferred region
     gemini.api.model=gemini-1.5-pro  # or your preferred model
     ```

4. **Build the application**
   ```bash
   mvn clean package
   ```

5. **Run the application**
   ```bash
   mvn spring-boot:run
   ```
   The application will start on `http://localhost:8080`

## API Endpoints

### Send a message to the chatbot
```
POST /api/chat
Content-Type: application/json

{
  "message": "How can I start investing?",
  "userId": "user123",
  "sessionId": "sess_abc123"
}
```

### Health Check
```
GET /api/chat/health
```

## Testing

You can test the API using curl:

```bash
# Health check
curl http://localhost:8080/api/chat/health

# Send a message
curl -X POST http://localhost:8080/api/chat \
  -H "Content-Type: application/json" \
  -d '{"message": "How can I save money?"}'
```

## Configuration

| Property | Description | Default |
|----------|-------------|---------|
| server.port | Server port | 8080 |
| gemini.api.key-path | Path to service account key | service-account-key.json |
| gemini.api.project-id | Google Cloud project ID | your-project-id |
| gemini.api.location | Google Cloud region | us-central1 |
| gemini.api.model | Gemini model name | gemini-1.5-pro |

## Troubleshooting

- **Missing Dependencies**: Run `mvn clean install` to download all dependencies
- **Authentication Errors**: Verify your service account key has the correct permissions
- **Model Not Found**: Ensure the specified model exists in your region
- **Port Already in Use**: Change `server.port` in `application.properties` if 8080 is in use
