package com.fatalzero.ui


import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.media.MediaScannerConnection
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.fatalzero.R
import com.fatalzero.databinding.CatInfoBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream

private const val FOLDER_NAME = "my_cat"
private const val URL = "url"

class CatInfoFragment : Fragment() {

    private var url: String? = null
    private var _binding: CatInfoBinding? = null
    private val binding get() = _binding!!
    private var saveButton: FloatingActionButton? = null
    private var calback:Calback? = null




    override fun onAttach(context: Context) {
        super.onAttach(context)
        calback = context as Calback
    }

    interface Calback{
         fun openListFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = CatInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        url = arguments?.getString(URL)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val catInfoImageView: ImageView = binding.imageView
        saveButton = binding.floatingActionButton
        Glide.with(requireActivity())
            .load(url)
            .placeholder(R.drawable.ic_cat_empty)
            .into(catInfoImageView)


        saveButton?.setOnClickListener {
            if (!isCallPermissionGranted()) requestCallPermissions()
            val fileName = File(url).name
            val folderName = FOLDER_NAME
            CoroutineScope(Dispatchers.IO).launch {
                if(saveImage(
                    Glide.with(requireActivity())
                        .asBitmap()
                        .load(url) // sample image
                        .placeholder(android.R.drawable.progress_indeterminate_horizontal) // need placeholder to avoid issue like glide annotations
                        .error(android.R.drawable.stat_notify_error) // need error to avoid issue like glide annotations
                        .submit()
                        .get(), fileName,folderName

                )!=null)  withContext(Dispatchers.Main) {
                    Toast.makeText(requireContext(), "image $fileName saved to $folderName", Toast.LENGTH_LONG).show();
                }
            }
            calback?.openListFragment()
        }
    }


    private  fun saveImage(
        image: Bitmap,
        imageFileName: String,
        folderName: String = ""
    ): String? {
        var savedImagePath: String? = null

        val storageDir = File(
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
                .toString() + "/" + folderName
        )
        var success = true
        if (!storageDir.exists()) {
            success = storageDir.mkdirs()
        }
        if (success) {
            val imageFile = File(storageDir, imageFileName)
            savedImagePath = imageFile.getAbsolutePath()
            try {
                val fOut: OutputStream = FileOutputStream(imageFile)
                image.compress(Bitmap.CompressFormat.JPEG, 100, fOut)
                fOut.close()
            } catch (e: Exception) {
                e.printStackTrace()
            }
            galleryAddPic(savedImagePath)
        }
        return savedImagePath
    }

    private fun galleryAddPic(imagePath: String?) {
        imagePath?.let { path ->
            val mediaScanIntent = Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE)
            val f = File(path)
            val contentUri: Uri = Uri.fromFile(f)
            mediaScanIntent.data = contentUri
            requireActivity().sendBroadcast(mediaScanIntent)
        }
    }


    private fun isCallPermissionGranted(): Boolean {
        return ContextCompat.checkSelfPermission(
            requireContext(),
            android.Manifest.permission.WRITE_EXTERNAL_STORAGE
        ) == PackageManager.PERMISSION_GRANTED

    }

    private fun requestCallPermissions() {
        ActivityCompat.requestPermissions(
            requireActivity(),
            arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE),
            100
        )
    }


}
