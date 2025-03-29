package com.cericatto.thousandsseparator.ui.utils

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.cericatto.thousandsseparator.ui.thousandseparator.ThousandsSeparatorType

fun paddingStartByType(
	type: ThousandsSeparatorType,
	outer: Dp,
	inner: Dp,
	width: Dp
): Dp {
	val paddingStart = width / 3
	return when (type) {
		ThousandsSeparatorType.POINT -> 0.dp
		ThousandsSeparatorType.COMMA -> paddingStart - (inner * 2)
		ThousandsSeparatorType.SPACE -> paddingStart * 2 - (outer * 2)
	}
}

fun thousandsSeparator(
	text: String = "1000",
	type: ThousandsSeparatorType = ThousandsSeparatorType.POINT
) = when (type) {
	ThousandsSeparatorType.POINT -> {
		text.replace(Regex("(?<=\\d)(?=\\d{3})"), ".")
	}
	ThousandsSeparatorType.COMMA -> {
		text.replace(Regex("(?<=\\d)(?=\\d{3})"), ",")
	}
	ThousandsSeparatorType.SPACE -> {
		text.replace(Regex("(?<=\\d)(?=\\d{3})"), " ")
	}
}