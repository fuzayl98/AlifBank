package com.fuzaylofficial.alifbank.ui.main.repository.remote

import com.fuzaylofficial.alifbank.models.Guides
import retrofit2.http.GET

interface GuidesService {
    @GET("upcomingGuides")
    suspend fun guides(): Guides
}