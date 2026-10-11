package com.example.codebreak.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun GameSummaryScreen(onRepeatClick: () -> Unit) {
    val backgroundColor = Color(0xFF0F141F)
    val cardBackgroundColor = Color(0xFF161E2E)
    val accentGreen = Color(0xFF4ADE80)
    val textGray = Color(0xFF9AA3B5)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 20.dp, vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                Text(text = "←", color = Color.White, fontSize = 20.sp)
                Text(text = "Итоги игры", color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold)
            }
            Text(text = "03 / 03", color = textGray, fontSize = 12.sp)
        }

        Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                Text(text = "✅", fontSize = 14.sp)
                Text(text = "НЕО-СИТИ СНОВА В СЕТИ", color = accentGreen, fontSize = 11.sp, fontWeight = FontWeight.Bold)
            }
            Text(text = "Система\nвосстановлена", color = Color.White, fontSize = 28.sp, fontWeight = FontWeight.Bold, lineHeight = 34.sp)
        }

        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = cardBackgroundColor),
            shape = RoundedCornerShape(16.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Column {
                        Text(text = "150 XP", color = Color.White, fontSize = 24.sp, fontWeight = FontWeight.Bold)
                        Text(text = "Всего опыта · 3 × 50 XP", color = textGray, fontSize = 11.sp)
                    }
                }
                Box(modifier = Modifier.fillMaxWidth().height(6.dp).background(Color(0xFF1E293B), CircleShape)) {
                    Box(modifier = Modifier.fillMaxWidth().fillMaxHeight().background(accentGreen, CircleShape))
                }
            }
        }

        Button(
            onClick = onRepeatClick,
            modifier = Modifier.fillMaxWidth().height(52.dp),
            shape = RoundedCornerShape(14.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
            contentPadding = PaddingValues(0.dp)
        ) {
            Box(modifier = Modifier.fillMaxSize().background(Brush.horizontalGradient(listOf(Color(0xFF38BDF8), Color(0xFF2563EB)))), contentAlignment = Alignment.Center) {
                Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text(text = "Повторить миссии →", color = Color.White, fontSize = 15.sp, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}
