package com.hydra.gjhw.photogallery;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.hydra.gjhw.photogallery.fragment.PhotoGalleryFragment;

public class PhotoGalleryActivity extends SingleFragmentActivity {


    @Override
    protected Fragment creatFragment() {
        return PhotoGalleryFragment.newInstance();
    }
}
