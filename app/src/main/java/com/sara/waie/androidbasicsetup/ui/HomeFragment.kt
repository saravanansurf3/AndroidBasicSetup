package com.sara.waie.androidbasicsetup.ui

import android.os.Bundle
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.core.widget.doAfterTextChanged
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.sara.waie.androidbasicsetup.BuildConfig
import com.sara.waie.androidbasicsetup.R
import com.sara.waie.androidbasicsetup.databinding.FragmentHomeBinding
import com.sara.waie.androidbasicsetup.network.NetworkResource
import com.sara.waie.androidbasicsetup.viewmodel.DashboardViewmodel
import dagger.hilt.android.AndroidEntryPoint


/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class HomeFragment : Fragment() {
    private lateinit var binding:FragmentHomeBinding
    private val viewModel: DashboardViewmodel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       // binding= FragmentHomeBinding.bind(view)
        viewModel.dashboardResponse.observe(viewLifecycleOwner) {
            when (it) {
                is NetworkResource.onSuccess -> {it.data
                    binding.lyProgressbar.visibility=View.GONE
                    println("this is the data i reccived : "+it.data.toString())

                }
                is NetworkResource.onFailure -> {
                    binding.lyProgressbar.visibility=View.GONE
                }
                is NetworkResource.onLoading -> {
                    binding.lyProgressbar.visibility=View.VISIBLE
                }
            }
        }

        binding.etSearchView.setOnEditorActionListener { view, actionId, keyEvent ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH || actionId == EditorInfo.IME_ACTION_DONE){
//                viewModel.search(view.text.toString())
    viewModel.loadDashboard()
                true
            }
            false
        }

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentHomeBinding.inflate(inflater,container,false)
         val aa=BuildConfig.APK_KEY
        println("aa"+aa)

        return binding.root
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