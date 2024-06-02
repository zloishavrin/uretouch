package com.uretouch.domain.generations.interactor

import com.uretouch.domain.generations.model.Generation
import com.uretouch.domain.generations.repository.GenerationsRepository
import com.uretouch.domain.generations.util.ImageUtil

internal class DefaultGenerationsInteractor(
    private val repository: GenerationsRepository,
    private val imageUtil: ImageUtil,
) : GenerationsInteractor {
    override suspend fun getGenerationsHistory(): List<Generation> {
        return repository.getHistoryGenerations()
    }

    override suspend fun createGeneration(path: String, prompt: String): String {
        val image = imageUtil.getImage(path = path)
        return repository.createGeneration(image = image, prompt = prompt)
    }
}