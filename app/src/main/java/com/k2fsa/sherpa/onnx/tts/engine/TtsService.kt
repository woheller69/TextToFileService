package com.k2fsa.sherpa.onnx.tts.engine

import android.Manifest
import android.content.pm.PackageManager
import android.media.AudioFormat
import android.os.Environment
import android.speech.tts.SynthesisCallback
import android.speech.tts.SynthesisRequest
import android.speech.tts.TextToSpeech
import android.speech.tts.TextToSpeechService
import android.util.Log
import androidx.core.content.ContextCompat
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

class TtsService : TextToSpeechService() {

    private val TAG = "TextToFileService"
    private val FILENAME = "textToFile.txt"

    override fun onCreate() {
        Log.i(TAG, "onCreate tts service")
        super.onCreate()
    }

    override fun onDestroy() {
        Log.i(TAG, "onDestroy tts service")
        super.onDestroy()
    }

    override fun onIsLanguageAvailable(_lang: String?, _country: String?, _variant: String?): Int {
        return TextToSpeech.LANG_AVAILABLE
    }

    override fun onGetLanguage(): Array<String> {
        // Not used for file output, but required
        return arrayOf("", "", "")
    }

    override fun onLoadLanguage(_lang: String?, _country: String?, _variant: String?): Int {
        return TextToSpeech.LANG_AVAILABLE
    }

    override fun onStop() {}

    override fun onSynthesizeText(request: SynthesisRequest?, callback: SynthesisCallback?) {
        Log.i(TAG, "onSynthesizeText")
        if (request == null || callback == null) {
            return
        }

        val text = request.charSequenceText.toString()
        //Log.d(TAG, "Text to write: $text")

        // Write text to file
        writeTextToFile(text)

        // Since this is text-to-file (not audio TTS), no audio data is produced,
        // but we must still call callback methods to signal completion.
         callback.start(16000, AudioFormat.ENCODING_PCM_16BIT, 1)
         callback.done()
    }

    private fun writeTextToFile(text: String) {
        val dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
        val file = File(dir, FILENAME)

        try {
            // Append mode: true
            FileOutputStream(file, true).use { fos ->
                fos.write(text.toByteArray())
                fos.write("\n".toByteArray()) // add newline for readability
            }
        } catch (e: IOException) {
            Log.e(TAG, "Error writing to file: ${e.message}", e)
        }
    }

}
