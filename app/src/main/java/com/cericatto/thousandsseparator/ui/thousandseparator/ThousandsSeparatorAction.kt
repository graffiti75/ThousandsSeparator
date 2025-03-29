package com.cericatto.thousandsseparator.ui.thousandseparator

sealed interface ThousandsSeparatorAction {
	data class UpdateType(val type: ThousandsSeparatorType) : ThousandsSeparatorAction
}