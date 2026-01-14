package com.example.doba_majembe.ui.theme.screens


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.doba_majembe.data.ViewModel
import com.example.doba_majembe.models.PlayerStatsResponse


@Composable
fun Login_Chess(navController: NavController) {

    var username by remember { mutableStateOf("") }
    var stats by remember { mutableStateOf<PlayerStatsResponse?>(null) }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    var isLoading by remember { mutableStateOf(false) }
    val viewModel = remember { ViewModel() }

    Column(modifier = Modifier.padding(16.dp)) {

        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Chess.com Username") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        Button(onClick = {
            isLoading = true
            viewModel.fetchChessStats(
                username = username,
                onSuccess = {
                    stats = it
                    isLoading = false
                    println("CHESS STATS: $it") // 👈 LOGCAT
                },
                onError = {
                    errorMessage = it
                    isLoading = false
                }
            )
        }) {
            Text("Fetch Stats")
        }

        Spacer(modifier = Modifier.height(16.dp))

        when {
            isLoading -> {
                Text("Loading...")
            }

            errorMessage != null -> {
                Text(
                    text = errorMessage!!,
                    color = Color.Red,
                    fontSize = 12.sp
                )
            }

            stats != null -> {
                val blitz = stats!!.chess_blitz?.record
                Text("Blitz Wins: ${blitz?.win}")
                Text("Blitz Losses: ${blitz?.loss}")
                Text("Blitz Draws: ${blitz?.draw}")
            }
        }

    }
}
