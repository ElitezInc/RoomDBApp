package com.gabrielius.roomdbapp.view.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.gabrielius.roomdbapp.R
import com.gabrielius.roomdbapp.databinding.AddFragmentBinding
import com.gabrielius.roomdbapp.model.CustomModel
import com.gabrielius.roomdbapp.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddFragment : DialogFragment(R.layout.add_fragment)
{
    companion object
    {
        fun newInstance() : AddFragment
        {
            return AddFragment()
        }
    }

    var _binding : AddFragmentBinding? = null
    val binding get() = _binding!!
    private val mainViewModel : MainViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.DialogFragment)
    }

    override fun onCreateView(
        inflater : LayoutInflater,
        container : ViewGroup?,
        savedInstanceState: Bundle?
    ) : View
    {
        _binding = AddFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?)
    {
        super.onActivityCreated(savedInstanceState)

        binding.editText.requestFocus()
        showSoftKeyboard()

        binding.button.setOnClickListener(object : View.OnClickListener
        {
            override fun onClick(view : View)
            {
                mainViewModel.insertItem(CustomModel(binding.editText.text.toString()))
                hideSoftKeyboard()
                dismiss()
            }
        })
    }

    private fun showSoftKeyboard()
    {
        val inputMethodManager : InputMethodManager = requireContext().getSystemService(
            Context.INPUT_METHOD_SERVICE
        ) as InputMethodManager
        inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
    }

    private fun hideSoftKeyboard()
    {
        val inputMethodManager : InputMethodManager = requireContext().getSystemService(
            Context.INPUT_METHOD_SERVICE
        ) as InputMethodManager
        inputMethodManager.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0)
    }
}