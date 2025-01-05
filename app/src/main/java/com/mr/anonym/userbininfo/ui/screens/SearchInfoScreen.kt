package com.mr.anonym.userbininfo.ui.screens

import android.provider.ContactsContract.Data
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.text.isDigitsOnly
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.mr.anonym.data.local.DataStoreInstance
import com.mr.anonym.domain.model.SearchHistoryEntity
import com.mr.anonym.userbininfo.presentation.viewModel.SearchInfoViewModel
import com.mr.anonym.userbininfo.ui.items.BinDataItem
import com.mr.anonym.userbininfo.ui.navigation.Screens

@Composable
fun SearchInfoScreen(
    navController: NavController,
    viewModel: SearchInfoViewModel = hiltViewModel()
) {

    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val dataStoreInstance = DataStoreInstance(context)

    val key = dataStoreInstance.getKey().collectAsState(-1)
    val history = dataStoreInstance.getHistory().collectAsState("")

    val searchFieldText = remember { mutableStateOf("") }

    val keyboardController = LocalSoftwareKeyboardController.current
    val keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search)

    val binInfos = viewModel.localBinInfos

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 30.dp, end = 10.dp, start = 10.dp)
            .background(if (isSystemInDarkTheme()) Color.Black else Color.White)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(70.dp)
                .padding(top = 15.dp)
        ) {
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(0.75f),
                value = if(key.value == -1) searchFieldText.value else history.value,
                onValueChange = { searchFieldText.value = it },
                singleLine = true,
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = if (isSystemInDarkTheme()) Color.Black else Color.White,
                    unfocusedContainerColor = if (isSystemInDarkTheme()) Color.Black else Color.White,
                ),
                textStyle = TextStyle(
                    fontSize = 18.sp,
                    color = if (isSystemInDarkTheme()) Color.White else Color.Black
                ),
                placeholder = {
                    Text(
                        text = "Please enter your BIN",
                        color = if (isSystemInDarkTheme()) Color.DarkGray else Color.LightGray,
                        fontSize = 16.sp
                    )
                },
                keyboardActions = KeyboardActions {
                    if (searchFieldText.value.isDigitsOnly() && searchFieldText.value.length == 8){
                        viewModel.getByBin(searchFieldText.value,searchFieldText.value)
                        viewModel.insertSearchHistory(SearchHistoryEntity(
                            history = searchFieldText.value
                        ))
                    }
                    keyboardController?.hide()
                },
                keyboardOptions = keyboardOptions,
            )
            Card(
                shape = RoundedCornerShape(topEnd = 10.dp, bottomEnd = 10.dp)
            ) {
                IconButton(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Red),
                    onClick = {
                        if (searchFieldText.value.isDigitsOnly() && searchFieldText.value.length == 8){
                            viewModel.getByBin(searchFieldText.value,searchFieldText.value)
                            viewModel.insertSearchHistory(SearchHistoryEntity(
                                history = searchFieldText.value
                            ))
                        }
                    },
                ) {
                    Icon(
                        modifier = Modifier
                            .size(40.dp),
                        imageVector = Icons.Default.Search,
                        contentDescription = "button search",
                        tint = Color.White
                    )
                }
            }
        }
        Spacer(Modifier.padding(vertical = 5.dp))
        HorizontalDivider(
            thickness = 1.dp,
            color = if (isSystemInDarkTheme()) Color.White else Color.Black
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            TextButton(
                onClick = {
                    if(binInfos.value.isNotEmpty()){
                        viewModel.clearLocalBins(binInfos.value)
                    }
                }
            ) {
                Text(
                    text = "Очистить всё",
                    color = Color.Blue,
                    fontSize = 18.sp
                )
            }
            TextButton(
                onClick = {
                    navController.navigate(Screens.SearchHistoryScreen.route)
                }
            ) {
                Text(
                    text = "История поиска",
                    color = Color.Blue,
                    fontSize = 18.sp
                )
            }
        }
        Spacer(Modifier.height(5.dp))
        LazyColumn {
            viewModel.getLocalBin()
            items(binInfos.value.distinctBy { it.binNumber }){entity->
                BinDataItem(
                    entity = entity
                )
            }
        }
    }
}