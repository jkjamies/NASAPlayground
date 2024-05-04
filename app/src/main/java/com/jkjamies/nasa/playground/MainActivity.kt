package com.jkjamies.nasa.playground

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.jkjamies.nasa.playground.presentation.NasaPlaygroundNavHost
import com.jkjamies.nasa.playground.ui.theme.NASAPlaygroundTheme
import org.koin.compose.KoinContext

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            KoinContext {
                NASAPlaygroundTheme {
                    NasaPlaygroundNavHost()
                }
            }
        }
    }
}
