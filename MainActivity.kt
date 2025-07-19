package com.example.kuaforhatirlatici

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.kuaforhatirlatici.ui.theme.KuaforHatirlaticiTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KuaforHatirlaticiTheme {
                AnaSayfaWithDrawer {
                    // Butona tıklandığında çalışacak fonksiyon:
                    val intent = Intent(this, AddAppointmentActivity::class.java)
                    startActivity(intent)
                }
            }
        }
    }
}

// AnaSayfaWithDrawer fonksiyonunu şu şekilde kullanabilirsiniz:
@Composable
fun AnaSayfaWithDrawer(onAddAppointmentClick: () -> Unit) {
    Box(modifier = Modifier.fillMaxSize()) {
        // Diğer içerikler buraya...

        Button(
            onClick = { onAddAppointmentClick() },
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(text = "+ Randevu")
        }
    }
}