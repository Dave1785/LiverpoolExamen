package com.examen.liverpool.ui.searchproduct

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.examen.liverpool.R
import com.examen.liverpool.adapter.SearchAdapter
import com.examen.liverpool.data.RequestDTO
import com.examen.liverpool.repository.LiverpoolRepository
import com.examen.liverpool.viewmodelfactory.SearchActivityViewModelFactory
import com.examen.liverpool.viewmodels.SearchActivityViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: SearchActivityViewModel
    private lateinit var productsRv:RecyclerView
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var searchAdapter:SearchAdapter
    private var  count:Int=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(findViewById(R.id.toolbar))

        val factory = SearchActivityViewModelFactory(this, LiverpoolRepository())
        viewModel = ViewModelProvider(this, factory).get(SearchActivityViewModel::class.java)

        searchAdapter= SearchAdapter()
        linearLayoutManager= LinearLayoutManager(this)
        productsRv=findViewById(R.id.products_rv)
        productsRv.layoutManager=linearLayoutManager
        productsRv.adapter=searchAdapter

        viewModel.ResponseSearch.observe(this, Observer {
            searchAdapter.setData(it.plpResults.records)
        })

        handleIntent(intent)



    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)

        handleIntent(intent)

    }

    private fun handleIntent(intent: Intent) {

        count++
        if (Intent.ACTION_SEARCH == intent.action) {
            val query = intent.getStringExtra(SearchManager.QUERY)
         //  Toast.makeText(this,"Se busco $query",Toast.LENGTH_LONG).show()
            viewModel.getProducts(RequestDTO(true,query.toString(),count,30))
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        // Associate searchable configuration with the SearchView
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        (menu.findItem(R.id.app_bar_search).actionView as SearchView).apply {
            setSearchableInfo(searchManager.getSearchableInfo(componentName))
        }

        return true
    }


}