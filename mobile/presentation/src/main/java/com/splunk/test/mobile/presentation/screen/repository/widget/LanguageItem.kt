package com.splunk.test.mobile.presentation.screen.repository.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.splunk.test.mobile.presentation.model.RepositoryUiModel

@Preview
@Composable
private fun LanguageItemPreview() {
    LanguageItem(
        language = RepositoryUiModel.LanguageUiModel(
            name = "Kotlin",
            color = 13666500,
        )
    )
}

@Composable
fun LanguageItem(
    modifier: Modifier = Modifier,
    language: RepositoryUiModel.LanguageUiModel,
) {
    val color = Color(language.color)
    Box(
        modifier = modifier
            .background(
                color = color.copy(alpha = 0.2f),
                shape = CircleShape,
            )
    ) {
        Text(
            modifier = Modifier.padding(vertical = 8.dp, horizontal = 12.dp),
            text = language.name,
            color = color,
            style = MaterialTheme.typography.labelMedium,
        )
    }
}