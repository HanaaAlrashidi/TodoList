package com.example.todolist.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.R
import com.example.todolist.adapter.TodoAdapter
import com.example.todolist.database.ItemModel
import com.google.android.material.floatingactionbutton.FloatingActionButton


class TodoList_Fragment : Fragment() {

    private val todoList = mutableListOf<ItemModel>()

    private val todoViewModel: TodoViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_todo_list_, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val taskRecyclerView: RecyclerView = view.findViewById(R.id.todo_recyclerview)
        val addFloatingActionButton: FloatingActionButton = view.findViewById(R.id.add_Button)

        val todoAdapter = TodoAdapter(todoList,todoViewModel)

        taskRecyclerView.adapter = todoAdapter
        todoViewModel.todoItems.observe(viewLifecycleOwner, Observer {
            it.let {
                todoList.clear()
                todoList.addAll(it)
                todoAdapter.notifyDataSetChanged()
            }
        })

        addFloatingActionButton.setOnClickListener{
            findNavController().navigate(R.id.action_todoList_Fragment_to_add_Item_Fragment)
        }

    }


    }
