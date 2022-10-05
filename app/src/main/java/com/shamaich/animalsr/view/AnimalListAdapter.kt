package com.shamaich.animalsr.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.shamaich.animalsr.R
import com.shamaich.animalsr.databinding.ItemAnimalBinding
import com.shamaich.animalsr.model.Animal

class AnimalListAdapter(private val animalList: ArrayList<Animal>):
    RecyclerView.Adapter<AnimalListAdapter.AnimalViewHolder>() {

    fun updateAnimalList(newAnimalList: List<Animal>){
        animalList.clear()
        animalList.addAll(newAnimalList)
        notifyDataSetChanged()

    }

    private lateinit var _binding:ItemAnimalBinding

    class AnimalViewHolder(var view: View): RecyclerView.ViewHolder(view) {
    }

    override fun getItemCount() = animalList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimalViewHolder {
        val inflater =LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_animal, parent, false)
        return AnimalViewHolder(view)
    }

    override fun onBindViewHolder(holder: AnimalViewHolder, position: Int) {

        _binding.animalName.text = animalList[position].name

    }

}