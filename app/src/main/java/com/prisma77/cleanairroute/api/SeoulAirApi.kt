package com.prisma77.cleanairroute.api

import android.content.Context
import android.content.pm.PackageManager
import android.util.Log
import com.prisma77.cleanairroute.model.StationDTO
import okhttp3.*
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserFactory
import java.io.IOException
import java.io.StringReader

object SeoulAirApi {
    private const val TAG = "SeoulAirApi"
    private const val API_KEY = "414f68467670726935314150535765"
    private const val API_URL =
        "http://openapi.seoul.go.kr:8088/$API_KEY/xml/airPolutionMeasuringPlace/1/1000/"

    fun fetchStationList(callback: (List<StationDTO>) -> Unit) {
        val client = OkHttpClient()
        val request = Request.Builder().url(API_URL).build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.e(TAG, "API 요청 실패", e)
            }

            override fun onResponse(call: Call, response: Response) {
                val body = response.body?.string()
                if (body == null) {
                    Log.e(TAG, "응답 body 없음")
                    return
                }

                Log.d(TAG, "응답: $body")
                val stationList = parseXml(body)
                callback(stationList)
            }
        })
    }

    private fun parseXml(xml: String): List<StationDTO> {
        val result = mutableListOf<StationDTO>()

        try {
            val factory = XmlPullParserFactory.newInstance()
            val parser = factory.newPullParser()
            parser.setInput(StringReader(xml))

            var eventType = parser.eventType
            var tag: String?
            var name = ""
            var address = ""
            var code = ""

            while (eventType != XmlPullParser.END_DOCUMENT) {
                when (eventType) {
                    XmlPullParser.START_TAG -> {
                        tag = parser.name
                        when (tag) {
                            "LOC_NAME" -> name = parser.nextText()
                            "LOC_ADDRRESS" -> address = parser.nextText()
                            "LOC_CODE" -> code = parser.nextText()
                        }
                    }

                    XmlPullParser.END_TAG -> {
                        if (parser.name == "row") {
                            result.add(StationDTO(name, address, code))
                            name = ""
                            address = ""
                            code = ""
                        }
                    }
                }
                eventType = parser.next()
            }
        } catch (e: Exception) {
            Log.e(TAG, "XML 파싱 오류", e)
        }

        return result
    }
}
