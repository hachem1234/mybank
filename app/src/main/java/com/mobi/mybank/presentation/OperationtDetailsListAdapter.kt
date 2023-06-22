package com.mobi.mybank.presentation

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

import com.mobi.mybank.R
import com.mobi.mybank.data.model.AccountDTOItem
import com.mobi.mybank.data.model.Operation
import com.mobi.mybank.data.model.OperationWithDate
import java.text.SimpleDateFormat


class OperationtDetailsListAdapter(
    private val context: Context?,
    var list: List<OperationWithDate>,

    ) : RecyclerView.Adapter<OperationtDetailsListAdapter.ViewHolder>() {
    /**
     * Find all the views of the list item
     */

    fun setContentList(list: MutableList<OperationWithDate>) {
        this.list = list
        notifyDataSetChanged()
    }
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val account_title: TextView = itemView.findViewById(R.id.account_title)
        private val date: TextView = itemView.findViewById(R.id.date)
        private val amount: TextView = itemView.findViewById(R.id.amount)

        val view: View = itemView.findViewById(R.id.view)


        /**
         * Show the data in the views
         */
        fun bindView(
            item: OperationWithDate,
            position: Int,
            context: Context?
        ) {

           account_title.text = item.title
           date.text=item.newDate
           amount.text=item.amount+" €"



        }

    }



    override fun getItemCount(): Int = list.size
    /**
     * get itemviewType according to position
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]

        holder.bindView(item, position, context )


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView: View =
            LayoutInflater.from(context).inflate(R.layout.account_operation_item, parent, false)
        return ViewHolder(itemView)
    }
}