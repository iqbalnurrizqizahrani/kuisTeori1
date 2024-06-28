package com.example.kuisteori1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import java.io.BufferedReader
import java.io.FileNotFoundException
import java.io.IOException
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.lang.Exception
import java.lang.StringBuilder

class MainActivity<TextView> : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Contoh penggunaan fungsi writeToFile
        val fileName = "example.txt"
        val data = "Ini adalah contoh data yang akan ditulis ke dalam file."
        writeToFile(fileName, data)

        // Membaca data dari file dan menampilkannya di TextView
        val fileContent = readFromFile(fileName)
    }

    private fun writeToFile(fileName: String, data: String) {
        try {
            val outputStreamWriter = OutputStreamWriter(openFileOutput(fileName, MODE_PRIVATE))
            outputStreamWriter.write(data)
            outputStreamWriter.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    private fun readFromFile(fileName: String): String {
        return try {
            val inputStream = openFileInput(fileName)
            val inputStreamReader = InputStreamReader(inputStream)
            val bufferedReader = BufferedReader(inputStreamReader)
            val stringBuilder = StringBuilder()
            var line: String?
            while (bufferedReader.readLine().also { line = it } != null) {
                stringBuilder.append(line).append('\n')
            }
            bufferedReader.close()
            stringBuilder.toString()
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
            "File not found"
        } catch (e: IOException) {
            e.printStackTrace()
            "Error reading file"
        }
    }
}
