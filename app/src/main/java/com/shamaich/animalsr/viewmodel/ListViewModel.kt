package com.shamaich.animalsr.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.shamaich.animalsr.model.Animal

class ListViewModel(application: Application): AndroidViewModel(application) {

    val animals by lazy { MutableLiveData<List<Animal>>() }
    val loadError by lazy { MutableLiveData<Boolean>() }
    val loading by lazy { MutableLiveData<Boolean>() }

    fun refresh() {
        getAnimals()
    }

    private fun getAnimals(){
        val a1 = Animal("alligator")
        val a2 = Animal("cat")
        val a3 = Animal("dog")
        val a4 = Animal("elephant")
        val a5 = Animal("bee")
        val a6 = Animal("ant")

        val animalList = arrayListOf(a1,a2,a3,a4,a5,a6)

        animals.value = animalList
        loadError.value = false
        loading.value = false
    }
}