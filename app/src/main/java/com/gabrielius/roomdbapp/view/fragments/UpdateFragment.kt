package com.gabrielius.roomdbapp.view.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.gabrielius.roomdbapp.R
import com.gabrielius.roomdbapp.databinding.UpdateFragmentBinding
import com.gabrielius.roomdbapp.model.CustomModel
import com.gabrielius.roomdbapp.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

@AndroidEntryPoint
class UpdateFragment : DialogFragment(R.layout.update_fragment)
{
    companion object
    {
        fun newInstance() : UpdateFragment {
            return UpdateFragment()
        }
    }

    var _binding : UpdateFragmentBinding? = null
    val binding get() = _binding!!
    private val mainViewModel : MainViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.DialogFragment)
    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?
    ) : View
    {
        _binding = UpdateFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState : Bundle?)
    {
        super.onActivityCreated(savedInstanceState)

        mainViewModel.getUpdate().observe(getViewLifecycleOwner(),
            object : Observer<CustomModel>
            {
                override fun onChanged(item : CustomModel)
                {
                    binding.editText.setText(item.name)
                    binding.editText.requestFocus()
                    binding.editText.selectAll()
                    showSoftKeyboard()

                    binding.button.setOnClickListener(object : View.OnClickListener
                    {
                        override fun onClick(view : View) {
                            item.name = binding.editText.text.toString()
                            mainViewModel.updateItem()
                            hideSoftKeyboard()
                            dismiss()
                        }
                    })
                }
            })
    }

    private fun showSoftKeyboard()
    {
        val inputMethodManager: InputMethodManager = requireContext().getSystemService(
            Context.INPUT_METHOD_SERVICE
        ) as InputMethodManager
        inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
    }

    private fun hideSoftKeyboard()
    {
        val inputMethodManager: InputMethodManager = requireContext().getSystemService(
            Context.INPUT_METHOD_SERVICE
        ) as InputMethodManager
        inputMethodManager.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0)
    }
}