package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.FormatQuote
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Medication
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.RadioButtonUnchecked
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.SelfImprovement
import androidx.compose.material.icons.filled.VolunteerActivism
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.data.FreiGalvaoData
import com.example.data.model.FreiGalvaoIntention
import com.example.data.model.FreiGalvaoMiracle
import com.example.data.model.FreiGalvaoNovenaDay
import com.example.data.model.FreiGalvaoPrayer
import com.example.ui.PaxUiState
import com.example.ui.PaxViewModel
import com.example.ui.theme.FranciscanBrown
import com.example.ui.theme.RubricRed
import com.example.ui.theme.SeraphicGold

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FreiGalvaoScreen(
    viewModel: PaxViewModel,
    uiState: PaxUiState,
    modifier: Modifier = Modifier
) {
    val subTabs = listOf(
        "Novena das Pílulas",
        "As Pílulas",
        "Devocionário",
        "Vida & Milagres",
        "Livro de Preces"
    )

    var showPillGuideSheet by remember { mutableStateOf(false) }
    var showNewIntentionDialog by remember { mutableStateOf(false) }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .testTag("frei_galvao_screen")
    ) {
        // Hero Visual Banner
        item {
            FreiGalvaoHeroBanner(
                onOpenPillGuide = { showPillGuideSheet = true },
                onQuickPrayer = {
                    viewModel.showNotice("«Post partum, Virgo, inviolata permansisti: Dei Genitrix, intercede pro nobis!»")
                }
            )
        }

        // Sub Tabs Row
        item {
            ScrollableTabRow(
                selectedTabIndex = uiState.freiGalvaoSubTab,
                edgePadding = 12.dp,
                containerColor = MaterialTheme.colorScheme.surface,
                contentColor = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("frei_galvao_tabs")
            ) {
                subTabs.forEachIndexed { index, title ->
                    Tab(
                        selected = uiState.freiGalvaoSubTab == index,
                        onClick = { viewModel.setFreiGalvaoSubTab(index) },
                        text = {
                            Text(
                                text = title,
                                style = MaterialTheme.typography.titleSmall,
                                fontWeight = if (uiState.freiGalvaoSubTab == index) FontWeight.Bold else FontWeight.Normal
                            )
                        },
                        modifier = Modifier.testTag("frei_galvao_tab_$index")
                    )
                }
            }
            HorizontalDivider(color = MaterialTheme.colorScheme.outline.copy(alpha = 0.2f))
        }

        // Tab Content
        when (uiState.freiGalvaoSubTab) {
            0 -> {
                // Novena das Pílulas (9 Dias)
                item {
                    FreiGalvaoNovenaSection(
                        selectedDayNumber = uiState.selectedNovenaDayNumber,
                        completedDays = uiState.completedNovenaDays,
                        onSelectDay = { viewModel.selectNovenaDay(it) },
                        onToggleCompleted = { viewModel.toggleNovenaDay(it) },
                        onResetNovena = { viewModel.resetNovena() }
                    )
                }
            }

            1 -> {
                // As Pílulas de Frei Galvão (Origem, Como Tomar, Simulador)
                item {
                    FreiGalvaoPillsExplainedSection(
                        onOpenAntiphonModal = {
                            viewModel.selectFreiGalvaoPrayer(FreiGalvaoData.prayers.find { it.id == "oracao_pilulas" })
                        }
                    )
                }
            }

            2 -> {
                // Devocionário (Orações Oficiais e Específicas)
                item {
                    FreiGalvaoPrayersSection(
                        prayers = FreiGalvaoData.prayers,
                        onSelectPrayer = { viewModel.selectFreiGalvaoPrayer(it) }
                    )
                }
            }

            3 -> {
                // Vida & Milagres (Bilocação, Cálculos, Parto, Mosteiro)
                item {
                    FreiGalvaoMiraclesSection(
                        miracles = FreiGalvaoData.miracles,
                        selectedMiracle = uiState.selectedFreiGalvaoMiracle,
                        onSelectMiracle = { viewModel.selectFreiGalvaoMiracle(it) }
                    )
                }
            }

            4 -> {
                // Livro de Preces & Intenções
                item {
                    FreiGalvaoIntentionsSection(
                        intentions = uiState.devoteeIntentions,
                        onOpenNewIntention = { showNewIntentionDialog = true },
                        onPrayForIntention = { viewModel.prayForIntention(it) },
                        onToggleGrace = { viewModel.toggleIntentionGrace(it) },
                        onDeleteIntention = { viewModel.deleteIntention(it) }
                    )
                }
            }
        }

        item {
            Spacer(modifier = Modifier.height(32.dp))
        }
    }

    // Modal BottomSheet for Prayer Reading
    if (uiState.selectedFreiGalvaoPrayer != null) {
        val prayer = uiState.selectedFreiGalvaoPrayer
        ModalBottomSheet(
            onDismissRequest = { viewModel.selectFreiGalvaoPrayer(null) },
            sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true),
            containerColor = MaterialTheme.colorScheme.surface
        ) {
            FreiGalvaoPrayerDetailSheet(
                prayer = prayer,
                onClose = { viewModel.selectFreiGalvaoPrayer(null) }
            )
        }
    }

    // Miracle Detail Modal
    if (uiState.selectedFreiGalvaoMiracle != null) {
        val miracle = uiState.selectedFreiGalvaoMiracle
        ModalBottomSheet(
            onDismissRequest = { viewModel.selectFreiGalvaoMiracle(null) },
            sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true),
            containerColor = MaterialTheme.colorScheme.surface
        ) {
            FreiGalvaoMiracleDetailSheet(
                miracle = miracle,
                onClose = { viewModel.selectFreiGalvaoMiracle(null) }
            )
        }
    }

    // Quick Pill Guide Sheet
    if (showPillGuideSheet) {
        ModalBottomSheet(
            onDismissRequest = { showPillGuideSheet = false },
            containerColor = MaterialTheme.colorScheme.surface
        ) {
            FreiGalvaoPillGuideModal(onClose = { showPillGuideSheet = false })
        }
    }

    // Add New Intention Dialog
    if (showNewIntentionDialog) {
        NewIntentionDialog(
            onDismiss = { showNewIntentionDialog = false },
            onConfirm = { name, category, text ->
                viewModel.addDevoteeIntention(name, category, text)
                showNewIntentionDialog = false
            }
        )
    }
}

@Composable
private fun FreiGalvaoHeroBanner(
    onOpenPillGuide: () -> Unit,
    onQuickPrayer: () -> Unit
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(14.dp)
            .border(1.dp, SeraphicGold.copy(alpha = 0.5f), RoundedCornerShape(16.dp))
            .testTag("frei_galvao_hero_card")
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(230.dp)
        ) {
            // Background Artwork
            Image(
                painter = painterResource(id = R.drawable.img_frei_galvao),
                contentDescription = "São Frei Galvão",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )

            // Gradient Overlay for readability
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color.Black.copy(alpha = 0.5f),
                                Color.Black.copy(alpha = 0.92f)
                            )
                        )
                    )
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Bottom
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .background(SeraphicGold, RoundedCornerShape(4.dp))
                            .padding(horizontal = 6.dp, vertical = 2.dp)
                    ) {
                        Text(
                            text = "PRIMEIRO SANTO DO BRASIL",
                            style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                    }

                    Box(
                        modifier = Modifier
                            .background(Color.White.copy(alpha = 0.25f), RoundedCornerShape(4.dp))
                            .padding(horizontal = 6.dp, vertical = 2.dp)
                    ) {
                        Text(
                            text = "1739 – 1822 • OFM Alcantarino",
                            style = MaterialTheme.typography.labelSmall,
                            color = Color.White
                        )
                    }
                }

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "Santo Antônio de Sant'Ana Galvão",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )

                Text(
                    text = "«Depois do parto, ó Virgem, permaneceste intacta: Mãe de Deus, intercedei por nós!»",
                    style = MaterialTheme.typography.bodySmall,
                    fontStyle = FontStyle.Italic,
                    color = SeraphicGold.copy(alpha = 0.95f),
                    maxLines = 2
                )

                Spacer(modifier = Modifier.height(10.dp))

                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Button(
                        onClick = onOpenPillGuide,
                        colors = ButtonDefaults.buttonColors(containerColor = SeraphicGold),
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier
                            .weight(1f)
                            .testTag("hero_pills_button")
                    ) {
                        Icon(
                            imageVector = Icons.Default.Medication,
                            contentDescription = null,
                            tint = Color.Black,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "As Pílulas",
                            color = Color.Black,
                            fontWeight = FontWeight.Bold,
                            fontSize = 12.sp
                        )
                    }

                    OutlinedButton(
                        onClick = onQuickPrayer,
                        colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.White),
                        border = ButtonDefaults.outlinedButtonBorder.copy(brush = Brush.horizontalGradient(listOf(Color.White, Color.White))),
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier
                            .weight(1f)
                            .testTag("hero_antiphon_button")
                    ) {
                        Icon(
                            imageVector = Icons.Default.AutoAwesome,
                            contentDescription = null,
                            tint = SeraphicGold,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "Jaculatória",
                            color = Color.White,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 12.sp
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun FreiGalvaoNovenaSection(
    selectedDayNumber: Int,
    completedDays: Set<Int>,
    onSelectDay: (Int) -> Unit,
    onToggleCompleted: (Int) -> Unit,
    onResetNovena: () -> Unit
) {
    val novenaDay = FreiGalvaoData.novenaDays.find { it.day == selectedDayNumber } ?: FreiGalvaoData.novenaDays.first()
    val isCurrentDayCompleted = completedDays.contains(selectedDayNumber)

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 14.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = "A Grande Novena de Frei Galvão",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = "Progresso: ${completedDays.size} de 9 dias rezados",
                    style = MaterialTheme.typography.bodySmall,
                    color = SeraphicGold
                )
            }

            IconButton(
                onClick = onResetNovena,
                modifier = Modifier.testTag("reset_novena_button")
            ) {
                Icon(
                    imageVector = Icons.Default.Refresh,
                    contentDescription = "Reiniciar Novena",
                    tint = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        // Days horizontal carousel (1 to 9)
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            items(FreiGalvaoData.novenaDays) { dayItem ->
                val isSelected = dayItem.day == selectedDayNumber
                val isDone = completedDays.contains(dayItem.day)

                Card(
                    onClick = { onSelectDay(dayItem.day) },
                    colors = CardDefaults.cardColors(
                        containerColor = when {
                            isSelected -> MaterialTheme.colorScheme.primaryContainer
                            isDone -> SeraphicGold.copy(alpha = 0.15f)
                            else -> MaterialTheme.colorScheme.surface
                        }
                    ),
                    shape = RoundedCornerShape(12.dp),
                    border = if (isSelected) {
                        androidx.compose.foundation.BorderStroke(2.dp, SeraphicGold)
                    } else if (dayItem.isPillDay) {
                        androidx.compose.foundation.BorderStroke(1.dp, RubricRed.copy(alpha = 0.6f))
                    } else {
                        androidx.compose.foundation.BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.3f))
                    },
                    modifier = Modifier
                        .width(72.dp)
                        .testTag("novena_day_item_${dayItem.day}")
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.padding(vertical = 8.dp, horizontal = 4.dp)
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            if (isDone) {
                                Icon(
                                    imageVector = Icons.Default.CheckCircle,
                                    contentDescription = null,
                                    tint = SeraphicGold,
                                    modifier = Modifier.size(14.dp)
                                )
                                Spacer(modifier = Modifier.width(2.dp))
                            }
                            Text(
                                text = "Dia ${dayItem.day}",
                                style = MaterialTheme.typography.labelSmall,
                                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                            )
                        }

                        Spacer(modifier = Modifier.height(2.dp))

                        if (dayItem.isPillDay) {
                            Text(
                                text = "💊 Pílula",
                                style = MaterialTheme.typography.labelSmall,
                                fontSize = 9.sp,
                                color = RubricRed,
                                fontWeight = FontWeight.Bold
                            )
                        } else {
                            Text(
                                text = "Oração",
                                style = MaterialTheme.typography.labelSmall,
                                fontSize = 9.sp,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        // Selected Day Details Card
        Card(
            shape = RoundedCornerShape(14.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
            border = androidx.compose.foundation.BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.3f)),
            modifier = Modifier
                .fillMaxWidth()
                .testTag("novena_active_day_card")
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = novenaDay.title,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier.weight(1f)
                    )

                    if (novenaDay.isPillDay) {
                        Box(
                            modifier = Modifier
                                .background(RubricRed.copy(alpha = 0.15f), RoundedCornerShape(6.dp))
                                .border(1.dp, RubricRed, RoundedCornerShape(6.dp))
                                .padding(horizontal = 8.dp, vertical = 4.dp)
                        ) {
                            Text(
                                text = "Dia da Pílula",
                                style = MaterialTheme.typography.labelSmall,
                                fontWeight = FontWeight.Bold,
                                color = RubricRed
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "Virtude Seráfica: ${novenaDay.virtue}",
                    style = MaterialTheme.typography.labelMedium,
                    color = SeraphicGold,
                    fontWeight = FontWeight.SemiBold
                )

                Spacer(modifier = Modifier.height(10.dp))

                // Scripture / Thought Box
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.3f), RoundedCornerShape(8.dp))
                        .padding(10.dp)
                ) {
                    Text(
                        text = novenaDay.scriptureOrThought,
                        style = MaterialTheme.typography.bodySmall,
                        fontStyle = FontStyle.Italic,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "Meditação do Dia:",
                    style = MaterialTheme.typography.labelLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = novenaDay.meditation,
                    style = MaterialTheme.typography.bodyMedium,
                    lineHeight = 22.sp,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Spacer(modifier = Modifier.height(14.dp))

                Text(
                    text = "Oração do Dia:",
                    style = MaterialTheme.typography.labelLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = novenaDay.prayer,
                    style = MaterialTheme.typography.bodyMedium,
                    fontFamily = FontFamily.Serif,
                    lineHeight = 22.sp,
                    color = MaterialTheme.colorScheme.onSurface
                )

                if (novenaDay.isPillDay) {
                    Spacer(modifier = Modifier.height(14.dp))
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(SeraphicGold.copy(alpha = 0.15f), RoundedCornerShape(8.dp))
                            .border(1.dp, SeraphicGold.copy(alpha = 0.6f), RoundedCornerShape(8.dp))
                            .padding(10.dp)
                    ) {
                        Row(verticalAlignment = Alignment.Top) {
                            Icon(
                                imageVector = Icons.Default.Medication,
                                contentDescription = null,
                                tint = RubricRed,
                                modifier = Modifier.size(20.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = novenaDay.pillInstruction,
                                style = MaterialTheme.typography.bodySmall,
                                fontWeight = FontWeight.Medium,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = { onToggleCompleted(selectedDayNumber) },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (isCurrentDayCompleted) SeraphicGold else MaterialTheme.colorScheme.primary
                    ),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("toggle_day_completed_button")
                ) {
                    Icon(
                        imageVector = if (isCurrentDayCompleted) Icons.Default.CheckCircle else Icons.Default.RadioButtonUnchecked,
                        contentDescription = null,
                        tint = if (isCurrentDayCompleted) Color.Black else Color.White,
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = if (isCurrentDayCompleted) "Dia $selectedDayNumber Rezado (Toque para desmarcar)" else "Marcar Dia $selectedDayNumber como Rezado",
                        color = if (isCurrentDayCompleted) Color.Black else Color.White,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

@Composable
private fun FreiGalvaoPillsExplainedSection(
    onOpenAntiphonModal: () -> Unit
) {
    var selectedPillNumber by remember { mutableStateOf(1) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 14.dp)
    ) {
        Text(
            text = "O Santo Mistério das Pílulas",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurface
        )
        Text(
            text = "Uma das maiores devoções do povo brasileiro e de cura pela fé",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Holy Antiphon Box with Latin & Portuguese
        Card(
            shape = RoundedCornerShape(14.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
            border = androidx.compose.foundation.BorderStroke(1.dp, SeraphicGold.copy(alpha = 0.7f)),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.FormatQuote,
                        contentDescription = null,
                        tint = SeraphicGold,
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "O Texto Sagrado Escrito por Frei Galvão",
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold,
                        color = SeraphicGold
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "«Post partum, Virgo, inviolata permansisti: Dei Genitrix, intercede pro nobis.»",
                    style = MaterialTheme.typography.bodyLarge,
                    fontFamily = FontFamily.Serif,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary,
                    lineHeight = 24.sp
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "«Depois do parto, ó Virgem, permaneceste intacta: Mãe de Deus, intercedei por nós!»",
                    style = MaterialTheme.typography.bodyMedium,
                    fontStyle = FontStyle.Italic,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Spacer(modifier = Modifier.height(12.dp))

                Button(
                    onClick = onOpenAntiphonModal,
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Icon(
                        imageVector = Icons.Default.AutoAwesome,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text("Rezar a Oração da Pílula Completa")
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Interactive 3-Pill Meditation Guide
        Text(
            text = "Roteiro Tradicional para Tomar as 3 Pílulas",
            style = MaterialTheme.typography.titleSmall,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurface
        )

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            listOf(1, 2, 3).forEach { pillNum ->
                FilterChip(
                    selected = selectedPillNumber == pillNum,
                    onClick = { selectedPillNumber = pillNum },
                    label = { Text("${pillNum}ª Pílula") },
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = SeraphicGold,
                        selectedLabelColor = Color.Black
                    ),
                    modifier = Modifier.weight(1f).testTag("pill_tab_$pillNum")
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Card(
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(14.dp)) {
                when (selectedPillNumber) {
                    1 -> {
                        Text(
                            text = "1ª Pílula: No 1º Dia da Novena",
                            style = MaterialTheme.typography.titleSmall,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "1. Faça o Sinal da Cruz com fé viva.\n" +
                                    "2. Engula a primeira pílula com um pouco de água (ou água benta).\n" +
                                    "3. Reze 1 Pai Nosso, 1 Ave Maria, 1 Glória ao Pai.\n" +
                                    "4. Recite a antífona: «Depois do parto, ó Virgem, permaneceste intacta: Mãe de Deus, intercedei por nós!». Apresente a sua intenção a Frei Galvão.",
                            style = MaterialTheme.typography.bodySmall,
                            lineHeight = 20.sp
                        )
                    }
                    2 -> {
                        Text(
                            text = "2ª Pílula: No 5º Dia da Novena",
                            style = MaterialTheme.typography.titleSmall,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "1. Renove com serenidade a confiança no auxílio da Mãe de Deus.\n" +
                                    "2. Engula a segunda pílula com água.\n" +
                                    "3. Reze o Pai Nosso, a Ave Maria e a oração pelos enfermos ou gestantes.\n" +
                                    "4. Recite 3 vezes a antífona mariana, oferecendo os sofrimentos em união com a Paixão de Cristo.",
                            style = MaterialTheme.typography.bodySmall,
                            lineHeight = 20.sp
                        )
                    }
                    3 -> {
                        Text(
                            text = "3ª Pílula: No 9º Dia da Novena",
                            style = MaterialTheme.typography.titleSmall,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "1. Tome a terceira pílula em profunda atitude de gratidão a Deus.\n" +
                                    "2. Reze a Oração Oficial de São Frei Galvão e a Salve Rainha.\n" +
                                    "3. Faça o propósito de viver em comunhão fraterna, caridade com os pobres e devoção sincera à Virgem Maria.",
                            style = MaterialTheme.typography.bodySmall,
                            lineHeight = 20.sp
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Information on how the pills are crafted today
        Card(
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
            border = androidx.compose.foundation.BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.3f)),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(14.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Info,
                        contentDescription = null,
                        tint = SeraphicGold,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "Onde Encontrar as Pílulas Físicas",
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = "As pílulas físicas são confeccionadas artesanalmente pelas Irmãs Concepcionistas do Mosteiro da Luz (São Paulo) e distribuídas gratuitamente no Mosteiro da Luz e no Santuário Arquidiocesano de São Frei Galvão em Guaratinguetá (SP).\n\nNunca devem ser comercializadas, pois são um sacramental gratuito concedido pela caridade franciscana.",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    lineHeight = 18.sp
                )
            }
        }
    }
}

@Composable
private fun FreiGalvaoPrayersSection(
    prayers: List<FreiGalvaoPrayer>,
    onSelectPrayer: (FreiGalvaoPrayer) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 14.dp)
    ) {
        Text(
            text = "Devocionário de São Frei Galvão",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurface
        )
        Text(
            text = "Orações para pedir a intercessão do Santo em todas as necessidades",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Spacer(modifier = Modifier.height(12.dp))

        prayers.forEach { prayer ->
            Card(
                onClick = { onSelectPrayer(prayer) },
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                border = androidx.compose.foundation.BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.3f)),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
                    .testTag("prayer_card_${prayer.id}")
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(14.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .size(42.dp)
                            .clip(CircleShape)
                            .background(MaterialTheme.colorScheme.primaryContainer)
                    ) {
                        Icon(
                            imageVector = Icons.Default.MenuBook,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(20.dp)
                        )
                    }

                    Spacer(modifier = Modifier.width(12.dp))

                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = prayer.title,
                            style = MaterialTheme.typography.titleSmall,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        Text(
                            text = prayer.intentionSubtitle,
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            maxLines = 1
                        )
                    }

                    Icon(
                        imageVector = Icons.Default.PlayArrow,
                        contentDescription = "Ler oração",
                        tint = SeraphicGold,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }
        }
    }
}

@Composable
private fun FreiGalvaoMiraclesSection(
    miracles: List<FreiGalvaoMiracle>,
    selectedMiracle: FreiGalvaoMiracle?,
    onSelectMiracle: (FreiGalvaoMiracle) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 14.dp)
    ) {
        Text(
            text = "Vida, Caridade & Milagres",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurface
        )
        Text(
            text = "Fatos extraordinários documentados no processo canônico de canonização",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Spacer(modifier = Modifier.height(12.dp))

        miracles.forEach { miracle ->
            Card(
                onClick = { onSelectMiracle(miracle) },
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                border = androidx.compose.foundation.BorderStroke(1.dp, SeraphicGold.copy(alpha = 0.4f)),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 5.dp)
                    .testTag("miracle_card_${miracle.id}")
            ) {
                Column(modifier = Modifier.padding(14.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = miracle.title,
                            style = MaterialTheme.typography.titleSmall,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface,
                            modifier = Modifier.weight(1f)
                        )
                        Box(
                            modifier = Modifier
                                .background(SeraphicGold.copy(alpha = 0.15f), RoundedCornerShape(4.dp))
                                .padding(horizontal = 6.dp, vertical = 2.dp)
                        ) {
                            Text(
                                text = miracle.yearAndPlace.take(18) + "...",
                                style = MaterialTheme.typography.labelSmall,
                                fontSize = 9.sp,
                                color = SeraphicGold
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = miracle.subtitle,
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.SemiBold
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    Text(
                        text = miracle.summary,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        lineHeight = 18.sp
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Ler relato completo",
                            style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.Bold,
                            color = SeraphicGold
                        )
                        Icon(
                            imageVector = Icons.Default.PlayArrow,
                            contentDescription = null,
                            tint = SeraphicGold,
                            modifier = Modifier.size(16.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun FreiGalvaoIntentionsSection(
    intentions: List<FreiGalvaoIntention>,
    onOpenNewIntention: () -> Unit,
    onPrayForIntention: (String) -> Unit,
    onToggleGrace: (String) -> Unit,
    onDeleteIntention: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 14.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = "Livro de Preces a Frei Galvão",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = "Apresente suas intenções ao Primeiro Santo Brasileiro",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            Button(
                onClick = onOpenNewIntention,
                colors = ButtonDefaults.buttonColors(containerColor = SeraphicGold),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.testTag("add_intention_button")
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = null,
                    tint = Color.Black,
                    modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "Pedir Graça",
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        if (intentions.isEmpty()) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(32.dp)
            ) {
                Text(
                    text = "Nenhuma intenção cadastrada ainda. Toque em 'Pedir Graça' para registrar seu pedido a São Frei Galvão.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        } else {
            intentions.forEach { intention ->
                Card(
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = if (intention.isGraceReceived) {
                            SeraphicGold.copy(alpha = 0.12f)
                        } else {
                            MaterialTheme.colorScheme.surface
                        }
                    ),
                    border = androidx.compose.foundation.BorderStroke(
                        1.dp,
                        if (intention.isGraceReceived) SeraphicGold else MaterialTheme.colorScheme.outline.copy(alpha = 0.3f)
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 5.dp)
                        .testTag("intention_card_${intention.id}")
                ) {
                    Column(modifier = Modifier.padding(14.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Box(
                                    modifier = Modifier
                                        .background(MaterialTheme.colorScheme.primaryContainer, RoundedCornerShape(4.dp))
                                        .padding(horizontal = 6.dp, vertical = 2.dp)
                                ) {
                                    Text(
                                        text = intention.intentionType,
                                        style = MaterialTheme.typography.labelSmall,
                                        fontWeight = FontWeight.Bold,
                                        color = MaterialTheme.colorScheme.primary
                                    )
                                }
                                Spacer(modifier = Modifier.width(6.dp))
                                Text(
                                    text = "• ${intention.devoteeName}",
                                    style = MaterialTheme.typography.labelSmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }

                            if (intention.isGraceReceived) {
                                Box(
                                    modifier = Modifier
                                        .background(SeraphicGold, RoundedCornerShape(4.dp))
                                        .padding(horizontal = 6.dp, vertical = 2.dp)
                                ) {
                                    Text(
                                        text = "GRAÇA ALCANÇADA",
                                        style = MaterialTheme.typography.labelSmall,
                                        fontWeight = FontWeight.Bold,
                                        color = Color.Black,
                                        fontSize = 9.sp
                                    )
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = intention.description,
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurface,
                            lineHeight = 20.sp
                        )

                        Spacer(modifier = Modifier.height(10.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Orado ${intention.prayersCount}x • ${intention.dateCreated}",
                                style = MaterialTheme.typography.labelSmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )

                            Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                                OutlinedButton(
                                    onClick = { onToggleGrace(intention.id) },
                                    shape = RoundedCornerShape(6.dp),
                                    modifier = Modifier.height(34.dp)
                                ) {
                                    Text(
                                        text = if (intention.isGraceReceived) "Agradecido" else "Marcar Graça",
                                        fontSize = 11.sp
                                    )
                                }

                                Button(
                                    onClick = { onPrayForIntention(intention.id) },
                                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
                                    shape = RoundedCornerShape(6.dp),
                                    modifier = Modifier.height(34.dp)
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.VolunteerActivism,
                                        contentDescription = null,
                                        modifier = Modifier.size(14.dp)
                                    )
                                    Spacer(modifier = Modifier.width(4.dp))
                                    Text(text = "Rezar +1", fontSize = 11.sp)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun FreiGalvaoPrayerDetailSheet(
    prayer: FreiGalvaoPrayer,
    onClose: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = prayer.title,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.weight(1f)
            )
            IconButton(onClick = onClose) {
                Icon(Icons.Default.Close, contentDescription = "Fechar")
            }
        }

        Text(
            text = prayer.intentionSubtitle,
            style = MaterialTheme.typography.labelMedium,
            color = SeraphicGold,
            fontWeight = FontWeight.SemiBold
        )

        Spacer(modifier = Modifier.height(14.dp))

        // Sacred Latin Antiphon Box
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(SeraphicGold.copy(alpha = 0.12f), RoundedCornerShape(8.dp))
                .border(1.dp, SeraphicGold.copy(alpha = 0.5f), RoundedCornerShape(8.dp))
                .padding(12.dp)
        ) {
            Column {
                Text(
                    text = "Antífona Mariana de São Frei Galvão:",
                    style = MaterialTheme.typography.labelSmall,
                    color = SeraphicGold,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = prayer.latinAntiphon,
                    style = MaterialTheme.typography.bodyMedium,
                    fontFamily = FontFamily.Serif,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        Text(
            text = prayer.portugueseText,
            style = MaterialTheme.typography.bodyLarge,
            fontFamily = FontFamily.Serif,
            lineHeight = 26.sp,
            color = MaterialTheme.colorScheme.onSurface
        )

        if (prayer.historicalContext.isNotBlank()) {
            Spacer(modifier = Modifier.height(16.dp))
            HorizontalDivider(color = MaterialTheme.colorScheme.outline.copy(alpha = 0.2f))
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Contexto Histórico e Canônico: ${prayer.historicalContext}",
                style = MaterialTheme.typography.bodySmall,
                fontStyle = FontStyle.Italic,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        Spacer(modifier = Modifier.height(20.dp))
    }
}

@Composable
private fun FreiGalvaoMiracleDetailSheet(
    miracle: FreiGalvaoMiracle,
    onClose: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = miracle.title,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.weight(1f)
            )
            IconButton(onClick = onClose) {
                Icon(Icons.Default.Close, contentDescription = "Fechar")
            }
        }

        Text(
            text = "${miracle.subtitle} • ${miracle.yearAndPlace}",
            style = MaterialTheme.typography.labelMedium,
            color = SeraphicGold,
            fontWeight = FontWeight.SemiBold
        )

        Spacer(modifier = Modifier.height(14.dp))

        Text(
            text = miracle.fullStory,
            style = MaterialTheme.typography.bodyMedium,
            lineHeight = 24.sp,
            color = MaterialTheme.colorScheme.onSurface
        )

        Spacer(modifier = Modifier.height(16.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.3f), RoundedCornerShape(8.dp))
                .padding(10.dp)
        ) {
            Text(
                text = "Reconhecimento Canônico: ${miracle.canonicalSignificance}",
                style = MaterialTheme.typography.bodySmall,
                fontStyle = FontStyle.Italic,
                color = MaterialTheme.colorScheme.onSurface
            )
        }

        Spacer(modifier = Modifier.height(20.dp))
    }
}

@Composable
private fun FreiGalvaoPillGuideModal(
    onClose: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Guia das Pílulas de Frei Galvão",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )
            IconButton(onClick = onClose) {
                Icon(Icons.Default.Close, contentDescription = "Fechar")
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "As Pílulas de São Frei Galvão não são remédio de farmácia nem ato supersticioso. São um sacramental da Igreja Católica, cujo poder vem unicamente da Fé em Deus, da intercessão da Virgem Maria Imaculada e dos méritos de São Frei Galvão.",
            style = MaterialTheme.typography.bodyMedium,
            lineHeight = 22.sp
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "Como Rezar e Tomar:",
            style = MaterialTheme.typography.titleSmall,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = "• A novena dura 9 dias.\n" +
                    "• No 1º dia toma-se a 1ª pílula.\n" +
                    "• No 5º dia toma-se a 2ª pílula.\n" +
                    "• No 9º dia toma-se a 3ª pílula.\n" +
                    "• Em cada dia, reza-se 1 Pai Nosso, 1 Ave Maria, 1 Glória e a oração da pílula com a jaculatória em honra à Pureza da Mãe de Deus.",
            style = MaterialTheme.typography.bodySmall,
            lineHeight = 20.sp
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = onClose,
            colors = ButtonDefaults.buttonColors(containerColor = SeraphicGold),
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Entendido • Pax et Bonum", color = Color.Black, fontWeight = FontWeight.Bold)
        }

        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
private fun NewIntentionDialog(
    onDismiss: () -> Unit,
    onConfirm: (name: String, category: String, description: String) -> Unit
) {
    var devoteeName by remember { mutableStateOf("") }
    var selectedCategory by remember { mutableStateOf("Saúde e Cura") }
    var description by remember { mutableStateOf("") }

    val categories = listOf(
        "Saúde e Cura",
        "Gestação e Parto",
        "Cálculos Renais",
        "Causas Urgentes",
        "Família e Paz",
        "Ação de Graças"
    )

    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(
                text = "Confiar Intenção a Frei Galvão",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
        },
        text = {
            Column(modifier = Modifier.fillMaxWidth()) {
                OutlinedTextField(
                    value = devoteeName,
                    onValueChange = { devoteeName = it },
                    label = { Text("Seu nome ou da família") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth().testTag("input_devotee_name")
                )

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = "Categoria da Graça:",
                    style = MaterialTheme.typography.labelSmall,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(4.dp))

                LazyRow(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                    items(categories) { cat ->
                        FilterChip(
                            selected = selectedCategory == cat,
                            onClick = { selectedCategory = cat },
                            label = { Text(cat, fontSize = 11.sp) },
                            colors = FilterChipDefaults.filterChipColors(
                                selectedContainerColor = SeraphicGold,
                                selectedLabelColor = Color.Black
                            )
                        )
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                OutlinedTextField(
                    value = description,
                    onValueChange = { description = it },
                    label = { Text("Descreva a intenção ou pedido de cura") },
                    minLines = 3,
                    maxLines = 5,
                    modifier = Modifier.fillMaxWidth().testTag("input_intention_desc")
                )
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    if (description.isNotBlank()) {
                        onConfirm(devoteeName, selectedCategory, description)
                    }
                },
                enabled = description.isNotBlank(),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.testTag("submit_intention_button")
            ) {
                Text("Gravar Pedido")
            }
        },
        dismissButton = {
            OutlinedButton(onClick = onDismiss) {
                Text("Cancelar")
            }
        }
    )
}
