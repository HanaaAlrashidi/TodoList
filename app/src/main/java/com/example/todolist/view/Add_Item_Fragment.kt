package com.example.todolist.view

import android.app.DatePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.todolist.R
import java.util.*


class Add_Item_Fragment : Fragment() {

    private val todoViewModel:TodoViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add__item_, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val taskEditText: EditText = view.findViewById(R.id.task_editText)
        val descriptionEditText:EditText = view.findViewById(R.id.description_editText)
        val dueDatePicker: DatePicker = view.findViewById(R.id.due_date_picker)
        val saveButton: Button = view.findViewById(R.id.save_button)


        saveButton.setOnClickListener {
            val task = taskEditText.text.toString()
            val description = descriptionEditText.text.toString()
            val dueDate = "${dueDatePicker.year}/${dueDatePicker.month+1}/${dueDatePicker.dayOfMonth}"

            if (task.isNotEmpty() && description.isNotEmpty()){

                todoViewModel.addItem(task, dueDate, description, false)

                findNavController().popBackStack()
            }
        }



    }

}
