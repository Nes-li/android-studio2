package com.example.kuaforhatirlatici.screens.com.example.kuaforhatirlatici.screens

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView

data class MenuItem(val title: String, val icon: ImageVector)

@OptIn(UnstableApi::class)
@Composable
fun AnaSayfaWithDrawer() {
    val menuItems = listOf(
        MenuItem("Anasayfa", Icons.Default.Person),
        MenuItem("Reels", Icons.Default.PlayCircle),
        MenuItem("Yapay Zeka", Icons.Default.Psychology),
        MenuItem("Randevular", Icons.Default.DateRange),
        MenuItem("Paylaşımlar", Icons.Default.Share),
        MenuItem("İstatistik", Icons.Default.BarChart),
        MenuItem("Harita", Icons.Default.Map),
        MenuItem("Ayarlar", Icons.Default.Settings),
    )

    // Reels Video State
    var reelsVideo by remember { mutableStateOf<Uri?>(null) }
    var reelsTimestamp by remember { mutableStateOf<Long?>(null) }

    // Video Seçme için launcher
    val pickVideoLauncher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        uri?.let {
            reelsVideo = it
            reelsTimestamp = System.currentTimeMillis()
        }
    }

    // 24 saat dolduysa paylaşımı kaldır
    if (reelsTimestamp != null && System.currentTimeMillis() - reelsTimestamp!! > 24 * 60 * 60 * 1000) {
        reelsVideo = null
        reelsTimestamp = null
    }

    Row(modifier = Modifier
        .fillMaxSize()
        .background(Color(0xFFFDF6E3))) {
        // SOL MENU
        Column(
            modifier = Modifier
                .width(220.dp)
                .fillMaxHeight()
                .background(Color(0xFFFFFCF4))
                .padding(vertical = 12.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(start = 16.dp, bottom = 18.dp, top = 6.dp)
            ) {
                Icon(Icons.Default.Menu, contentDescription = null, tint = Color(0xFFFBC02D), modifier = Modifier.size(26.dp))
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = "Menü",
                    color = Color(0xFFFBC02D),
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
            }
            Spacer(modifier = Modifier.height(4.dp))
            menuItems.forEachIndexed { i, item ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(vertical = 4.dp, horizontal = 12.dp)
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(8.dp))
                        .background(if (i == 0) Color(0x20FBC02D) else Color.Transparent)
                        .height(36.dp)
                        .clickable { }
                ) {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.title,
                        tint = if (i == 0) Color(0xFFFBC02D) else Color(0xFFFBC02D),
                        modifier = Modifier.size(22.dp)
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(
                        text = item.title,
                        color = if (i == 0) Color(0xFFFBC02D) else Color.Black,
                        fontWeight = if (i == 0) FontWeight.Bold else FontWeight.Normal,
                        fontSize = 16.sp
                    )
                }
            }
        }
        // SAG ANA ALAN
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            // AppBar
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(90.dp)
                    .background(Color(0xFFFBC02D))
                    .padding(top = 32.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Kuaför Hatırlatıcı",
                    color = Color.Black,
                    fontWeight = FontWeight.Medium,
                    fontSize = 26.sp,
                )
            }
            // İçerik kartları
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(18.dp),
                verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                HomeCard(
                    icon = Icons.Default.Psychology,
                    title = "Yapay Zeka Önerisi",
                    titleColor = Color(0xFFFBC02D)
                ) {
                    Text("Bugün için en uygun saat: 14:30", color = Color.Black, fontSize = 16.sp)
                }

                // REELS Video Kartı - Video seç, yükle ve izle!
                HomeCard(
                    icon = Icons.Default.GraphicEq,
                    title = "Öne Çıkan Reels",
                    titleColor = Color(0xFFFBC02D)
                ) {
                    if (reelsVideo == null) {
                        Button(
                            onClick = { pickVideoLauncher.launch("video/*") },
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFBC02D)),
                            modifier = Modifier.padding(vertical = 8.dp)
                        ) {
                            Icon(Icons.Default.VideoLibrary, contentDescription = "Video Seç", tint = Color.White)
                            Spacer(Modifier.width(6.dp))
                            Text("Video Seç ve Paylaş", color = Color.White)
                        }
                        Text(
                            text = "Reels videosu burada oynatılacak",
                            color = Color.Black,
                            fontSize = 16.sp
                        )
                    } else {
                        VideoPlayer(videoUri = reelsVideo!!)
                        Text(
                            text = "Bu paylaşım 24 saat sonra kaybolacak.",
                            color = Color.Gray,
                            fontSize = 13.sp,
                            modifier = Modifier.padding(top = 6.dp)
                        )
                    }
                }

                HomeCard(
                    icon = Icons.Default.BarChart,
                    title = "Haftalık İstatistik",
                    titleColor = Color(0xFFFBC02D)
                ) {
                    Text("Randevu grafikleri burada olacak", color = Color.Black, fontSize = 16.sp)
                }
                HomeCard(
                    icon = Icons.Default.LocationOn,
                    title = "Konum ve Harita",
                    titleColor = Color(0xFFFBC02D)
                ) {
                    Text("Google Maps konum görünümü burada olacak", color = Color.Black, fontSize = 16.sp)
                }
            }
        }
    }
}

@OptIn(UnstableApi::class)
@Composable
fun VideoPlayer(videoUri: Uri) {
    val context = LocalContext.current
    val videoPlayer = remember {
        ExoPlayer.Builder(context).build().apply {
            val mediaItem = androidx.media3.common.MediaItem.fromUri(videoUri)
            setMediaItem(mediaItem)
            prepare()
            playWhenReady = false
            volume = 1f // Video sesi
        }
    }
    DisposableEffect(videoPlayer) {
        onDispose {
            videoPlayer.release()
        }
    }
    Box(modifier = Modifier
        .fillMaxWidth()
        .height(200.dp)
        .clip(RoundedCornerShape(16.dp))
        .background(Color.Black)
    ) {
        AndroidView(factory = {
            PlayerView(it).apply {
                useController = true
                this.player = videoPlayer
            }
        })
    }
}

@Composable
fun HomeCard(
    icon: ImageVector,
    title: String,
    titleColor: Color,
    content: @Composable ColumnScope.() -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White, RoundedCornerShape(20.dp))
            .shadow(2.dp, RoundedCornerShape(20.dp))
            .padding(16.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(icon, contentDescription = title, tint = titleColor, modifier = Modifier.size(22.dp))
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                title,
                color = titleColor,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
        }
        Spacer(modifier = Modifier.height(6.dp))
        content()
    }
}