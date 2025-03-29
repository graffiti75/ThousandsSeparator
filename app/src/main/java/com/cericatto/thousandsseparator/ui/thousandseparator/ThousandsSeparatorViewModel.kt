package com.cericatto.thousandsseparator.ui.thousandseparator

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ThousandsSeparatorViewModel : ViewModel() {

	private val _state = MutableStateFlow(ThousandsSeparatorState())
	val state: StateFlow<ThousandsSeparatorState> = _state.asStateFlow()

	fun onAction(action: ThousandsSeparatorAction) {
		when (action) {
			is ThousandsSeparatorAction.UpdateType -> updateType(action.type)
		}
	}

	private fun updateType(type: ThousandsSeparatorType) {
		_state.update { state ->
			state.copy(
				type = type,
			)
		}
	}
}