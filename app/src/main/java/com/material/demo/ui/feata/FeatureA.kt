package com.material.demo.ui.feata

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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