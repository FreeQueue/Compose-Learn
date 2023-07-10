package pers.kwk.newspaper.client.ui.home.newspaperpage

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import pers.kwk.newspaper.client.ui.Route
import pers.kwk.newspaper.client.ui.component.NoNetwork

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewspaperPage(navController: NavController,viewModel: NewspaperPageViewModel = hiltViewModel()) {
	var searchInput by rememberSaveable { mutableStateOf("") }
	var active by rememberSaveable { mutableStateOf(false) }

	Column(
		modifier = Modifier.fillMaxSize(),
		verticalArrangement = Arrangement.Center
	) {
		SearchBar(
			modifier = Modifier.fillMaxWidth(),
			query = searchInput,
			onQueryChange = {
				searchInput = it
				viewModel.searchIds(it)
			},
			onSearch = {
				active = false
				viewModel.search(it)
			},
			active = active,
			onActiveChange = { active = it },
			placeholder = { Text("搜索") },
			leadingIcon = { Icon(Icons.Filled.Search, null) },
			trailingIcon = {
				IconButton(onClick = { searchInput = "" }) {
					Icon(Icons.Filled.Clear, null)
				}
			},
		) {
			DropdownMenu(
				expanded = active,
				modifier = Modifier.fillMaxWidth(),
				onDismissRequest = {},
			) {
				viewModel.tmpSearchIdsFlow.collectAsState().value.forEach {
					DropdownMenuItem(
						onClick = {
							active = false
							searchInput = it
							viewModel.search(searchInput)
						},
						text = { Text(text = it) }
					)
				}
			}
		}
		when (viewModel.status) {
			ViewModelStatus.Default -> NewspaperList(
				Modifier.weight(1f),
				viewModel.newspapersFlow.collectAsState().value,
			) {
				navController.navigate(Route.ADD_ORDER)
			}

			ViewModelStatus.Loading -> CircularProgressIndicator()
			ViewModelStatus.Error -> NoNetwork()
		}
	}
}

