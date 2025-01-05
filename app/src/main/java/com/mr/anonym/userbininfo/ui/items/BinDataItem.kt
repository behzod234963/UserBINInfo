package com.mr.anonym.userbininfo.ui.items

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mr.anonym.domain.model.BINInfoEntity

@Composable
fun BinDataItem(
    entity: BINInfoEntity
) {

    val isCardClicked = remember { mutableStateOf(false) }
    val isCountryClicked = remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(if (isCardClicked.value) 350.dp else 60.dp)
            .padding(vertical = 5.dp),
        onClick = { isCardClicked.value = !isCardClicked.value },
        colors = CardDefaults.cardColors(
            contentColor = Color.Red,
            containerColor = Color.Red
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)
        ) {
            Text(
                text = entity.binNumber,
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(Modifier.height(10.dp))
            HorizontalDivider(thickness = 1.dp, color = Color.Black)
            Spacer(Modifier.height(10.dp))
            Text(
                text = "Схема: ${entity.schema}",
                color = Color.White,
                fontSize = 18.sp,
            )
            Text(
                text = "Тип: ${entity.type}",
                color = Color.White,
                fontSize = 18.sp,
            )
            Text(
                text = "Бренд: ${entity.brand}",
                color = Color.White,
                fontSize = 18.sp,
            )
            Text(
                modifier = Modifier
                    .clickable { isCountryClicked.value = !isCountryClicked.value },
                text = "Страна: ${entity.countryName}",
                color = Color.Blue,
                fontSize = 18.sp,
            )
            if (isCountryClicked.value) {
                Text(
                    modifier = Modifier
                        .padding(start = 15.dp),
                    text = "Числовой: ${entity.numeric}",
                    color = Color.White,
                    fontSize = 18.sp,
                )
                Text(
                    modifier = Modifier
                        .padding(start = 15.dp),
                    text = "alpha2: ${entity.alpha2}",
                    color = Color.White,
                    fontSize = 18.sp,
                )
                Text(
                    modifier = Modifier
                        .padding(start = 15.dp),
                    text = "Эмоджи: ${entity.emoji}",
                    color = Color.White,
                    fontSize = 18.sp,
                )
                Text(
                    modifier = Modifier
                        .padding(start = 15.dp),
                    text = "Валюта: ${entity.currency}",
                    color = Color.White,
                    fontSize = 18.sp,
                )
//            Latitude
                Text(
                    modifier = Modifier
                        .padding(start = 15.dp),
                    text = "Широта: ${entity.latitude}",
                    color = Color.White,
                    fontSize = 18.sp,
                )
                Text(
                    modifier = Modifier
                        .padding(start = 15.dp),
                    text = "Долгота: ${entity.longitude}",
                    color = Color.White,
                    fontSize = 18.sp,
                )
            }
            Text(
                text = "Банк: ${entity.bankName}",
                color = Color.White,
                fontSize = 18.sp,
            )
        }
    }
}