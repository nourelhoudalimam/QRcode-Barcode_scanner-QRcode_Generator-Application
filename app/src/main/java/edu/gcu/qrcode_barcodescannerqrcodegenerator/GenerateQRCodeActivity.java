package edu.gcu.qrcode_barcodescannerqrcodegenerator;
import android.Manifest;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import com.google.android.material.textfield.TextInputEditText;
import com.google.zxing.WriterException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Objects;
import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;
public class GenerateQRCodeActivity extends AppCompatActivity {
    private TextView qrCodeTV;
    private ImageView qrCodeIV;
    private TextInputEditText dataEdt;
    private Button generateQRBtn, btnsave;
    private QRGEncoder qrgEncoder;
    private Bitmap bitmap;
    private String savePath = Environment.getExternalStorageDirectory().getPath() + "/QRCode/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate_qrcode);
        ActivityCompat.requestPermissions(GenerateQRCodeActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        qrCodeTV = findViewById(R.id.idTVGenerateQR);
        qrCodeIV = findViewById(R.id.idIVQRCode);
        dataEdt = findViewById(R.id.idEdtData);
        btnsave = findViewById(R.id.idBtnsave);
        generateQRBtn = findViewById(R.id.idBtnGenerateQR);
        generateQRBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String data = dataEdt.getText().toString();
                if(data.isEmpty()){
                    Toast.makeText(GenerateQRCodeActivity.this, "Please enter some data to generate QR Code...", Toast.LENGTH_SHORT).show();
                }else{
                    WindowManager manager = (WindowManager) getSystemService(WINDOW_SERVICE);
                    Display display = manager.getDefaultDisplay();
                    Point point = new Point();
                    display.getSize(point);
                    int width = point.x;
                    int height = point.y;
                    int dimen = width<height ? width:height;
                    dimen = dimen * 3/4;
                    qrgEncoder = new QRGEncoder(dataEdt.getText().toString(),null, QRGContents.Type.TEXT,dimen);
                    try {
                        bitmap = qrgEncoder.encodeAsBitmap();
                        qrCodeIV.setImageBitmap(bitmap);

                    } catch (WriterException e){
                        e.printStackTrace();
                    }
        }
        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String filename = dataEdt.getText().toString().trim();
                try {
                    ContentResolver resolver = getContentResolver();
                    ContentValues contentValues = new ContentValues();
                    contentValues.put(MediaStore.MediaColumns.DISPLAY_NAME, filename + ".jpg");
                    contentValues.put(MediaStore.MediaColumns.MIME_TYPE, "image/jpg");
                    contentValues.put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_DCIM);
                    Uri imageUri = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
                    OutputStream fos = resolver.openOutputStream(Objects.requireNonNull(imageUri));
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
                    Objects.requireNonNull(fos).close();
                    Toast.makeText(getApplicationContext(), "Image Saved. Check your gallery.", Toast.LENGTH_LONG).show();
                    qrCodeTV.setText(null);
                } catch (
                        IOException e) {
                    e.printStackTrace();
                }
                }}); }}); }}


