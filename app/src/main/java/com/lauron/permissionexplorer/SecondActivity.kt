package com.lauron.permissionexplorer

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import com.lauron.permissionexplorer.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {
    //private lateinit var layout: View
    private lateinit var binding: ActivitySecondBinding

    private val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                // Permission is granted, open the camera
                openCamera()
            } else {
                // Permission is denied, handle accordingly (e.g., show a message)
                showToast("Camera permission denied!")
            }
        }

    private val requestStoragePermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                // Storage permission is granted, perform storage-related action
                showToast("Storage permission granted!")
            } else {
                // Storage permission is denied, handle accordingly
                showToast("Storage permission denied!")
            }
        }

    private val requestLocationPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                // Location permission is granted, perform location-related action
                showToast("Location permission granted!")
            } else {
                // Location permission is denied, handle accordingly
                showToast("Location permission denied!")
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val openCameraButton: Button = binding.cameraBtn
        val openStorageButton: Button = binding.storageBtn
        val openLocationButton: Button = binding.locBtn

        openCameraButton.setOnClickListener {
            // Check if the camera permission is granted
            if (ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.CAMERA
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                // Permission is already granted, open the camera
                openCamera()
            } else {
                // Request camera permission
                requestCameraPermission()
            }
        }

        openStorageButton.setOnClickListener {
            // Check if the camera permission is granted
            if (ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                // Permission is already granted, open the camera
                //openCamera()
            } else {
                // Request camera permission
                requestStoragePermission()
            }
        }

        openLocationButton.setOnClickListener {
            // Check if the camera permission is granted
            if (ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                // Permission is already granted, open the camera
                //openCamera()
            } else {
                // Request camera permission
                requestLocationPermission()
            }
        }
    }

    private fun requestCameraPermission() {
        // Request camera permission
        requestPermissionLauncher.launch(Manifest.permission.CAMERA)
    }

    private fun requestStoragePermission() {
        // Request storage permission at runtime
        requestStoragePermissionLauncher.launch(Manifest.permission.WRITE_EXTERNAL_STORAGE)
    }

    private fun requestLocationPermission() {
        // Request location permission at runtime
        requestLocationPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
    }

    private fun openCamera() {
        // Add code to open the camera here
        // You can start a new activity, use Intent to launch the camera, etc.
        // For simplicity, let's show a toast message for now
        // Replace this with your actual camera opening code
        showToast("Camera Access Granted!")
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}