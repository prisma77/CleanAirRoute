package com.prisma77.cleanairroute.util

import com.naver.maps.geometry.LatLng
import kotlin.math.*

object CoordinateUtils {
    // TM좌표계를 위경도 좌표로 변환 (간단한 예제값 기반)
    fun tmToLatLng(tmX: Double, tmY: Double): LatLng {
        // 🔧 실제 변환식은 보정 필요함 — 예시용으로 단순 치환
        // 서울 중심을 기준으로 단순 변환한 임시 버전
        val lat = 37.0 + (tmY - 200000.0) / 100000.0
        val lng = 126.0 + (tmX - 200000.0) / 100000.0
        return LatLng(lat, lng)
    }
}
