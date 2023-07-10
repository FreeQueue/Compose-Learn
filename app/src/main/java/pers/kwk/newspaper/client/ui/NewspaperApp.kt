package pers.kwk.newspaper.client.ui

import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

object Route {
	const val LOGIN = "login"
	const val REGISTER = "register"
	const val HOME = "home"
	const val ADD_ORDER = "add_order"
}

@Composable
fun NewspaperApp(globalViewModel: GlobalViewModel) {

//	val navController = rememberNavController()
//	NavHost(
//		navController = navController,
//		startDestination = Route.LOGIN
//	) {
//		composable(Route.LOGIN) {
//			LoginScreen(navController,globalViewModel)
//		}
//		composable(Route.REGISTER) {
//			RegisterScreen(navController,globalViewModel)
//		}
//		composable(Route.HOME) {
//			HomeScreen(navController, globalViewModel)
//		}
//		composable(Route.ADD_ORDER) {
//			AddOrderScreen(navController,globalViewModel)
//		}
//	}

	if (globalViewModel.snackBarVisible) {
		Box(
			modifier = Modifier
				.fillMaxSize()
				.wrapContentSize(Alignment.TopCenter)
				.padding(16.dp)
				.alpha(animateFloatAsState(targetValue = 1f, animationSpec = TweenSpec(300)).value)
		) {
			Snackbar(
				modifier = Modifier.align(Alignment.TopCenter),
				content = { Text(globalViewModel.snackBarText) },
			)
		}
	}
}