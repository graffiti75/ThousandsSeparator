package com.cericatto.thousandsseparator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.cericatto.thousandsseparator.ui.theme.ThousandsSeparatorTheme
import com.cericatto.thousandsseparator.ui.thousandseparator.ThousandsSeparatorRoot

class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		enableEdgeToEdge()
		setContent {
			ThousandsSeparatorTheme {
				Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
					ThousandsSeparatorRoot(
						modifier = Modifier.padding(innerPadding)
					)
				}
			}
		}
	}
}