package my.project.cofee.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import my.project.cofee.R
import my.project.cofee.data.models.CardModel
import my.project.cofee.data.models.CoffeeModel
import my.project.cofee.databinding.CoffeeBinding
import my.project.cofee.presentation.viewModels.CardViewModel
import my.project.cofee.presentation.viewModels.CoffeeViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class Coffee : Fragment() {

    private var binding: CoffeeBinding? = null
    private var coffeeAdapter: CoffeeAdapter? = null
    private val coffeeViewModel: CoffeeViewModel by viewModel()
    private val cardViewModel: CardViewModel by viewModel()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.coffee, container, false)

        initRecyclerCoffee()
        loadCoffee()

        return binding?.root
    }

    private fun initRecyclerCoffee() {

        binding?.catalogCoffee?.layoutManager =
            LinearLayoutManager(context)
        coffeeAdapter =
            CoffeeAdapter({coffeModel: CoffeeModel -> addToCard(coffeModel)})
        binding?.catalogCoffee?.adapter = coffeeAdapter

    }

    private fun loadCoffee() {

        coffeeViewModel.loadCoffee.observe(viewLifecycleOwner, Observer {
            coffeeAdapter?.setList(it)
            coffeeAdapter?.notifyDataSetChanged()
        })


    }

    private fun addToCard(coffeeModel: CoffeeModel) {
        cardViewModel?.startInsert(coffeeModel.name,
            coffeeModel.image,
            coffeeModel.price,
            coffeeModel.id.toString(),
            "1")
    }

}