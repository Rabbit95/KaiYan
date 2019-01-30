package com.rabbit.kaiyan.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.rabbit.kaiyan.R;
import com.rabbit.kaiyan.base.BaseActivity;
import com.rabbit.kaiyan.base.contract.LoginContract;
import com.rabbit.kaiyan.presenter.LoginPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginContract.View {

    @BindView(R.id.iv_account_close)
    ImageView ivAccountClose;
    @BindView(R.id.et_account_name)
    EditText etAccountName;
    @BindView(R.id.et_account_pass)
    EditText etAccountPass;
    @BindView(R.id.bt_account_login)
    Button btAccountLogin;

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected void initEventAndData() {
        ivAccountClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btAccountLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(etAccountName.getText().equals("") || etAccountName.getText() == null || etAccountPass.getText().equals("") || etAccountPass.getText() == null){
                    showToastMsg("用户名或密码不能为空");
                }else{
                    mPresenter.checkAccount(etAccountName.getText().toString(),etAccountPass.getText().toString());
                }
            }
        });
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public void showToastMsg(String msg) {
         Toast.makeText(mContext,msg,Toast.LENGTH_LONG).show();
    }

    @Override
    public void closeView() {
        finish();
    }
}
