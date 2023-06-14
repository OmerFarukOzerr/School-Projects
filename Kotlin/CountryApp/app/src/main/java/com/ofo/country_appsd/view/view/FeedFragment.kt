package com.ofo.country_appsd.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ofo.country_appsd.R
import com.ofo.country_appsd.view.adapter.feedAdapter
import com.ofo.country_appsd.view.viewmodel.FeedViewModel
import kotlinx.android.synthetic.main.fragment_feed.*

class FeedFragment : Fragment() {
    private val countryAdapter = feedAdapter(arrayListOf())
    private lateinit var viewModel: FeedViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_feed, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val layoutManager = LinearLayoutManager(this.context)
        countryList.layoutManager = layoutManager
        countryList.adapter = countryAdapter

        viewModel = ViewModelProvider(this).get(FeedViewModel::class.java)
        viewModel.refresh()

        Observers()

        SwipeRefresh.setOnRefreshListener {
            countryList.visibility = View.GONE
            errorTextView.visibility = View.GONE
            SwipeRefresh.isRefreshing = false
            viewModel.refreshSwipe()
            countryProgressBar.visibility = View.VISIBLE

        }

        //burada görünüm create olurken viewModeldaki data güncellenir ama recycler viewda gösterilemez
        //bunu için aşağıdaki Observerda adapterda yazdığımız foksiyona ViewModeldaki listeyi yollarız
    }

    private fun Observers() {
        viewModel.countries.observe(viewLifecycleOwner, Observer { countries ->

            countries?.let {
                countryList.visibility = View.VISIBLE
                countryAdapter.countryListGuncelleme(it)
            }
        })

        viewModel.countryError.observe(viewLifecycleOwner, Observer { error ->

            error?.let {
                if (it) {
                    errorTextView.visibility = View.VISIBLE
                    countryProgressBar.visibility = View.GONE
                    countryList.visibility = View.GONE

                } else {
                    errorTextView.visibility = View.GONE

                }
            }
        })

        viewModel.countryLoading.observe(viewLifecycleOwner, Observer { loading ->

            loading?.let {
                if (it) {
                    countryProgressBar.visibility = View.VISIBLE
                    countryList.visibility = View.GONE
                    errorTextView.visibility = View.GONE

                } else {
                    countryProgressBar.visibility = View.GONE
                }
            }
        })
    }
}