package com.allybros.jetpack_compose_games_app.entity.list

data class GameListResponse(
    val count: Int? = null,
    val description: String? = null,
    val filters: Filters? = null,
    val next: String? = null,
    val nofollow: Boolean? = null,
    val nofollow_collections: List<String>? = null,
    val noindex: Boolean? = null,
    val previous: String? = null,
    val results: ArrayList<Result>? = null,
    val seo_description: String? = null,
    val seo_h1: String? = null,
    val seo_keywords: String? = null,
    val seo_title: String? = null
)