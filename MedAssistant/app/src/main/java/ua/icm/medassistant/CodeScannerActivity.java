package ua.icm.medassistant;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;
import com.budiyev.android.codescanner.DecodeCallback;
import com.google.zxing.Result;

import java.util.Arrays;

public class CodeScannerActivity extends AppCompatActivity {
    private CodeScanner mCodeScanner;
    private static int CAMERA_REQUEST_CODE = 101;
    private String code;
    private Button next;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_code_scanner);

        CodeScannerView scannerView = findViewById(R.id.scanner_view);
        next = findViewById(R.id.next);
        codeScanner(scannerView);

        next.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                handleNext();
            }
        });

    }

    private void handleNext(){
        if (code!=null){
            Intent goToAddMed = new Intent();
            goToAddMed.putExtra("barcode", code);
            setResult(RESULT_OK, goToAddMed);
            finish();
        }else{
            Toast.makeText(CodeScannerActivity.this, "Please scan the medication Barcode", Toast.LENGTH_SHORT).show();
        }
    }

    private void codeScanner(CodeScannerView scannerView){
        mCodeScanner = new CodeScanner(this, scannerView);
        mCodeScanner.setCamera(CodeScanner.CAMERA_BACK);
        mCodeScanner.setDecodeCallback(new DecodeCallback() {
            @Override
            public void onDecoded(@NonNull final Result result) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        code = result.getText();
                        Toast.makeText(CodeScannerActivity.this, result.getText(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        scannerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCodeScanner.startPreview();
            }
        });
    }
    @Override
    protected void onResume() {
        super.onResume();
        mCodeScanner.startPreview();
    }

    @Override
    protected void onPause() {
        mCodeScanner.releaseResources();
        super.onPause();
    }
}
