package com.example.codebreak.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.codebreak.data.Question

@Composable
fun MissionScreen(
    question: Question,
    currentQuestionIndex: Int,
    totalQuestions: Int,
    onAnswerSubmitted: (Boolean, String) -> Unit
) {
    var selectedOptionIndex by remember { mutableStateOf(-1) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF090D16))
            .padding(start = 20.dp, end = 20.dp, top = 20.dp, bottom = 45.dp), // Подняли нижнюю часть
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        // Добавили weight и СВОБОДНЫЙ СВАЙП для контента теста!
        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
                .padding(bottom = 12.dp)
        ) {
            Text(
                text = question.title,
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(12.dp))

            Card(
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFF101726)),
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(modifier = Modifier.padding(12.dp), verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(32.dp)
                            .background(Color(0xFF1E293B), RoundedCornerShape(8.dp)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = "🤖", fontSize = 18.sp)
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(text = "БИТ / ПОДСКАЗКА", color = Color(0xFFC5CEE0), fontSize = 12.sp, fontWeight = FontWeight.Bold)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Card(
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFF05080E)),
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, Color(0xFF1E293B), RoundedCornerShape(12.dp))
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(text = "PYTHON", color = Color(0xFF3B82F6), fontSize = 10.sp, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = question.codeSnippet,
                        color = Color(0xFF00FFCC),
                        fontFamily = FontFamily.Monospace,
                        fontSize = 13.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(text = question.questionText, color = Color.White, fontSize = 14.sp, fontWeight = FontWeight.Medium)

            Spacer(modifier = Modifier.height(12.dp))

            question.options.forEachIndexed { index, option ->
                val isSelected = selectedOptionIndex == index

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                        .border(
                            width = 1.dp,
                            color = if (isSelected) Color(0xFF3B82F6) else Color(0xFF1E293B),
                            shape = RoundedCornerShape(10.dp)
                        )
                        .clickable { selectedOptionIndex = index },
                    shape = RoundedCornerShape(10.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = if (isSelected) Color(0xFF1E293B) else Color(0xFF101726)
                    )
                ) {
                    Text(
                        text = option,
                        color = Color.White,
                        fontSize = 13.sp,
                        modifier = Modifier.padding(14.dp)
                    )
                }
            }
        }

        Button(
            onClick = {
                if (selectedOptionIndex != -1) {
                    val isCorrect = selectedOptionIndex == question.correctAnswerIndex
                    val explanation = if (isCorrect) question.successExplanation else question.errorExplanation
                    onAnswerSubmitted(isCorrect, explanation)
                }
            },
            enabled = selectedOptionIndex != -1,
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF3B82F6))
        ) {
            Text(text = "Проверить", color = Color.White, fontSize = 15.sp, fontWeight = FontWeight.Bold)
        }
    }
}
