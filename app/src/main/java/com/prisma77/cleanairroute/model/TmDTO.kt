package com.prisma77.cleanairroute.model

data class TmDTO(
    val stationName: String, // 측정소 이름
    val addr: String,        // 주소
    val tmX: String,         // TM 좌표 X
    val tmY: String          // TM 좌표 Y
)
