package thongphungvan.FinalExam;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    Button btnInsert, btnDelete, btnUpdate, btnQuery;
    EditText edtMaHang, edtTenHang, edtSoLuong, edtDonGia;
    SQLiteDatabase database1 = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnInsert = (Button) findViewById(R.id.btnInsert);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        btnUpdate = (Button) findViewById(R.id.btnUpdate);
        btnQuery = (Button) findViewById(R.id.btnQuery);
        edtMaHang = (EditText) findViewById(R.id.edtMaHang);
        edtTenHang = (EditText) findViewById(R.id.edtTenHang);
        edtSoLuong = (EditText) findViewById(R.id.edtSoLuong);
        edtDonGia = (EditText) findViewById(R.id.edtDonGia);

        // Tao database
        database1 = openOrCreateDatabase("qlbanhang.db", MODE_PRIVATE, null);

        //Tạo Table nếu không tồn tại
        try {
            docreattable();
        } catch (Exception e) {
            Log.e("Error", "Table is exists");
        }

        // Insert
        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doinsertrecordtolop();
            }
        });

        // Delete
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleterowLop();
            }
        });

        // Update
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doupdaterowlop();
            }
        });

        // Query
        btnQuery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                querytablelop();
            }
        });


    }
}