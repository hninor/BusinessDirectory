package com.example.hnino.businessdirectory;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.example.hnino.businessdirectory.adapter.BusinessDirectoryAdapter;
import com.example.hnino.businessdirectory.business.EnterpriseBusiness;
import com.example.hnino.businessdirectory.entities.Enterprise;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.hnino.businessdirectory.AddEnterpriseActivity.ID;

public class BusinessDirectoryActivity extends AppCompatActivity {

    @BindView(R.id.rvBusinessDirectory)
    RecyclerView rvBusinessDirectory;

    //private LineAdapter mAdapter;
    public static final int CREATE_REQUEST = 11;
    public static final int EDIT_REQUEST = 12;
    private BusinessDirectoryAdapter mBusinessDirectoryAdapter;
    private EnterpriseBusiness mEnterpriseBusiness;
    private int mPositionUpdated;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_directory);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mEnterpriseBusiness = new EnterpriseBusiness(getApplicationContext());

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(BusinessDirectoryActivity.this, AddEnterpriseActivity.class), CREATE_REQUEST);
            }
        });
        setupRecycler();
    }

    private void setupRecycler() {

        List<Enterprise> enterpriseList = mEnterpriseBusiness.getAll();
        // Configurando o gerenciador de layout para ser uma lista.
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rvBusinessDirectory.setLayoutManager(layoutManager);

        // Adiciona o adapter que irá anexar os objetos à lista.
        // Está sendo criado com lista vazia, pois será preenchida posteriormente.
        mBusinessDirectoryAdapter = new BusinessDirectoryAdapter(enterpriseList, mEnterpriseBusiness, this);
        rvBusinessDirectory.setAdapter(mBusinessDirectoryAdapter);

        // Configurando um dividr entre linhas, para uma melhor visualização.
        rvBusinessDirectory.addItemDecoration(
                new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        if (requestCode == CREATE_REQUEST) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                long id = data.getLongExtra(ID, -1);
                Enterprise enterprise = mEnterpriseBusiness.searchEnterprise(id);
                mBusinessDirectoryAdapter.updateList(enterprise);
            }
        } else if (requestCode == EDIT_REQUEST) {
            if (resultCode == RESULT_OK) {
                long id = data.getLongExtra(ID, -1);
                mBusinessDirectoryAdapter.notifyItemChanged(mPositionUpdated);
            }

        }
    }

    public void updateEnterprise(Enterprise userModel, int position) {
        mPositionUpdated = position;
        Intent intent = new Intent(this, AddEnterpriseActivity.class);
        intent.putExtra(ID, userModel.getId());
        startActivityForResult(intent, EDIT_REQUEST);
    }
}
