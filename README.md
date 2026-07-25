# TextToFileService

A minimal Android `TextToSpeechService` that **writes spoken text to a file** instead of producing audio output.

✅ **No audio generation**  
✅ **Captures all TTS requests** (e.g., from any app using `TextToSpeech`)  
✅ **Appends to `Downloads/textToFile.txt`**  
✅ Designed for **debugging, logging, or accessibility tooling**

---

## 🛠 Use Cases

- **Debug TTS flows**: See exactly what text your app (or another app) *would* speak.
- **Accessibility tools**: Log utterances for visual feedback, subtitle generation, or archival.
- **Testing**: Replace a heavy or flaky TTS engine during UI tests.

---

## ⚙️ How It Works

Set it as the default (or preferred) TTS engine in **System Settings → Accessibility → Text-to-Speech output**.
