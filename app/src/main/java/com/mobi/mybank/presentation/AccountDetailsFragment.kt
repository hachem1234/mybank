package com.mobi.mybank.presentation

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.mobi.mybank.data.model.Account
import com.mobi.mybank.data.model.Operation
import com.mobi.mybank.data.model.OperationWithDate
import com.mobi.mybank.databinding.FragmentAccountDetailsBinding
import java.sql.Date
import java.text.SimpleDateFormat


class AccountDetailsFragment : Fragment() {

    private lateinit var operationtDetailsListAdapter: OperationtDetailsListAdapter
    private lateinit var itemliste: Account
    var Liste: ArrayList<Operation> = arrayListOf()
    var finalListe: ArrayList<OperationWithDate> = arrayListOf()

    private var _binding: FragmentAccountDetailsBinding? = null
    val binding: FragmentAccountDetailsBinding
        get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAccountDetailsBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        operationtDetailsListAdapter = OperationtDetailsListAdapter(context, emptyList())
        binding.accountCreditRecycler.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = operationtDetailsListAdapter

        }
        binding.backBtn.setOnClickListener {
             findNavController().navigateUp()
         }

        if (arguments?.getSerializable("Item_operation") != null){
            itemliste = arguments?.getSerializable("Item_operation") as Account
        }
        val sdf = SimpleDateFormat("dd/MM/yyyy")
        itemliste.operations?.forEach {

            Liste.add(Operation(it.amount,it.category, getDateTime(it.date) .toString(),it.id,it.title))
        }

        Liste.forEach {
            finalListe.add((OperationWithDate(it.amount,it.category, sdf.parse(it.date),it.id,it.title,it.date)))
        }


        binding.blance.text= itemliste.balance.toString()+" €"
        binding.accountTitl.text= itemliste.label?:""

        println(finalListe.groupingBy { it }.eachCount().filter { it.value >= 1 })
        var count=0

        finalListe.forEachIndexed{index, data ->

             count = finalListe.stream().filter { animal -> data.newDate == animal.newDate }.count().toInt()


        }

       if (count>=2)
        operationtDetailsListAdapter.setContentList(finalListe.sortedBy { it.title }.toMutableList())
        else{
        operationtDetailsListAdapter.setContentList(finalListe.sortedBy { it.newDate }.toMutableList())

       }

    }

    private fun getDateTime(s: String): String? {
        try {
            val sdf = SimpleDateFormat("dd/MM/yyyy")
            val netDate = Date(s.toLong() * 1000)
            Log.e("Tag","Formatted Date"+sdf.format(netDate))
            return sdf.format(netDate)
        } catch (e: Exception) {
            return e.toString()
        }
    }
}