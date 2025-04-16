import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.practicum.testappshop.R
import com.practicum.testappshop.domain.models.Purchase
import com.practicum.testappshop.ui.home.CartProductsViewAdapter

class PurchaseSummaryDialog(private val purchase: Purchase) : DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.dialog_purchase_summary, container, false)

        val datetimeTextView: TextView = view.findViewById(R.id.datetimeTextView)
        val itemsRecyclerView: RecyclerView = view.findViewById(R.id.itemsRecyclerView)
        val closeButton: Button = view.findViewById(R.id.closeButton)

        datetimeTextView.text = "от: ${purchase.datetime}"

        itemsRecyclerView.layoutManager = LinearLayoutManager(context)
        val adapter = CartProductsViewAdapter(
            { return@CartProductsViewAdapter true },
            { return@CartProductsViewAdapter true },
            {},
            true
        )
        itemsRecyclerView.adapter = adapter
        adapter.setProducts(purchase.items.map { it.product }, purchase.items.map {it.count.toLong()})

        closeButton.setOnClickListener {
            dismiss()
        }

        return view
    }
}