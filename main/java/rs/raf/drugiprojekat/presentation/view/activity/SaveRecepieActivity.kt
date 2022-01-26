package rs.raf.drugiprojekat.presentation.view.activity

import android.app.Activity
import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.provider.Settings
import android.view.View
import android.widget.ArrayAdapter
import android.widget.DatePicker
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_save_recepie.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import rs.raf.drugiprojekat.R
import rs.raf.drugiprojekat.data.models.entities.MealEntity
import rs.raf.drugiprojekat.presentation.contract.MainContract
import rs.raf.drugiprojekat.presentation.viewmodel.MainViewModel
import timber.log.Timber
import java.text.SimpleDateFormat
import java.util.*
import java.util.jar.Manifest


class SaveRecepieActivity : AppCompatActivity() {

    private val mainViewModel: MainContract.ViewModel by viewModel<MainViewModel>()

    var cal = Calendar.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_save_recepie)
        init()
    }

    private fun init(){
        initSpinner()
        initPrelaz()
        initDatePicker()
        initListeners()
        initDatePicker()
        initObservers()
    }

    private fun initObservers() {

        //intent.getStringExtra("spisak").toString()
///intent.getStringExtra("nazivJela").toString(),spinner.selectedItem.toString()
        mainViewModel.insertMeal(MealEntity(
            1,"Test","Test","Tets,"//, "TEST", "TEST"
        )
        )
    }


    val REQUEST_CODE = 200

    private fun initDatePicker() {
        val dateSetListener = object : DatePickerDialog.OnDateSetListener {
            override fun onDateSet(view: DatePicker, year: Int, monthOfYear: Int,
                                   dayOfMonth: Int) {
                cal.set(Calendar.YEAR, year)
                cal.set(Calendar.MONTH, monthOfYear)
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                updateDateInView()
            }
        }

        // when you click on the button, show DatePickerDialog that is set with OnDateSetListener
        dateButton.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View) {
                DatePickerDialog(this@SaveRecepieActivity,
                    dateSetListener,
                    // set DatePickerDialog to point to today's date when it loads up
                    cal.get(Calendar.YEAR),
                    cal.get(Calendar.MONTH),
                    cal.get(Calendar.DAY_OF_MONTH)).show()
            }

        })
    }
    var datum: String = String()
    private fun updateDateInView() {
        val myFormat = "MM/dd/yyyy" // mention the format you need
        val sdf = SimpleDateFormat(myFormat, Locale.US)
        prikazDatuma.text = sdf.format(cal.getTime())
        datum = sdf.format(cal.getTime())
        Timber.e("${sdf.format(cal.getTime())}")
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODE && data != null){
            slikaCuvanje.setImageBitmap(data.extras?.get("data") as Bitmap)
        }
    }

    private fun initListeners(){
        saveButtonBaza.setOnClickListener(){

        }
        slikaCuvanje.setOnClickListener(){
            if(askForPermissions()){

                    val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                    startActivityForResult(cameraIntent, REQUEST_CODE)

            }

        }
        backFromSavingButton.setOnClickListener(){
            finish()
        }
    }

    private fun initPrelaz(){
        val pref = applicationContext.getSharedPreferences("PREF_0", 0)

        ImeJelaCuvanje.text = intent.getStringExtra("nazivJela")
        Glide
            .with(this)
            .load(pref.getString("trenutnaSlika",null))
            .into(slikaCuvanje)

    }
    private fun initSpinner(){
        var spinnerArray = arrayOf("Breakfast", "Lunch", "Dinner")

            val spinnerArrayAdapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, spinnerArray)
            //selected item will look like a spinner set from XML
            spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = spinnerArrayAdapter}

    fun isPermissionsAllowed(): Boolean {
        return if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            false
        } else true
    }

    fun askForPermissions(): Boolean {
        if (!isPermissionsAllowed()) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this as Activity,android.Manifest.permission.CAMERA)) {
                showPermissionDeniedDialog()
            } else {
                ActivityCompat.requestPermissions(this as Activity,arrayOf(android.Manifest.permission.CAMERA),100)

            }
            return false
        }
        return true
    }

    override fun onRequestPermissionsResult(requestCode: Int,permissions: Array<String>,grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            100 -> {
                if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Timber.e("Dozvolio")

                } else {
                        Timber.e("Odbio")
                    //  askForPermissions()
                }
                return
            }
        }
    }

    private fun showPermissionDeniedDialog() {
        AlertDialog.Builder(this)
            .setTitle("Permission Denied")
            .setMessage("Permission is denied, Please allow permissions from App Settings.")
            .setPositiveButton("App Settings",
                DialogInterface.OnClickListener { dialogInterface, i ->
                    // send to app settings if permission is denied permanently
                    val intent1 = Intent()
                    intent1.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
                    val uri = Uri.fromParts("package", getPackageName(), null)
                    intent1.data = uri
                    startActivity(intent1)
                })
            .setNegativeButton("Cancel",null)
            .show()
    }
}