package com.mobi.mybank.presentation

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mobi.mybank.R
import com.mobi.mybank.data.model.AccountDTOItem
import com.mobi.mybank.domain.model.AccountData

class AccountManagmentAdapter(
    private val context: Context?,
    var list: List<AccountDTOItem?>,
    private val listAccountClickListener: ListAccountClickListener

) : RecyclerView.Adapter<AccountManagmentAdapter.FeatureViewHolder>() {

    private fun conditions(isExpanded :Boolean , holder : AccountManagmentAdapter.FeatureViewHolder){
        if (isExpanded) {
            holder.dropDownIcon.setImageDrawable(context?.let { ContextCompat.getDrawable(it,R.drawable.baseline_keyboard_arrow_up_24) })
            holder.container.translationZ=5f
            holder.container.elevation=2F
        } else {
            holder.dropDownIcon.setImageDrawable(context?.let { ContextCompat.getDrawable(it,R.drawable.baseline_keyboard_arrow_down_24) })
            holder.container.translationZ=0f
            holder.container.elevation=0F
        }
    }

    fun setContentList(list: MutableList<AccountDTOItem>) {
        this.list = list
        notifyDataSetChanged()
    }

    var mExpandedPosition = -1
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): FeatureViewHolder {
        return FeatureViewHolder(
            LayoutInflater.from(context)
                .inflate(R.layout.account_item, parent, false)
        )


    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: FeatureViewHolder, position: Int) {

        list[position]?.let { holder.bind(it) }
        val isExpanded = position == mExpandedPosition
        holder.descriptionContainer.visibility = if (isExpanded) View.VISIBLE else View.GONE

   /*
        holder.validateBtn.setOnClickListener {
            list[position]?.let { it1 -> listMyLineClickListener.onItemButtonClick(position, it1) }
        }

    */
        holder.itemView.setOnClickListener { v: View? ->
            val previousExpandedPosition = mExpandedPosition
            mExpandedPosition = if (isExpanded) -1 else position
            if (previousExpandedPosition != -1) {

                notifyItemChanged(previousExpandedPosition)
            }
            if (mExpandedPosition != -1) {

                notifyItemChanged(mExpandedPosition)
            }


        }


        conditions(isExpanded,holder)

    }


    /**
     * Find all the views of the list item
     */
    inner class FeatureViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.title_text)
        val balance: TextView = itemView.findViewById(R.id.sub_txt)
        val container: ConstraintLayout = itemView.findViewById(R.id.container)
        val dropDownIcon: ImageView = itemView.findViewById(R.id.icon_drop_down)
        val descriptionContainer: ConstraintLayout = itemView.findViewById(R.id.descriptionContainer)
        private val listItem: RecyclerView = itemView.findViewById(R.id.detail_list_account)

        /**
         * Show the data in the views
         */
        fun bind(line: AccountDTOItem) {
            title.text = line.name
            balance.text=line.accounts.get(0).balance.toString()+" €"

            var adapterListItem =AccountDetailsListAdapter(context, line.accounts,listAccountClickListener)

            listItem.layoutManager =
                LinearLayoutManager(itemView.context, RecyclerView.VERTICAL, true)
            listItem.adapter = adapterListItem
        }

        }
    }





