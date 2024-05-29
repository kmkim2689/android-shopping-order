package woowacourse.shopping.data.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import woowacourse.shopping.R
import woowacourse.shopping.view.state.UIState

@BindingAdapter("app:imageUrl")
fun loadImage(
    view: ImageView,
    url: String?,
) {
    if (!url.isNullOrEmpty()) {
        Glide.with(view.context)
            .load(url)
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_background)
            .into(view)
    }
}

@BindingAdapter("app:price")
fun setPrice(
    view: TextView,
    price: Int,
) {
    view.text = view.context.getString(R.string.price_format, price)
}

@BindingAdapter("app:selectedBasedOn")
fun setSelectedBasedOn(
    button: AppCompatButton,
    isSelected: Boolean,
) {
    button.isSelected = isSelected
}

@BindingAdapter("app:viewVisibility")
fun setViewVisibility(
    view: View,
    isVisible: Boolean,
) {
    view.visibility = if (isVisible) View.VISIBLE else View.GONE
}

@BindingAdapter("app:totalQuantityVisibility")
fun setTotalQuantityVisibility(
    textView: TextView,
    totalQuantity: Int,
) {
    textView.visibility = if (totalQuantity > 0) View.VISIBLE else View.GONE
}

@BindingAdapter("app:isEmpty", "app:state", requireAll = true)
fun <T> setEmptyCartVisibility(
    textView: TextView,
    isEmpty: Boolean,
    state: UIState<T>,
) {
    textView.visibility =
        if (state is UIState.Success && isEmpty) View.VISIBLE else View.GONE
}

@BindingAdapter("app:isEmpty", "app:state", requireAll = true)
fun <T> setCartVisibility(
    view: RecyclerView,
    isEmpty: Boolean,
    state: UIState<T>,
) {
    view.visibility =
        if (state is UIState.Success && !isEmpty || state is UIState.Loading) {
            View.VISIBLE
        } else {
            View.GONE
        }
}