package com.example.kuaforhatirlatici // <<-- BU SATIR SADECE BİR KERE VE DOSYANIN EN BAŞINDA OLMALI

// Gereksiz "class MyComposables {}" satırını SİLİN.
// Ayrıca ikinci "package com.example.kuaforhatirlatici" satırını da SİLİN.

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.* // getValue ve setValue zaten bunun içinde
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kuaforhatirlatici.ui.theme.KuaforHatirlaticiTheme

@Composable
fun MyApp() {
    var count by remember { mutableStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Butona $count kez tıklandı.")
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { count++ }) {
            Text("Tıkla")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MyAppPreview() {
    KuaforHatirlaticiTheme {
        MyApp()
    }
}

@Composable
fun CounterApp() {
    var count by remember { mutableStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Sayaç: $count", fontSize = 24.sp)
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { count++ }) {
            Text("Artır")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CounterAppPreview() {
    KuaforHatirlaticiTheme {
        CounterApp()
    }
}