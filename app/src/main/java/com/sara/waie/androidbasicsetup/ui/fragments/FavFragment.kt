package com.sara.waie.androidbasicsetup.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.ExperimentalPagingApi
import com.sara.waie.androidbasicsetup.R
import com.sara.waie.androidbasicsetup.databinding.FragmentFavBinding
import com.sara.waie.androidbasicsetup.databinding.FragmentRecentsBinding
import com.sara.waie.androidbasicsetup.ui.adapters.MyFavPostListAdapter
import com.sara.waie.androidbasicsetup.ui.adapters.RecentOrderHistoryPagingAdapter
import com.sara.waie.androidbasicsetup.viewmodel.DashboardViewmodel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FavFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@ExperimentalPagingApi
class FavFragment : Fragment() {
    private lateinit var binding: FragmentFavBinding
    private lateinit var adapter: MyFavPostListAdapter
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
        binding= FragmentFavBinding.inflate(inflater,container,false)
        initView()
        return binding.root
    }
    private fun initView() {
        adapter=MyFavPostListAdapter()
        binding.rvMyFav.adapter=adapter
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.loadMyFavPost().collectLatest {
                adapter.submitData(it)
            }
        }
    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FavFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FavFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}