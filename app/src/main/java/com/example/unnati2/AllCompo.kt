package com.example.unnati2

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.unnati2.ui.theme.Unnati2Theme
import com.google.firebase.auth.FirebaseAuth

class AllCompo : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Unnati2Theme {
                Compo()
            }
        }
    }
    override fun onBackPressed() {
        super.onBackPressed()
        finishAffinity()
    }
}
@Preview
@Composable
fun Compo(){
    var text1 by remember { mutableStateOf("") }
    val context = LocalContext.current
    val backgroundColor = MaterialTheme.colorScheme.background
    val textColor = MaterialTheme.colorScheme.onBackground
    var c by remember { mutableStateOf(false) }
    Box(modifier= Modifier
        .fillMaxSize()
        .background(backgroundColor)) {
        LazyColumn {
            item {
                if(c)
                {
                    AlertDialog(
                        onDismissRequest = { c = false },
                        title = { Text("Sign Out") },
                        text = { Text("Are you sure that you want to sign out?") },
                        confirmButton = {
                            TextButton(
                                onClick = {
                                    c = false
                                    FirebaseAuth.getInstance().signOut()
                                    val intent = Intent(context, StartupActivity::class.java)
                                    context.startActivity(intent)

                                }
                            ) {
                                Text("Log Out")
                            }
                        },
                        dismissButton = {
                            TextButton(
                                onClick = { c = false }
                            ) {
                                Text("Close")
                            }
                        }
                    )
                }
                Spacer(modifier = Modifier.padding(10.dp))
                Row(modifier = Modifier.fillMaxWidth()) {
                    Button(
                        onClick = { c = true },
                        shape = CircleShape,
                        modifier = Modifier.size(100.dp).padding(bottom = 40.dp, top = 8.dp, start = 20.dp, end = 10.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.p),
                            contentDescription = null,
                            modifier = Modifier.size(50.dp)
                        )
                    }
                    Text(
                        text = "Unnati",
                        modifier = Modifier
                            .padding(top = 10.dp, start = 40.dp)
                            .weight(1f),
                        style = TextStyle(fontSize = 40.sp), color = textColor
                    )
                }
                Spacer(modifier = Modifier.padding(top = 20.dp))
                Card(modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp), colors = CardDefaults.cardColors(containerColor = Color(0xFFA2DB5D))) {
                    Text(text = "Health", style = TextStyle(fontSize = 30.sp), modifier = Modifier.padding(5.dp))
                }
                Spacer(modifier = Modifier.padding(19.dp))
                Row {
                    Spacer(modifier = Modifier.weight(.3f))
                    Column(modifier = Modifier.weight(1f)) {
                        Button(
                            onClick = {
                                text1 = "Doctors"
                                val intent = Intent(context, MainActivity::class.java).apply {
                                    putExtra("text1", text1)
                                }
                                context.startActivity(intent)
                            }, shape = CircleShape, modifier = Modifier
                                .size(70.dp)
                        ) {
                            Icon(painter = painterResource(id = R.drawable.h1), contentDescription = null, modifier = Modifier.size(50.dp))
                        }
                        Text(text = "Doctors", style = TextStyle(fontSize = 15.sp), modifier = Modifier.padding(start=8.dp,top=10.dp), color = textColor)
                        Text(text = "Near you", style = TextStyle(fontSize = 15.sp), modifier = Modifier.padding(start = 5.dp), color = textColor)
                    }
                    Column(modifier = Modifier.weight(1f)) {
                        Button(
                            onClick = {
                                val intent = Intent(context, ResultActivity::class.java).apply {
                                    putExtra("web", "https://www.medifee.com/treatment/")
                                }
                                context.startActivity(intent)
                            }, shape = CircleShape, modifier = Modifier
                                .size(70.dp)
                        ) {
                            Icon(painter = painterResource(id = R.drawable.h2), contentDescription = null, modifier = Modifier.size(50.dp))
                        }
                        Text(text = "Cost", modifier = Modifier.padding(top = 10.dp, start = 19.dp), style = TextStyle(fontSize = 15.sp), color = textColor)
                        Text(text = "Details", modifier = Modifier.padding(start=12.dp), style = TextStyle(fontSize = 15.sp), color = textColor)
                    }
                    Column {
                        Button(
                            onClick = {
                                text1 = "Nurse or emergency services"
                                val intent = Intent(context, MainActivity::class.java).apply {
                                    putExtra("text1", text1)
                                }
                                context.startActivity(intent)
                            }, shape = CircleShape, modifier = Modifier
                                .size(70.dp)
                        ) {
                            Icon(painter = painterResource(id = R.drawable.h3), contentDescription = null, modifier = Modifier.size(50.dp))
                        }
                        Text(text = "Nurse/", modifier = Modifier.padding(top = 10.dp, start = 13.dp), style = TextStyle(fontSize = 15.sp), color = textColor)
                        Text(text = "Emergency", style = TextStyle(fontSize = 14.sp), color = textColor)
                        Text(text = "Services", style = TextStyle(fontSize = 15.sp), modifier = Modifier.padding(start = 5.dp), color = textColor)
                        Text(text = "Near You", style = TextStyle(fontSize = 15.sp), modifier = Modifier.padding(start = 5.dp), color = textColor)
                    }
                    Spacer(modifier = Modifier.weight(.3f))
                }
                Spacer(modifier = Modifier.padding(6.dp))
                Card(modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp), colors = CardDefaults.cardColors(containerColor = Color(0xFFA2DB5D))) {
                    Text(text = "Education", style = TextStyle(fontSize = 30.sp), modifier = Modifier.padding(5.dp))
                }
                Spacer(modifier = Modifier.padding(19.dp))
                Row {
                    Spacer(modifier = Modifier.weight(.3f))
                    Column(modifier = Modifier.weight(1f)) {
                        Button(
                            onClick = {
                                text1 = "Schools"
                                val intent = Intent(context, MainActivity::class.java).apply {
                                    putExtra("text1", text1)
                                }
                                context.startActivity(intent)
                            }, shape = CircleShape, modifier = Modifier
                                .size(70.dp)
                        ) {
                            Icon(painter = painterResource(id = R.drawable.e1), contentDescription = null, modifier = Modifier.size(50.dp))
                        }
                        Text(text = "Schools", style = TextStyle(fontSize = 15.sp), modifier = Modifier.padding(start=7.dp,top=10.dp), color = textColor)
                        Text(text = "Near You", style = TextStyle(fontSize = 15.sp), modifier = Modifier.padding(start=4.dp), color = textColor)
                    }
                    Column(modifier = Modifier.weight(1f)) {
                        Button(
                            onClick = {
                                text1 = "Art,music,sports and cultural activity centers"
                                val intent = Intent(context, MainActivity::class.java).apply {
                                    putExtra("text1", text1)
                                }
                                context.startActivity(intent)
                            }, shape = CircleShape, modifier = Modifier
                                .size(70.dp)
                        ) {
                            Icon(painter = painterResource(id = R.drawable.e2), contentDescription = null, modifier = Modifier.size(50.dp))
                        }
                        Text(text = "Art/Music/", color = textColor, style = TextStyle(fontSize = 15.sp), modifier = Modifier.padding(top=10.dp))
                        Text(text = "Sports and", color = textColor, style = TextStyle(fontSize = 15.sp))
                        Text(text = "Cultural", color = textColor, style = TextStyle(fontSize = 15.sp), modifier = Modifier.padding(start = 8.dp))
                        Text(text = "Centers", color = textColor, style = TextStyle(fontSize = 15.sp), modifier = Modifier.padding(start = 8.dp))
                    }
                    Column {
                        Button(
                            onClick = {
                                text1 = "Tuition Centers"
                                val intent = Intent(context, MainActivity::class.java).apply {
                                    putExtra("text1", text1)
                                }
                                context.startActivity(intent)
                            }, shape = CircleShape, modifier = Modifier
                                .size(70.dp)
                        ) {
                            Icon(painter = painterResource(id = R.drawable.e3), contentDescription = null, modifier = Modifier.size(50.dp))
                        }
                        Text(text = "Tuition's", color = textColor, style = TextStyle(fontSize = 15.sp), modifier = Modifier.padding(start = 6.dp, top=10.dp))
                        Text(text = "Near You", color = textColor, style = TextStyle(fontSize = 15.sp), modifier = Modifier.padding(start=3.dp))
                    }
                    Spacer(modifier = Modifier.weight(.3f))
                }
                Row {
                    Spacer(modifier = Modifier.weight(.7f))
                    Column(modifier = Modifier.weight(1f)) {
                        Button(
                            onClick = {
                                text1 = "Industry Training and job aid center"
                                val intent = Intent(context, MainActivity::class.java).apply {
                                    putExtra("text1", text1)
                                }
                                context.startActivity(intent)
                            }, shape = CircleShape, modifier = Modifier
                                .size(70.dp)
                        ) {
                            Icon(painter = painterResource(id = R.drawable.e4), contentDescription = null, modifier = Modifier.size(50.dp))
                        }
                        Text(text = "Industrial", color = textColor, style = TextStyle(fontSize = 15.sp), modifier = Modifier.padding(start = 4.dp, top=10.dp))
                        Text(text = "Training &", color = textColor, style = TextStyle(fontSize = 15.sp))
                        Text(text = "Job Aid", color = textColor, style = TextStyle(fontSize = 15.sp), modifier = Modifier.padding(start = 8.dp))
                        Text(text = "Centers", color = textColor, style = TextStyle(fontSize = 15.sp), modifier = Modifier.padding(start=8.dp))
                    }
                    Column {
                        Button(
                            onClick = {
                                val intent = Intent(context, ResultActivity::class.java).apply {
                                    putExtra("web", "https://www.mypustak.com/")
                                }
                                context.startActivity(intent)
                            }, shape = CircleShape, modifier = Modifier
                                .size(70.dp)
                        ) {
                            Icon(painter = painterResource(id = R.drawable.e5), contentDescription = null, modifier = Modifier.size(50.dp))
                        }
                        Text(text = "2nd Hand/", color = textColor, style = TextStyle(fontSize = 15.sp), modifier = Modifier.padding(start = 1.dp, top=10.dp))
                        Text(text = "Reusable", color = textColor, style = TextStyle(fontSize = 15.sp), modifier = Modifier.padding(start=4.dp))
                        Text(text = "Materials", color = textColor, style = TextStyle(fontSize = 15.sp), modifier = Modifier.padding(start=4.dp))
                    }
                    Spacer(modifier = Modifier.weight(.7f))
                }
                Spacer(modifier = Modifier.padding(6.dp))
                Card(modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp), colors = CardDefaults.cardColors(containerColor = Color(0xFFA2DB5D))) {
                    Text(text = "Transportation", style = TextStyle(fontSize = 30.sp), modifier = Modifier.padding(5.dp))
                }
                Spacer(modifier = Modifier.padding(19.dp))
                Row {
                    Spacer(modifier = Modifier.weight(.3f))
                    Column(modifier = Modifier.weight(1f)) {
                        Button(
                            onClick = {
                                text1 = "Vehicle service center"
                                val intent = Intent(context, MainActivity::class.java).apply {
                                    putExtra("text1", text1)
                                }
                                context.startActivity(intent)
                            }, shape = CircleShape, modifier = Modifier
                                .size(70.dp)
                        ) {
                            Icon(painter = painterResource(id = R.drawable.t1), contentDescription = null, modifier = Modifier.size(50.dp))
                        }
                        Text(text = "Nearest", color = textColor, style = TextStyle(fontSize = 15.sp), modifier = Modifier.padding(start = 8.dp, top=10.dp))
                        Text(text = "Vehicle", color = textColor, style = TextStyle(fontSize = 15.sp), modifier = Modifier.padding(start=9.dp))
                        Text(text = "Service", color = textColor, style = TextStyle(fontSize = 15.sp), modifier = Modifier.padding(start=9.dp))
                        Text(text = "Centers", color = textColor, style = TextStyle(fontSize = 15.sp), modifier = Modifier.padding(start=7.dp))
                    }
                    Column(modifier = Modifier.weight(1f)) {
                        Button(
                            onClick = {
                                text1 = "Bus station"
                                val intent = Intent(context, MainActivity::class.java).apply {
                                    putExtra("text1", text1)
                                }
                                context.startActivity(intent)
                            }, shape = CircleShape, modifier = Modifier
                                .size(70.dp)
                        ) {
                            Icon(painter = painterResource(id = R.drawable.t2), contentDescription = null, modifier = Modifier.size(50.dp))
                        }
                        Text(text = "Nearest", color = textColor, style = TextStyle(fontSize = 15.sp), modifier = Modifier.padding(start = 8.dp, top=10.dp))
                        Text(text = "Bus", color = textColor, style = TextStyle(fontSize = 15.sp), modifier = Modifier.padding(start=20.dp))
                        Text(text = "Station", color = textColor, style = TextStyle(fontSize = 15.sp), modifier = Modifier.padding(start=10.dp))
                    }
                    Column {
                        Button(
                            onClick = {
                                val intent = Intent(context, ResultActivity::class.java).apply {
                                    putExtra("web", "https://droom.in/")
                                }
                                context.startActivity(intent)
                            }, shape = CircleShape, modifier = Modifier
                                .size(70.dp)
                        ) {
                            Icon(painter = painterResource(id = R.drawable.t3), contentDescription = null, modifier = Modifier.size(50.dp))
                        }
                        Text(text = "Buy/Sell", color = textColor, style = TextStyle(fontSize = 15.sp), modifier = Modifier.padding(start = 8.dp, top=10.dp))
                        Text(text = "Vehicles", color = textColor, style = TextStyle(fontSize = 15.sp), modifier = Modifier.padding(start=6.dp))
                    }
                    Spacer(modifier = Modifier.weight(.3f))
                }
                Row {
                    Spacer(modifier = Modifier.weight(.7f))
                    Column(modifier = Modifier.weight(1f)) {
                        Button(
                            onClick = {
                                val intent = Intent(context, ResultActivity::class.java).apply {
                                    putExtra("web", "https://www.redbus.in/bus-timetable/")
                                }
                                context.startActivity(intent)
                            }, shape = CircleShape, modifier = Modifier
                                .size(70.dp)
                        ) {
                            Icon(painter = painterResource(id = R.drawable.t4), contentDescription = null, modifier = Modifier.size(50.dp))
                        }
                        Text(text = "Bus Arrival", color = textColor, style = TextStyle(fontSize = 15.sp), modifier = Modifier.padding( top=10.dp))
                        Text(text = "Details", color = textColor, style = TextStyle(fontSize = 15.sp), modifier = Modifier.padding(start=12.dp))
                    }
                    Column {
                        Button(
                            onClick = {
                                val intent = Intent(context, ResultActivity::class.java).apply {
                                    putExtra("web", "https://ride.guru/")
                                }
                                context.startActivity(intent)
                            }, shape = CircleShape, modifier = Modifier
                                .size(70.dp)
                        ) {
                            Icon(painter = painterResource(id = R.drawable.t5), contentDescription = null, modifier = Modifier.size(50.dp))
                        }
                        Text(text = "Cost", color = textColor, style = TextStyle(fontSize = 15.sp), modifier = Modifier.padding(start=18.dp,top=10.dp))
                        Text(text = "Finder", color = textColor, style = TextStyle(fontSize = 15.sp), modifier = Modifier.padding(start=14.dp))
                    }
                    Spacer(modifier = Modifier.weight(.7f))
                }
                Spacer(modifier = Modifier.padding(6.dp))
                Card(modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp), colors = CardDefaults.cardColors(containerColor = Color(0xFFA2DB5D))) {
                    Text(text = "Finance", style = TextStyle(fontSize = 30.sp), modifier = Modifier.padding(5.dp))
                }
                Spacer(modifier = Modifier.padding(19.dp))
                Row {
                    Spacer(modifier = Modifier.weight(.3f))
                    Column(modifier = Modifier.weight(1f)) {
                        Button(
                            onClick = {
                                val intent = Intent(context, ResultActivity::class.java).apply {
                                    putExtra("web", "https://www.paisabazaar.com/home-loan/interest-rates/")
                                }
                                context.startActivity(intent)
                            }, shape = CircleShape, modifier = Modifier
                                .size(70.dp)
                        ) {
                            Icon(painter = painterResource(id = R.drawable.f1), contentDescription = null, modifier = Modifier.size(50.dp))
                        }
                        Text(text = "Interest", color = textColor, style = TextStyle(fontSize = 15.sp), modifier = Modifier.padding(start = 10.dp, top=10.dp))
                        Text(text = "Rate", color = textColor, style = TextStyle(fontSize = 15.sp), modifier = Modifier.padding(start=20.dp))
                        Text(text = "Finder", color = textColor, style = TextStyle(fontSize = 15.sp), modifier = Modifier.padding(start=14.dp))
                    }
                    Column(modifier = Modifier.weight(1f)) {
                        Button(
                            onClick = {
                                val intent = Intent(context, ResultActivity::class.java).apply {
                                    putExtra("web", "https://www.insurancedekho.com/")
                                }
                                context.startActivity(intent)
                            }, shape = CircleShape, modifier = Modifier
                                .size(70.dp)
                        ) {
                            Icon(painter = painterResource(id = R.drawable.f2), contentDescription = null, modifier = Modifier.size(50.dp))
                        }
                        Text(text = "Insurance", color = textColor, modifier = Modifier.padding(top = 10.dp), style = TextStyle(fontSize = 15.sp))
                        Text(text = "Details", color = textColor, modifier = Modifier.padding(start=12.dp), style = TextStyle(fontSize = 15.sp))
                    }
                    Column {
                        Button(
                            onClick = {
                                val intent = Intent(context, ResultActivity::class.java).apply {
                                    putExtra("web", "https://scripbox.com/tax/tax-saving-options/")
                                }
                                context.startActivity(intent)
                            }, shape = CircleShape, modifier = Modifier
                                .size(70.dp)
                        ) {
                            Icon(painter = painterResource(id = R.drawable.f3), contentDescription = null, modifier = Modifier.size(50.dp))
                        }
                        Text(text = "Tax Saving", color = textColor, style = TextStyle(fontSize = 15.sp), modifier = Modifier.padding(top=10.dp))
                        Text(text = "Plans", color = textColor, style = TextStyle(fontSize = 15.sp), modifier = Modifier.padding(start=16.dp))
                    }
                    Spacer(modifier = Modifier.weight(.3f))
                }
                Spacer(modifier = Modifier.padding(6.dp))
                Card(modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp), colors = CardDefaults.cardColors(containerColor = Color(0xFFA2DB5D))) {
                    Text(text = "Government Services", style = TextStyle(fontSize = 30.sp), modifier = Modifier.padding(5.dp))
                }
                Spacer(modifier = Modifier.padding(19.dp))
                Row {
                    Spacer(modifier = Modifier.weight(.3f))
                    Column(modifier = Modifier.weight(1f)) {
                        Button(
                            onClick = {
                                text1 = "Government office for aadhar registration"
                                val intent = Intent(context, MainActivity::class.java).apply {
                                    putExtra("text1", text1)
                                }
                                context.startActivity(intent)
                            }, shape = CircleShape, modifier = Modifier
                                .size(70.dp)
                        ) {
                            Icon(painter = painterResource(id = R.drawable.g1), contentDescription = null, modifier = Modifier.size(50.dp))
                        }
                        Text(text = "Govt. Office", color = textColor, style = TextStyle(fontSize = 15.sp), modifier = Modifier.padding(top=10.dp))
                        Text(text = "For Aadhar", color = textColor, style = TextStyle(fontSize = 15.sp), modifier = Modifier.padding(start=2.dp))
                        Text(text = "Registration", color = textColor, style = TextStyle(fontSize = 15.sp))
                    }
                    Column(modifier = Modifier.weight(1f)) {
                        Button(
                            onClick = {
                                text1 = "Passport center and consultants"
                                val intent = Intent(context, MainActivity::class.java).apply {
                                    putExtra("text1", text1)
                                }
                                context.startActivity(intent)
                            }, shape = CircleShape, modifier = Modifier
                                .size(70.dp)
                        ) {
                            Icon(painter = painterResource(id = R.drawable.g2), contentDescription = null, modifier = Modifier.size(50.dp))
                        }
                        Text(text = "Nearest", color = textColor, style = TextStyle(fontSize = 15.sp), modifier = Modifier.padding(start = 8.dp, top=10.dp))
                        Text(text = "Passport", color = textColor, style = TextStyle(fontSize = 15.sp), modifier = Modifier.padding(start=6.dp))
                        Text(text = "Center &", color = textColor, style = TextStyle(fontSize = 15.sp), modifier = Modifier.padding(start=7.dp))
                        Text(text = "Consultants", color = textColor, style = TextStyle(fontSize = 15.sp))
                    }
                    Column {
                        Button(
                            onClick = {
                                text1 = "Housing and water suppliers"
                                val intent = Intent(context, MainActivity::class.java).apply {
                                    putExtra("text1", text1)
                                }
                                context.startActivity(intent)
                            }, shape = CircleShape, modifier = Modifier
                                .size(70.dp)
                        ) {
                            Icon(painter = painterResource(id = R.drawable.g3), contentDescription = null, modifier = Modifier.size(50.dp))
                        }
                        Text(text = "Nearest", color = textColor, style = TextStyle(fontSize = 15.sp), modifier = Modifier.padding(start = 8.dp, top=10.dp))
                        Text(text = "Housing &", color = textColor, style = TextStyle(fontSize = 15.sp), modifier = Modifier.padding(start=2.dp))
                        Text(text = "Water", color = textColor, style = TextStyle(fontSize = 15.sp), modifier = Modifier.padding(start=15.dp))
                        Text(text = "Suppliers", color = textColor, style = TextStyle(fontSize = 15.sp), modifier = Modifier.padding(start=2.dp))
                    }
                    Spacer(modifier = Modifier.weight(.3f))
                }
                Spacer(modifier = Modifier.padding(6.dp))
                Row {
                    Spacer(modifier = Modifier.weight(.3f))
                    Column(modifier = Modifier.weight(1f)) {
                        Button(
                            onClick = {
                                text1 = "Ration and voter id centers"
                                val intent = Intent(context, MainActivity::class.java).apply {
                                    putExtra("text1", text1)
                                }
                                context.startActivity(intent)
                            }, shape = CircleShape, modifier = Modifier
                                .size(70.dp)
                        ) {
                            Icon(painter = painterResource(id = R.drawable.g4), contentDescription = null, modifier = Modifier.size(50.dp))
                        }
                        Text(text = "Nearest", color = textColor, style = TextStyle(fontSize = 15.sp), modifier = Modifier.padding(start = 8.dp, top=10.dp))
                        Text(text = "Ration &", color = textColor, style = TextStyle(fontSize = 15.sp), modifier = Modifier.padding(start=6.dp))
                        Text(text = "Voter ID", color = textColor, style = TextStyle(fontSize = 15.sp), modifier = Modifier.padding(start=8.dp))
                        Text(text = "Centers", color = textColor, style = TextStyle(fontSize = 15.sp), modifier = Modifier.padding(start=7.dp))
                    }
                    Column(modifier = Modifier.weight(1f)) {
                        Button(
                            onClick = {
                                text1 = "Pension, pf, gratuity consultants"
                                val intent = Intent(context, MainActivity::class.java).apply {
                                    putExtra("text1", text1)
                                }
                                context.startActivity(intent)
                            }, shape = CircleShape, modifier = Modifier
                                .size(70.dp)
                        ) {
                            Icon(painter = painterResource(id = R.drawable.g5), contentDescription = null, modifier = Modifier.size(50.dp))
                        }
                        Text(text = "Pension", color = textColor, style = TextStyle(fontSize = 15.sp), modifier = Modifier.padding(start = 8.dp, top=10.dp))
                        Text(text = "PF &", color = textColor, style = TextStyle(fontSize = 15.sp), modifier = Modifier.padding(start=17.dp))
                        Text(text = "Gratuity", color = textColor, style = TextStyle(fontSize = 15.sp), modifier = Modifier.padding(start=9.dp))
                        Text(text = "Details", color = textColor, style = TextStyle(fontSize = 15.sp), modifier = Modifier.padding(start=11.dp))
                    }
                    Column {
                        Button(
                            onClick = {
                                val intent = Intent(context, ResultActivity::class.java).apply {
                                    putExtra("web", "https://www.myscheme.gov.in/find-scheme")
                                }
                                context.startActivity(intent)
                            }, shape = CircleShape, modifier = Modifier
                                .size(70.dp)
                        ) {
                            Icon(painter = painterResource(id = R.drawable.g6), contentDescription = null, modifier = Modifier.size(50.dp))
                        }
                        Text(text = "Govt.", color = textColor, style = TextStyle(fontSize = 15.sp), modifier = Modifier.padding(start=17.dp,top=10.dp))
                        Text(text = "Schemes", color = textColor, style = TextStyle(fontSize = 15.sp), modifier = Modifier.padding(start=2.dp))
                    }
                    Spacer(modifier = Modifier.weight(.3f))
                }
                Spacer(modifier = Modifier.padding(6.dp))
                Card(modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp), colors = CardDefaults.cardColors(containerColor = Color(0xFFA2DB5D))) {
                    Text(text = "Housing Services", style = TextStyle(fontSize = 30.sp), modifier = Modifier.padding(5.dp))
                }
                Spacer(modifier = Modifier.padding(19.dp))
                Row {
                    Spacer(modifier = Modifier.weight(.3f))
                    Column(modifier = Modifier.weight(1f)) {
                        Button(
                            onClick = {
                                text1 = "Community helpers"
                                val intent = Intent(context, MainActivity::class.java).apply {
                                    putExtra("text1", text1)
                                }
                                context.startActivity(intent)
                            }, shape = CircleShape, modifier = Modifier
                                .size(70.dp)
                        ) {
                            Icon(painter = painterResource(id = R.drawable.s1), contentDescription = null, modifier = Modifier.size(50.dp))
                        }
                        Text(text = "Nearest", color = textColor, style = TextStyle(fontSize = 15.sp), modifier = Modifier.padding(start=8.dp,top=10.dp))
                        Text(text = "Community", color = textColor, style = TextStyle(fontSize = 15.sp))
                        Text(text = "Helper", color = textColor, style = TextStyle(fontSize = 15.sp), modifier = Modifier.padding(start=12.dp))
                    }
                    Column(modifier = Modifier.weight(1f)) {
                        Button(
                            onClick = {
                                text1 = "Food, water suppliers"
                                val intent = Intent(context, MainActivity::class.java).apply {
                                    putExtra("text1", text1)
                                }
                                context.startActivity(intent)
                            }, shape = CircleShape, modifier = Modifier
                                .size(70.dp)
                        ) {
                            Icon(painter = painterResource(id = R.drawable.s2), contentDescription = null, modifier = Modifier.size(50.dp))
                        }
                        Text(text = "Food,water", color = textColor, style = TextStyle(fontSize = 15.sp), modifier = Modifier.padding(top=10.dp))
                        Text(text = "Suppliers", color = textColor, style = TextStyle(fontSize = 15.sp), modifier = Modifier.padding(start=5.dp))
                    }
                    Column {
                        Button(
                            onClick = {
                                text1 = "Maids"
                                val intent = Intent(context, MainActivity::class.java).apply {
                                    putExtra("text1", text1)
                                }
                                context.startActivity(intent)
                            }, shape = CircleShape, modifier = Modifier
                                .size(70.dp)
                        ) {
                            Icon(painter = painterResource(id = R.drawable.s3), contentDescription = null, modifier = Modifier.size(50.dp))
                        }
                        Text(text = "Maid", color = textColor, style = TextStyle(fontSize = 15.sp), modifier = Modifier.padding(start=17.dp,top=10.dp))
                        Text(text = "Contacts", color = textColor, style = TextStyle(fontSize = 15.sp), modifier = Modifier.padding(start=5.dp))
                    }
                    Spacer(modifier = Modifier.weight(.3f))
                }
                Row {
                    Spacer(modifier = Modifier.weight(.7f))
                    Column(modifier = Modifier.weight(1f)) {
                        Button(
                            onClick = {
                                text1 = "Packers and movers"
                                val intent = Intent(context, MainActivity::class.java).apply {
                                    putExtra("text1", text1)
                                }
                                context.startActivity(intent)
                            }, shape = CircleShape, modifier = Modifier
                                .size(70.dp)
                        ) {
                            Icon(painter = painterResource(id = R.drawable.s4), contentDescription = null, modifier = Modifier.size(50.dp))
                        }
                        Text(text = "Packers &", color = textColor, style = TextStyle(fontSize = 15.sp), modifier = Modifier.padding(start = 2.dp, top=10.dp))
                        Text(text = "Movers", color = textColor, style = TextStyle(fontSize = 15.sp), modifier = Modifier.padding(start=10.dp))
                    }
                    Column {
                        Button(
                            onClick = {
                                val intent = Intent(context, ResultActivity::class.java).apply {
                                    putExtra("web", "https://www.magicbricks.com/")
                                }
                                context.startActivity(intent)
                            }, shape = CircleShape, modifier = Modifier
                                .size(70.dp)
                        ) {
                            Icon(painter = painterResource(id = R.drawable.s5), contentDescription = null, modifier = Modifier.size(50.dp))
                        }
                        Text(text = "House", color = textColor, style = TextStyle(fontSize = 15.sp), modifier = Modifier.padding(start=13.dp,top=10.dp))
                        Text(text = "Renting &", color = textColor, style = TextStyle(fontSize = 15.sp), modifier = Modifier.padding(start=2.dp))
                        Text(text = "Purchase", color = textColor, style = TextStyle(fontSize = 15.sp), modifier = Modifier.padding(start=3.dp))
                    }
                    Spacer(modifier = Modifier.weight(.7f))
                }
                Spacer(modifier = Modifier.padding(6.dp))
            }
        }
    }
}
