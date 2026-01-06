package com.example.doba_majembe

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.doba_majembe.navigation.AppNavHost
import com.example.doba_majembe.ui.theme.DobamajembeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DobamajembeTheme {
              AppNavHost()
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    DobamajembeTheme {
       AppNavHost()
    }
}