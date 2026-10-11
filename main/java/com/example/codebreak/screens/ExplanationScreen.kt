package com.example.codebreak.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.codebreak.R

@Composable
fun ExplanationScreen(
    isCorrect: Boolean,
    explanation: String,
    districtTitle: String,
    onContinueClick: () -> Unit,
    onMapClick: () -> Unit
) {
    val isTheory = explanation.contains("LOGIC_LECTURE")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF090D16))
            // Подняли кнопки и добавили отступ снизу
            .padding(start = 20.dp, end = 20.dp, top = 16.dp, bottom = 45.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        // Добавили weight и СВOБОДНЫЙ СВАЙП для контента лекции!
        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
                .padding(bottom = 12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier.fillMaxWidth().padding(top = 4.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = if (isTheory) "← Вводная лекция" else "← Результат миссии", color = Color.White, fontSize = 14.sp)
                Text(text = "01 / 03", color = Color(0xFF5A657D), fontSize = 12.sp)
            }

            Spacer(modifier = Modifier.height(12.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(6.dp)
                        .background(if (isTheory) Color(0xFF3B82F6) else if (isCorrect) Color(0xFF10B981) else Color(0xFFEF4444), RoundedCornerShape(50))
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = if (isTheory) "ИЗУЧЕНИЕ ТЕОРИИ" else if (isCorrect) "МИССИЯ ВЫПОЛНЕНА" else "МИССИЯ НЕ ЗАВЕРШЕНА",
                    color = if (isTheory) Color(0xFF3B82F6) else if (isCorrect) Color(0xFF10B981) else Color(0xFFEF4444),
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = if (isTheory) "Освой базу кода" else if (isCorrect) "Открыт новый район!" else "Сбой ещё здесь",
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )

            Text(text = districtTitle, color = Color(0xFF5A657D), fontSize = 12.sp)

            Spacer(modifier = Modifier.height(16.dp))

            Card(
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(
                    containerColor = if (isTheory) Color(0xFF0F172A) else if (isCorrect) Color(0xFF101726) else Color(0xFF211115)
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .border(
                        width = 1.dp,
                        color = if (isTheory) Color(0xFF1E293B) else if (isCorrect) Color(0xFF1E2D4A) else Color(0xFF3D1A20),
                        shape = RoundedCornerShape(12.dp)
                    )
            ) {
                Column(modifier = Modifier.padding(14.dp)) {
                    Text(
                        text = if (isTheory) "БИТ / ПОЛНАЯ ТЕОРИЯ" else "ОТВЕТ ПРОВЕРЕН",
                        color = Color(0xFF3B82F6),
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = explanation.replace("LOGIC_LECTURE\n", ""),
                        color = Color(0xFFD1D5DB),
                        fontSize = if (isTheory) 16.sp else 12.sp,
                        lineHeight = if (isTheory) 22.sp else 18.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(130.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color(0xFF0F172A))
                    .border(1.dp, Color(0xFF1E293B), RoundedCornerShape(16.dp)),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.img_traffic_light),
                    contentDescription = "Локация",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            if (!isTheory && isCorrect) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Card(
                        modifier = Modifier.weight(1f),
                        shape = RoundedCornerShape(12.dp),
                        colors = CardDefaults.cardColors(containerColor = Color(0xFF101726))
                    ) {
                        Column(modifier = Modifier.padding(12.dp)) {
                            Text(text = "⚡ +50 XP", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                            Text(text = "За прохождение", color = Color(0xFF5A657D), fontSize = 10.sp)
                        }
                    }
                    Card(
                        modifier = Modifier.weight(1f),
                        shape = RoundedCornerShape(12.dp),
                        colors = CardDefaults.cardColors(containerColor = Color(0xFF101726))
                    ) {
                        Column(modifier = Modifier.padding(12.dp)) {
                            Text(text = "🔑 +1 ключ", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                            Text(text = "К новому району", color = Color(0xFF5A657D), fontSize = 10.sp)
                        }
                    }
                }
            }
        }

        Column(modifier = Modifier.fillMaxWidth().padding(top = 8.dp)) {
            Button(
                onClick = { onContinueClick() },
                modifier = Modifier.fillMaxWidth().height(50.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF3B82F6))
            ) {
                Text(
                    text = if (isTheory) "Пройти тест" else if (isCorrect) "Следующий урок" else "Попробовать снова",
                    color = Color.White,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            if (!isCorrect && !isTheory) {
                Spacer(modifier = Modifier.height(8.dp))
                TextButton(onClick = { onMapClick() }, modifier = Modifier.fillMaxWidth()) {
                    Text(text = "В город", color = Color(0xFF5A657D), fontSize = 13.sp)
                }
            }
        }
    }
}
