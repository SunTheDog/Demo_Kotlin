package com.ugsg.sip.demo_kotlin.ui.place

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.hjq.toast.ToastUtils
import com.ugsg.sip.demo_kotlin.R
import com.ugsg.sip.demo_kotlin.ui.weather.WeatherActivity
import kotlinx.android.synthetic.main.fragment_place.*

class PlaceFragment : Fragment() {

    val viewModel by lazy { ViewModelProvider(this).get(PlaceViewModel::class.java) }


    private lateinit var adapter: PlaceAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_place, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (viewModel.isPlaceSave()) {
            val savePlace = viewModel.getSavePlace()
            val intent = Intent(context, WeatherActivity::class.java).apply {
                putExtra("location_lng", savePlace.location.lng)
                putExtra("location_lat", savePlace.location.lat)
                putExtra("place_name", savePlace.name)
            }

            startActivity(intent)
            activity?.finish()
            return

        }

        recyclerView.layoutManager = LinearLayoutManager(activity)
        adapter = PlaceAdapter(viewModel.place, this)
        recyclerView.adapter = adapter


        searchPlaceEdit.addTextChangedListener {
            val searchStr = it.toString()
            if (searchStr.isNotEmpty()) {
                viewModel.searchPlaces(searchStr)
            } else {
                recyclerView.visibility = View.GONE
                bgImageView.visibility = View.VISIBLE
                viewModel.place.clear()
                adapter.notifyDataSetChanged()
            }
        }

        viewModel.placeLiveData.observe(this, Observer {
            val place = it.getOrNull()
            if (place != null) {
                recyclerView.visibility = View.VISIBLE
                bgImageView.visibility = View.GONE
                viewModel.place.addAll(place)
                adapter.notifyDataSetChanged()
            } else {
                ToastUtils.show("获取城市信息失败")
            }
        })


    }

}