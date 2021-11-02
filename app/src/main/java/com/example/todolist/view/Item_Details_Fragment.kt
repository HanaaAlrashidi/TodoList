package com.example.todolist.view

import android.app.DatePickerDialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.todolist.R
import com.example.todolist.database.ItemModel
import java.util.*


class Item_Details_Fragment : Fragment() {

    private val todoViewModel: TodoViewModel by activityViewModels()
    private lateinit var selectedItem: ItemModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_item__details_, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val titleEditText: EditText = view.findViewById(R.id.title_editText)
        val dueDateEditText: EditText = view.findViewById(R.id.Duedate_editText)
        val descriptoinEditText: EditText = view.findViewById(R.id.descrip_editText)
        val editButton:Button = view.findViewById(R.id.edit_button)

        todoViewModel.selectedItemMutableLiveData.observe(viewLifecycleOwner, {
            it?.let { item ->
                titleEditText.setText(item.title)
                dueDateEditText.setText(item.douDate)
                descriptoinEditText.setText(item.description)
                selectedItem = item

            }
        })

        editButton.setOnClickListener{
            selectedItem.title = titleEditText.text.toString()
            selectedItem.douDate = dueDateEditText.text.toString()
            selectedItem.description = descriptoinEditText.text.toString()

            todoViewModel.updateItem(selectedItem)

            findNavController().popBackStack()
        }


        val dateRangePicker = DatePickerDialog(requireActivity())
        dueDateEditText.setOnClickListener {

            val dateRangePicker = DatePickerDialog(requireActivity())
            dueDateEditText.setOnClickListener {

                dateRangePicker.show()

            }

            dateRangePicker.setButton(DialogInterface.BUTTON_POSITIVE, "OK") { _, _ ->
                dueDateEditText.setText(
                    "${dateRangePicker.datePicker.year}/" +
                        "${dateRangePicker.datePicker.month+1}/" +
                        "${dateRangePicker.datePicker.dayOfMonth}")
            }




        }


    }

}