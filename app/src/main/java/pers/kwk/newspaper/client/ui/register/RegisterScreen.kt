package pers.kwk.newspaper.client.ui.register

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import pers.kwk.newspaper.R
import pers.kwk.newspaper.client.ui.GlobalViewModel
import pers.kwk.newspaper.client.ui.Route

@Composable
fun RegisterScreen(
	navController: NavController,
	globalViewModel: GlobalViewModel,
	viewModel: RegisterViewModel = hiltViewModel(),
) {
	var usernameText by rememberSaveable { mutableStateOf("") }
	var passwordText by rememberSaveable { mutableStateOf("") }
	var rePasswordText by rememberSaveable { mutableStateOf("") }

	ConstraintLayout(
		modifier = Modifier
			.fillMaxSize()
			.padding(start = 40.dp, end = 40.dp)
			.systemBarsPadding()
	) {
		val (back, welcome, username, username_error, password, password_error, re_password, re_password_error, register, sign_up, to_sign_in) = createRefs()
		val registerTopBarrier = createTopBarrier(register)
		val registerBottomBarrier = createBottomBarrier(register)
		IconButton(
			onClick = { navController.navigateUp() },
			modifier = Modifier
				.size(30.dp)
				.constrainAs(back) {
					top.linkTo(parent.top, margin = 15.dp) //top约束
				}
		) {
			Icon(imageVector = Icons.Filled.ArrowBackIos, contentDescription = null)
		}
		Text(
			text = "进入${stringResource(R.string.app_name)}的世界",
			style = MaterialTheme.typography.headlineLarge,
			modifier = Modifier
				.constrainAs(welcome) {
					top.linkTo(back.bottom, 60.dp)
				}
		)

		TextField(
			value = usernameText,
			onValueChange = {
				usernameText = it
			},
			placeholder = {
				Text("请输入用户名")
			},
			modifier = Modifier
				.fillMaxWidth()
				.height(50.dp)
				.constrainAs(username) {
					bottom.linkTo(password.top, 40.dp)
				}
		)
		Text(
			text = viewModel.usernameError,
			color = MaterialTheme.colorScheme.error,
			modifier = Modifier
				.padding(start = 10.dp)
				.constrainAs(username_error) {
					top.linkTo(username.bottom)
					start.linkTo(username.start)
				}
		)
		TextField(
			value = passwordText,
			onValueChange = {
				passwordText = it
			},
			placeholder = {
				Text("请输入用户密码")
			},
			visualTransformation = PasswordVisualTransformation(),
			keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
			modifier = Modifier
				.fillMaxWidth()
				.height(50.dp)
				.constrainAs(password) {
					bottom.linkTo(re_password.top, 40.dp)
				}
		)
		Text(
			text = viewModel.passwordError,
			color = MaterialTheme.colorScheme.error,
			modifier = Modifier
				.padding(start = 10.dp)
				.constrainAs(password_error) {
					top.linkTo(password.bottom)
					start.linkTo(password.start)
				}
		)
		TextField(
			value = rePasswordText,
			onValueChange = {
				rePasswordText = it
			},
			placeholder = {
				Text("请重复用户密码")
			},
			visualTransformation = PasswordVisualTransformation(),
			keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
			modifier = Modifier
				.fillMaxWidth()
				.height(50.dp)
				.constrainAs(re_password) {
					bottom.linkTo(register.top, 40.dp)
				}
		)
		Text(
			text = viewModel.rePasswordError,
			color = MaterialTheme.colorScheme.error,
			modifier = Modifier
				.padding(start = 10.dp)
				.constrainAs(re_password_error) {
					top.linkTo(re_password.bottom)
					start.linkTo(re_password.start)
				}
		)
		Text(
			text = "注册",
			fontSize = 25.sp,
			modifier = Modifier
				.constrainAs(sign_up) {
					top.linkTo(registerTopBarrier)
					bottom.linkTo(registerBottomBarrier)
				}
		)
		IconButton(
			onClick = {
				viewModel.register(globalViewModel, navController, usernameText, passwordText, rePasswordText)
			},
			enabled = usernameText.trim().isNotEmpty()
					&& passwordText.trim().isNotEmpty()
					&& rePasswordText.trim().isNotEmpty(),
			modifier = Modifier
				.clip(CircleShape)
				.background(MaterialTheme.colorScheme.primaryContainer)
				.size(75.dp)
				.padding(25.dp)
				.constrainAs(register) {
					end.linkTo(parent.end)
					bottom.linkTo(to_sign_in.top, 60.dp)
				}
		) {
			Icon(Icons.Filled.ArrowForward, null)
		}
		Text(
			text = "已有账号？返回登录",
			textDecoration = TextDecoration.Underline,
			fontSize = 16.sp,
			modifier = Modifier
				.constrainAs(to_sign_in) {
					bottom.linkTo(parent.bottom, 40.dp)
				}
				.clickable {
					navController.navigate(Route.LOGIN)
				}
		)
	}
}

//@Preview
//@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
//@Composable
//fun RegisterScreenPreview() {
//	AppTheme {
//		Surface {
//			RegisterScreen(rememberNavController())
//		}
//	}
//}