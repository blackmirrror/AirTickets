package ru.blackmirrror.airtickets.common

import android.annotation.SuppressLint
import android.view.KeyEvent
import android.view.MotionEvent
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.TextView

object EditTextUtils {
    class ClearTextOnTouchListener(
        private val editText: EditText,
        private val action: () -> Unit
    ) : android.view.View.OnTouchListener {

        @SuppressLint("ClickableViewAccessibility")
        override fun onTouch(v: android.view.View?, event: MotionEvent?): Boolean {
            if (event?.action == MotionEvent.ACTION_UP) {
                if (event.rawX >= editText.right - editText.compoundDrawablesRelative[2].bounds.width()) {
                    action.invoke()
                    return true
                }
            }
            return false
        }
    }

    class OnEditorActionDoneListener(
        private val action: () -> Unit
    ) : TextView.OnEditorActionListener {

        override fun onEditorAction(v: TextView?, actionId: Int, event: KeyEvent?): Boolean {
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                action.invoke()
                return true
            }
            return false
        }
    }

}
