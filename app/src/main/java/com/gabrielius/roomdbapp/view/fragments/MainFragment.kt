package com.gabrielius.roomdbapp.view.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gabrielius.roomdbapp.R
import com.gabrielius.roomdbapp.databinding.MainFragmentBinding
import com.gabrielius.roomdbapp.model.CustomModel
import com.gabrielius.roomdbapp.view.CustomListeners
import com.gabrielius.roomdbapp.view.MainActivity
import com.gabrielius.roomdbapp.view.adapter.CustomAdapter
import com.gabrielius.roomdbapp.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.main_fragment.*
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainFragment : Fragment(R.layout.main_fragment), CustomListeners
{
    companion object
    {
        fun newInstance() : MainFragment {
            return MainFragment()
        }
    }

    var _binding : MainFragmentBinding? = null
    val binding get() = _binding!!
    private val mainViewModel : MainViewModel by activityViewModels()
    private lateinit var adapter : CustomAdapter

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?
    ) : View
    {
        _binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?)
    {
        super.onActivityCreated(savedInstanceState)

        setRecyclerView()
        setFloatingActionButton()
    }

    private fun setRecyclerView()
    {
        adapter = CustomAdapter(requireContext(), this@MainFragment)

        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL,false)
        binding.recyclerView.adapter = adapter

        (binding.recyclerView.layoutManager as LinearLayoutManager).setAutoMeasureEnabled(false)

        mainViewModel.getItems().observe(getViewLifecycleOwner(),
            object : Observer<List<CustomModel>>
        {
            override fun onChanged(list : List<CustomModel>)
            {
                Log.d("MainFragment","ID ${list.map { it.id }}, Name ${list.map { it.name }}")
                binding.recyclerView.removeAllViews()
                adapter.setItems(list)
            }
        })

        binding.recyclerView.setHasFixedSize(true)
    }

    private fun setFloatingActionButton()
    {
        binding.floatingActionButtonAdd.setOnClickListener(object : View.OnClickListener
        {
            override fun onClick(view : View?) {
                onAdd()
            }
        })

        binding.floatingActionButtonDelete.setOnClickListener(object : View.OnClickListener
        {
            override fun onClick(view : View?) {
                mainViewModel.deleteAll()
            }
        })
    }

    private fun onAdd()
    {
        (activity as MainActivity).callAddFragment()
    }

    override fun onUpdate(item : CustomModel, position : Int)
    {
        mainViewModel.setUpdate(item)
        (activity as MainActivity).callUpdateFragment()
    }

    override fun onDelete(item : CustomModel, position : Int)
    {
        lifecycleScope.launch { mainViewModel.deleteItem(item) }
    }
}