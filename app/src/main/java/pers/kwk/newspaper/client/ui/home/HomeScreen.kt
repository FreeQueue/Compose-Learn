package pers.kwk.newspaper.client.ui.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import pers.kwk.newspaper.client.ui.GlobalViewModel
import pers.kwk.newspaper.client.ui.Route
import pers.kwk.newspaper.client.ui.component.BottomBar
import pers.kwk.newspaper.client.ui.component.TopBar
import pers.kwk.newspaper.client.ui.home.newspaperpage.NewspaperPage
import pers.kwk.newspaper.client.ui.home.orderpage.OrderPage
import pers.kwk.newspaper.client.ui.home.userpage.UserPage

@Composable
fun HomeScreen(navController:NavController,globalViewModel: GlobalViewModel) {
	if (!globalViewModel.loggedIn) {
		navController.navigate(Route.LOGIN)
	}
//Scaffold 根据选中的状态显示不同的内容
	var selectedItemIndex by remember { mutableStateOf(0) }

	val bottomBarItems = remember { HomePage.values() }

	// A surface container using the 'background' color from the theme
	Surface(
		modifier = Modifier.fillMaxSize(),
		color = MaterialTheme.colorScheme.background
	) {
		Scaffold(
			topBar = {
				TopBar(title = bottomBarItems[selectedItemIndex].label) {
					selectedItemIndex = 0
				}
			},
			bottomBar = {
				BottomBar(bottomBarItems, selectedItemIndex) {
					//点击底部 item 时更新 selectedItemIndex
					selectedItemIndex = it
				}
			},
			containerColor = Color.LightGray
		) { paddingValues ->
			Box(
				modifier = Modifier
					.padding(paddingValues)
					.fillMaxSize()
			) {
				//根据 selectedItemIndex 在 content 中显示不同的页面
				when (selectedItemIndex) {
					0 -> NewspaperPage(navController)
					1 -> OrderPage(navController)
					2 -> UserPage()
				}
			}
		}
	}
}