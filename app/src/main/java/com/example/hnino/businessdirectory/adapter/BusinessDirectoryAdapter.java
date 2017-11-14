package com.example.hnino.businessdirectory.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.hnino.businessdirectory.R;
import com.example.hnino.businessdirectory.entities.Enterprise;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by hnino on 13/11/2017.
 */

public class BusinessDirectoryAdapter extends RecyclerView.Adapter<BusinessDirectoryAdapter.LineHolder> {

    private final List<Enterprise> mUsers;

    public BusinessDirectoryAdapter(ArrayList users) {
        mUsers = users;
    }

    @Override
    public LineHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new LineHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.business_item, parent, false));
    }

    @Override
    public void onBindViewHolder(LineHolder holder, final int position) {
        holder.title.setText(String.format(Locale.getDefault(), "%s, %d - %s",
                mUsers.get(position).getName(),
                mUsers.get(position).getPhone(),
                mUsers.get(position).getEmail()
        ));

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
        //userModel.incrementAge();
        notifyItemChanged(position);
    }

    // Método responsável por remover um usuário da lista.
    private void removerItem(int position) {
        mUsers.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, mUsers.size());
    }

    @Override
    public int getItemCount() {
        return mUsers != null ? mUsers.size() : 0;
    }

    public class LineHolder extends RecyclerView.ViewHolder {

        public TextView title;
        public ImageButton moreButton;
        public ImageButton deleteButton;

        public LineHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.main_line_title);
            //moreButton = (ImageButton) itemView.findViewById(R.id.main_line_more);
            //deleteButton = (ImageButton) itemView.findViewById(R.id.main_line_delete);
        }
    }
}
