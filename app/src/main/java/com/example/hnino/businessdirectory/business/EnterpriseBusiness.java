package com.example.hnino.businessdirectory.business;

import android.content.Context;

import com.example.hnino.businessdirectory.BusinessDirectoryApp;
import com.example.hnino.businessdirectory.entities.DaoSession;
import com.example.hnino.businessdirectory.entities.Enterprise;
import com.example.hnino.businessdirectory.entities.EnterpriseDao;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

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

    public long saveEnterprise(Enterprise acometidaDB) {
        return mDaoSession.getEnterpriseDao().insert(acometidaDB);
    }

    public void deleteEnterprise(Enterprise acometidaDB) {
        mDaoSession.getEnterpriseDao().delete(acometidaDB);
    }

    public Enterprise searchEnterprise(long id) {
        QueryBuilder<Enterprise> queryBuilder = mDaoSession.getEnterpriseDao().queryBuilder();
        return queryBuilder.where(EnterpriseDao.Properties.Id.eq(id)).unique();

    }

    public List<Enterprise> getAll() {
        QueryBuilder<Enterprise> queryBuilder = mDaoSession.getEnterpriseDao().queryBuilder();
        return queryBuilder.list();
    }

    public long saveEnterprise(String name, String url, String phone, String email, String products, String classification) {
        Enterprise enterprise = new Enterprise();
        enterprise.setName(name);
        enterprise.setUrl(url);
        enterprise.setPhone(phone);
        enterprise.setEmail(email);
        enterprise.setProducts(products);
        enterprise.setClassification(classification);
        return saveEnterprise(enterprise);
    }

    public void updateEnterprise(long id, String name, String url, String phone, String email, String products, String classification) {
        Enterprise enterprise = searchEnterprise(id);
        enterprise.setName(name);
        enterprise.setUrl(url);
        enterprise.setPhone(phone);
        enterprise.setEmail(email);
        enterprise.setProducts(products);
        enterprise.setClassification(classification);
        mDaoSession.getEnterpriseDao().update(enterprise);
    }
}
