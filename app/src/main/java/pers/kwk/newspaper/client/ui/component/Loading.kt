package pers.kwk.newspaper.client.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.WifiOff
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import pers.kwk.newspaper.R

@Composable
fun Loading() {
	Column(
		horizontalAlignment = Alignment.CenterHorizontally
	) {
		Icon(
			imageVector = Icons.Filled.WifiOff,
			modifier = Modifier
				.size(100.dp)
				.padding(bottom = 16.dp),
			tint = MaterialTheme.colorScheme.primary,
			contentDescription = null
		)
		Text(
			text = stringResource(R.string.please_check_network_connection),
			color = MaterialTheme.colorScheme.onBackground
		)
	}
}