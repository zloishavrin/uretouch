package com.uretouch.feature.tab.navigation.ui

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.uretouch.common.ui.kit.theme.AppTheme
import com.uretouch.feature.tab.navigation.ui.icons.ArchiveBoxOpenDocs24Solid
import com.uretouch.feature.tab.navigation.ui.icons.SettingsGear24Solid
import com.uretouch.feature.tab.navigation.ui.icons.TechCamPhoto24Solid

@Composable
internal fun AppBottomNavigation(
    activeTab: NavigationTab,
    onTabClick: (NavigationTab) -> Unit,
    modifier: Modifier = Modifier,
) {
    BottomNavigation(
        modifier = modifier,
        backgroundColor = AppTheme.colors.background,
    ) {
        BottomNavigationItem(
            selected = activeTab == NavigationTab.History,
            onClick = { onTabClick(NavigationTab.History) },
            icon = {
                Icon(
                    imageVector = Icons.ArchiveBoxOpenDocs24Solid,
                    contentDescription = null
                )
            }
        )
        BottomNavigationItem(
            selected = activeTab == NavigationTab.Camera,
            onClick = { onTabClick(NavigationTab.Camera) },
            icon = {
                Icon(
                    imageVector = Icons.TechCamPhoto24Solid,
                    contentDescription = null
                )
            }
        )
        BottomNavigationItem(
            selected = activeTab == NavigationTab.Settings,
            onClick = { onTabClick(NavigationTab.Settings) },
            icon = {
                Icon(
                    imageVector = Icons.SettingsGear24Solid,
                    contentDescription = null
                )
            }
        )
    }
}