package com.example.diary.fragments.add

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.diary.R
import com.example.diary.data.models.DiaryEntity
import com.example.diary.data.viewmodel.DiaryViewModel
import com.example.diary.databinding.FragmentAddBinding
import java.text.SimpleDateFormat
import java.util.Calendar

class AddFragment : Fragment() {

    private lateinit var binding: FragmentAddBinding
    private val mDiaryViewModel: DiaryViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true)
        // binding.prioritiesSpinner.onItemSelectedListener = mSharedViewModel.listener
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        dateFormat()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.add_fragment_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_add) {
            insertDataToDB()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun insertDataToDB() {
        val mDate = binding.dateEt.text.toString()
        val mDescription = binding.descriptionEt.text.toString()

        /*val validation = mSharedViewModel.verifyDataFromUser(mDate, mDescription)
        if (validation) {*/
            val newData = DiaryEntity(
                0,
                mDate,
                mDescription
            )
            mDiaryViewModel.insertData(newData)
            findNavController().navigate(R.id.action_addFragment_to_listFragment)

    }

    private fun dateFormat() {
        val formatter = SimpleDateFormat("dd.MM.yy")
        val date = Calendar.getInstance().time
        val timeString = formatter.format(date)
        binding.dateEt.setText(timeString).toString()
    }
}