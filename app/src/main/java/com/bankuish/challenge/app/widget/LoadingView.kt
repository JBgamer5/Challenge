package com.bankuish.challenge.app.widget

import android.content.res.Configuration
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.bankuish.challenge.app.theme.*

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO, showSystemUi = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showSystemUi = true)
@Composable
private fun Preview() {
    LoadingView(true)
}

@Composable
fun LoadingView(isLoading: Boolean) {
    if (isLoading) {
        Dialog(onDismissRequest = {}) {
            Surface(
                color = if (isSystemInDarkTheme()) onSecondary else onPrimary,
                shape = RoundedCornerShape(15.dp),
                modifier = Modifier
                    .size(100.dp)
            ) {
                CircularProgressIndicator(
                    color = greenHover,
                    modifier = Modifier
                        .padding(20.dp)
                )
            }
        }
    }
}