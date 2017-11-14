package com.example.hnino.businessdirectory.business;

import android.content.Context;

import com.example.hnino.businessdirectory.BusinessDirectoryApp;
import com.example.hnino.businessdirectory.entities.DaoSession;
import com.example.hnino.businessdirectory.entities.Enterprise;
import com.example.hnino.businessdirectory.entities.EnterpriseDao;

import org.greenrobot.greendao.query.QueryBuilder;

/**
 * Created by hnino on 13/11/2017.
 */

public class EnterpriseBusiness {

    private DaoSession mDaoSession;
    private BusinessDirectoryApp mApp;

    public EnterpriseBusiness(Context context) {
        mApp = (BusinessDirectoryApp) context;
        mDaoSession = mApp.getDaoSession();
    }

    public void saveEnterprise(Enterprise acometidaDB) {
        Enterprise tblRotAcometidaEncontradaDB = searchEnterprise(acometidaDB.getId());
        if ( tblRotAcometidaEncontradaDB == null) {
            mDaoSession.getEnterpriseDao().insert(acometidaDB);
        } else {
            acometidaDB.setId(tblRotAcometidaEncontradaDB.getId());
            mDaoSession.getEnterpriseDao().update(acometidaDB);
        }

    }

    public Enterprise searchEnterprise(long idOrdenTrabajo) {
        QueryBuilder<Enterprise> queryBuilder = mDaoSession.getEnterpriseDao().queryBuilder();
        return queryBuilder.where(EnterpriseDao.Properties.Id.eq(idOrdenTrabajo)).unique();

    }
}
