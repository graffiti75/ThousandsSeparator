package com.cericatto.thousandsseparator.ui.thousandseparator

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.cericatto.thousandsseparator.ui.theme.pinkBackground
import com.cericatto.thousandsseparator.ui.utils.paddingStartByType
import com.cericatto.thousandsseparator.ui.utils.thousandsSeparator

enum class ThousandsSeparatorType {
	POINT,
	COMMA,
	SPACE
}

@Composable
fun ThousandsSeparatorRoot(
	modifier: Modifier = Modifier
) {
	val viewModel: ThousandsSeparatorViewModel = viewModel()
	val state by viewModel.state.collectAsStateWithLifecycle()
	ThousandsSeparator(
		modifier = modifier,
		onAction = viewModel::onAction,
		state = state
	)
}

@Composable
fun ThousandsSeparator(
	modifier: Modifier = Modifier,
	onAction: (ThousandsSeparatorAction) -> Unit,
	state: ThousandsSeparatorState
) {
	val configuration = LocalConfiguration.current
	val deviceWidth: Dp = configuration.screenWidthDp.dp
	val outerHorizontalPadding = 10.dp
	val innerHorizontalPadding = 5.dp

	val paddingStart by animateDpAsState(
		targetValue = paddingStartByType(
			width = deviceWidth,
			outer = outerHorizontalPadding,
			inner = innerHorizontalPadding,
			type = state.type
		),
		animationSpec = tween(
			durationMillis = 600,
			easing = FastOutSlowInEasing
		),
		label = "Error Padding Start Animation"
	)
	Column(
		horizontalAlignment = Alignment.Start,
		verticalArrangement = Arrangement.Top,
		modifier = modifier
			.padding(top = 30.dp)
			.fillMaxSize()
			.background(Color.White)
			.padding(outerHorizontalPadding)
	) {
		Text(
			text = "Thousands separator",
			style = TextStyle(
				fontSize = 20.sp
			)
		)
		var boxHeight by remember { mutableStateOf(0.dp) }
		val density = LocalDensity.current
		Box(
			contentAlignment = Alignment.TopStart,
			modifier = Modifier
				.padding(vertical = 20.dp)
				.fillMaxWidth()
				.background(
					color = pinkBackground,
					shape = RoundedCornerShape(15.dp)
				)
				.padding(horizontal = innerHorizontalPadding, vertical = 5.dp)
				.onSizeChanged { size ->
					boxHeight = with(density) { size.height.toDp() }
				}
		) {
			ShadowPadding(
				paddingStart = paddingStart,
				height = boxHeight,
				width = (deviceWidth - outerHorizontalPadding - innerHorizontalPadding) / 3
			)
			Row(
				horizontalArrangement = Arrangement.SpaceAround,
				verticalAlignment = Alignment.Top,
				modifier = Modifier
					.fillMaxWidth()
					.background(
						color = Color.Transparent,
						shape = RoundedCornerShape(15.dp)
					)
					.padding(5.dp)
			) {
				FormattedText(
					onAction = onAction,
					text = thousandsSeparator()
				)
				FormattedText(
					onAction = onAction,
					modifier = Modifier.padding(start = 10.dp),
					type = ThousandsSeparatorType.COMMA
				)
				FormattedText(
					onAction = onAction,
					modifier = Modifier.padding(start = 10.dp),
					type = ThousandsSeparatorType.SPACE
				)
			}
		}
		Text(
			text = thousandsSeparator(type = state.type),
			style = TextStyle(
				fontSize = 20.sp
			)
		)
	}
}

@Composable
private fun ShadowPadding(
	modifier: Modifier = Modifier,
	paddingStart: Dp,
	height: Dp,
	width : Dp
) {
	Text(
		text = "1000",
		style = TextStyle(
			fontSize = 22.sp,
			textAlign = TextAlign.Center,
			fontWeight = FontWeight.Bold,
			color = Color.Transparent
		),
		modifier = modifier
			.padding(start = paddingStart)
			.background(
				color = Color.White,
				shape = RoundedCornerShape(10.dp)
			)
			.width(width)
			.height(height)
	)
}

@Composable
private fun RowScope.FormattedText(
	modifier: Modifier = Modifier,
	onAction: (ThousandsSeparatorAction) -> Unit,
	text: String = "1000",
	type: ThousandsSeparatorType = ThousandsSeparatorType.POINT,
) {
	Text(
		text = thousandsSeparator(
			text = text,
			type = type
		),
		style = TextStyle(
			fontSize = 24.sp,
			textAlign = TextAlign.Center,
			fontWeight = FontWeight.Bold
		),
		modifier = modifier
			.background(Color.Transparent)
			.clickable {
				onAction(ThousandsSeparatorAction.UpdateType(type))
			}
			.weight(1f)
	)
}

@Preview(showBackground = true)
@Composable
fun ThousandsSeparatorPreview() {
	ThousandsSeparator(
		onAction = {},
		state = ThousandsSeparatorState()
	)
}