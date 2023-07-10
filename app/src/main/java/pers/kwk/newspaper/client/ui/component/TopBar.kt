package pers.kwk.newspaper.client.ui.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(title: String, onProfileClick: () -> Unit) {//标题,左侧导航按钮点击事件
	CenterAlignedTopAppBar(
		title = { Text(text = title) },
		navigationIcon = {
			IconButton(onClick = onProfileClick) {
				Icon(imageVector = Icons.Filled.Person, contentDescription = "")
			}
		}
	)
}