package com.uretouch.domain.generations.interactor

import com.uretouch.domain.generations.model.Generation
import com.uretouch.domain.generations.model.GenerationMode
import com.uretouch.domain.generations.repository.GenerationsRepository
import com.uretouch.domain.generations.util.ImageUtil

internal class DefaultGenerationsInteractor(
    private val repository: GenerationsRepository,
    private val imageUtil: ImageUtil,
) : GenerationsInteractor {
    override suspend fun getGenerationsHistory(): List<Generation> {
        return repository.getHistoryGenerations()
    }

    override suspend fun getGeneration(id: String): Generation {
        return repository.getGeneration(id = id)
    }

    override suspend fun createGeneration(path: String, prompt: String, modeId: String?): String {
        val image = imageUtil.getImage(path = path)
        return repository.createGeneration(image = image, prompt = prompt, modeId = modeId)
    }

    override suspend fun getGenerationModes(): List<GenerationMode> {
        val remoteGenerationModes = runCatching { repository.getGenerationModes() }.getOrNull()
        return if (remoteGenerationModes != null) {
            repository.saveGenerationModes(remoteGenerationModes)
            remoteGenerationModes
        } else {
            repository.getGenerationModes()
        }
    }

    override suspend fun saveGenerationIntoDevice(generationUrl: String, prompt: String) {
        imageUtil.saveGeneration(url = generationUrl, prompt = prompt)
    }
}