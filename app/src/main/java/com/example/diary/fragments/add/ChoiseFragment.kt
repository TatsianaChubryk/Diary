package com.example.diary.fragments.add

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.diary.R
import com.example.diary.databinding.FragmentChoiseBinding

class ChoiseFragment : Fragment() {
    private lateinit var binding: FragmentChoiseBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentChoiseBinding.inflate(inflater, container, false)

        binding.addBtn.setOnClickListener {
            findNavController().navigate(R.id.action_choiseFragment_to_addFragment)

        }

        setHasOptionsMenu(true)
        // binding.prioritiesSpinner.onItemSelectedListener = mSharedViewModel.listener
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.reflectionText.text = getReflection()

        binding.refreshReflection.setOnClickListener {
            binding.reflectionText.text = getReflection()
        }

        binding.motivationText.text = getMotivation()

        binding.refreshMotivation.setOnClickListener {
            binding.motivationText.text = getMotivation()
        }

        binding.dreamText.text = getDream()

        binding.refreshDream.setOnClickListener {
            binding.dreamText.text = getDream()
        }
    }

    private fun randomReflection(): Int {
        val size = resources.getStringArray(R.array.reflection).size - 1
        return (0..size).random()
    }

    private fun getReflection(): String {
        return resources.getStringArray(R.array.reflection)[randomReflection()]
    }

    private fun randomMotivation(): Int {
        val size = resources.getStringArray(R.array.motivation).size - 1
        return (0..size).random()
    }

    private fun getMotivation(): String {
        return resources.getStringArray(R.array.motivation)[randomMotivation()]
    }

    private fun randomDream(): Int {
        val size = resources.getStringArray(R.array.dream).size - 1
        return (0..size).random()
    }

    private fun getDream(): String {
        return resources.getStringArray(R.array.dream)[randomDream()]
    }
}