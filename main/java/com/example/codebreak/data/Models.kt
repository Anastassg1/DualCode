package com.example.codebreak.data

import androidx.annotation.DrawableRes

data class Question(
    val id: Int,
    val title: String,
    val bitHint: String,
    val codeSnippet: String,
    val questionText: String,
    val options: List<String>,
    val correctAnswerIndex: Int,
    val successExplanation: String,
    val errorExplanation: String
)

data class Mission(
    val id: Int,
    val district: String,
    val title: String,
    val duration: String,
    val requiredKeys: Int,
    @DrawableRes val imageRes: Int,
    val questions: List<Question>
)