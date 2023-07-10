package pers.kwk.newspaper.client.ui.home.newspaperpage

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import pers.kwk.newspaper.R
import pers.kwk.newspaper.client.data.entity.NewspaperEntity
import pers.kwk.newspaper.client.util.toYuan

@Composable
fun NewspaperList(modifier: Modifier=Modifier, newspapers: List<NewspaperEntity>, onBuy: (NewspaperEntity) -> Unit) {
	LazyColumn(
		verticalArrangement = Arrangement.spacedBy(8.dp),
		contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
		modifier = modifier.background(colorScheme.background)
	) {
		items(newspapers) { newspaper ->
			// 显示单个报纸记录
			NewspaperItem(newspaper, onBuy)
		}
	}
}

@Composable
fun NewspaperItem(newspaper: NewspaperEntity, onBuy: (NewspaperEntity) -> Unit) {
	Card {
		Row(
			modifier = Modifier
				.fillMaxWidth()
				.height(220.dp)
		) {
			AsyncImage(
				model = newspaper.imageUrl,
				contentDescription = "报刊图片")
			Column(
				verticalArrangement = Arrangement.Top,
				modifier = Modifier
					.weight(1f)
					.fillMaxHeight()
					.padding(8.dp, 16.dp)
			) {
				Text(
					text = newspaper.name,
					style = typography.titleLarge,
					maxLines = 1,
					overflow = TextOverflow.Ellipsis,
					modifier = Modifier.padding(vertical = 16.dp),
				)
				Column(
				) {
					listOf(
						Pair("发刊周期", newspaper.period),
						Pair("年刊数", "${newspaper.annualIssueNumber} 期"),
						Pair("出版社", newspaper.publishingHouse),
					).forEach { (key, value) ->
						Row(
							verticalAlignment = Alignment.CenterVertically,
							modifier = Modifier
								.fillMaxWidth()
								.height(28.dp)
						) {
							Text(
								text = "$key:",
								maxLines = 1,
								overflow = TextOverflow.Clip,
								modifier = Modifier
									.width(80.dp)
							)
							Text(
								text = value,
								maxLines = 1,
								overflow = TextOverflow.Ellipsis,
								modifier = Modifier
							)
						}
					}
				}
				Spacer(modifier = Modifier.weight(1f))
				Row {
					Spacer(modifier = Modifier.weight(1f))
					Button(onClick = {
						onBuy(newspaper)
					}) {
						Text(text = "${newspaper.annualPrice.toYuan()} / 年")
					}
				}
			}
		}
	}
}


//@Preview
//@Composable
//fun NewspaperItemPreview() {
//	AppTheme {
//		Surface {
//			NewspaperItem(小朋友, {})
//		}
//	}
//}
//
//@Preview(name = "NewspaperList")
//@Preview(name = "NewspaperList(Dark)", uiMode = UI_MODE_NIGHT_YES)
//@Composable
//fun NewspaperListPreview() {
//	AppTheme {
//		Surface {
//			NewspaperList(Modifier, newspapers, {})
//		}
//	}
//}