package com.sara.waie.androidbasicsetup.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.sara.waie.androidbasicsetup.R
import com.sara.waie.androidbasicsetup.databinding.FragmentRecentsBinding
import com.sara.waie.androidbasicsetup.ui.adapters.RecentOrderHistoryPagingAdapter
import com.sara.waie.androidbasicsetup.viewmodel.DashboardViewmodel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


/**
 * A simple [Fragment] subclass.
 * Use the [RecentsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RecentsFragment : Fragment() {
    private lateinit var binding: FragmentRecentsBinding
    private lateinit var adapter:RecentOrderHistoryPagingAdapter
    private val viewModel: DashboardViewmodel by activityViewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=FragmentRecentsBinding.inflate(inflater,container,false)
        initView()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       viewLifecycleOwner.lifecycleScope.launch {
           viewModel.loadMyOrderHistory().collectLatest {
               adapter.submitData(it)
           }
       }

    }

    private fun initView() {
        adapter=RecentOrderHistoryPagingAdapter()
        binding.rvOrderHistory.adapter=adapter
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment RecentsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            RecentsFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}