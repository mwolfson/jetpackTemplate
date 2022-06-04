package com.material.demo.ui.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.DragHandle
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.material.demo.R
import com.material.demo.data.ColorItem
import com.material.demo.ui.theme.ComposeTemplateTheme
import com.material.demo.ui.theme.card_corner_radius_lg
import com.material.demo.ui.theme.margin_double
import com.material.demo.ui.theme.margin_standard

@Composable
fun DetailBody(
    colorItem: ColorItem,
) {
    ComposeTemplateTheme {
        Surface(
            color = MaterialTheme.colorScheme.background,
            shape = RoundedCornerShape(card_corner_radius_lg)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = margin_double, end = margin_double)
            ) {
                Icon(
                    Icons.Outlined.DragHandle,
                    contentDescription = "Drag Sheet",
                )
                Text(
                    text = stringResource(R.string.detail_screen),
                    style = MaterialTheme.typography.displayLarge,
                    modifier = Modifier.padding(top = margin_standard, bottom = margin_double)
                )
                Row {
                    Image(
                        painter = rememberAsyncImagePainter(
                            model = colorItem.iconUrlLg
                        ),
                        contentDescription = colorItem.name,
                        contentScale = ContentScale.FillHeight,
                        modifier = Modifier.height(256.dp)
                    )
                    Text(
                        text = colorItem.name,
                        style = MaterialTheme.typography.displayMedium,
                        modifier = Modifier.padding(all = margin_standard)
                    )
                }
                // This is to give the bottom sheet some room to scroll to fill height
                Spacer(Modifier.height(512.dp))
            }
        }
    }
}
