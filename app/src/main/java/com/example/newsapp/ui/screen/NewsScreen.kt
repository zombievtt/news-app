package com.example.newsapp.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.material3.Text
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Article
import androidx.compose.material.icons.filled.Article
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.newsapp.data.local.NewsEntity
import com.example.newsapp.navigation.Screen
import com.example.newsapp.viewmodel.NewsViewModel

@Composable
fun NewsScreen(
    navController: NavHostController,
    viewModel: NewsViewModel = hiltViewModel()
) {

    val uiState by viewModel.uiState.collectAsState()

    LazyColumn {
        items(uiState.news) { news ->

            NewsCard(
                news = news,
                onClick = {
                    navController.navigate(
                        Screen.NewsDetail.createRoute(news.id)
                    )
                }
            )
        }
    }
}

@Composable
fun NewsCard(
    news: NewsEntity,
    onClick: (news:NewsEntity) -> Unit
) {

    Spacer(modifier = Modifier.height(32.dp))

    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
            .clickable { onClick(news) },
        shape = RoundedCornerShape(16.dp)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.Top
        ) {

            Icon(
                imageVector = Icons.AutoMirrored.Filled.Article,
                contentDescription = "News",
                tint = Color(0xFF1E3A8A),
                modifier = Modifier
                    .size(40.dp)
                    .padding(top = 4.dp)
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {

                Text(
                    text = news.title,
                    style = MaterialTheme.typography.titleMedium,
                    color = Color(0xFF1E3A8A),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = news.body,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color(0xFF1E3A8A),
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(12.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Just now",
                        style = MaterialTheme.typography.labelSmall,
                        color = Color.Gray
                    )
                }
            }
        }
    }
}