package com.enzo.wwcam

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import com.enzo.wwcam.application.WebcamApplication
import com.enzo.wwcam.dagger.DaggerAppComponent
import com.enzo.wwcam.wct.WctApi

import com.toptoche.searchablespinnerlibrary.SearchableSpinner
import io.reactivex.Observable
import io.reactivex.android.plugins.RxAndroidPlugins
import kotlinx.android.synthetic.main.fragment_webcam_list_params.*
import java.util.*
import javax.inject.Inject

class WebcamListParamsFragment: Fragment() {

    @Inject lateinit var wctApi: WctApi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (activity!!.application as WebcamApplication).appComponent.inject(this)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_webcam_list_params, container, false)

        // Fill countries spinner
        /* TODO Add multiselection */

//        categorySpinner.adapter = ArrayAdapter<String>(context, R.layout.support_simple_spinner_dropdown_item, wctApi.categories)
//        continentSpinner.adapter = ArrayAdapter<String>(context, R.layout.support_simple_spinner_dropdown_item, wctApi.continents)
//        countrySpinner.adapter = ArrayAdapter<String>(context, R.layout.support_simple_spinner_dropdown_item, wctApi.countries)
//        countrySpinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
//            override fun onNothingSelected(parent: AdapterView<*>?) {
//            }
//
//            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
//                val selectedCountry = parent?.adapter?.getItem(position)
//                wctApi.filterByCountry(selectedCountry as String)
//            }
//        }
//        propertySpinner.adapter = ArrayAdapter<String>(context, R.layout.support_simple_spinner_dropdown_item, wctApi.properties)
//        regionSpinner.adapter = ArrayAdapter<String>(context, R.layout.support_simple_spinner_dropdown_item, wctApi.regions)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        categorySpinner.adapter = ArrayAdapter<String>(context, R.layout.support_simple_spinner_dropdown_item, wctApi.categories)
        continentSpinner.adapter = ArrayAdapter<String>(context, R.layout.support_simple_spinner_dropdown_item, wctApi.continents)
        countrySpinner.adapter = ArrayAdapter<String>(context, R.layout.support_simple_spinner_dropdown_item, wctApi.countries)
        countrySpinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedCountry = parent?.adapter?.getItem(position)
                wctApi.filterByCountry(selectedCountry as String)
            }
        }
        propertySpinner.adapter = ArrayAdapter<String>(context, R.layout.support_simple_spinner_dropdown_item, wctApi.properties)
        regionSpinner.adapter = ArrayAdapter<String>(context, R.layout.support_simple_spinner_dropdown_item, wctApi.regions)
    }
}