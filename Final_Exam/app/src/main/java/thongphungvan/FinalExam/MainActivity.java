package thongphungvan.FinalExam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.progressindicator.LinearProgressIndicatorSpec;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button btnInsert, btnDelete, btnUpdate, btnQuery;
    EditText edtMaHang, edtTenHang, edtSoLuong, edtDonGia;
    SQLiteDatabase database1 = null;
    ListView ListView1;
    ArrayList<String> arraylist1;

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
        ListView1 = (ListView) findViewById(R.id.ListView1);
        arraylist1 = new ArrayList<>();





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
    //TẠO TABLE
    private void docreattable() {
        String sql1="CREATE TABLE tablehang ("+
                "mahang TEXT primary key,"+
                "tenhang TEXT,"+
                "soluong INTEGER,"+
                "dongia INTEGER)";
        database1.execSQL(sql1);
    }

    // Insert
    private void doinsertrecordtolop() {
        String mahang = edtMaHang.getText().toString();
        String tenhang = edtTenHang.getText().toString();
        String soluong = edtSoLuong.getText().toString();
        String dongia = edtDonGia.getText().toString();
        ContentValues values=new ContentValues();
        values.put("mahang", mahang);
        values.put("tenhang", tenhang);
        values.put("soluong", soluong);
        values.put("dongia", dongia);
        String msg="";
        if(database1.insert("tablehang", null, values)==-1){
            msg="Failed to insert record";
        }
        else{
            msg="Insert record is successful";
        }
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    // DELETE ROW
    private void deleterowLop() {
        String mahang = edtMaHang.getText().toString();
        int d = database1.delete("tablehang", "mahang =?", new String[]{mahang});
        String msg = "";
        if (d ==0)
        {
            msg ="Delete Row "+mahang +" Fail";
        }
        else
        {
            msg ="Delete Row "+mahang +" Sucessful";
        }
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    // UPDATE ROW
    private void doupdaterowlop() {
        String mahang = edtMaHang.getText().toString();
        String new_tenhang = edtTenHang.getText().toString();
        String new_soluong = edtSoLuong.getText().toString();
        String new_dongia = edtDonGia.getText().toString();
        ContentValues values=new ContentValues();
        values.put("tenhang", new_tenhang);
        values.put("soluong", new_soluong);
        values.put("dongia", new_dongia);

        String msg="";
        int ret=database1.update("tablehang", values,
                "mahang=?", new String[]{mahang});
        if(ret==0){
            msg="Failed to update";
        }
        else{
            msg="updating is successful";
        }
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    // Query
    public void querytablelop() {
        arraylist1.clear();
        Cursor c = database1.query("tablehang", null, null, null, null, null, null);
        c.moveToFirst();
        String data="";
        while(c.isAfterLast()==false)
        {
            data = c.getString(0)+"-"+
                    c.getString(1)+"-"+
                    c.getString(2)+"-"+
                    c.getString(3);
            arraylist1.add(data);
            c.moveToNext();
        }
        ArrayAdapter adapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, arraylist1);
        ListView1.setAdapter(adapter);
        c.close();
    }


}