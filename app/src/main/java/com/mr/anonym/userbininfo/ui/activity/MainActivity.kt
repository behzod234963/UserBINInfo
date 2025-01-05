package com.mr.anonym.userbininfo.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.mr.anonym.userbininfo.ui.navigation.NavGraph
import com.mr.anonym.userbininfo.ui.theme.UserBINInfoTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UserBINInfoTheme {
                NavGraph()
            }
        }
    }
}