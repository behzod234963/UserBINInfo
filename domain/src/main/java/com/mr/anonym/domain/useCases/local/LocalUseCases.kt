package com.mr.anonym.domain.useCases.local

data class LocalUseCases(
    val insertBIN:InsertBINUseCase,
    val getAllBIN:GetAllBINUseCase,
    val deleteBIN:DeleteBINUseCase,
    val clearBIN:ClearBINUseCase,
    val insertSearchHistory:InsertSearchHistoryUseCase,
    val getAllSearchHistory:GetAllHistoryUseCase,
    val clearSearchHistory:ClearSearchHistory
)
