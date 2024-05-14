package com.example.weatherforecast.widget

import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

fun mySnackBar(
    scope: CoroutineScope,
    snackBarHostState: SnackbarHostState,
    message: String,
    actionLabel: String,
    onAction: () -> Unit
) {
    scope.launch {
        snackBarHostState.currentSnackbarData?.dismiss()
        val snackBarResult = snackBarHostState.showSnackbar(
            message = message,
            actionLabel = actionLabel,
            duration = SnackbarDuration.Short
        )

        when (snackBarResult) {
            SnackbarResult.Dismissed -> {

            }
            SnackbarResult.ActionPerformed -> {
                onAction()
            }
        }
    }
}