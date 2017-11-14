package com.example.hnino.businessdirectory;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hnino.businessdirectory.business.EnterpriseBusiness;
import com.example.hnino.businessdirectory.entities.Enterprise;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddEnterpriseActivity extends AppCompatActivity {

    @BindView(R.id.etName)
    EditText etName;
    @BindView(R.id.etEmail)
    EditText etEmail;
    @BindView(R.id.etPhone)
    EditText etPhone;
    @BindView(R.id.etUrl)
    EditText etUrl;
    @BindView(R.id.etProducts)
    EditText etProducts;
    @BindView(R.id.spClassification)
    Spinner spClassification;

    private EnterpriseBusiness mEnterpriseBusiness;
    private long mId;
    public static final String ID = "ID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_enterprise);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mId = getIntent().getLongExtra(ID, -1);

        mEnterpriseBusiness = new EnterpriseBusiness(getApplicationContext());
        final String[] classificationList = {"Select", "Consultancy", "Custom Development", "Software Factory"};
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, classificationList);
        spClassification.setAdapter(arrayAdapter);
        if (mId != -1) {
            cargarDatos();
        }
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = etName.getText().toString().trim();
                String url = etUrl.getText().toString().trim();
                String phone = etPhone.getText().toString().trim();
                String email = etEmail.getText().toString().trim();
                String products = etProducts.getText().toString().trim();
                String classification = spClassification.getSelectedItem().toString();

                if (name.isEmpty()) {
                    etName.requestFocus();
                    etName.setError(getString(R.string.required));
                    return;
                } else if (products.isEmpty()) {
                    etProducts.requestFocus();
                    etProducts.setError(getString(R.string.required));
                    return;
                } else if (classification.equals("Select")) {
                    TextView errorText = (TextView) spClassification.getSelectedView();
                    errorText.setError(getString(R.string.required));
                    errorText.setTextColor(Color.RED);//just to highlight that this is an error
                    errorText.requestFocus();
                    return;
                } else {
                    Intent intent;
                    if (mId == -1) {
                        long id = mEnterpriseBusiness.saveEnterprise(name, url, phone, email, products, classification);
                        intent = new Intent();
                        intent.putExtra(ID, id);

                    } else {
                        mEnterpriseBusiness.updateEnterprise(mId, name, url, phone, email, products, classification);
                        intent = new Intent();
                        intent = new Intent();
                        intent.putExtra(ID, mId);
                    }
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }
        });


    }

    private void cargarDatos() {
        Enterprise enterprise = mEnterpriseBusiness.searchEnterprise(mId);
        etName.setText(enterprise.getName());
        etUrl.setText(enterprise.getUrl());
        etPhone.setText(enterprise.getPhone());
        etEmail.setText(enterprise.getEmail());
        etProducts.setText(enterprise.getProducts());
        spClassification.setSelection(getIndex(spClassification, enterprise.getClassification()));

    }

    //private method of your class
    private int getIndex(Spinner spinner, String myString) {
        int index = 0;

        for (int i = 0; i < spinner.getCount(); i++) {
            if (spinner.getItemAtPosition(i).toString().equalsIgnoreCase(myString)) {
                index = i;
                break;
            }
        }
        return index;
    }
}
