package pers.kwk.newspaper.client.ui.home

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Newspaper
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector

enum class HomePage(
	val label: String,
	val icon: ImageVector
) {
	Newspapers("报刊查询", Icons.Filled.Newspaper),
	YourOrders("你的订单", Icons.Filled.ShoppingCart),
	Test("test", Icons.Filled.AccountCircle),
}