package com.zzh.dell.guoku.activity;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import com.zzh.dell.guoku.R;
import com.zzh.dell.guoku.fragment.CategoryFragment;
import com.zzh.dell.guoku.fragment.MeFragment;
import com.zzh.dell.guoku.fragment.MessageFragment;
import com.zzh.dell.guoku.fragment.RecommendFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 王立鹏
 */
public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener{
    @BindView(R.id.rl_content)
    RelativeLayout rl_content;
    @BindView(R.id.rl_bottom)
    RadioGroup rl_bottom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
    }

    FragmentManager manager;
    RecommendFragment recommend;
    CategoryFragment category;
    MessageFragment message;
    MeFragment me;
    private void initView() {
        recommend = new RecommendFragment();
        category = new CategoryFragment();
        message = new MessageFragment();
        me = new MeFragment();
        //组装fra
        manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.rl_content,me).hide(me);
        transaction.add(R.id.rl_content,message).hide(message);
        transaction.add(R.id.rl_content,category).hide(category);
        transaction.add(R.id.rl_content,recommend);
        transaction.commit();
        rl_bottom.setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        FragmentTransaction fragmentTransaction = manager.beginTransaction();
        hide(fragmentTransaction);
        switch (checkedId){
            case R.id.selection:
                fragmentTransaction.show(recommend);
                break;
            case R.id.discover:
                fragmentTransaction.show(category);
                break;
            case R.id.notification:
                fragmentTransaction.show(message);
                break;
            case R.id.me:
                fragmentTransaction.show(me);
                break;
        }
        fragmentTransaction.commit();

    }

    private void hide(FragmentTransaction fragmentTransaction) {
        fragmentTransaction.hide(me);
        fragmentTransaction.hide(message);
        fragmentTransaction.hide(category);
        fragmentTransaction.hide(recommend);
    }
}
