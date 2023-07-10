package pers.kwk.newspaper.client.ui.addorder

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import pers.kwk.newspaper.client.ui.GlobalViewModel
import pers.kwk.newspaper.client.ui.Route

@Composable
fun AddOrderScreen(
	navController: NavController,
	globalViewModel: GlobalViewModel,
	viewModel: AddOrderScreenViewModel = hiltViewModel()
) {
	if (!globalViewModel.loggedIn) {
		navController.navigate(Route.LOGIN)
	}
	ConstraintLayout(
		modifier = Modifier
			.fillMaxSize()
			.padding(start = 40.dp, end = 40.dp)
			.systemBarsPadding()
	) {
		val (back, username, newspaperInfo, addressInput, yearDropdown,addButton) = createRefs()
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
			text = viewModel.newspaper.name,
			maxLines = 1,
			overflow = TextOverflow.Clip,
			modifier = Modifier
				.width(80.dp)
				.height(30.dp)
				.constrainAs(username) {
					bottom.linkTo(newspaperInfo.top)
				}
		)

		Row(
			verticalAlignment = Alignment.CenterVertically,
			modifier = Modifier
				.fillMaxWidth()
				.height(28.dp)
				.constrainAs(username) {
					bottom.linkTo(newspaperInfo.top)
				}
		) {
			Text(
				text = "用户名：",
				maxLines = 1,
				overflow = TextOverflow.Clip,
				modifier = Modifier
					.width(80.dp)
			)
			Text(
				text = viewModel.user.username,
				maxLines = 1,
				overflow = TextOverflow.Ellipsis,
				modifier = Modifier
			)
		}

		Row(
			verticalAlignment = Alignment.CenterVertically,
			modifier = Modifier
				.fillMaxWidth()
				.height(28.dp)
				.constrainAs(newspaperInfo) {
					bottom.linkTo(yearDropdown.top,40.dp)
				}
		) {
			Text(
				text = "报刊ID：",
				maxLines = 1,
				overflow = TextOverflow.Clip,
				modifier = Modifier
					.width(80.dp)
			)
			Text(
				text = viewModel.newspaper.id,
				maxLines = 1,
				overflow = TextOverflow.Ellipsis,
				modifier = Modifier
			)
		}

		Row(
			verticalAlignment = Alignment.CenterVertically,
			modifier = Modifier
				.fillMaxWidth()
				.height(28.dp)
				.constrainAs(yearDropdown) {
					bottom.linkTo(addressInput.top,40.dp)
				}
		) {
			Text(
				text = "订阅年份：",
				maxLines = 1,
				overflow = TextOverflow.Clip,
				modifier = Modifier
					.width(80.dp)
			)
			var expanded by remember { mutableStateOf(false) }
			// 设置年份选择下拉框的约束
			DropdownMenu(
				expanded = false,
				onDismissRequest = { expanded = false },
			) {
				(1..5).forEach { number ->
					DropdownMenuItem(
						{
							Text(text = number.toString())
						},
						onClick = {
							viewModel.orderYear = number
							expanded = false
						}
					)
				}
			}
		}
		Column(modifier = Modifier
			.fillMaxWidth()
			.height(50.dp)
			.constrainAs(addressInput) {
				bottom.linkTo(addButton.top, 40.dp)
			}) {
			Text(
				text = "地址：",
				maxLines = 1,
				overflow = TextOverflow.Clip,
				modifier = Modifier
					.width(80.dp)
			)
			// 设置地址输入框的约束
			TextField(
				value = viewModel.address?:"",
				onValueChange = {
					viewModel.address = it
				},
				placeholder = {
					Text("请输入您的地址信息，或完善个人资料")
				},
				modifier = Modifier
					.fillMaxWidth()
					.height(200.dp)
					.padding(16.dp),
				singleLine = false,
				maxLines = 5,
			)
		}
		IconButton(
			onClick = {
				viewModel.addOrder(navController,globalViewModel)
			},
			enabled = viewModel.address!=null&& viewModel.address!!.isNotEmpty(),
			modifier = Modifier
				.clip(CircleShape)
				.background(MaterialTheme.colorScheme.primaryContainer)
				.size(75.dp)
				.padding(25.dp)
				.constrainAs(addButton) {
					bottom.linkTo(parent.bottom, 60.dp)
				}
		) {
			Icon(Icons.Filled.ArrowForward, null)
		}
	}
}

//@Preview
//@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
//@Composable
//fun AddOrderScreenPreview() {
//	AppTheme {
//		Surface {
//			AddOrderScreen(rememberNavController())
//		}
//	}
//}