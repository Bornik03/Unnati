package com.example.unnati2

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.unnati2.ui.theme.Unnati2Theme
import com.google.firebase.auth.FirebaseAuth

class Signup : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Unnati2Theme {
                SignupScreen()
            }
        }
    }
}
@Preview
@Composable
fun SignupScreen() {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var str by remember { mutableStateOf("") }
    val auth = FirebaseAuth.getInstance()
    val context = LocalContext.current
    val backgroundColor = MaterialTheme.colorScheme.background
    val textColor = MaterialTheme.colorScheme.onBackground
    var check by remember { mutableStateOf(false) }
    val keyboardController = LocalSoftwareKeyboardController.current
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)  // Set background color
            .padding(16.dp)
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Spacer(modifier = Modifier.padding(top = 50.dp))
                Text(text = "Unnati", style = TextStyle(fontSize = 50.sp), color = textColor)
                Spacer(modifier = Modifier.height(150.dp))
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = email,
                    label = { Text("Enter email") },
                    onValueChange = { newText -> email = newText.trim()
                        check=false}
                )
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = password,
                    label = { Text("Enter password") },
                    onValueChange = { newText -> password = newText
                        check=false
                    },
                    visualTransformation = PasswordVisualTransformation()
                )
                Spacer(modifier = Modifier.padding(top = 30.dp))
                Button(onClick = {
                    keyboardController?.hide()
                    if ( (email.endsWith("@gmail.com")||email.endsWith("@email.com")||email.endsWith("@yahoo.com")||email.endsWith("@microsoft.com"))&& password.length >= 6) {
                        str="Signing You Up..."
                        check=true
                        auth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener { task ->
                                if (task.isSuccessful) {
                                    val intent = Intent(context, AllCompo::class.java)
                                    context.startActivity(intent)
                                    (context as? ComponentActivity)?.finish()
                                } else {
                                    str="Bad Credentials"
                                    check=true
                                }
                            }
                    }
                    else if (password.length<6)
                    {
                        str="Password must be atleast 6 digits"
                        check=true
                    }
                    else if (email == "")
                    {
                        str="Email cannot be empty"
                        check=true
                    }
                    else if (!(email.endsWith("@gmail.com")||email.endsWith("@email.com")||email.endsWith("@yahoo.com")||email.endsWith("@microsoft.com")))
                    {
                        str="Enter valid Email"
                        check=true
                    }
                }, modifier = Modifier.fillMaxWidth()) {
                    Text(text = "Sign up")
                }
                Spacer(modifier = Modifier.padding(top = 15.dp))
                if(check)
                {
                    Dialog(onDismissRequest = { check=false }) {
                        Text(text = str, color = textColor, style = TextStyle(fontSize = 20.sp), modifier = Modifier.padding(top = 200.dp))
                    }
                }
            }
        }
    }
}
