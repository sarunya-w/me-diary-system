package com.example.mediary.report;

import android.app.Activity;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.example.mediary.CustomLocalStorage;
import com.example.mediary.R;
import com.google.gson.Gson;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

import java.util.HashMap;
import java.util.Map;

import static android.graphics.Color.BLACK;
import static android.graphics.Color.WHITE;

public class QrCodeSyncActivity extends AppCompatActivity {

    private Activity currentActivity;
    private ImageView qrcodeView;
    public final static int WIDTH = 400;
    public final static int HEIGHT = 400;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_code_sync);
        this.currentActivity = this;

        /////// GENERATE JSON to be sent to terminal via QR CODE //////
        Gson gson = new Gson();
        Map<Integer, Integer> products_quantity = new HashMap<>();

        Map<String, Object> future_json = new HashMap<>();
        future_json.put("user", CustomLocalStorage.getString(this, "uuid"));
        future_json.put("cart", products_quantity);
        future_json.put("vouchers", "");
        future_json.put("pin", CustomLocalStorage.getString(this, "pin"));
        String json_str = gson.toJsonTree(future_json).toString();

        Log.d("json cart", json_str);
        //////////////////// END of JSON generation //////////////////
        qrcodeView = (ImageView) findViewById(R.id.qrcode);
        try {
            Bitmap bitmap = encodeAsBitmap(json_str);
            qrcodeView.setImageBitmap(bitmap);
            qrcodeView.invalidate();
        } catch (WriterException e) {
            e.printStackTrace();
        }
    }

    private Bitmap encodeAsBitmap(String str) throws WriterException {
        BitMatrix result;
        try {
            result = new MultiFormatWriter().encode(str,
                    BarcodeFormat.QR_CODE, WIDTH, WIDTH, null);
        } catch (IllegalArgumentException iae) {
            // Unsupported format
            return null;
        }
        int w = result.getWidth();
        int h = result.getHeight();
        int[] pixels = new int[w * h];
        for (int y = 0; y < h; y++) {
            int offset = y * w;
            for (int x = 0; x < w; x++) {
                pixels[offset + x] = result.get(x, y) ? BLACK : WHITE;
            }
        }
        Bitmap bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        bitmap.setPixels(pixels, 0, w, 0, 0, w, h);
        return bitmap;
    }
}
