package com.barmagah.tms_demo.home

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.*
import com.barmagah.tms_demo.R
import com.barmagah.tms_demo.databinding.ActivityHomeBinding
import com.barmagah.tms_demo.home.ui.CompanyViewModel
import com.barmagah.tms_demo.home.ui.CompanyViewModelFactory
import com.barmagah.tms_demo.system.data.login.db.TmsDatabase
import com.barmagah.tms_demo.system.provider.UserPreferenceProviderImpl
import com.barmagah.tms_demo.system.ui.LoginActivity
import com.barmagah.tms_demo.utils.startNewActivity
import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein
import org.kodein.di.generic.instance


class HomeActivity : AppCompatActivity(), KodeinAware {
    // Injection
    override val kodein by closestKodein()

    //Binding
    private lateinit var mBinding: ActivityHomeBinding
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    //ViewModel
    private val viewModelFactory: CompanyViewModelFactory by instance()
    lateinit var viewModel: CompanyViewModel

    companion object {
        const val TAG = "HOME_TAG"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /*
        *
        * set Navigation
        * */
        mBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_home)
        setNavHostWithController()

        /*
        * setViewModel
        * */
        viewModel =
            ViewModelProviders.of(this, viewModelFactory)
                .get(CompanyViewModel::class.java)
        /* action*/
        loadUserData()

    }

    /*
    *
    *
    * Navigation Component!
    * */
    private fun setNavHostWithController() {
        setSupportActionBar(mBinding.toolBar)//supportActionBar!!.hide()
        mBinding.collapsingToolbarLayout.isTitleEnabled = true
        mBinding.collapsingToolbarLayout.title = "company_name"


        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.findNavController()
        inflateNavigationMenus()


    }

    private fun inflateNavigationMenus() {
        // Bottom
        mBinding.bottomNav.menu.clear()
        mBinding.bottomNav.inflateMenu(R.menu.home_bottom_nav)
        mBinding.bottomNav.setupWithNavController(navController)

        // Slide Menu
        mBinding.navView.menu.clear();
        mBinding.navView.inflateMenu(R.menu.home_admin_side_nav);


        /*
        *  set App Configuration
        * */
        appBarConfiguration = AppBarConfiguration(
            setOf(R.id.taskReminderFragment/*,fragment*/), mBinding.drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        mBinding.navView.setupWithNavController(navController)

    }


    /*
    * User Stuff
    * */

    private fun loadUserData() {
        val dao = TmsDatabase.invoke(this@HomeActivity).currentUserDao()
        dao.getCurrentUser().observe(this@HomeActivity, Observer {
            mBinding.user = it
        })
    }

    private fun logout() {
        AlertDialog.Builder(this)
            .setTitle("Do you really want to log out")
            .setPositiveButton(android.R.string.yes,
                DialogInterface.OnClickListener { dialog, _ ->
                    val userPreferenceProvider = UserPreferenceProviderImpl(this@HomeActivity)
                    userPreferenceProvider.deleteUser()

                    this@HomeActivity.startNewActivity(LoginActivity::class.java)
                    dialog.dismiss()
                })
            .show()

    }
    /*
    *
    * Override & Class!
    *
    * */

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.home_options_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.log_out ->
                Handler(Looper.getMainLooper()).postDelayed({
                    logout()
                }, 2000)
        }
        return item.onNavDestinationSelected(navController) || super.onOptionsItemSelected(item)
    }


}