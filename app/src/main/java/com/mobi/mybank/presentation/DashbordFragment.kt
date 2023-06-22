package com.mobi.mybank.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.mobi.mybank.R
import com.mobi.mybank.databinding.FragmentAccountBinding
import com.mobi.mybank.databinding.FragmentDashbordBinding
import dagger.hilt.android.AndroidEntryPoint

class DashbordFragment : Fragment() {

    private var _binding: FragmentDashbordBinding? = null
    val binding: FragmentDashbordBinding
        get() = _binding!!
    private lateinit var selectedFragment: Fragment

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentDashbordBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {


        setFragment(AccountFragment())
        binding.navigation.setupWithNavController(findNavController())
        binding.navigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    val homeFrag = AccountFragment()
                    // frag.setProfileObservable(viewModel.responseGetProfile)
                    setFragment(homeFrag)
                }
                R.id.navigation_simulation -> {


                }
                R.id.navigation_pay -> {

                }

            }
            true
        }


    }




    fun setFragment(fr: Fragment) {
        selectedFragment = fr
        val frag = childFragmentManager.beginTransaction()
        frag.replace(R.id.frag_content, fr)
        frag.commit()
    }


}