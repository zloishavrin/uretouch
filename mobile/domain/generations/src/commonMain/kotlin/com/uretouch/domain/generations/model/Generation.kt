package com.uretouch.domain.generations.model

data class Generation(
    val id: String,
    val status: GenerationStatusType,
    val generationUrls: List<String>,
    val originalUrl: String,
    val prompt: String,
)