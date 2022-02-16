package com.example.customersapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.customersapp.R
import com.example.customersapp.pojo.User


class UsersAdapter : RecyclerView.Adapter<UsersAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val tvName: TextView =itemView.findViewById(R.id.CustmerNameTv)
        val tvTimeAdded: TextView =itemView.findViewById(R.id.TimeAddedTV)


    }
    fun submitList(list:List<User>)=differ.submitList(list)
    val diffCallback= object : DiffUtil.ItemCallback<User>(){
        override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem.userTimeAdded==newItem.userTimeAdded
        }

        override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem.hashCode()==newItem.hashCode()
        }
    }
    val differ= AsyncListDiffer(this,diffCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersAdapter.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.cm_custmer_layout,parent,false))
    }

    override fun onBindViewHolder(holder: UsersAdapter.ViewHolder, position: Int) {
        val user = differ.currentList[position]
        holder.tvName.text=user.usrName
        holder.tvTimeAdded.text=user.userTimeAdded
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}