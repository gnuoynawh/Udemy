package com.gnuoynawh.udemy.todo.fragment.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.gnuoynawh.udemy.todo.R
import com.gnuoynawh.udemy.todo.data.models.Priority
import com.gnuoynawh.udemy.todo.data.models.TodoData
import com.gnuoynawh.udemy.todo.databinding.RowLayoutBinding

class ListAdapter() : RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    var dataList = emptyList<TodoData>()

    //class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {}
    class MyViewHolder(private val binding: RowLayoutBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(todoData: TodoData) {
            binding.todoData = todoData
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): MyViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = RowLayoutBinding.inflate(layoutInflater, parent, false)
                return MyViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
//        return MyViewHolder(
//            LayoutInflater.from(parent.context).inflate(R.layout.row_layout, parent, false)
//        )
        return MyViewHolder.from(parent)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = dataList[position]
        holder.bind(currentItem)

//        holder.itemView.findViewById<TextView>(R.id.txt_title).text = dataList[position].title
//        holder.itemView.findViewById<TextView>(R.id.txt_description).text = dataList[position].description
//        holder.itemView.findViewById<ConstraintLayout>(R.id.row_background).setOnClickListener {
//
//            //holder.itemView.findNavController().navigate(R.id.action_listFragment_to_updateFragment)
//
//            val action = ListFragmentDirections.actionListFragmentToUpdateFragment(dataList[position])
//            holder.itemView.findNavController().navigate(action)
//        }
//
//        when (dataList[position].priority) {
//            Priority.HIGH -> holder.itemView.findViewById<CardView>(R.id.priority_indicator)
//                .setCardBackgroundColor(
//                    ContextCompat.getColor(
//                        holder.itemView.context,
//                        R.color.red
//                    )
//                )
//
//            Priority.MEDIUM -> holder.itemView.findViewById<CardView>(R.id.priority_indicator)
//                .setCardBackgroundColor(
//                    ContextCompat.getColor(
//                        holder.itemView.context,
//                        R.color.yellow
//                    )
//                )
//
//            Priority.LOW -> holder.itemView.findViewById<CardView>(R.id.priority_indicator)
//                .setCardBackgroundColor(
//                    ContextCompat.getColor(
//                        holder.itemView.context,
//                        R.color.green
//                    )
//                )
//        }
    }

    fun setData(todoData: List<TodoData>) {
//        this.dataList = todoData
//        notifyDataSetChanged()

        val todoDiffUtil = TodoDiffUtil(dataList, todoData)
        val todoDiffResult = DiffUtil.calculateDiff(todoDiffUtil)
        this.dataList = todoData
        todoDiffResult.dispatchUpdatesTo(this)
    }
}

