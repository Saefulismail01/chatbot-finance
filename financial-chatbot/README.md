# Financial Chatbot (Backend)

**Peringatan:** Proyek ini masih dalam tahap pengembangan aktif. Kode yang ada saat ini mungkin belum berfungsi sepenuhnya dan masih mengandung galat (error).

## Tujuan Proyek

Proyek ini bertujuan untuk membangun layanan backend untuk sebuah chatbot asisten keuangan. Backend ini menggunakan Spring Boot dan berintegrasi dengan Google Gemini API untuk memberikan respons cerdas terhadap pertanyaan-pertanyaan seputar keuangan.

## Alur Aplikasi

Alur kerja aplikasi dirancang agar sederhana dan efisien:

1.  **Permintaan Pengguna (Request):**
    - Pengguna mengirimkan permintaan `POST` ke endpoint `/api/chat`.
    - Isi permintaan (body) berupa JSON yang berisi pesan dari pengguna, contohnya: `{"message": "Apa itu reksa dana?"}`.

2.  **Pemrosesan di Backend:**
    - `ChatController` menerima permintaan dan meneruskannya ke `GeminiChatServiceImpl`.
    - Service akan membungkus pesan pengguna dalam sebuah *prompt* yang lebih detail, menginstruksikan AI untuk berperan sebagai asisten keuangan yang andal.

3.  **Komunikasi dengan Gemini API:**
    - Service mengirimkan *prompt* tersebut ke Google Gemini API menggunakan API Key yang telah dikonfigurasi.

4.  **Respons dari Gemini:**
    - Gemini API memproses permintaan dan menghasilkan jawaban yang relevan.
    - Jawaban tersebut dikirimkan kembali ke backend.

5.  **Mengirim Respons ke Pengguna:**
    - Backend menerima respons dari Gemini, mengekstrak teks jawabannya, dan membungkusnya dalam format JSON `ChatResponse`.
    - `ChatResponse` ini kemudian dikirim kembali ke pengguna sebagai jawaban atas permintaan mereka.

## Teknologi yang Digunakan

-   **Java 17**
-   **Spring Boot 3**
-   **Maven**
-   **Google Gemini API** (via `google-ai-generativelanguage` client)
-   **Lombok**

## Prerequisites

- Java 17 or higher
- Maven 3.6 or higher
- Gemini API key from Google AI Studio

## Setup

1. Clone the repository
2. Get your Gemini API key from [Google AI Studio](https://makersuite.google.com/app/apikey)
3. Update the `application.properties` file with your API key:
   ```
   gemini.api.key=your_api_key_here
   ```

## Building the Application

```bash
mvn clean package
```

## Running the Application

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

## Example Response

```json
{
  "response": "To start investing, first determine your financial goals and risk tolerance. Then consider these steps:\n1. Build an emergency fund\n2. Pay off high-interest debt\n3. Start with low-cost index funds\n4. Consider tax-advantaged accounts\n5. Diversify your investments",
  "sessionId": "sess_abc123",
  "status": "SUCCESS",
  "timestamp": "2025-06-17T15:30:45.123456"
}
```

## Error Handling

The API will return appropriate HTTP status codes and error messages in case of failures.

## License

This project is licensed under the MIT License.
