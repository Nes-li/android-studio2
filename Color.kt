package com.example.kuaforhatirlatici.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.material3.lightColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography

public val BlackBackground = Color(0xFF000000)
public val GoldAccent = Color(0xFFFFD700)
public val DarkGrayCard = Color(0xFFA9A9A9)
public val WhiteText = Color(0xFFFFFFFF)

private val LightColors = lightColorScheme(
    primary = GoldAccent,
    background = BlackBackground,
    onBackground = WhiteText,
    surface = DarkGrayCard,
    onSurface = WhiteText,
    // diğer renkler burada eklenebilir
)

public val MyTypography = Typography()

@Composable
public fun KuaforHatirlaticiTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = LightColors,
        typography = MyTypography, // Burada yeni ismi kullandık
        content = content
    )
}