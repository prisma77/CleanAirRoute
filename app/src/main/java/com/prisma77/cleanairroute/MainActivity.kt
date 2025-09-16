package com.prisma77.cleanairroute

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.naver.maps.map.MapView
import com.naver.maps.map.NaverMap
import com.naver.maps.map.OnMapReadyCallback
import com.naver.maps.map.util.FusedLocationSource
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.overlay.Marker
import com.prisma77.cleanairroute.api.SeoulAirApi
import com.prisma77.cleanairroute.model.TmDTO
import com.prisma77.cleanairroute.ui.theme.CleanAirRouteTheme
import com.prisma77.cleanairroute.util.addMarkersFromTM
import android.util.Log
import com.prisma77.cleanairroute.util.addMarkersFromAddress



class MainActivity : ComponentActivity(), OnMapReadyCallback {

    private lateinit var mapView: MapView
    private lateinit var locationSource: FusedLocationSource

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mapView = MapView(this)
        mapView.onCreate(savedInstanceState)
        mapView.getMapAsync(this)

        locationSource = FusedLocationSource(this, LOCATION_PERMISSION_REQUEST_CODE)

        setContent {
            CleanAirRouteTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MapScreen()
                }
            }
        }
    }

    override fun onMapReady(naverMap: NaverMap) {
        naverMap.locationSource = locationSource

        naverMap.setOnMapClickListener { _, _ ->
            SeoulAirApi.fetchStationList { stationList ->
                runOnUiThread {
                    addMarkersFromAddress(this, naverMap, stationList)
                }
            }


        }

        Marker().apply {
            position = LatLng(37.5665, 126.9780)
            map = naverMap
        }
    }

    @Composable
    fun MapScreen() {
        AndroidView(factory = { mapView }, modifier = Modifier.fillMaxSize())
    }

    override fun onStart() {
        super.onStart()
        mapView.onStart()
    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapView.onPause()
    }

    override fun onStop() {
        super.onStop()
        mapView.onStop()
    }

    override fun onDestroy() {
        mapView.onDestroy()
        super.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView.onLowMemory()
    }

    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1000
    }
}
