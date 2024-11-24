package com.example.dataparsing

import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserFactory

object Parser {
    fun parseCurrencyData(xmlData: String): List<String> {
        val currencies = mutableListOf<String>()

        try {
            val factory = XmlPullParserFactory.newInstance()
            val parser = factory.newPullParser()
            parser.setInput(xmlData.reader())

            var eventType = parser.eventType
            var currencyCode = ""
            var rate = ""

            while (eventType != XmlPullParser.END_DOCUMENT) {
                val tagName = parser.name

                when (eventType) {
                    XmlPullParser.START_TAG -> {
                        if (tagName == "targetCurrency") {
                            currencyCode = parser.nextText()
                        }
                        if (tagName == "exchangeRate") {
                            rate = parser.nextText()
                        }
                    }
                    XmlPullParser.END_TAG -> {
                        if (tagName == "item") {
                            currencies.add("$currencyCode - $rate")
                        }
                    }
                }
                eventType = parser.next()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return currencies
    }
}
