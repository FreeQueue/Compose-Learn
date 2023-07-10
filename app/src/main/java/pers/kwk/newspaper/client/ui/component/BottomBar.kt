package pers.kwk.newspaper.client.ui.component

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import pers.kwk.newspaper.client.ui.home.HomePage

@Composable
fun BottomBar(
	items: Array<HomePage>, //item 集合
	selectedIndex: Int = 0, // 当前选中 item 的索引
	onBottomItemClick: (Int) -> Unit// item 点击事件
)
{
	NavigationBar {
		items.forEachIndexed { index, appPage ->
			NavigationBarItem(
				icon = {
					Icon(appPage.icon, appPage.label)
				},
				selected = index == selectedIndex,
				onClick = { onBottomItemClick(index) },
				colors = NavigationBarItemDefaults.colors()// 设置颜色
			)
		}
	}
}