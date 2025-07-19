package com.example.kuaforhatirlatici.ui.theme

package com.yourdomain.kuaforhatirlatici.ui

import android.app.Activity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider

@Composable
fun LoginScreen(
    onLoginSuccess: (String) -> Unit,
    onRegisterClicked: () -> Unit,
    onGoogleLoginSuccess: (String) -> Unit
) {
    val context = LocalContext.current
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var error by remember { mutableStateOf<String?>(null) }
    val auth = remember { FirebaseAuth.getInstance() }

    // Google Sign-In Launcher (kısaltılmış, tam entegrasyon için GoogleSignInClient yapılandırması da gerekir)
    val googleSignInLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        // Google Auth işlemi burada tamamlanır
        // Firebase ile bağlanmak için idToken alınmalı
        // onGoogleLoginSuccess(email) çağrılır
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = stringResource(id = R.string.login), style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = email, onValueChange = { email = it },
            label = { Text(stringResource(id = R.string.login)) }
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = password, onValueChange = { password = it },
            label = { Text("Şifre") },
            visualTransformation = PasswordVisualTransformation()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            auth.signInWithEmailAndPassword(email, password)
                .addOnSuccessListener {
                    onLoginSuccess(email)
                }
                .addOnFailureListener {
                    error = context.getString(R.string.login_error)
                }
        }) {
            Text(stringResource(id = R.string.login))
        }
        TextButton(onClick = onRegisterClicked) {
            Text(stringResource(id = R.string.register))
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            // Google giriş başlatılır
            // googleSignInLauncher.launch(...)
        }) {
            Text(stringResource(id = R.string.sign_in_google))
        }
        error?.let {
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = it, color = MaterialTheme.colorScheme.error)
        }
    }
}