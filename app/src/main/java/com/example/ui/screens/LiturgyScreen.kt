package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.ViewAgenda
import androidx.compose.material.icons.filled.ViewColumn
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.LiturgicalDay1962
import com.example.ui.BilingualViewMode
import com.example.ui.PaxUiState
import com.example.ui.PaxViewModel
import com.example.ui.components.BilingualProperSection
import com.example.ui.components.LiturgicalColorBadge
import com.example.ui.theme.RubricRed
import com.example.ui.theme.SeraphicGold

@Composable
fun LiturgyScreen(
    viewModel: PaxViewModel,
    uiState: PaxUiState,
    liturgicalDays: List<LiturgicalDay1962>,
    modifier: Modifier = Modifier
) {
    val currentDay = liturgicalDays.find { it.dateCode == uiState.selectedDateCode }
        ?: liturgicalDays.firstOrNull()

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(horizontal = 14.dp)
            .testTag("liturgy_screen")
    ) {
        item {
            Spacer(modifier = Modifier.height(10.dp))

            // Liturgical Date Bar & Quick Feasts Navigator
            Card(
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.2f), RoundedCornerShape(12.dp))
                    .testTag("calendar_selector_card")
            ) {
                Column(modifier = Modifier.padding(12.dp)) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.CalendarToday,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.primary,
                                modifier = Modifier.size(20.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "Calendário Litúrgico de 1962",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        }

                        Button(
                            onClick = { viewModel.selectDateCode("10-04") },
                            shape = RoundedCornerShape(8.dp),
                            contentPadding = androidx.compose.foundation.layout.PaddingValues(horizontal = 10.dp, vertical = 4.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
                            modifier = Modifier.testTag("today_button")
                        ) {
                            Text(
                                text = "São Francisco",
                                style = MaterialTheme.typography.labelSmall,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.primary
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    // Date Feast Selector Chips
                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        items(liturgicalDays) { day ->
                            val isSelected = day.dateCode == uiState.selectedDateCode
                            FilterChip(
                                selected = isSelected,
                                onClick = { viewModel.selectDateCode(day.dateCode) },
                                label = {
                                    Text(
                                        text = "${day.dateCode}: ${day.dayTitle.take(22)}...",
                                        style = MaterialTheme.typography.labelSmall
                                    )
                                },
                                colors = FilterChipDefaults.filterChipColors(
                                    selectedContainerColor = MaterialTheme.colorScheme.primary,
                                    selectedLabelColor = MaterialTheme.colorScheme.onPrimary
                                ),
                                modifier = Modifier.testTag("day_chip_${day.dateCode}")
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(10.dp))
        }

        if (currentDay != null) {
            item {
                // Day Title & Rubrics Header Card
                Card(
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                    shape = RoundedCornerShape(14.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(1.5.dp, MaterialTheme.colorScheme.primary.copy(alpha = 0.3f), RoundedCornerShape(14.dp))
                        .testTag("liturgy_day_header")
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                LiturgicalColorBadge(colorName = currentDay.color)
                                Spacer(modifier = Modifier.width(8.dp))
                                Box(
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(6.dp))
                                        .background(MaterialTheme.colorScheme.secondaryContainer)
                                        .padding(horizontal = 8.dp, vertical = 2.dp)
                                ) {
                                    Text(
                                        text = currentDay.rankClass,
                                        style = MaterialTheme.typography.labelSmall,
                                        fontWeight = FontWeight.Bold,
                                        color = MaterialTheme.colorScheme.secondary
                                    )
                                }
                            }

                            Text(
                                text = currentDay.season,
                                style = MaterialTheme.typography.labelSmall,
                                fontStyle = FontStyle.Italic,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        Text(
                            text = currentDay.dayTitle,
                            style = MaterialTheme.typography.headlineMedium,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface
                        )

                        Text(
                            text = currentDay.latinDayTitle,
                            style = MaterialTheme.typography.titleMedium,
                            fontStyle = FontStyle.Italic,
                            color = RubricRed
                        )

                        if (currentDay.rubrics.isNotBlank()) {
                            Spacer(modifier = Modifier.height(8.dp))
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    imageVector = Icons.Default.Info,
                                    contentDescription = null,
                                    tint = RubricRed,
                                    modifier = Modifier.size(16.dp)
                                )
                                Spacer(modifier = Modifier.width(6.dp))
                                Text(
                                    text = "Rubricas 1962: ${currentDay.rubrics}",
                                    style = MaterialTheme.typography.labelSmall,
                                    color = RubricRed
                                )
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                // Bilingual View Mode Switcher
                Card(
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.45f)),
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 12.dp, vertical = 8.dp)
                    ) {
                        Text(
                            text = "Exibição dos Textos:",
                            style = MaterialTheme.typography.labelMedium,
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colorScheme.onSurface
                        )

                        LazyRow(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                            items(BilingualViewMode.values()) { mode ->
                                FilterChip(
                                    selected = uiState.bilingualMode == mode,
                                    onClick = { viewModel.setBilingualMode(mode) },
                                    label = { Text(mode.label, style = MaterialTheme.typography.labelSmall) },
                                    colors = FilterChipDefaults.filterChipColors(
                                        selectedContainerColor = MaterialTheme.colorScheme.primary,
                                        selectedLabelColor = MaterialTheme.colorScheme.onPrimary
                                    ),
                                    modifier = Modifier.testTag("mode_chip_${mode.name}")
                                )
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))
            }

            // Propers of the Mass (Bilingual)
            item {
                BilingualProperSection(
                    title = "Intróito (Introitus)",
                    latinText = currentDay.introitLat,
                    portugueseText = currentDay.introitPt,
                    mode = uiState.bilingualMode
                )
            }

            item {
                BilingualProperSection(
                    title = "Coleta (Oratio Collecta)",
                    latinText = currentDay.collectLat,
                    portugueseText = currentDay.collectPt,
                    mode = uiState.bilingualMode
                )
            }

            item {
                BilingualProperSection(
                    title = "Epístola (Lectio Epistolae)",
                    latinText = currentDay.epistleLat,
                    portugueseText = currentDay.epistlePt,
                    mode = uiState.bilingualMode,
                    reference = currentDay.epistleRef
                )
            }

            item {
                BilingualProperSection(
                    title = "Gradual & Aleluia (Graduale)",
                    latinText = currentDay.gradualLat,
                    portugueseText = currentDay.gradualPt,
                    mode = uiState.bilingualMode
                )
            }

            item {
                BilingualProperSection(
                    title = "Santo Evangelho (Sanctum Evangelium)",
                    latinText = currentDay.gospelLat,
                    portugueseText = currentDay.gospelPt,
                    mode = uiState.bilingualMode,
                    reference = currentDay.gospelRef
                )
            }

            item {
                BilingualProperSection(
                    title = "Ofertório (Offertorium)",
                    latinText = currentDay.offertoryLat,
                    portugueseText = currentDay.offertoryPt,
                    mode = uiState.bilingualMode
                )
            }

            item {
                BilingualProperSection(
                    title = "Secreta (Oratio Secreta)",
                    latinText = currentDay.secretLat,
                    portugueseText = currentDay.secretPt,
                    mode = uiState.bilingualMode
                )
            }

            item {
                BilingualProperSection(
                    title = "Comunhão (Communio)",
                    latinText = currentDay.communionLat,
                    portugueseText = currentDay.communionPt,
                    mode = uiState.bilingualMode
                )
            }

            item {
                BilingualProperSection(
                    title = "Pós-Comunhão (Postcommunio)",
                    latinText = currentDay.postCommunionLat,
                    portugueseText = currentDay.postCommunionPt,
                    mode = uiState.bilingualMode
                )
            }

            item {
                Spacer(modifier = Modifier.height(28.dp))
            }
        }
    }
}
