package com.example.brastlewark.ui.adapters

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.example.brastlewark.R
import com.example.brastlewark.data.CitizenModel
import com.example.brastlewark.ui.viewHolders.BrastlewarkViewHolder
import java.util.stream.Collectors
import javax.inject.Inject


class BrastlewarkAdapter @Inject constructor(val citizen:ArrayList<CitizenModel>, private val context: Context, private val listener: (citizen : CitizenModel) -> Unit ): RecyclerView.Adapter<BrastlewarkViewHolder>(), Filterable {

    var citizenList = ArrayList<CitizenModel>()


    init {
        citizenList = citizen
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BrastlewarkViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return BrastlewarkViewHolder(layoutInflater.inflate(R.layout.gnome_card, parent, false),context)
    }

    override fun onBindViewHolder(holder: BrastlewarkViewHolder, position: Int) {
        val allCitizen = citizenList[position]
        holder.bind(allCitizen)

        holder.itemView.setOnClickListener {
            listener.invoke(allCitizen)
        }
    }
    override fun getItemCount(): Int {
        return citizenList.size
    }

    override fun getFilter(): Filter {
        return object : Filter(){
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                citizenList = if (charSearch.isEmpty()){
                    citizen
                }else{
                    val resultList = ArrayList<CitizenModel>()
                        for(row in citizen){
                            if(row.name.contains(charSearch)){
                                resultList.add(row)
                            }
                        }
                    resultList
                }
                val filterResults = FilterResults()
                filterResults.values = citizenList
                return filterResults
            }
            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                citizenList = results?.values as ArrayList<CitizenModel>
                notifyDataSetChanged()
            }

        }
    }
}