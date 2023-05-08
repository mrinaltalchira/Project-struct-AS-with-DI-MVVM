package app.Project_setup.utils.extensions

import android.content.Context
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.TextPaint
import android.text.TextWatcher
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.URLSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import app.Project_setup.utils.views.recyclerview.DoubleClickListener
import app.Project_setup.utils.views.recyclerview.RecyclerItemClickListener
import com.google.android.material.snackbar.Snackbar

fun ViewGroup.inflate(@LayoutRes resourceId: Int) = LayoutInflater.from(context).inflate(resourceId, this, false)

fun EditText.clear() {
    this.setText("")
}

@JvmOverloads
fun RecyclerView.affectOnItemClicks(onClick: ((position: Int, view: View) -> Unit)? = null, onLongClick: ((position: Int, view: View) -> Unit)? = null) {
    this.addOnChildAttachStateChangeListener(RecyclerItemClickListener(this, onClick, onLongClick))
}

inline fun View.snack(@StringRes messageRes: Int, length: Int = Snackbar.LENGTH_LONG, f: Snackbar.() -> Unit) {
    snack(resources.getString(messageRes), length, f)
}

inline fun View.snack(message: String, length: Int = Snackbar.LENGTH_LONG, f: Snackbar.() -> Unit) {
    val snack = Snackbar.make(this, message, length)
    snack.f()
    snack.show()
}

inline fun View.snack(context : Context, message: String, length: Int = Snackbar.LENGTH_LONG, f: Snackbar.() -> Unit) {
    val snack = Snackbar.make(context, this, message, length).apply {
        anchorView = this@snack
    }
    snack.f()
    snack.show()
}

fun Snackbar.action(@StringRes actionRes: Int, color: Int? = null, listener: (View) -> Unit) {
    action(view.resources.getString(actionRes), color, listener)
}

fun Snackbar.action(action: String, color: Int? = null, listener: (View) -> Unit) {
    setAction(action, listener)
    color?.let { setActionTextColor(ContextCompat.getColor(context, color)) }
}

fun TextView.handleUrlClicks(onClicked: ((String) -> Unit)? = null) {
    text = SpannableStringBuilder.valueOf(text).apply {
        getSpans(0, length, URLSpan::class.java).forEach {

            setSpan(
                object : ClickableSpan() {
                    override fun onClick(widget: View) {
                        onClicked?.invoke(it.url)
                    }

                    override fun updateDrawState(ds: TextPaint) {
                        super.updateDrawState(ds)
                        ds.isUnderlineText = false
                    }
                },
                getSpanStart(it),
                getSpanEnd(it),
                Spanned.SPAN_INCLUSIVE_EXCLUSIVE
            )
            //remove old URLSpan
            removeSpan(it)
        }
    }
    movementMethod = LinkMovementMethod.getInstance()
}

fun EditText.placeCursorToEnd() {
    this.setSelection(this.text.length)
}

fun TextView.applyWithDisabledTextWatcher(textWatcher: TextWatcher, codeBlock: TextView.() -> Unit) {
    this.removeTextChangedListener(textWatcher)
    codeBlock()
    this.addTextChangedListener(textWatcher)
}

fun View.setOnDoubleClickListener(action: (view: View) -> Unit) =
    this.setOnClickListener(DoubleClickListener(action))
