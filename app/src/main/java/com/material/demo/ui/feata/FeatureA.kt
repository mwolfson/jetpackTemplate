package com.material.demo.ui.feata

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import com.material.demo.R
import com.material.demo.ui.theme.margin_half
import com.material.demo.ui.theme.margin_standard

@Composable
fun FeatABody(
    onLaunchClicked: (String) -> Unit,
) {
    Surface(color = MaterialTheme.colorScheme.background) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            var text by remember { mutableStateOf("") }
            val focusManager = LocalFocusManager.current

            Spacer(Modifier.height(margin_standard))
            Text(
                text = stringResource(R.string.enter_a_color),
                style = MaterialTheme.typography.displayLarge,
                modifier = Modifier.padding(top = margin_half)
            )
            OutlinedTextField(
                value = text,
                onValueChange = { text = it },
                modifier = Modifier
                    .padding(margin_standard)
                    .fillMaxWidth()
            )
            Button(
                onClick = {
                    onLaunchClicked(text)
                    text = ""
                    focusManager.clearFocus()
                },
                modifier = Modifier.padding(top = margin_half)
            ) {
                Text(
                    stringResource(R.string.launch).uppercase(),
                    style = MaterialTheme.typography.labelMedium
                )
            }
        }
    }
}