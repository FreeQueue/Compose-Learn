package pers.kwk.newspaper.client.ui.home.orderpage

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import pers.kwk.newspaper.client.data.entity.OrderEntity

@Composable
fun OrderItem(order: OrderEntity) {
	Card(
		modifier = Modifier
			.padding(16.dp)
			.fillMaxWidth()
	) {
		Column {
			Text(text = order.id.toString())
			Text(text = order.userId.toString())
			Text(text = order.newspaperId)
			Text(text = order.address)
			Text(text = order.date.toString())
			Text(text = order.orderYear.toString())
		}
	}
}

@Composable
fun OrderList(orders: List<OrderEntity>) {
	LazyColumn {
		items(orders) { order ->
			// 显示单个报纸记录
			OrderItem(order)
		}
	}
}
