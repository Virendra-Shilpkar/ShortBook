package com.example.shortbook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.ContentResolver;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shortbook.Geter_Seter.ContectList;

import java.util.ArrayList;

public class AddvenderActivity extends AppCompatActivity {
  public static RecyclerView contect_recycler;
    ContectAdapter adapter;
    String id;
    ArrayList<ContectList> list=new ArrayList<ContectList>();
    LinearLayoutManager layoutManager;
    TextView add_new_vendor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addvender);
        add_new_vendor=findViewById(R.id.add_new_vendor);
        add_new_vendor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addVendor();
//                int sixe=list.size();
//                Toast.makeText(AddvenderActivity.this, sixe, Toast.LENGTH_SHORT).show();
            }
        });
        contect_recycler=findViewById(R.id.contect_recycler);
        layoutManager = new LinearLayoutManager(AddvenderActivity.this, LinearLayoutManager.VERTICAL, false);
        contect_recycler.setLayoutManager(layoutManager);
        adapter = new ContectAdapter(list,this);
        contect_recycler.setAdapter(adapter);
        list.clear();
        displayContacts();

    }

    private void addVendor() {
        Dialog dialog = new Dialog(AddvenderActivity.this, R.style.Theme_AppCompat_DayNight_Dialog_Alert);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        View view = getLayoutInflater().inflate(R.layout.add_vendor, null);
        dialog.setContentView(view);
        dialog.setCancelable(false);
        dialog.show();
        view.findViewById(R.id.cancel_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        view.findViewById(R.id.save_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AddvenderActivity.this, "Do Something", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void displayContacts() {

            ContentResolver cr = getContentResolver();
            Cursor cur = cr.query(ContactsContract.Contacts.CONTENT_URI,
                    null, null, null, null);
            if (cur.getCount() > 0) {
                while (cur.moveToNext()) {
                    id = cur.getString(cur.getColumnIndex(ContactsContract.Contacts._ID));
                    String name = cur.getString(cur.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                    if (Integer.parseInt(cur.getString(
                            cur.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))) > 0) {
                        Cursor pCur = cr.query(
                                ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                                null,
                                ContactsContract.CommonDataKinds.Phone.CONTACT_ID +" = ?",
                                new String[]{id}, null);

                        while (pCur.moveToNext()) {
                            String phoneNo = pCur.getString(pCur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                            ContectList contectList=new ContectList();
                            contectList.setName(name);
                            contectList.setNumber(phoneNo);
                            contectList.setId(id);
                            list.add(contectList);
                        }
                        adapter.notifyDataSetChanged();
                        pCur.close();
                }
            }
        }

    }

}