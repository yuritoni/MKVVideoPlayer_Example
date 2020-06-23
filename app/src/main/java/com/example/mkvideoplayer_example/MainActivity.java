package com.example.mkvideoplayer_example;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.khizar1556.mkvideoplayer.MKPlayerActivity;

import net.alhazmy13.mediapicker.Video.VideoPicker;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getId() == R.id.play2) {
                    String url = ((EditText) findViewById(R.id.et_url)).getText().toString();
                   if(url.isEmpty()){
                       Toast.makeText(getApplicationContext(),"Please place link in field",Toast.LENGTH_LONG).show();
                   }
                   else{
                       Intent i=new Intent(MainActivity.this,videoplayer.class);
                       i.putExtra("url",url);
                       startActivity(i);
                   }
                }
            }
        };
        findViewById(R.id.play2).setOnClickListener(clickListener);
    }

    public void pick(View view) {
        new VideoPicker.Builder(MainActivity.this)
                .mode(VideoPicker.Mode.CAMERA_AND_GALLERY)
                .directory(VideoPicker.Directory.DEFAULT)
                .extension(VideoPicker.Extension.MP4)
                .enableDebuggingMode(true)
                .build();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == VideoPicker.VIDEO_PICKER_REQUEST_CODE && resultCode == RESULT_OK) {
           List<String> mPaths =  data.getStringArrayListExtra(VideoPicker.EXTRA_VIDEO_PATH);
            MKPlayerActivity.configPlayer(this).play(mPaths.get(0));
            //Your Code
        }
    }
}