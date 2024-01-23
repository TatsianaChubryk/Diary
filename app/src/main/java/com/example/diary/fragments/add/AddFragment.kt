package com.example.diary.fragments.add

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.diary.R
import com.example.diary.databinding.FragmentAddBinding
import com.example.diary.databinding.FragmentListBinding
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.logging.SimpleFormatter

class AddFragment : Fragment() {

    private lateinit var binding: FragmentAddBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment

        binding = FragmentAddBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dateFormat()
    }

    private fun dateFormat() {
        val formatter = SimpleDateFormat("dd.MM.yy")
        val date = Calendar.getInstance().time
        val timeString = formatter.format(date)
        binding.dateEt.setText(timeString).toString()
    }
}