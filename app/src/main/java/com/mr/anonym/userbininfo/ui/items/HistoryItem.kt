package com.mr.anonym.userbininfo.ui.items

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mr.anonym.domain.model.BINInfoEntity
import com.mr.anonym.domain.model.SearchHistoryEntity

@Composable
fun HistoryItem(
    entity: SearchHistoryEntity,
    onHistoryClick:()->Unit
) {
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .clickable { onHistoryClick() }
            .background(if (isSystemInDarkTheme())Color.Black else Color.White)
            .padding(vertical = 5.dp)
            .padding(start = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ){
        Text(
            text = entity.history,
            color = if (isSystemInDarkTheme()) Color.White else Color.Black,
            fontSize = 18.sp
        )
    }
}