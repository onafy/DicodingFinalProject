package onafy.kade_finalproject.Features.TeamDetail.PlayerDetail

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.MenuItem
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.detail_player.*
import onafy.kade_finalproject.R

class PlayerDetailActivity : AppCompatActivity() {
    //=================================== declaration ===========================================
    private var playerPict: String? = ""
    private var playerPosition: String? = ""
    private var playerHeight: String? = ""
    private var playerWeight: String? = ""
    private var playerDesc: String? = ""


    //=============================================================================================


    //============================= main =========================================================
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_player)

        declaration()
        showActionBar()


    } //==========================================================================================



    // ============================= function ====================================================
    private fun declaration(){
        playerPict = intent?.getStringExtra("Pict")
        playerHeight = intent?.getStringExtra("Height")
        playerWeight = intent?.getStringExtra("Weight")
        playerPosition = intent?.getStringExtra("Position")
        playerDesc = intent?.getStringExtra("Desc")
        Log.d("pict", playerPict)
        Glide.with(this).load(playerPict).into(ivPict)
        tvHeight.text = playerHeight
        tvWeight.text = playerWeight
        tvPosition.text = playerPosition
        tvDescription.text = playerDesc

    }

    private fun showActionBar(){
        val actionbar = supportActionBar
        actionbar?.title = "Detail"
        actionbar?.setDisplayHomeAsUpEnabled(true)
    }


    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }



    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }


    // ===========================================================================================







}

