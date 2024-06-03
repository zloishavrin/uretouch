package com.uretouch.data.generations.util

import com.uretouch.common.core.database.GenerationModeEntity
import com.uretouch.domain.generations.model.GenerationMode

internal object GenerationMappers {
    fun GenerationModeEntity.toDomain(): GenerationMode {
        return GenerationMode(id = id, name = name, description = description)
    }

    fun GenerationMode.toEntity(): GenerationModeEntity {
        return GenerationModeEntity(id = id, name = name, description = description)
    }
}