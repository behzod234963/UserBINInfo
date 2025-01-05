package com.mr.anonym.userbininfo.presentation.utils

import com.mr.anonym.domain.model.BINInfoEntity
import com.mr.anonym.domain.model.SearchHistoryEntity

data class BinState(
    val localBinInfos:List<BINInfoEntity> = emptyList(),
    val histories:List<SearchHistoryEntity> = emptyList()
)