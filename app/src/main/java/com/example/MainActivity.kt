package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Headphones
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.notifications.PaxNotificationHelper
import com.example.ui.PaxTab
import com.example.ui.PaxViewModel
import com.example.ui.components.AppearanceDialog
import com.example.ui.components.FranciscanHeader
import com.example.ui.components.RosaryMiniPlayer
import com.example.ui.screens.FreiGalvaoScreen
import com.example.ui.screens.LiturgyScreen
import com.example.ui.screens.RosaryScreen
import com.example.ui.screens.SettingsScreen
import com.example.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {

    private val viewModel: PaxViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        PaxNotificationHelper.createNotificationChannel(this)

        setContent {
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()
            val rosaryState by viewModel.rosaryState.collectAsStateWithLifecycle()
            val saints by viewModel.saints.collectAsStateWithLifecycle()
            val prayers by viewModel.prayers.collectAsStateWithLifecycle()
            val liturgicalDays by viewModel.liturgicalDays.collectAsStateWithLifecycle()
            val reminders by viewModel.reminders.collectAsStateWithLifecycle()
            val audioPackages by viewModel.audioPackages.collectAsStateWithLifecycle()

            val snackbarHostState = remember { SnackbarHostState() }
            var showAppearanceDialog by remember { mutableStateOf(false) }

            LaunchedEffect(uiState.userNotice) {
                uiState.userNotice?.let { notice ->
                    snackbarHostState.showSnackbar(notice)
                    viewModel.clearNotice()
                }
            }

            MyApplicationTheme(
                readingMode = uiState.readingTheme,
                fontSizeScale = uiState.fontSizeScale
            ) {
                val currentLiturgyDay = liturgicalDays.find { it.dateCode == uiState.selectedDateCode }

                Scaffold(
                    snackbarHost = { SnackbarHost(snackbarHostState) },
                    topBar = {
                        FranciscanHeader(
                            title = when (uiState.currentTab) {
                                PaxTab.FREI_GALVAO -> "São Frei Galvão"
                                PaxTab.LITURGY_1962 -> "Liturgia Diária 1962"
                                PaxTab.ROSARY_AUDIO -> "Terços & Devocionais"
                                PaxTab.SETTINGS -> "Lembretes & Ajustes"
                            },
                            subtitle = when (uiState.currentTab) {
                                PaxTab.FREI_GALVAO -> "Rezando com São Frei Galvão • Pílulas, Novena & Milagres"
                                PaxTab.LITURGY_1962 -> currentLiturgyDay?.dayTitle ?: "Missal Tradicional Tridentino"
                                PaxTab.ROSARY_AUDIO -> rosaryState.rosaryType.title
                                PaxTab.SETTINGS -> "Horas Canônicas, Angelus & Áudio Offline"
                            },
                            liturgicalColorName = if (uiState.currentTab == PaxTab.LITURGY_1962) {
                                currentLiturgyDay?.color
                            } else null,
                            onOpenAppearanceDialog = { showAppearanceDialog = true }
                        )
                    },
                    bottomBar = {
                        Column {
                            // Mini Player if Rosary audio is active or playing
                            AnimatedVisibility(
                                visible = rosaryState.isPlaying && uiState.currentTab != PaxTab.ROSARY_AUDIO
                            ) {
                                RosaryMiniPlayer(
                                    rosaryState = rosaryState,
                                    onTogglePlayPause = { viewModel.togglePlayPauseRosary() },
                                    onNextBead = { viewModel.nextRosaryBead() },
                                    onClick = { viewModel.selectTab(PaxTab.ROSARY_AUDIO) }
                                )
                            }

                            NavigationBar(
                                containerColor = MaterialTheme.colorScheme.surface,
                                contentColor = MaterialTheme.colorScheme.primary,
                                tonalElevation = 3.dp,
                                modifier = Modifier.testTag("pax_navigation_bar")
                            ) {
                                NavigationBarItem(
                                    selected = uiState.currentTab == PaxTab.FREI_GALVAO,
                                    onClick = { viewModel.selectTab(PaxTab.FREI_GALVAO) },
                                    icon = { Icon(Icons.Default.AutoAwesome, contentDescription = "Rezando com São Frei Galvão") },
                                    label = { Text("Frei Galvão", style = MaterialTheme.typography.labelSmall) },
                                    colors = NavigationBarItemDefaults.colors(
                                        selectedIconColor = MaterialTheme.colorScheme.primary,
                                        selectedTextColor = MaterialTheme.colorScheme.primary,
                                        indicatorColor = MaterialTheme.colorScheme.primaryContainer
                                    ),
                                    modifier = Modifier.testTag("nav_frei_galvao")
                                )

                                NavigationBarItem(
                                    selected = uiState.currentTab == PaxTab.LITURGY_1962,
                                    onClick = { viewModel.selectTab(PaxTab.LITURGY_1962) },
                                    icon = { Icon(Icons.Default.MenuBook, contentDescription = "Liturgia 1962") },
                                    label = { Text("Liturgia 1962", style = MaterialTheme.typography.labelSmall) },
                                    colors = NavigationBarItemDefaults.colors(
                                        selectedIconColor = MaterialTheme.colorScheme.primary,
                                        selectedTextColor = MaterialTheme.colorScheme.primary,
                                        indicatorColor = MaterialTheme.colorScheme.primaryContainer
                                    ),
                                    modifier = Modifier.testTag("nav_liturgy")
                                )

                                NavigationBarItem(
                                    selected = uiState.currentTab == PaxTab.ROSARY_AUDIO,
                                    onClick = { viewModel.selectTab(PaxTab.ROSARY_AUDIO) },
                                    icon = { Icon(Icons.Default.Headphones, contentDescription = "Terços & Áudio") },
                                    label = { Text("Terços & Áudio", style = MaterialTheme.typography.labelSmall) },
                                    colors = NavigationBarItemDefaults.colors(
                                        selectedIconColor = MaterialTheme.colorScheme.primary,
                                        selectedTextColor = MaterialTheme.colorScheme.primary,
                                        indicatorColor = MaterialTheme.colorScheme.primaryContainer
                                    ),
                                    modifier = Modifier.testTag("nav_rosary")
                                )

                                NavigationBarItem(
                                    selected = uiState.currentTab == PaxTab.SETTINGS,
                                    onClick = { viewModel.selectTab(PaxTab.SETTINGS) },
                                    icon = { Icon(Icons.Default.Settings, contentDescription = "Lembretes & Ajustes") },
                                    label = { Text("Ajustes", style = MaterialTheme.typography.labelSmall) },
                                    colors = NavigationBarItemDefaults.colors(
                                        selectedIconColor = MaterialTheme.colorScheme.primary,
                                        selectedTextColor = MaterialTheme.colorScheme.primary,
                                        indicatorColor = MaterialTheme.colorScheme.primaryContainer
                                    ),
                                    modifier = Modifier.testTag("nav_settings")
                                )
                            }
                        }
                    },
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    ) {
                        when (uiState.currentTab) {
                            PaxTab.FREI_GALVAO -> FreiGalvaoScreen(
                                viewModel = viewModel,
                                uiState = uiState
                            )

                            PaxTab.LITURGY_1962 -> LiturgyScreen(
                                viewModel = viewModel,
                                uiState = uiState,
                                liturgicalDays = liturgicalDays
                            )

                            PaxTab.ROSARY_AUDIO -> RosaryScreen(
                                viewModel = viewModel,
                                uiState = uiState,
                                rosaryState = rosaryState
                            )

                            PaxTab.SETTINGS -> SettingsScreen(
                                viewModel = viewModel,
                                uiState = uiState,
                                reminders = reminders,
                                audioPackages = audioPackages
                            )
                        }
                    }

                    if (showAppearanceDialog) {
                        AppearanceDialog(
                            currentTheme = uiState.readingTheme,
                            currentScale = uiState.fontSizeScale,
                            onSelectTheme = { viewModel.setReadingTheme(it) },
                            onSelectScale = { viewModel.setFontSizeScale(it) },
                            onDismiss = { showAppearanceDialog = false }
                        )
                    }
                }
            }
        }
    }
}
