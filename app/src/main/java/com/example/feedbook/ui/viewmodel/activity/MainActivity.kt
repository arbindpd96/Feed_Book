package com.example.feedbook.ui.viewmodel.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AbsListView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.feedbook.R
import com.example.feedbook.ui.viewmodel.adapter.FeedAdapter
import com.example.feedbook.ui.viewmodel.vm.MainViewModel
import com.example.feedbook.utils.Status
import com.example.myfeed.data.Posts
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private val mainViewModel : MainViewModel by viewModel()
    private lateinit var adapter : FeedAdapter

    var visibleItemCount : Int = 0
    var totalItemCount : Int =0
    var scrolledOutItemCount : Int =0
    var page : Int =1
    var isScrolling=false

   private lateinit var layoutManager : RecyclerView.LayoutManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupUI()
        setUpObserver(1)

    }

    private  fun setupUI(){
        layoutManager = LinearLayoutManager(this)

        adapter = FeedAdapter(arrayListOf())

        feedlist.adapter =adapter
        feedlist.layoutManager=layoutManager




    }


    private fun setUpObserver(page : Int) {
        when (page) {
            1 -> (mainViewModel.feeds1.observe(this, Observer {
                when (it.status) {
                    Status.SUCCESS -> {
                        progressBar.visibility = View.GONE
                        it.data?.let { it1 -> renderList(it1) }
                        feedlist.visibility = View.VISIBLE
                        Log.d("MainActivity", "the data finally fetched is :-  " + it.data)
                        pagination()
                    }
                    Status.LOADING -> {
                        progressBar.visibility = View.VISIBLE
                        feedlist.visibility = View.GONE
                    }
                    Status.ERROR -> {
                        progressBar.visibility = View.GONE
                        Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                    }
                }
            }))

            2 -> (
                    mainViewModel.feeds2.observe(this, Observer {
                when(it.status){
                    Status.SUCCESS -> {
                        progressBar.visibility = View.GONE
                        it.data?.let { it1 -> renderList(it1) }
                        feedlist.visibility = View.VISIBLE
                        Log.d("MainActivity", "the data finally fetched is :-  " + it.data)
                        Toast.makeText(this,"2nd page loaded",Toast.LENGTH_SHORT).show()
                        pagination()

                    }
                    Status.LOADING -> {
                        progressBar.visibility = View.VISIBLE
                        feedlist.visibility = View.GONE
                    }
                    Status.ERROR -> {
                        progressBar.visibility = View.GONE
                        Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                    }
                }
            }))

            3-> (mainViewModel.feeds3.observe(this, Observer {
                when(it.status){
                    Status.SUCCESS -> {
                        progressBar.visibility = View.GONE
                        it.data?.let { it1 -> renderList(it1) }
                        feedlist.visibility = View.VISIBLE
                        Log.d("MainActivity", "the data finally fetched is :-  " + it.data)
                        Toast.makeText(this,"3rd page loaded",Toast.LENGTH_SHORT).show()

                    }
                    Status.LOADING -> {
                        progressBar.visibility = View.VISIBLE
                        feedlist.visibility = View.GONE
                    }
                    Status.ERROR -> {
                        progressBar.visibility = View.GONE
                        Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                    }
                }
            })

                    )
            else -> (Toast.makeText(this,"no more pages" , Toast.LENGTH_SHORT).show())
        }
    }

    private fun renderList(users: List<Posts>) {
        adapter.addData(users)
        adapter.notifyDataSetChanged()
    }

    //binding data to the adapter
    fun pagination(){


        feedlist.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if(newState== AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL)
                    isScrolling=true
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                Log.d("scrollListend","the scrollings function :-  "+dx +"and " +dy)
                if(dy>0)
                    visibleItemCount= layoutManager.childCount
                totalItemCount = layoutManager.itemCount
                scrolledOutItemCount = (recyclerView!!.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()

                Log.d("MainActivity" , "visibletemcount:-  " + visibleItemCount + "  totalitemcount :-  " +totalItemCount
                        +" pastVisibleItemPosition  =   "  +  scrolledOutItemCount+"  page :- " +page)


                if(isScrolling&&(visibleItemCount+scrolledOutItemCount==totalItemCount)){
                    isScrolling=false
                    if(page<4) {
                        page++
                       setUpObserver(page)
                    } else{
                        //nothing
                    }
                }}

            }
        )


    }



}