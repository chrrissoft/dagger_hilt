package com.chrrissoft.daggerhilt.ui.theme

import androidx.compose.material3.*
import androidx.compose.material3.CardDefaults.cardColors
import androidx.compose.material3.InputChipDefaults.inputChipColors
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.TopAppBarDefaults.centerAlignedTopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@OptIn(ExperimentalMaterial3Api::class)
val centerAlignedTopAppBarColors
    @Composable get() = run {
        centerAlignedTopAppBarColors(
            containerColor = colorScheme.primaryContainer,
            navigationIconContentColor = colorScheme.primary,
            titleContentColor = colorScheme.primary,
            actionIconContentColor = colorScheme.primary,
        )
    }


@OptIn(ExperimentalMaterial3Api::class)
val inputChipColors
    @Composable get() = run {
        inputChipColors(
            containerColor = colorScheme.onPrimary,
            labelColor = colorScheme.primary,
            leadingIconColor = colorScheme.primary,
            trailingIconColor = colorScheme.primary,
            disabledContainerColor = colorScheme.primaryContainer.copy(.5f),
            disabledLabelColor = colorScheme.onPrimary,
            disabledLeadingIconColor = colorScheme.onPrimary,
            disabledTrailingIconColor = colorScheme.onPrimary,
            selectedContainerColor = colorScheme.primary,
            disabledSelectedContainerColor = colorScheme.onPrimaryContainer,
            selectedLabelColor = colorScheme.onPrimary,
            selectedLeadingIconColor = colorScheme.primary,
            selectedTrailingIconColor = colorScheme.error,
        )
    }


val cardColors
    @Composable get() = run {
        cardColors(
            containerColor = colorScheme.primaryContainer,
            contentColor = colorScheme.onPrimaryContainer,
        )
    }

@Composable
fun textFieldColors(
    focusedTextColor: Color = colorScheme.primary,
    unfocusedTextColor: Color = colorScheme.primary,
    disabledTextColor: Color = colorScheme.primary,
    focusedContainerColor: Color = colorScheme.primaryContainer,
    unfocusedContainerColor: Color = colorScheme.primaryContainer,
    disabledContainerColor: Color = colorScheme.primaryContainer,
    cursorColor: Color = colorScheme.primary,
    focusedIndicatorColor: Color = Color.Transparent,
    unfocusedIndicatorColor: Color = Color.Transparent,
    disabledIndicatorColor: Color = Color.Transparent,
    focusedTrailingIconColor: Color = colorScheme.primary,
    unfocusedTrailingIconColor: Color = colorScheme.primary,
    disabledTrailingIconColor: Color = colorScheme.primary,
    focusedLeadingIconColor: Color = colorScheme.primary,
    unfocusedLeadingIconColor: Color = colorScheme.primary,
    disabledLeadingIconColor: Color = colorScheme.primary,
    focusedLabelColor: Color = colorScheme.primary.copy(.6f),
    unfocusedLabelColor: Color = colorScheme.primary.copy(.6f),
    disabledLabelColor: Color = colorScheme.primary.copy(.6f),
): TextFieldColors {
    return TextFieldDefaults.colors(
        focusedTextColor = focusedTextColor,
        unfocusedTextColor = unfocusedTextColor,
        disabledTextColor = disabledTextColor,
        focusedContainerColor = focusedContainerColor,
        unfocusedContainerColor = unfocusedContainerColor,
        disabledContainerColor = disabledContainerColor,
        cursorColor = cursorColor,
        focusedIndicatorColor = focusedIndicatorColor,
        unfocusedIndicatorColor = unfocusedIndicatorColor,
        disabledIndicatorColor = disabledIndicatorColor,
        focusedTrailingIconColor = focusedTrailingIconColor,
        unfocusedTrailingIconColor = unfocusedTrailingIconColor,
        disabledTrailingIconColor = disabledTrailingIconColor,
        focusedLeadingIconColor = focusedLeadingIconColor,
        unfocusedLeadingIconColor = unfocusedLeadingIconColor,
        disabledLeadingIconColor = disabledLeadingIconColor,
        focusedLabelColor = focusedLabelColor,
        unfocusedLabelColor = unfocusedLabelColor,
        disabledLabelColor = disabledLabelColor,
    )
}
