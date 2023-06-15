package com.homairaahmed.bddoctorhub.ui.fragment

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.text.Html
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.homairaahmed.bddoctorhub.R
import com.homairaahmed.bddoctorhub.databinding.FragmentDoctorDetailsBinding
import com.homairaahmed.bddoctorhub.network.ApiClient
import com.homairaahmed.bddoctorhub.network.ApiService
import com.homairaahmed.bddoctorhub.utils.DialerUtils.Companion.dailNumber
import com.homairaahmed.bddoctorhub.utils.NetworkUtils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.jsoup.Jsoup
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import retrofit2.http.Url


/**
 * A simple [Fragment] subclass.
 * Use the [DoctorDetailsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DoctorDetailsFragment : Fragment() {

    private lateinit var binding : FragmentDoctorDetailsBinding
    private val args by navArgs<DoctorDetailsFragmentArgs>()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDoctorDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        checkScrapping()
        initView()
        setOnclickListener()
    }

    private fun checkScrapping() {

        if (NetworkUtils.isInternetAvailable(requireContext())) {
            val api = ApiClient.getRetrofit().create(ApiService::class.java)
            CoroutineScope(Dispatchers.IO).launch {
                val response = api.getScapeData("prof-dr-syed-atiqul-haq/")
                if (response.isSuccessful && response.body() != null) {
                    Log.d(TAG, "checkScrapping: " + response.body().toString())
                    val htmlDoc = Jsoup.parse(response.body())
                    val elements = htmlDoc.select(".entry-content p strong a")
                    Log.d(TAG, "checkScrapping: "+Html.fromHtml(elements.html().toString()).toString())
                    //Html.fromHtml(elements.html()).toString()
                    html2text(elements.html()).toString()

                }
            }
        } else {
            Toast.makeText(requireContext(), "No Internet Connection", Toast.LENGTH_SHORT).show()
        }

    }

    private fun setOnclickListener() {

        binding.ivBack.setOnClickListener {
            findNavController().navigateUp()
        }

        binding.btnCall.setOnClickListener {
            dailNumber(requireContext(),args.doctor.phone)
        }

        binding.ivShare.setOnClickListener {
            showSharingDialogAsKotlin("Doctor Name : ${args.doctor.name}\nDoctor Education : ${args.doctor.education}\nDoctor Speciality : ${args.doctor.specility}\nDoctor Professor : ${args.doctor.professor}\nDoctor About : ${args.doctor.about}\nDoctor Chamber : ${args.doctor.chamber}\nDoctor Image : ${args.doctor.image}")
        }
    }

    @SuppressLint("SetTextI18n")
    private fun initView() {
        val doctor = args.doctor
        binding.apply {
            tvDoctorName.text = doctor.name
            tvDoctorEducation.text = doctor.education
            tvDoctorSpeciality.text = doctor.specility
            tvDoctorProfessor.text = doctor.professor
            tvAppointmentChamberDetails.text = doctor.about
            tvAppointmentChamber.text = "About ${doctor.name}"
            Glide.with(requireContext()).load(doctor.image).placeholder(R.drawable.male_placeholder).into(ivDoctor)
            doctor.chamber[0].let { tvDoctorAppointmentChamber.text = it }
            doctor.chamber[1].let { tvDoctorAppointmentAddress.text = "Address : $it" }
            doctor.chamber[2].let { tvDoctorAppointmentTime.text = "Visiting Hour : $it" }


        }

    }



    fun showSharingDialogAsKotlin(text: String) {
        val intent = Intent()
        intent.action = Intent.ACTION_SEND
        intent.type = "text/plain"
        intent.putExtra(Intent.EXTRA_TEXT, text)
        startActivity(Intent.createChooser(intent, "Share with:"))
    }

    fun html2text(html: String?): String? {
        Log.d(TAG, "html2text: "+Jsoup.parse(html).text())
        return Jsoup.parse(html).text()
    }



}

interface apiService {
    @GET
    suspend fun getDoctorDetails(@Url url: String): Response<String>
}