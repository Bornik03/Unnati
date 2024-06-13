package com.example.unnati2

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.location.LocationManager
import android.os.Bundle
import android.os.Looper
import android.provider.Settings
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.core.app.ActivityCompat
import com.example.unnati2.ui.theme.Unnati2Theme
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import java.util.Locale

class MainActivity : ComponentActivity() {
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private var text1: String? = null
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        text1 = intent.getStringExtra("text1")
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        setContent {
            Unnati2Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) {
                    Greeting(
                        fusedLocationClient = fusedLocationClient,
                        text1 = text1 ?: ""
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(fusedLocationClient: FusedLocationProviderClient, text1: String) {
    var text2 by remember { mutableStateOf("") }
    var tt by remember { mutableStateOf(false) }
    var isLocationDialogOpen by remember { mutableStateOf(false) }
    val context = LocalContext.current
    val textColor = MaterialTheme.colorScheme.onBackground
    var c by remember { mutableStateOf(false) }

    // This will recheck the location status every time the composable recomposes
    val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
    var isLocationEnabled by remember { mutableStateOf(locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) }

    LaunchedEffect(key1 = isLocationDialogOpen) {
        isLocationEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        if(c)
        {
            AlertDialog(
                onDismissRequest = { c = false },
                text = { Text("Location cannot be empty") },
                confirmButton = {
                    TextButton(
                        onClick = {
                            c=false
                        }
                    ) {
                        Text("Ok")
                    }
                },
            )
        }
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Spacer(modifier = Modifier.height(300.dp))
                Row {
                    OutlinedTextField(
                        value = text2,
                        label = { Text("Enter Location") },
                        onValueChange = { newText -> text2 = newText },
                        modifier = Modifier.weight(1f)
                    )
                    Button(
                        onClick = {
                            if (text2.isNotEmpty()) {
                                val intent = Intent(context, ResultActivity::class.java).apply {
                                    putExtra("text1", text1)
                                    putExtra("text2", text2)
                                }
                                context.startActivity(intent)
                            }
                            else if(text2.isEmpty())
                            {
                                c=true
                            }
                        },
                        modifier = Modifier
                            .weight(0.5f)
                            .padding(10.dp)
                    ) {
                        Text(text = "Get Details")
                    }
                }
                Spacer(modifier = Modifier.height(10.dp))
                Text(text = "OR")
                Spacer(modifier = Modifier.height(10.dp))
                Button(onClick = {
                    tt=false
                    isLocationEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
                    if (!isLocationEnabled) {
                        isLocationDialogOpen = true
                    } else {
                        tt=true
                        getLocation(fusedLocationClient, context, text1){tt=it}
                    }
                }) {
                    Text(text = "Auto Detect Location")
                }
            }
        }
        if(tt)
        {
            Dialog(onDismissRequest = { tt=false }) {
                Text(text = "Fetching your location...", color = textColor, style = TextStyle(fontSize = 20.sp), modifier = Modifier.padding(top=150.dp))
            }
        }
        
        DisposableEffect(Unit) {
            onDispose {
                tt = false
            }
        }
    }

    if (isLocationDialogOpen) {
        AlertDialog(
            onDismissRequest = { isLocationDialogOpen = false },
            title = { Text("Location Required") },
            text = { Text("Please turn on your device location to use this feature.") },
            confirmButton = {
                TextButton(
                    onClick = {
                        isLocationDialogOpen = false
                        tt=false
                        context.startActivity(Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS))
                    }
                ) {
                    Text("Turn On")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = { isLocationDialogOpen = false }
                ) {
                    Text("Dismiss")
                }
            }
        )
    }
}

@SuppressLint("MissingPermission")
fun getLocation(fusedLocationClient: FusedLocationProviderClient, context: Context, text1: String, updateTT: (Boolean) -> Unit) {
    if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
        ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
        ActivityCompat.requestPermissions(context as Activity, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 1)
        updateTT(false)
        return
    }
    val locationRequest = LocationRequest.Builder(LocationRequest.PRIORITY_HIGH_ACCURACY, 10000)
        .setWaitForAccurateLocation(false)
        .setMinUpdateIntervalMillis(5000)
        .setMaxUpdateDelayMillis(10000)
        .build()
    val locationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult) {
            val location = locationResult.lastLocation
            val address = getAddressFromLocation(context, location?.latitude ?: 0.0, location?.longitude ?: 0.0)
            updateTT(false)
            val intent = Intent(context, ResultActivity::class.java).apply {
                putExtra("text1", text1)
                putExtra("text2", address)
            }
            context.startActivity(intent)
            fusedLocationClient.removeLocationUpdates(this)
        }
    }

    fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, Looper.getMainLooper())
}

fun getAddressFromLocation(context: Context, latitude: Double, longitude: Double): String {
    val geocoder = Geocoder(context, Locale.getDefault())
    val addresses: MutableList<Address>? = geocoder.getFromLocation(latitude, longitude, 1)
    if (addresses != null) {
        return if (addresses.isNotEmpty()) {
            addresses[0].getAddressLine(0)
        } else {
            "Address not found"
        }
    }
    return TODO("Provide the return value")
}