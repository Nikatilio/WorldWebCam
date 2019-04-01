package com.enzo.wwcam

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import com.enzo.wwcam.application.WebcamApplication
import com.enzo.wwcam.wct.WctApi
import com.enzo.wwcam.wct.params.WctItem

import kotlinx.android.synthetic.main.fragment_webcam_list_params.*
import javax.inject.Inject

class WebcamListParamsFragment: Fragment() {

    @Inject lateinit var wctApi: WctApi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (activity!!.application as WebcamApplication).appComponent.inject(this)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_webcam_list_params, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        continentSpinner.adapter = ArrayAdapter<WctItem>(context, R.layout.support_simple_spinner_dropdown_item, wctApi.continents)
        countrySpinner.adapter = ArrayAdapter<WctItem>(context, R.layout.support_simple_spinner_dropdown_item, wctApi.countries)
        regionSpinner.adapter = ArrayAdapter<WctItem>(context, R.layout.support_simple_spinner_dropdown_item, wctApi.regions)
        categorySpinner.adapter = ArrayAdapter<WctItem>(context, R.layout.support_simple_spinner_dropdown_item, wctApi.categories)
        propertySpinner.adapter = ArrayAdapter<WctItem>(context, R.layout.support_simple_spinner_dropdown_item, wctApi.properties)
        webcamFieldsSpinner.adapter = ArrayAdapter<String>(context, R.layout.support_simple_spinner_dropdown_item, wctApi.webcamFields)
        orderSpinner.adapter = ArrayAdapter<String>(context, R.layout.support_simple_spinner_dropdown_item, wctApi.orderParams)
        fieldsToShowSpinner.adapter = ArrayAdapter<String>(context, R.layout.support_simple_spinner_dropdown_item, wctApi.fieldsToShow)
        languageSpinner.adapter = ArrayAdapter<String>(context, R.layout.support_simple_spinner_dropdown_item, wctApi.languages)

        continentSpinner.onItemSelectedListener = continentSelectedListener
        countrySpinner.onItemSelectedListener = countrySelectedListener
        regionSpinner.onItemSelectedListener = regionSelectedListener
        categorySpinner.onItemSelectedListener = categorySelectedListener
        propertySpinner.onItemSelectedListener = propertySelectedListener

    }

    private val continentSelectedListener = object: AdapterView.OnItemSelectedListener {

        override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
            wctApi.setSelectedContinents(arrayOf(continentSpinner.selectedItemPosition))
        }

        override fun onNothingSelected(parent: AdapterView<*>?) {
        }

    }

    private val countrySelectedListener = object: AdapterView.OnItemSelectedListener {

        override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
            wctApi.setSelectedCountries(arrayOf(countrySpinner.selectedItemPosition))
        }

        override fun onNothingSelected(parent: AdapterView<*>?) {
        }

    }

    private val regionSelectedListener = object: AdapterView.OnItemSelectedListener {

        override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
            wctApi.setSelectedRegions(arrayOf(regionSpinner.selectedItemPosition))
        }

        override fun onNothingSelected(parent: AdapterView<*>?) {
        }

    }

    private val categorySelectedListener = object: AdapterView.OnItemSelectedListener {

        override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
            wctApi.setSelectedCategories(arrayOf(categorySpinner.selectedItemPosition))
        }

        override fun onNothingSelected(parent: AdapterView<*>?) {
        }

    }

    private val propertySelectedListener = object: AdapterView.OnItemSelectedListener {

        override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
            wctApi.setSelectedProperties(arrayOf(propertySpinner.selectedItemPosition))
        }

        override fun onNothingSelected(parent: AdapterView<*>?) {
        }

    }

}
