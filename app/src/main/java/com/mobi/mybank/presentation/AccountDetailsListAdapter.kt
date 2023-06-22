package com.mobi.mybank.presentation

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

import android.R.string.no
import com.bumptech.glide.Glide
import com.mobi.mybank.R
import com.mobi.mybank.data.model.Account
import com.mobi.mybank.domain.model.AccountData


class AccountDetailsListAdapter(
    private val context: Context?,
    var list: List<Account>,
    private val listAccountClickListener: ListAccountClickListener


) : RecyclerView.Adapter<AccountDetailsListAdapter.ViewHolder>() {
    /**
     * Find all the views of the list item
     */
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val title: TextView = itemView.findViewById(R.id.title_text)
        private val subText: TextView = itemView.findViewById(R.id.sub_txt)
          val view: View = itemView.findViewById(R.id.view)


        /**
         * Show the data in the views
         */
        fun bindView(
            item: Account,
            position: Int,
            context: Context?
        ) {

           title.text = item.label
           subText.text=item.balance.toString()+" €"



        }

    }



    override fun getItemCount(): Int = list.size
    /**
     * get itemviewType according to position
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]

         //  if (position.equals(list.size-1)) holder.view.setBackgroundResource(0)

        holder.itemView.setOnClickListener {

            list[position]?.let { it1 -> listAccountClickListener.onItemClick(position, it1) }

        }

        holder.bindView(item, position, context )


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView: View =
            LayoutInflater.from(context).inflate(R.layout.account_details_item, parent, false)
        return ViewHolder(itemView)
    }
}