package com.shamaich.animalsr.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.shamaich.animalsr.databinding.FragmentListBinding
import com.shamaich.animalsr.model.Animal
import com.shamaich.animalsr.viewmodel.ListViewModel


class ListFragment : Fragment() {

    private var _binding:FragmentListBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var viewModel: ListViewModel
    private val listAdapter = AnimalListAdapter(arrayListOf())

    private val animalListDataObserver = Observer<List<Animal>>{ list ->
        list?.let {
            _binding?.animalrecyclerviewlist?.visibility = View.VISIBLE
             listAdapter.updateAnimalList(it)   }
    }

    private val loadingLiveDataObserver = Observer<Boolean>{ isloading ->
        isloading.let {
            _binding?.loadingview?.visibility = if (isloading == true)  View.VISIBLE else View.GONE
            if (isloading){
                _binding?.listError?.visibility = View.GONE
                _binding?.animalrecyclerviewlist?.visibility = View.GONE
            }

        }
    }

    private val errorLiveDataObserver = Observer<Boolean>{ isError ->
        _binding?.listError?.visibility = if(isError) View.VISIBLE else View.GONE
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentListBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this)[ListViewModel::class.java]
        viewModel.animals.observe(viewLifecycleOwner,animalListDataObserver)
        viewModel.loading.observe(viewLifecycleOwner, loadingLiveDataObserver)
        viewModel.loadError.observe(viewLifecycleOwner, errorLiveDataObserver)
        viewModel.refresh()

        _binding?.animalrecyclerviewlist?.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = listAdapter
        }

        _binding?.refreshLayout?.setOnRefreshListener {
            _binding?.animalrecyclerviewlist?.visibility = View.GONE
            _binding?.listError?.visibility = View.GONE
            _binding?.refreshLayout?.visibility = View.VISIBLE
            viewModel.refresh()
            _binding?.refreshLayout?.isRefreshing = false

        }

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}