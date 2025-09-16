package com.prisma77.cleanairroute.util

import android.content.Context
import android.location.Geocoder
import android.util.Log
import com.naver.maps.geometry.LatLng
import java.util.*

fun addressToLatLng(context: Context, address: String): LatLng? {
    return try {
        val geocoder = Geocoder(context, Locale.KOREA)
        val results = geocoder.getFromLocationName(address, 1)
        if (results != null && results.isNotEmpty()) {
            val loc = results[0]
            LatLng(loc.latitude, loc.longitude)  // LatLng? 반환
        } else null
    } catch (e: Exception) {
        Log.e("Geocode", "주소 변환 실패: $address", e)
        null  // null 반환
    }
}
