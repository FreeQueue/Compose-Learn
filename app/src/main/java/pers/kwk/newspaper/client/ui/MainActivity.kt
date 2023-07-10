package pers.kwk.newspaper.client.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import pers.kwk.newspaper.client.ui.theme.AppTheme

class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContent {
			AppTheme {
				NewspaperApp( viewModel())
			}
		}
	}
}


//@Preview(showBackground = true)
//@Composable
//fun AppPreview() {
//	AppTheme {
//		NewspaperApp()
//	}
//}