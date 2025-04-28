package za.co.todoapp.common.services.location

import android.app.Application
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import com.google.android.gms.location.LocationServices as AndroidLocationServices
import com.huawei.hms.location.LocationServices as HuaweiLocationServices

class LocationManager(
    private val application: Application
) {
    fun getDeviceLocation(
        isLocationPermissionGranted: Boolean,
        onFailureListener: () -> Unit,
        onPermissionDeniedListener: () -> Unit,
        onSuccessListener: (latitude: Double, longitude: Double) -> Unit,
    ) = CoroutineScope(Dispatchers.IO).launch {
        if (isLocationPermissionGranted) {
            try {
                if (ContextCompat.checkSelfPermission(application, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    if (android.os.Build.MANUFACTURER.equals("HUAWEI", true)) {
                        val fusedLocationProviderClient =
                            HuaweiLocationServices.getFusedLocationProviderClient(application.applicationContext)
                        fusedLocationProviderClient.lastLocation
                            .addOnSuccessListener {
                                val location = it
                                if (location != null) {
                                    onSuccessListener(location.latitude, location.longitude)
                                } else {
                                    onFailureListener()
                                }
                            }
                            .addOnFailureListener {
                                onFailureListener()
                            }
                    } else {
                        val fusedLocationProviderClient =
                            AndroidLocationServices.getFusedLocationProviderClient(application.applicationContext)
                        fusedLocationProviderClient.lastLocation
                            .addOnSuccessListener {
                                val location = it
                                if (location != null) {
                                    onSuccessListener(location.latitude, location.longitude)
                                } else {
                                    onFailureListener()
                                }
                            }
                            .addOnFailureListener {
                                onFailureListener()
                            }
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        } else {
            onPermissionDeniedListener()
        }
    }
}