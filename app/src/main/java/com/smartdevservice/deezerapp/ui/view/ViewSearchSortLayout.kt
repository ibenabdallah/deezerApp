package com.smartdevservice.deezerapp.ui.view

import android.content.Context
import android.graphics.Typeface
import android.text.SpannableString
import android.text.Spanned
import android.text.style.StyleSpan
import android.util.AttributeSet
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.widget.PopupMenu
import androidx.appcompat.widget.SearchView
import androidx.constraintlayout.widget.ConstraintLayout
import com.smartdevservice.deezerapp.databinding.ViewSearchSortBinding
import com.smartdevservice.domain.model.EnumSortMethod
import timber.log.Timber
import java.util.ArrayList

class ViewSearchSortLayout(context: Context, attrs: AttributeSet?, defStyleAttr: Int) :
    ConstraintLayout(
        context,
        attrs,
        defStyleAttr
    ) {

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    private var binding: ViewSearchSortBinding =
        ViewSearchSortBinding.inflate(LayoutInflater.from(context), this, true)

    var listener: SearchSortListener? = null

    var sortSelected: EnumSortMethod? = null

    var sortList: ArrayList<EnumSortMethod> = arrayListOf()

    init {

        binding.ivTrie.setOnClickListener {
            showMenuSelectSort(it)
        }

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                listener?.afterTextChanged(query.toString())
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                listener?.afterTextChanged(newText.toString())
                return false
            }
        })
    }

    private fun showMenuSelectSort(view: View) {
        PopupMenu(context, view, Gravity.END).apply {
            sortList.forEach { methodTri ->
                val textSpannable = getTextTrie(methodTri)
                menu.add(textSpannable).setOnMenuItemClickListener {
                    Timber.d("Sort selected, tri = $methodTri")
                    sortSelected = methodTri
                    listener?.onSortedMethodChanged(methodTri)
                    true
                }
            }
            // Show the popup menu.
            show()
        }
    }


    private fun getTextTrie(methodTri: EnumSortMethod) : SpannableString{

        val resId = resources.getIdentifier(methodTri.resId, "string", context.packageName)
        val text = if (resId != 0) resources.getString(resId) else ""

        return if (sortSelected == methodTri) {
            val spannableString = SpannableString(text)
            spannableString.setSpan(
                StyleSpan(Typeface.BOLD),
                0,
                text.length,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            spannableString
        }else {
            SpannableString(text)
        }
    }


    interface SearchSortListener {
        fun afterTextChanged(text: String)
        fun onSortedMethodChanged(sortedBy: EnumSortMethod)
    }
}