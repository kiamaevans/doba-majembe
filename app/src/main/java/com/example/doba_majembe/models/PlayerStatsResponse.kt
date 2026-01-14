package com.example.doba_majembe.models

data class PlayerStatsResponse(
    val chess_blitz: ChessStats?,
    val chess_rapid: ChessStats?,
    val chess_bullet: ChessStats?
)
