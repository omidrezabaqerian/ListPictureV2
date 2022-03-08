package ir.omidrezabagherian.listpicturesv2.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import ir.omidrezabagherian.listpicturesv2.R
import ir.omidrezabagherian.listpicturesv2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var mainBinding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainBinding = ActivityMainBinding.inflate(layoutInflater)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragment_container_main) as NavHostFragment

        navController = navHostFragment.navController

        mainBinding.bottomNavigationMain.setOnItemSelectedListener { result ->
            when (result.itemId) {
                R.id.menu_home -> {
                    navController.popBackStack()
                    navController.navigate(R.id.homeFragment)
                }
                R.id.menu_search -> {
                    navController.popBackStack()
                    navController.navigate(R.id.searchFragment)
                }
            }
            true
        }

        setContentView(mainBinding.root)
    }
}