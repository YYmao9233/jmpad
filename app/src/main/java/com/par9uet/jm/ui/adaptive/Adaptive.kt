package com.par9uet.jm.ui.adaptive

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.widthIn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * 大屏设备（平板）宽度阈值。
 * 宽度 >= 600dp 视为平板/大屏，此时启用大屏适配。
 */
private val LargeScreenWidthThreshold = 600.dp

/**
 * 平板设备上，内容面板 / 列表区域允许的最大宽度（居中显示）。
 * 超出部分作为留白，避免面板和图片被无限拉伸到全屏。
 */
private val MaxPanelWidth = 840.dp

/**
 * 判断当前设备是否为宽屏/平板（横屏或竖屏的大尺寸屏幕）。
 */
@Composable
fun isLargeScreen(): Boolean {
    val config = LocalConfiguration.current
    return config.screenWidthDp >= LargeScreenWidthThreshold.value
}

/**
 * 平板最大内容宽度。手机返回 Dp.Unspecified（即不限制宽度）。
 */
@Composable
fun maxPanelWidthDp(): Dp = if (isLargeScreen()) MaxPanelWidth else Dp.Unspecified

/**
 * 大屏适配容器：在平板设备上，将子内容限制在 [MaxPanelWidth] 内并水平居中，
 * 两侧自然留白；在手机设备上完全填充，行为不变。
 *
 * 适用于面板、列表、详情等普通页面内容。
 */
@Composable
fun AdaptivePanel(
    modifier: Modifier = Modifier,
    content: @Composable BoxScope.() -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .widthIn(max = maxPanelWidthDp()),
        contentAlignment = Alignment.TopCenter
    ) {
        content()
    }
}