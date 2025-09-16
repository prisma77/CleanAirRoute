package com.prisma77.cleanairroute.util

import android.content.Context
import com.naver.maps.map.NaverMap
import com.naver.maps.map.overlay.Marker
import com.prisma77.cleanairroute.model.StationDTO
import com.prisma77.cleanairroute.util.addressToLatLng

fun addMarkersFromAddress(context: Context, map: NaverMap, stations: List<StationDTO>) {
    stations.forEach { station ->
        val latLng = addressToLatLng(context, station.address)
        if (latLng != null) {
            Marker().apply {
                position = latLng
                captionText = station.name
                this.map = map
            }
        }
    }
}
