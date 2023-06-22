package com.mobi.mybank.presentation

import com.mobi.mybank.data.model.Account


interface ListAccountClickListener {
    fun onItemClick(position: Int, myLine: Account)
}