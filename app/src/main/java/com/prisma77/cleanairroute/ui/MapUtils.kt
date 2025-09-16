package com.prisma77.cleanairroute.util

import android.graphics.Color
import com.naver.maps.map.NaverMap
import com.naver.maps.map.overlay.Marker
import com.naver.maps.geometry.LatLng
import org.json.JSONArray

fun addMarkersFromTM(naverMap: NaverMap, tmList: JSONArray) {
    for (i in 0 until tmList.length()) {
        val obj = tmList.getJSONObject(i)
        val lat = obj.optDouble("dmX", 0.0)
        val lng = obj.optDouble("dmY", 0.0)
        val name = obj.optString("stationName", "Unknown")

        val marker = Marker().apply {
            position = LatLng(lng, lat)
            captionText = name
            captionColor = Color.BLACK
        }
        marker.map = naverMap
    }
}
