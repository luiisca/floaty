package com.floatingview.library.composables

import android.graphics.PointF
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onSizeChanged
import android.view.WindowManager
import androidx.compose.ui.platform.ComposeView
import kotlin.math.pow
import kotlin.math.sqrt

@Composable
fun CloseFloaty(
  windowManager: WindowManager,
  containerView: ComposeView,
  layoutParams: WindowManager.LayoutParams,
  modifier: Modifier = Modifier,
  content: @Composable BoxScope.() -> Unit
) {
  Box(
    modifier = modifier
      .onSizeChanged { size ->
        layoutParams.width = size.width
        layoutParams.height = size.height
        windowManager.updateViewLayout(containerView, layoutParams)
      }
  ) {
    content()
  }
}

fun isWithinCloseArea(floatyCenterPointF: PointF, closeCenterPointF: PointF, closingThreshold: Float): Boolean {
  return distanceBetweenTwoPoints(floatyCenterPointF, closeCenterPointF) <= closingThreshold
}

fun distanceBetweenTwoPoints(pointA: PointF, pointB: PointF): Float {
  return sqrt((pointA.x - pointB.x).pow(2) + (pointA.y - pointB.y).pow(2))
}
