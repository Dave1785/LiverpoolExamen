package com.examen.liverpool.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.examen.liverpool.R
import com.examen.liverpool.data.Records
import com.examen.liverpool.data.Refinement


/**
 * Shows how to implement a simple Adapter for a RecyclerView.
 * Demonstrates how to add a click handler for each item in the ViewHolder.
 */
class SearchAdapter() : RecyclerView.Adapter<SearchAdapter.SearchHolder?>() {

    private var mRefinement: List<Records>?=null

    class SearchHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private lateinit var nameProduct:TextView
        private lateinit var price:TextView
        private lateinit var items:LinearLayout
        private lateinit var subItems:LinearLayout
        private lateinit var image:ImageView

        companion object{
            fun from(parent: ViewGroup):SearchHolder{
                val view=LayoutInflater.from(parent.context).inflate(R.layout.product_item,parent,false)
                return SearchHolder(view)
            }
        }


        fun bind(refinementGroup: Records?) {

            nameProduct=itemView.findViewById(R.id.name)
            items=itemView.findViewById(R.id.items)
            subItems=itemView.findViewById(R.id.sub_items)
            price=itemView.findViewById(R.id.price)
            image=itemView.findViewById(R.id.image_product)

            nameProduct.text=refinementGroup?.productDisplayName
            price.text=refinementGroup?.listPrice


            Glide.with(itemView.context).load(refinementGroup?.xlImage).into(image);

        }


        private fun addElementsOrRemove(remove:Boolean,view: View,refinementList: List<Refinement>?){
            val viewGroup=view as LinearLayout

            if(remove){
                viewGroup.removeAllViews()
            }else{
                if (refinementList != null) {
                    for (refinement in refinementList){
                        var textView=TextView(viewGroup.context)
                        textView.setTextColor(viewGroup.context.resources.getColor(R.color.colorAccent))
                        textView.text=refinement.label
                        viewGroup.addView(textView)
                    }
                }
            }

        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchHolder {
        return SearchHolder.from(parent)
    }

    override fun getItemCount(): Int {
        return if (!mRefinement.isNullOrEmpty()) mRefinement!!.size else 0
    }

    override fun onBindViewHolder(holder: SearchHolder, position: Int) {
        holder.bind(mRefinement?.get(position))
    }

    fun setData(refinement:List<Records>?){
        mRefinement=refinement
        notifyDataSetChanged()
    }


}