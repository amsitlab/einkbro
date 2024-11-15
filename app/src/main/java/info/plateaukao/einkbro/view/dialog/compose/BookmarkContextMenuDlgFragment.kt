package info.plateaukao.einkbro.view.dialog.compose

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.Tab
import androidx.compose.material.icons.outlined.TabUnselected
import androidx.compose.material.icons.outlined.ViewStream
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import info.plateaukao.einkbro.R
import info.plateaukao.einkbro.database.Bookmark
import info.plateaukao.einkbro.view.compose.MyTheme

class BookmarkContextMenuDlgFragment(
    private val bookmark: Bookmark,
    private val allowEdit: Boolean = true,
    private val onClicked: (ContextMenuItemType) -> Unit,
) : ComposeDialogFragment() {
    override fun setupComposeView() {
        composeView.setContent {
            MyTheme {
                BookmarkContextMenuScreen(
                    title = bookmark.title,
                    allowEdit = allowEdit,
                    onClicked = { onClicked(it); dismiss() })
            }
        }
    }
}

@Composable
fun BookmarkContextMenuScreen(
    title: String,
    allowEdit: Boolean = true,
    onClicked: (ContextMenuItemType) -> Unit,
) {
    Column(
        modifier = Modifier
            .wrapContentHeight()
            .width(320.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = title,
            Modifier.padding(4.dp),
            color = MaterialTheme.colors.onBackground,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1,
        )
        HorizontalSeparator()
        Row(
            modifier = Modifier
                .width(IntrinsicSize.Max)
                .horizontalScroll(rememberScrollState()),
        ) {
            ContextMenuItem(R.string.main_menu_new_tabOpen, true, Icons.Outlined.Tab) {
                onClicked(ContextMenuItemType.NewTabForeground)
            }
            ContextMenuItem(R.string.main_menu_new_tab, true, Icons.Outlined.TabUnselected) {
                onClicked(ContextMenuItemType.NewTabBackground)
            }
            ContextMenuItem(R.string.split_screen, true, Icons.Outlined.ViewStream) {
                onClicked(ContextMenuItemType.SplitScreen)
            }
            if (allowEdit) {
                ContextMenuItem(R.string.menu_edit, true, Icons.Outlined.Edit) { onClicked(ContextMenuItemType.Edit) }
            }
            ContextMenuItem(R.string.menu_delete, true, Icons.Outlined.Delete) {
                onClicked(ContextMenuItemType.Delete)
            }
        }
    }
}
