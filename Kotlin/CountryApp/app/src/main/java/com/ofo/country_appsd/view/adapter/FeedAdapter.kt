package com.ofo.country_appsd.view.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.ofo.country_appsd.R
import com.ofo.country_appsd.databinding.ItemRowBinding
import com.ofo.country_appsd.view.FeedFragmentDirections
import com.ofo.country_appsd.view.model.Country
import kotlinx.android.synthetic.main.item_row.view.*


class feedAdapter(var countryList: ArrayList<Country>) :
    RecyclerView.Adapter<feedAdapter.countryVH>(), CountryListenerInterface {
    class countryVH(var view : ItemRowBinding) : RecyclerView.ViewHolder(view.root)



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): countryVH {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemView : ItemRowBinding = DataBindingUtil.inflate(layoutInflater,
            R.layout.item_row, parent, false)

        return countryVH(itemView)

    }


    override fun getItemCount(): Int = countryList.size


    override fun onBindViewHolder(holder: countryVH, position: Int) {
        holder.view.country = countryList[position]
        holder.view.countryL = this

    }

    fun countryListGuncelleme(newCountryList: List<Country>) {
        countryList.clear()
        countryList.addAll(newCountryList)
        notifyDataSetChanged()

    }

    override fun countryListener(view: View) {
        super.countryListener(view)
        //layoutta her temlara tıklama olduğunda bu fonksiyon çağıralacak

        val Uuid = view.countryRowUuid.text.toString().toInt()
        val action = FeedFragmentDirections.actionFeedFragmentToCountryFragment(Uuid)
        Navigation.findNavController(view).navigate(action)


    }


}