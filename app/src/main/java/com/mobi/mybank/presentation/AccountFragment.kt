package com.mobi.mybank.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.coroutineScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.mobi.mybank.R
import com.mobi.mybank.data.model.Account
import com.mobi.mybank.databinding.FragmentAccountBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class AccountFragment : Fragment(), ListAccountClickListener {

    private var _binding: FragmentAccountBinding? = null
    val binding: FragmentAccountBinding
        get() = _binding!!
    private lateinit var accountManagmentAdapterCi: AccountManagmentAdapter
    private lateinit var accountManagmentAdapter: AccountManagmentAdapter

    private val viewModel: AccountViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAccountBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {


        accountManagmentAdapterCi = AccountManagmentAdapter(context, emptyList(),this)
        accountManagmentAdapter = AccountManagmentAdapter(context, emptyList(),this)

        binding.accountCreditRecycler.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = accountManagmentAdapterCi

        }
        binding.accountOtherRecycler.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = accountManagmentAdapter

        }

        viewModel.getAccountListe()
        lifecycle.coroutineScope.launchWhenCreated {
            viewModel.maccountLists.collect {
                if (it.isLoading) {
                    binding.nothingFound.visibility = View.GONE
                    binding.progressMealSearch.visibility = View.VISIBLE
                }
                if (it.error.isNotBlank()) {
                    binding.nothingFound.visibility = View.GONE
                    binding.progressMealSearch.visibility = View.GONE
                    Toast.makeText(requireContext(), it.error, Toast.LENGTH_SHORT).show()
                }

                it.data?.let {

                    if (it.isEmpty()) {
                        binding.nothingFound.visibility = View.VISIBLE
                    }
                    binding.progressMealSearch.visibility = View.GONE
                    accountManagmentAdapterCi.setContentList(it.filter { it.isCA==1}.sortedBy { it.name }.toMutableList())
                    accountManagmentAdapter.setContentList(it.filter { it.isCA==0}.sortedBy { it.name }.toMutableList())

                }


            }
        }
    }

    override fun onItemClick(position: Int, myLine: Account) {
        val bundle = Bundle()
        bundle.putSerializable("Item_operation", myLine)
        findNavController().navigate(R.id.accountDetailsFragment,bundle)
    }
}