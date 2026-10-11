package com.example.codebreak.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.codebreak.data.MissionsData
import com.example.codebreak.screens.*
import com.example.codebreak.storage.ProgressManager
import kotlinx.coroutines.launch

@Composable
fun AppNavGraph(progressManager: ProgressManager) {
    val navController = rememberNavController()
    val scope = rememberCoroutineScope()

    val keysCount by progressManager.keysCountFlow.collectAsState(initial = 0)
    val completedMissionsRaw by progressManager.completedMissionsFlow.collectAsState(initial = emptySet())
    val completedMissions = completedMissionsRaw.mapNotNull { it.toString().toIntOrNull() }.toSet()

    var currentMissionId by remember { mutableStateOf(1) }
    var currentQuestionIndex by remember { mutableStateOf(0) }
    var lastIsCorrect by remember { mutableStateOf(false) }
    var lastExplanation by remember { mutableStateOf("") }

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route ?: "city"

    Scaffold(
        bottomBar = {
            if (currentRoute in listOf("city", "mission_map", "profile")) {
                BottomNavigationBar(
                    currentScreen = currentRoute,
                    onTabSelected = { route ->
                        navController.navigate(route) {
                            popUpTo("city") { saveState = true }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "city",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("city") {
                CityScreen(
                    userKeys = keysCount,
                    completedMissionsCount = completedMissions.size,
                    onReturnToMissionClick = { navController.navigate("mission_map") }
                )
            }

            composable("mission_map") {
                MissionMapScreen(
                    missions = MissionsData.missions,
                    userKeys = keysCount,
                    completedMissions = completedMissions,
                    onMissionClick = { mission ->
                        currentMissionId = mission.id
                        currentQuestionIndex = 0
                        navController.navigate("mission_theory")
                    },
                    onBackClick = { navController.navigate("city") }
                )
            }

            composable("mission_theory") {
                val mission = MissionsData.missions.first { it.id == currentMissionId }
                val question = mission.questions[currentQuestionIndex]

                ExplanationScreen(
                    isCorrect = true,
                    explanation = question.bitHint,
                    districtTitle = mission.district,
                    onContinueClick = {
                        navController.navigate("mission") {
                            popUpTo("mission_theory") { inclusive = true }
                        }
                    },
                    onMapClick = { navController.navigate("mission_map") }
                )
            }

            composable("mission") {
                val mission = MissionsData.missions.first { it.id == currentMissionId }
                val question = mission.questions[currentQuestionIndex]

                MissionScreen(
                    question = question,
                    currentQuestionIndex = currentQuestionIndex,
                    totalQuestions = mission.questions.size,
                    onAnswerSubmitted = { isCorrect, explanation ->
                        lastIsCorrect = isCorrect
                        lastExplanation = explanation
                        navController.navigate("explanation_result")
                    }
                )
            }

            composable("explanation_result") {
                val mission = MissionsData.missions.first { it.id == currentMissionId }

                ExplanationScreen(
                    isCorrect = lastIsCorrect,
                    explanation = lastExplanation,
                    districtTitle = mission.district,
                    onContinueClick = {
                        if (lastIsCorrect) {
                            scope.launch {
                                progressManager.addKey()
                                progressManager.markMissionCompleted(mission.id)
                            }
                            if (mission.id == 3) {
                                navController.navigate("game_summary") {
                                    popUpTo("city") { inclusive = true }
                                }
                            } else {
                                navController.navigate("city") {
                                    popUpTo("city") { inclusive = true }
                                }
                            }
                        } else {
                            navController.navigate("mission") {
                                popUpTo("explanation_result") { inclusive = true }
                            }
                        }
                    },
                    onMapClick = { navController.navigate("city") }
                )
            }

            composable("game_summary") {
                GameSummaryScreen(onRepeatClick = {
                    navController.navigate("city") {
                        popUpTo("city") { inclusive = true }
                    }
                })
            }

            composable("profile") {
                ProfileScreen(userKeys = keysCount)
            }
        }
    }
}
