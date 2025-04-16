package com.practicum.testappshop.ui.home

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.practicum.testappshop.R
import com.practicum.testappshop.domain.models.Product
import com.practicum.testappshop.util.ButtonAddClickListener
import com.practicum.testappshop.util.ButtonDelClickListener
import com.practicum.testappshop.util.ProductClickListener

class ProductsViewAdapter(
    private val buttonAddClickListener: ButtonAddClickListener,
    private val buttonDelClickListener: ButtonDelClickListener,
    private val clickListener: ProductClickListener,
) : RecyclerView.Adapter<ProductViewHolder>() {

    private var products = emptyList<Product>()
    private var productsInCart = mutableListOf<Long>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_view, parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(products[position], productsInCart[position])
        holder.itemView.setOnClickListener { clickListener.onProductClick(products[position]) }
        val btnAddToCart = holder.itemView.findViewById<Button>(R.id.button_add_to_cart)
        val btnInc = holder.itemView.findViewById<Button>(R.id.button_add)
        val btnDec = holder.itemView.findViewById<Button>(R.id.button_remove)
        btnAddToCart.setOnClickListener {
            if (buttonAddClickListener.onButtonAddClickListener(products[position])) {
                productsInCart[position]++
                notifyItemChanged(position)
            }
        }
        btnInc.setOnClickListener {
            if (buttonAddClickListener.onButtonAddClickListener(products[position])) {
                productsInCart[position]++
                notifyItemChanged(position)
            }
        }
        btnDec.setOnClickListener {
            if (buttonDelClickListener.onButtonDelClickListener(products[position])) {
                productsInCart[position]--
                notifyItemChanged(position)
            }
        }
    }

    override fun getItemCount() = products.size

    fun setProducts(products: List<Product>, productsInCart: List<Long>) {
        this.products = products
        this.productsInCart.addAll(productsInCart)
        notifyDataSetChanged()
    }
}

class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val productTitle: TextView
    val productPrice: TextView
    val productCountInCart: TextView
    val productCount: TextView
    val productSale: TextView
    val productBarcode: TextView
    val btnAddToCart: Button
    val addIncDec: LinearLayout

    init {
        productTitle = itemView.findViewById(R.id.name)
        productPrice = itemView.findViewById(R.id.price)
        productSale = itemView.findViewById(R.id.sale)
        productCountInCart = itemView.findViewById(R.id.counter)
        productCount = itemView.findViewById(R.id.count)
        productBarcode = itemView.findViewById(R.id.barcode)
        btnAddToCart = itemView.findViewById(R.id.button_add_to_cart)
        addIncDec = itemView.findViewById(R.id.add_delete_product)
    }

    @SuppressLint("SetTextI18n")
    fun bind(model: Product, countInCart: Long) {
        productTitle.text = model.title
        productPrice.text = ((model.price - model.sale).toFloat()/100).toString()+"p/"+"${model.unitName}"
        productCountInCart.text = countInCart.toString()
        productSale.text = model.price.toString()
        productSale.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
        productCount.text = "В наличии: "+"${model.stock}"
        productBarcode.text = model.barcode
        if (countInCart <= 0) {
            btnAddToCart.visibility = View.VISIBLE
            addIncDec.visibility = View.GONE
        } else {
            btnAddToCart.visibility = View.GONE
            addIncDec.visibility = View.VISIBLE
        }

    }

}

