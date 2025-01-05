package com.mr.anonym.userbininfo.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.mr.anonym.data.local.DataStoreInstance
import com.mr.anonym.userbininfo.presentation.viewModel.SearchHistoryViewModel
import com.mr.anonym.userbininfo.ui.items.HistoryItem
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchHistoryScreen(
    navController: NavController,
    viewModel:SearchHistoryViewModel = hiltViewModel()
) {

    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current

    val dataStoreInstance = DataStoreInstance(context)

    val histories = viewModel.searchHistories

    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(if (isSystemInDarkTheme()) Color.Black else Color.White)
            .padding(bottom = 80.dp)
    ){
        BackHandler (
            enabled = true
        ){
            coroutineScope.launch {
                dataStoreInstance.saveKey(-1)
            }
            navController.popBackStack()
        }
        TopAppBar(
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color.Red
            ),
            title = {
                Text(
                    text = "История поиска",
                    color = Color.White,
                    fontSize = 22.sp
                )
            },
            navigationIcon = {
                IconButton(
                    onClick = {
                        coroutineScope.launch {
                            dataStoreInstance.saveKey(-1)
                        }
                        navController.popBackStack()
                    }
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "icon btn back",
                        tint = Color.Black
                    )
                }
            }
        )
        TextButton(
            onClick = {
                viewModel.clearHistory(histories.value)
            }
        ) {
            Text(
                text = "Очистить историю поиска",
                color = Color.Blue,
                fontSize = 18.sp
            )
        }
        Spacer(Modifier.height(5.dp))
        LazyColumn (
            modifier = Modifier
                .fillMaxSize()
        ){
            items(histories.value.distinctBy { it.history }){entity->
                HistoryItem(
                    entity = entity
                ) {
                    coroutineScope.launch {
                        dataStoreInstance.saveKey(1)
                        dataStoreInstance.saveHistory(history = entity.history)
                        navController.popBackStack()
                    }
                }
            }
        }
    }
}