package com.homairaahmed.bddoctorhub.ui.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.homairaahmed.bddoctorhub.databinding.FragmentOtherServiceBinding
import com.homairaahmed.bddoctorhub.viewmodel.CategoryViewModel


/**
 * A simple [Fragment] subclass.
 * Use the [OtherServiceFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class OtherServiceFragment : Fragment() {

    private lateinit var binding: FragmentOtherServiceBinding
    private var context : Context? = null
    private val args by navArgs<OtherServiceFragmentArgs>()
    private val categoryViewModel: CategoryViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.context = context
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentOtherServiceBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        setOnClickListeners()
    }

    private fun initView() {
        val serviceName = args.serviceName
        binding.tvOtherServiceName.text = serviceName.serviceName.toString()
    }

    private fun setOnClickListeners() {

        binding.apply {
            ivBack.setOnClickListener {
                findNavController().navigateUp()
            }
        }
    }


}