package com.concept.user.util

import android.animation.LayoutTransition
import android.view.ViewGroup


fun ViewGroup.enableChangingAnimateLayoutChanges() {
    this.layoutTransition
            .enableTransitionType(LayoutTransition.CHANGING)
}
