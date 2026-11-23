package com.example

import androidx.compose.ui.semantics.SemanticsActions
import androidx.compose.ui.semantics.SemanticsNode
import androidx.compose.ui.semantics.getOrNull
import androidx.compose.ui.test.SemanticsNodeInteraction
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performScrollTo
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.junit.Assert.fail

/**
 * `performScrollTo()` scrolls minimally, so a row can end up on the bottom edge of the viewport, under the
 * floating bottom dock of the glass identities, and a tap then lands on the dock. This scrolls the node into
 * view and then nudges its innermost scrollable ancestor so the node's bottom clears [clearance] of the root
 * bottom. It never scrolls the node off the top. If the list cannot move far enough (end of list without
 * bottom padding) the node is genuinely covered: this fails loudly instead of hiding it.
 */
fun ComposeContentTestRule.revealAboveDock(tag: String, clearance: Dp = 140.dp, useUnmergedTree: Boolean = false): SemanticsNodeInteraction {
    val target = onNode(androidx.compose.ui.test.hasTestTag(tag), useUnmergedTree)
    target.performScrollTo()
    waitForIdle()
    val rootBottom = onRoot().fetchSemanticsNode().boundsInRoot.bottom
    val limit = rootBottom - with(density) { clearance.toPx() }
    val node = target.fetchSemanticsNode()
    val overlap = node.boundsInRoot.bottom - limit
    if (overlap > 0f) {
        var scrollable: SemanticsNode? = node.parent
        while (scrollable != null && SemanticsActions.ScrollBy !in scrollable.config) scrollable = scrollable.parent
        val action = scrollable?.config?.getOrNull(SemanticsActions.ScrollBy)?.action
        if (action == null) fail("'$tag' bottom ${node.boundsInRoot.bottom} is within the dock zone (limit $limit) and has no scrollable ancestor")
        // Never push the node off the top of its viewport.
        val room = (node.boundsInRoot.top - scrollable!!.boundsInRoot.top).coerceAtLeast(0f)
        runOnUiThread { action!!(0f, minOf(overlap, room)) }
        waitForIdle()
    }
    val after = target.fetchSemanticsNode().boundsInRoot.bottom
    if (after > limit + 1f) fail("'$tag' cannot be scrolled above the dock: bottom $after > limit $limit (root bottom $rootBottom)")
    return target
}
