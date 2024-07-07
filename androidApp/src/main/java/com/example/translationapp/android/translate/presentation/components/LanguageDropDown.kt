package com.example.translationapp.android.translate.presentation.components

import androidx.compose.foundation.clickable

import androidx.compose.material.DropdownMenu
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.translationapp.core.presentation.Colors.LightBlue
import com.example.translationapp.core.presentation.UiLanguage

@Composable
fun LanguageDropDown(
    language: UiLanguage,
    isOpen: Boolean,
    onClick: () -> Unit,
    onDismiss: () -> Unit,
    onSelectedLanguage: (UiLanguage) -> Unit,
    modifier: Modifier = Modifier
){
    Box (modifier = modifier){
        DropdownMenu(
            expanded = isOpen,
            onDismissRequest = onDismiss
        ) {
            UiLanguage.allLanguages.forEach { language ->
                LanguageDropDownItem(
                    language = language,
                    onClick = {
                        onSelectedLanguage(language)
                    },
                    modifier = Modifier.fillMaxWidth()
                )

            }
        }
        Row (
            modifier = Modifier
                .clickable(onClick = onClick)
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            AsyncImage(
                model = language.drawableRes,
                contentDescription = language.language.langName,
                modifier = Modifier.size(30.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = language.language.langName,
                color = Color(LightBlue)
            )
            Icon(
                imageVector = if(isOpen) Icons.Default.ArrowDropUp else Icons.Default.ArrowDropDown,
                contentDescription = if(isOpen){
                    stringResource(com.example.translationapp.android.R.string.close)
                }else{
                    stringResource(com.example.translationapp.android.R.string.open)
                },

                tint = Color(LightBlue),
                modifier = Modifier.size(30.dp)
            )
        }
    }
}