package com.example.dataparsing

import android.content.Context
import android.os.AsyncTask
import android.widget.ArrayAdapter
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class DataLoader(
    private val context: Context,
    private val adapter: ArrayAdapter<String>
) : AsyncTask<Void, Void, List<String>>() {

    override fun doInBackground(vararg params: Void?): List<String> {
        val urlString = "https://www.floatrates.com/daily/usd.xml"
        val result = mutableListOf<String>()

        try {
            val url = URL(urlString)
            val connection = url.openConnection() as HttpURLConnection
            connection.connectTimeout = 5000
            connection.readTimeout = 5000

            if (connection.responseCode == HttpURLConnection.HTTP_OK) {
                val reader = BufferedReader(InputStreamReader(connection.inputStream))
                val xmlData = reader.readText()
                reader.close()

                result.addAll(Parser.parseCurrencyData(xmlData))
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return result
    }

    override fun onPostExecute(result: List<String>) {
        super.onPostExecute(result)
        adapter.clear()
        adapter.addAll(result)
        adapter.notifyDataSetChanged()
    }
}
