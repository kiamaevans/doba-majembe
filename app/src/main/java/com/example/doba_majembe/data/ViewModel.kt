package com.example.doba_majembe.data

import com.example.doba_majembe.models.ChessStats
import com.example.doba_majembe.models.PlayerStatsResponse
import com.google.android.gms.common.api.Response
import com.google.gson.Gson
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException


class ViewModel {

    fun fetchChessStats(
        username: String,
        onSuccess: (PlayerStatsResponse) -> Unit,
        onError: (String) -> Unit
    ) {
        val client = OkHttpClient()

        val request = Request.Builder()
            .url("https://api.chess.com/pub/player/$username/stats")
            .build()

        client.newCall(request).enqueue(object : Callback{

            override fun onFailure(call: Call, e: IOException) {
                onError("Network error")
            }

            override fun onResponse(call: Call, response: okhttp3.Response) {
                if (!response.isSuccessful) {
                    onError("User not found")
                    return
                }

                val json = response.body?.string() ?: return

                val gson = Gson()
                val stats = gson.fromJson(json, PlayerStatsResponse::class.java)

                onSuccess(stats)
            }
        })
    }
    fun extractStats(stats: ChessStats?): String {
        if (stats == null || stats.record == null || stats.last == null) {
            return "No data"
        }

        val totalGames = stats.record.win + stats.record.loss + stats.record.draw
        val winRate = (stats.record.win * 100) / totalGames

        return "Rating: ${stats.last.rating}, Win rate: $winRate%"
    }

}