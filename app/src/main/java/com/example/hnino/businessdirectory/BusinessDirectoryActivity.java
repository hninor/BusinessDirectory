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

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BusinessDirectoryActivity extends AppCompatActivity {

    @BindView(R.id.rvBusinessDirectory)
    RecyclerView rvBusinessDirectory;

    //private LineAdapter mAdapter;
    public static final int CREATE_REQUEST = 11;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_directory);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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

        // Configurando o gerenciador de layout para ser uma lista.
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rvBusinessDirectory.setLayoutManager(layoutManager);

        // Adiciona o adapter que irá anexar os objetos à lista.
        // Está sendo criado com lista vazia, pois será preenchida posteriormente.
        //mAdapter = new LineAdapter(new ArrayList<>(0));
        //rvBusinessDirectory.setAdapter(mAdapter);

        // Configurando um dividr entre linhas, para uma melhor visualização.
        rvBusinessDirectory.addItemDecoration(
                new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
    }



}
