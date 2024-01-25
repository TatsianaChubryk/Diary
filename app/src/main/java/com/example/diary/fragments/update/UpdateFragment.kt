package com.example.diary.fragments.update

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.diary.R
import com.example.diary.data.models.DiaryEntity
import com.example.diary.data.models.Priority
import com.example.diary.data.viewmodel.DiaryViewModel
import com.example.diary.data.viewmodel.SharedViewModel
import com.example.diary.databinding.FragmentUpdateBinding

class UpdateFragment : Fragment() {

    private lateinit var binding: FragmentUpdateBinding
    private val args by navArgs<UpdateFragmentArgs>()
    private val mSharedViewModel: SharedViewModel by viewModels()
    private val mDiaryViewModel: DiaryViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUpdateBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true)

        //сетаю в экран обновления
        binding.currentDateEt.setText(args.currentItem.date)
        binding.currentDescriptionEt.setText(args.currentItem.description)
        binding.currentPrioritiesSpinner.setSelection(mSharedViewModel.parsePriorityToInt(args.currentItem.priority))
        binding.currentPrioritiesSpinner.onItemSelectedListener = mSharedViewModel.listener

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.update_fragment_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.menu_save -> updateItem()
            R.id.menu_delete -> confirmItemRemoval()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun confirmItemRemoval() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes") {_, _ ->
            mDiaryViewModel.deleteItem(args.currentItem)
            Toast.makeText(requireContext(), "Seccessful delete", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }
    }

    private fun updateItem() {
        val date = binding.currentDateEt.text.toString()
        val description = binding.currentDescriptionEt.text.toString()
        val getPriority = binding.currentPrioritiesSpinner.selectedItem.toString()

        val validation = mSharedViewModel.verifyDataFromUser(date, description)
        if(validation){
            val updateItem = DiaryEntity(
               args.currentItem.id,
                date,
                mSharedViewModel.parsePriority(getPriority),
                description
            )
            mDiaryViewModel.updateData(updateItem)
            Toast.makeText(requireContext(), "Successful", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        } else{
            Toast.makeText(requireContext(), "SOS", Toast.LENGTH_SHORT).show()
        }
    }
}