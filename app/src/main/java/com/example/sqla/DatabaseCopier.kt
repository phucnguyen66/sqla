package com.example.sqla

import android.content.Context
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream

class DatabaseCopier(private val context: Context) {

    fun copyDatabase() {
        val dbPath = context.getDatabasePath("tasks.db")

        // Nếu cơ sở dữ liệu chưa tồn tại, sao chép từ assets
        if (!dbPath.exists()) {
            context.assets.open("tasks.db").use { inputStream ->
                FileOutputStream(dbPath).use { outputStream ->
                    copyStream(inputStream, outputStream)
                }
            }
        }
    }

    private fun copyStream(input: InputStream, output: FileOutputStream) {
        val buffer = ByteArray(1024)
        var length: Int
        while (input.read(buffer).also { length = it } > 0) {
            output.write(buffer, 0, length)
        }
    }
}
