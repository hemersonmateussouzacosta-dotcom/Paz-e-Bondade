package com.example.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

enum class FontSizeScale(val label: String, val scaleFactor: Float) {
    SMALL("Pequeno", 0.88f),
    MEDIUM("Normal", 1.0f),
    LARGE("Grande", 1.20f),
    EXTRA_LARGE("Muito Grande", 1.40f)
}

enum class ReadingThemeMode(val label: String, val description: String) {
    PARCHMENT("Pergaminho", "Tons de sépia e papiro clássico"),
    DARK_MONASTIC("Noturno Monástico", "Escuro suave para oração e vigília"),
    ALBA_LIGHT("Alba Clara", "Fundo alvo de alto contraste")
}

fun createPaxTypography(scale: Float = 1.0f): Typography {
    val serifFamily = FontFamily.Serif

    return Typography(
        displayLarge = TextStyle(
            fontFamily = serifFamily,
            fontWeight = FontWeight.Bold,
            fontSize = (32 * scale).sp,
            lineHeight = (40 * scale).sp,
            letterSpacing = 0.sp
        ),
        displayMedium = TextStyle(
            fontFamily = serifFamily,
            fontWeight = FontWeight.Bold,
            fontSize = (26 * scale).sp,
            lineHeight = (32 * scale).sp,
            letterSpacing = 0.sp
        ),
        headlineLarge = TextStyle(
            fontFamily = serifFamily,
            fontWeight = FontWeight.SemiBold,
            fontSize = (24 * scale).sp,
            lineHeight = (30 * scale).sp
        ),
        headlineMedium = TextStyle(
            fontFamily = serifFamily,
            fontWeight = FontWeight.SemiBold,
            fontSize = (20 * scale).sp,
            lineHeight = (26 * scale).sp
        ),
        titleLarge = TextStyle(
            fontFamily = serifFamily,
            fontWeight = FontWeight.Bold,
            fontSize = (18 * scale).sp,
            lineHeight = (24 * scale).sp
        ),
        titleMedium = TextStyle(
            fontFamily = serifFamily,
            fontWeight = FontWeight.Medium,
            fontSize = (16 * scale).sp,
            lineHeight = (22 * scale).sp
        ),
        titleSmall = TextStyle(
            fontFamily = serifFamily,
            fontWeight = FontWeight.Medium,
            fontSize = (14 * scale).sp,
            lineHeight = (20 * scale).sp
        ),
        bodyLarge = TextStyle(
            fontFamily = serifFamily,
            fontWeight = FontWeight.Normal,
            fontSize = (16 * scale).sp,
            lineHeight = (25 * scale).sp,
            letterSpacing = 0.2.sp
        ),
        bodyMedium = TextStyle(
            fontFamily = serifFamily,
            fontWeight = FontWeight.Normal,
            fontSize = (14 * scale).sp,
            lineHeight = (22 * scale).sp,
            letterSpacing = 0.2.sp
        ),
        bodySmall = TextStyle(
            fontFamily = serifFamily,
            fontWeight = FontWeight.Normal,
            fontSize = (12 * scale).sp,
            lineHeight = (18 * scale).sp
        ),
        labelLarge = TextStyle(
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.SemiBold,
            fontSize = (13 * scale).sp,
            lineHeight = (18 * scale).sp
        ),
        labelMedium = TextStyle(
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Medium,
            fontSize = (11 * scale).sp,
            lineHeight = (16 * scale).sp
        ),
        labelSmall = TextStyle(
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Medium,
            fontSize = (10 * scale).sp,
            lineHeight = (14 * scale).sp
        )
    )
}

val Typography = createPaxTypography(1.0f)
