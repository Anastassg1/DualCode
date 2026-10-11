package com.example.codebreak

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.codebreak.navigation.AppNavGraph
import com.example.codebreak.storage.ProgressManager

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val progressManager = ProgressManager(applicationContext)

        setContent {
            AppNavGraph(progressManager = progressManager)
        }
    }
}