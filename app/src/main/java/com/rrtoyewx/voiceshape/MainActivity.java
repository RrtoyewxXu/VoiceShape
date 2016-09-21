package com.rrtoyewx.voiceshape;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.rrtoyewx.voiceshapelibrary.ShapeItem;
import com.rrtoyewx.voiceshapelibrary.VoiceShapeView;

public class MainActivity extends AppCompatActivity {
    VoiceShapeView mVoiceShapeView;
    Button mAddButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mVoiceShapeView = (VoiceShapeView) findViewById(R.id.voice_shape_view);
        mAddButton = (Button) findViewById(R.id.add);
        mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < 20; i++) {
                    int height = (int) (Math.random() * 200)+1;
                    mVoiceShapeView.addItem(new ShapeItem(height));
                }
            }
        });
    }
}
