package com.example.codebreak.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.codebreak.data.Mission

@Composable
fun MissionMapScreen(
    missions: List<Mission>,
    userKeys: Int,
    completedMissions: Set<Int>,
    onMissionClick: (Mission) -> Unit,
    onBackClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF090D16))
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "← Карты миссий",
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.clickable { onBackClick() }
            )
            Surface(
                color = Color(0xFF101726),
                shape = RoundedCornerShape(20.dp),
                border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFF1E293B))
            ) {
                Text(
                    text = "🔑 $userKeys ключей",
                    color = Color(0xFF3B82F6),
                    fontSize = 12.sp,
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        missions.forEach { mission ->
            val isUnlocked = userKeys >= mission.requiredKeys
            val isCompleted = completedMissions.contains(mission.id)

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 6.dp)
                    .clickable(enabled = isUnlocked) { onMissionClick(mission) },
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(
                    containerColor = if (isUnlocked) Color(0xFF101726) else Color(0xFF0A0E18)
                )
            ) {
                Column(modifier = Modifier.padding(14.dp)) {
                    Text(text = mission.district, color = Color(0xFF5A657D), fontSize = 11.sp)
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(text = mission.title, color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(6.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = if (isCompleted) "Пройдено ✓" else if (isUnlocked) "Доступно" else "Нужно ${mission.requiredKeys} ключа",
                            color = if (isCompleted) Color(0xFF10B981) else if (isUnlocked) Color(0xFF3B82F6) else Color(0xFFEF4444),
                            fontSize = 12.sp
                        )
                        Text(text = mission.duration, color = Color(0xFF5A657D), fontSize = 12.sp)
                    }
                }
            }
        }
    }
}