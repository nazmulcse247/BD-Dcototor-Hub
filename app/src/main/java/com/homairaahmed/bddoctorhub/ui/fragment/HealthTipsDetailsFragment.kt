package com.homairaahmed.bddoctorhub.ui.fragment

import android.os.Bundle
import android.text.Html
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.homairaahmed.bddoctorhub.R
import com.homairaahmed.bddoctorhub.databinding.FragmentHealthTipsDetailsBinding

/**
 * A simple [Fragment] subclass.
 * Use the [HealthTipsDetailsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HealthTipsDetailsFragment : Fragment() {

    private lateinit var binding : FragmentHealthTipsDetailsBinding
    private val args by navArgs<HealthTipsDetailsFragmentArgs>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHealthTipsDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
    }

    private fun initView() {
        val doctorTips = args.healthTips
        binding.apply {
            Glide.with(requireContext()).load(doctorTips.image).into(this.ivHealthTipsImage)
            convertHtmlToString(doctorTips.content)
        }
    }

    fun convertHtmlToString(html: String) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            binding.tvHealthTipsContent.setText(Html.fromHtml(html,Html.FROM_HTML_MODE_LEGACY))
        } else {
            binding.tvHealthTipsContent.setText(Html.fromHtml(html))
        }
    }


}