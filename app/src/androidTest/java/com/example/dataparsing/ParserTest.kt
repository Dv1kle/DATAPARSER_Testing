package com.example.dataparsing

import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ParserTest {

    @Test
    fun testParsingValidXmlData() {
        val xmlData = """
            <exchangeRates>
                <item>
                    <targetCurrency>EUR</targetCurrency>
                    <exchangeRate>0.85</exchangeRate>
                </item>
                <item>
                    <targetCurrency>GBP</targetCurrency>
                    <exchangeRate>0.75</exchangeRate>
                </item>
            </exchangeRates>
        """

        val parsedRates = Parser.parseCurrencyData(xmlData)

        assertTrue(parsedRates.contains("EUR - 0.85"))
        assertTrue(parsedRates.contains("GBP - 0.75"))
    }

    @Test
    fun testParsingEmptyXmlData() {
        val xmlData = "<exchangeRates></exchangeRates>"

        val parsedRates = Parser.parseCurrencyData(xmlData)

        assertTrue(parsedRates.isEmpty())
    }
}
