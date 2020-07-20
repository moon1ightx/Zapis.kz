package kz.ain.zapis.ui

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_dashboard.*

import kz.ain.zapis.R
import kz.ain.zapis.networking.ApiFactory
import kz.ain.zapis.repositories.FirmsRepository
import kz.ain.zapis.ui.adapters.FirmAdapter
import kz.ain.zapis.viewmodels.FirmsFactory
import kz.ain.zapis.viewmodels.FirmsViewModel

/**
 * A simple [Fragment] subclass.
 */
class DashboardFragment : Fragment() {

    private val firmsViewModel by lazy {
        ViewModelProviders.of(this, FirmsFactory(
            FirmsRepository(
                ApiFactory.getApi()
            )
        )
        )[FirmsViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_dashboard, container, false)
        initUI()
        initObservers()
        return  view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recentlyAddedRecyclerView.layoutManager =
            LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false)
        popularRecyclerView.layoutManager =
            LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false)
        recommendedRecyclerView.layoutManager =
            LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false)
    }

    private fun initObservers() {
        firmsViewModel.firmsLiveData.observe(this, Observer {
            recommendedRecyclerView.adapter = FirmAdapter(it.recommendedFirms, onClick = {firm ->
                openDetailsActivity(firm.id)
            })
            popularRecyclerView.adapter = FirmAdapter(it.popularFirms, onClick = {firm ->
                openDetailsActivity(firm.id)
            })
            recentlyAddedRecyclerView.adapter = FirmAdapter(it.recentlyAddedFirms, onClick = {firm ->
                openDetailsActivity(firm.id)
            })
        })

    }

    private fun initUI(){
        firmsViewModel.loadFirms()
    }

    private fun openDetailsActivity(id: Int){
        val intent = Intent(this.context, FirmDetailsActivity::class.java)
        intent.putExtra("firmId", id)
        startActivity(intent)
    }
}
