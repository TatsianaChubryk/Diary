package com.example.diary.fragments.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.diary.R
import com.example.diary.data.models.DiaryEntity
import com.example.diary.data.viewmodel.DiaryViewModel
import com.example.diary.databinding.FragmentListBinding
import com.google.android.material.snackbar.Snackbar
import java.text.FieldPosition

class ListFragment : Fragment() {

    private lateinit var binding: FragmentListBinding
    private val adapter: ListAdapter by lazy { ListAdapter() }
    private val mDiaryViewModel: DiaryViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentListBinding.inflate(inflater, container, false)

        val recyclerView = binding.recyclerView
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireActivity())

        swipeToDelete(recyclerView)

        mDiaryViewModel.getAllData.observe(viewLifecycleOwner) { data ->
            adapter.setData(data)
        }

        binding.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_choiseFragment)
        }

        setHasOptionsMenu(true)

        return binding.root
    }

    private fun swipeToDelete(recyclerView: RecyclerView) {
        val swipeToDeleteCallback = object : SwipeToDelete(requireContext()) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val deletedItem = adapter.dataList[viewHolder.adapterPosition]
                // Delete Item
                mDiaryViewModel.deleteItem(deletedItem)
                //Undo
                adapter.notifyItemChanged(viewHolder.adapterPosition)
                undoDelete(viewHolder.itemView, deletedItem, viewHolder.adapterPosition)
            }
        }
        val itemTouchHelper = ItemTouchHelper(swipeToDeleteCallback)
        itemTouchHelper.attachToRecyclerView(recyclerView)
    }

    private fun undoDelete(view: View, deletedItem: DiaryEntity, position: Int) {

        val snackBar = Snackbar.make(
            view,
            "Удалено '${deletedItem.date}'",
            Snackbar.LENGTH_LONG
        )
        snackBar.setAction("Отменить"){
            mDiaryViewModel.insertData(deletedItem)
            adapter.notifyItemChanged(position)
        }
        snackBar.show()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.list_fragment_menu, menu)
    }
}