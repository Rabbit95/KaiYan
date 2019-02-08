package com.rabbit.kaiyan.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.rabbit.kaiyan.R;
import com.rabbit.kaiyan.base.BaseActivity;
import com.rabbit.kaiyan.base.contract.LoginContract;
import com.rabbit.kaiyan.presenter.LoginPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginContract.View {

    @BindView(R.id.iv_account_close)
    ImageView ivAccountClose;
    @BindView(R.id.et_account_name)
    EditText etAccountName;
    @BindView(R.id.et_account_pass)
    EditText etAccountPass;
    @BindView(R.id.bt_account_login)
    Button btAccountLogin;
    @BindView(R.id.et_account_email)
    EditText etAccountEmail;
    @BindView(R.id.tv_account_register)
    TextView tvAccountRegister;
    @BindView(R.id.account_email_icon)
    ImageView accountEmailIcon;

    private boolean isLoginAction = true;

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
                    if (isLoginAction) {
                        //登录动作
                        mPresenter.checkAccount(etAccountName.getText().toString().trim(), etAccountPass.getText().toString().trim());
                    } else {
                        //注册动作
                        mPresenter.register(etAccountName.getText().toString().trim(), etAccountPass.getText().toString().trim(),etAccountEmail.getText().toString().trim());
                    }
            }
        });
    }

    @OnClick(R.id.tv_account_register)
    public void register() {
        changeView();
    }
    @Override
    public void changeView() {
        if (isLoginAction) {
            isLoginAction = false;
            etAccountEmail.setVisibility(View.VISIBLE);
            accountEmailIcon.setVisibility(View.VISIBLE);
            btAccountLogin.setText("注册");
            tvAccountRegister.setText("用户登录");
        } else {
            isLoginAction = true;
            etAccountEmail.setVisibility(View.GONE);
            accountEmailIcon.setVisibility(View.GONE);
            btAccountLogin.setText("登录");
            tvAccountRegister.setText("用户注册");
        }
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
        Toast.makeText(mContext, msg, Toast.LENGTH_LONG).show();
    }

    @Override
    public void closeView() {
        finish();
    }
}
