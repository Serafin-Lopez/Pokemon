package mx.com.developer.pokemon.ui.helpers


import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy


/**
 * Set visibility of a view to visible.
 * Usage: myView.show()
 */
fun View.show() {
    this.visibility = View.VISIBLE
}

/**
 * Set visibility of a view to invisible.
 * Usage: myView.invisible()
 */

fun View.invisible() {
    this.visibility = View.INVISIBLE
}


/**
 * Extension function to load images into a view from a given url.
 */
fun ImageView.loadImage(url: String?) {
    Glide.with(context)
        .load(url?:"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSuBizh_BlT_hCoVStNF1xk7H8UrJ8TQ0M8jw&usqp=CAU")
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .into(this)
}

fun TextView.loadText(text: String) {
    this.text = text
}