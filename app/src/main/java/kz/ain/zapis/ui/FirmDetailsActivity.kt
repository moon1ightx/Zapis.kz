package kz.ain.zapis.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_firm_details.*
import kotlinx.android.synthetic.main.fragment_dashboard.*
import kz.ain.zapis.R
import kz.ain.zapis.networking.ApiFactory
import kz.ain.zapis.networking.models.Firm
import kz.ain.zapis.repositories.FirmDetailsRepository
import kz.ain.zapis.ui.adapters.FirmAdapter
import kz.ain.zapis.ui.adapters.ImageAdapter
import kz.ain.zapis.ui.adapters.ServiceAdapter
import kz.ain.zapis.viewmodels.FirmDetailsFactory
import kz.ain.zapis.viewmodels.FirmDetailsViewmodel

class FirmDetailsActivity : AppCompatActivity() {

    private val firmDetailsViewmodel by lazy {
        ViewModelProviders.of(this, FirmDetailsFactory(
            FirmDetailsRepository(
                ApiFactory.getApi()
            )
        )
        )[FirmDetailsViewmodel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_firm_details)
        initUI()
        initObservers()
        serviceListRecyclerView.layoutManager = LinearLayoutManager(this)
        imageRecyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
    }

    private fun initObservers() {
        firmDetailsViewmodel.firmDetailsLiveData.observe(this, Observer {
            setupFirmData(it.firm)
            serviceListRecyclerView.adapter = ServiceAdapter(it.services)
        })
    }

    private fun initUI(){
        val id = intent.getIntExtra("firmId", 0)
        firmDetailsViewmodel.loadFirmDetails(id)
    }

    private fun setupFirmData(firm: Firm){
        firm_details_name.text = firm.name
        firm_details_address.text = firm.address
        imageRecyclerView.adapter = ImageAdapter(firm.pictures)
    }

}
