package dev.johnnylewis.weighttrackr.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.statusBars
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.zIndex
import androidx.core.view.WindowCompat
import dev.johnnylewis.weighttrackr.presentation.screen.WeightScreen
import dev.johnnylewis.weighttrackr.presentation.theme.AppTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContent {
      AppTheme(dynamicColor = false) {
        Scaffold(
          topBar = { StatusBar() }
        ) {
          Box(
            modifier = Modifier
              .fillMaxSize()
              .background(MaterialTheme.colorScheme.background)
          ) {
            WeightScreen()
          }
        }
      }
    }
  }
}

@Composable
private fun StatusBar() {
  LocalView.current.let { view ->
    if (!view.isInEditMode) {
      val window = (view.context as ComponentActivity).window
      val insetsController = WindowCompat.getInsetsController(window, view)
      insetsController.isAppearanceLightStatusBars = false
    }
  }
  val height = with(LocalDensity.current) {
    WindowInsets.statusBars.getTop(this).toDp()
  }
  Box(
    modifier = Modifier
      .fillMaxWidth()
      .height(height)
      .background(Color.Black.copy(alpha = 0.25f))
      .zIndex(1f)
  )
}
