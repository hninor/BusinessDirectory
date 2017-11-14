package com.example.hnino.businessdirectory.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.hnino.businessdirectory.BusinessDirectoryActivity;
import com.example.hnino.businessdirectory.R;
import com.example.hnino.businessdirectory.business.EnterpriseBusiness;
import com.example.hnino.businessdirectory.entities.Enterprise;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by hnino on 13/11/2017.
 */

public class BusinessDirectoryAdapter extends RecyclerView.Adapter<BusinessDirectoryAdapter.LineHolder> {

    private List<Enterprise> mUsers;
    private EnterpriseBusiness mEnterpriseBusiness;
    private  BusinessDirectoryActivity mBusinessDirectoryActivity;

    public BusinessDirectoryAdapter(List<Enterprise> users, EnterpriseBusiness enterpriseBusiness, BusinessDirectoryActivity businessDirectoryActivity) {
        mUsers = users;
        mEnterpriseBusiness =  enterpriseBusiness;
        mBusinessDirectoryActivity = businessDirectoryActivity;
    }

    @Override
    public LineHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new LineHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.business_item, parent, false));
    }

    @Override
    public void onBindViewHolder(LineHolder holder, final int position) {
        holder.tvName.setText(mUsers.get(position).getName());
        holder.tvProducts.setText(mUsers.get(position).getProducts());
        holder.tvClassification.setText(mUsers.get(position).getClassification());

        holder.moreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateItem(position);
            }
        });
        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removerItem(position);
            }
        });
    }

    /**
     * Método publico chamado para atualziar a lista.
     *
     * @param user Novo objeto que será incluido na lista.
     */
    public void updateList(Enterprise user) {
        insertItem(user);
    }

    // Método responsável por inserir um novo usuário na lista e notificar que há novos itens.
    private void insertItem(Enterprise user) {
        mUsers.add(user);
        notifyItemInserted(getItemCount());
    }

    // Método responsável por atualizar um usuário já existente na lista.
    private void updateItem(int position) {
        Enterprise userModel = mUsers.get(position);
        mBusinessDirectoryActivity.updateEnterprise(userModel, position);
    }

    // Método responsável por remover um usuário da lista.
    private void removerItem(int position) {
        mEnterpriseBusiness.deleteEnterprise(mUsers.get(position));
        mUsers.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, mUsers.size());
    }

    @Override
    public int getItemCount() {
        return mUsers != null ? mUsers.size() : 0;
    }

    public class LineHolder extends RecyclerView.ViewHolder {

        public TextView tvName;
        public TextView tvProducts;
        public TextView tvClassification;
        public ImageButton moreButton;
        public ImageButton deleteButton;

        public LineHolder(View itemView) {
            super(itemView);
            tvName = (TextView) itemView.findViewById(R.id.tvName);
            tvProducts = (TextView) itemView.findViewById(R.id.tvProducts);
            tvClassification = (TextView) itemView.findViewById(R.id.tvClassification);
            moreButton = (ImageButton) itemView.findViewById(R.id.ibUpdate);
            deleteButton = (ImageButton) itemView.findViewById(R.id.ibDelete);
        }
    }
}
