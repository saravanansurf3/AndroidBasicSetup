package com.sara.waie.androidbasicsetup.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.activityViewModels
import androidx.paging.ExperimentalPagingApi
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.sara.waie.androidbasicsetup.BuildConfig
import com.sara.waie.androidbasicsetup.databinding.FragmentHomeBinding
import com.sara.waie.androidbasicsetup.network.NetworkResource
import com.sara.waie.androidbasicsetup.ui.adapters.GridContainerAdapter
import com.sara.waie.androidbasicsetup.ui.adapters.SalesTodayAdapter
import com.sara.waie.androidbasicsetup.ui.adapters.SuggestionListAdapter
import com.sara.waie.androidbasicsetup.viewmodel.DashboardViewmodel
import dagger.hilt.android.AndroidEntryPoint


/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@ExperimentalPagingApi
@AndroidEntryPoint
class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private val viewModel: DashboardViewmodel by activityViewModels()
    private lateinit var salesTodayAdapter: SalesTodayAdapter
    private lateinit var suggestionListAdapter: SuggestionListAdapter
    private lateinit var gridContainerAdapter: GridContainerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // binding= FragmentHomeBinding.bind(view)
        //viewModel.loadDashboard()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        val aa = BuildConfig.APK_KEY
        initView()
        println("aa" + aa)

        return binding.root
    }

    private fun initView() {

        salesTodayAdapter = SalesTodayAdapter()
        suggestionListAdapter = SuggestionListAdapter()
        gridContainerAdapter = GridContainerAdapter(suggestionListAdapter)
        val concatAdapter = ConcatAdapter(salesTodayAdapter, gridContainerAdapter)

        binding.rvHome.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = concatAdapter
        }
        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.loadDashboard()
        }
        viewModel.dashboardResponse.observe(viewLifecycleOwner) {
            when (it) {
                is NetworkResource.onSuccess -> {
                    it.data
                    binding.lyProgressbar.visibility = View.GONE
                    binding.swipeRefreshLayout.isRefreshing=false
                    println("this is the data i reccived : " + it.data.toString())
                    it.data?.data?.let {
                        it.popularSale?.let {
                            salesTodayAdapter.updateList(it)
                        }
                        it.suggestion?.let {
                            suggestionListAdapter.updateSuggestion(it)
                        }
                    }

                }
                is NetworkResource.onFailure -> {
                    binding.lyProgressbar.visibility = View.GONE
                    binding.swipeRefreshLayout.isRefreshing=false
                }
                is NetworkResource.onLoading -> {
                    binding.lyProgressbar.visibility = View.VISIBLE
                }
            }
        }

        binding.etSearchView.setOnEditorActionListener { view, actionId, keyEvent ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH || actionId == EditorInfo.IME_ACTION_DONE) {
//                viewModel.search(view.text.toString())
//                viewModel.loadDashboard()
                true
            }
            false
        }

    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         * @return A new instance of fragment HomeFragment.
         */
        @JvmStatic
        fun newInstance() =
            HomeFragment().apply {

            }
    }
}