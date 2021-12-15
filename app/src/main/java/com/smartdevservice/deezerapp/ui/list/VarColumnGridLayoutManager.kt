package com.smartdevservice.deezerapp.ui.list

import android.content.Context
import androidx.annotation.Nullable
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler
import com.smartdevservice.deezerapp.utils.Utils.columnsForWidth


class VarColumnGridLayoutManager(context: Context, orientation: Int, reverseLayout: Boolean) :
    GridLayoutManager(context, 2, orientation, reverseLayout) {

    private var columnCountProvider: ColumnCountProvider? = null


    override fun onLayoutChildren(
        recycler: Recycler?,
        state: RecyclerView.State?
    ) {
        updateSpanCount(width)
        super.onLayoutChildren(recycler, state)
    }

    private fun updateSpanCount(width: Int) {
        if (columnCountProvider != null) {
            val spanCount = columnCountProvider?.getColumnCount(width)?:0
            setSpanCount(if (spanCount > 0) spanCount else 1)
        }
    }

    fun setColumnCountProvider(@Nullable provider: ColumnCountProvider) {
        columnCountProvider = provider
    }

    interface ColumnCountProvider {
        fun getColumnCount(recyclerViewWidth: Int): Int
    }

    class DefaultColumnCountProvider(val context: Context) : ColumnCountProvider {

        override fun getColumnCount(recyclerViewWidth: Int): Int {
            return columnsForWidth(context, recyclerViewWidth)
        }
    }
}