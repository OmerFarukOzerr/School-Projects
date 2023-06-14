package com.ofo.country_appsd.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.ofo.country_appsd.R
import com.ofo.country_appsd.databinding.FragmentCountryBinding
import com.ofo.country_appsd.view.util.DownloadFromUrl
import com.ofo.country_appsd.view.util.placeHolderProgressBar
import com.ofo.country_appsd.view.viewmodel.countryViewModel
import kotlinx.android.synthetic.main.fragment_country.*

class CountryFragment : Fragment() {
    private lateinit var viewModel : countryViewModel
    private var Uuid = 0
    private  lateinit var dataBind : FragmentCountryBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        dataBind= DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.fragment_country,
            container, false)
        return dataBind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            Uuid = CountryFragmentArgs.fromBundle(it).uuid
        }

        viewModel = ViewModelProvider(this).get(countryViewModel::class.java)
        viewModel.refresh(Uuid)


        Observers()
    }

    private fun Observers() {
        viewModel.countries.observe(viewLifecycleOwner, Observer { countries ->

            countries?.let {
                context?.let {
                    countryImage.DownloadFromUrl(countries.countryUrl,
                        placeHolderProgressBar(it.applicationContext))
                }
                dataBind.selectedCountry = it

            }
        })
    }
}

