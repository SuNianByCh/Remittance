package com.yaer.remittance.ui.home_modular.search;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.orhanobut.logger.Logger;
import com.yaer.remittance.R;
import com.yaer.remittance.base.BaseActivity;
import com.yaer.remittance.view.ClearEditText;
import com.yaer.remittance.view.FlowLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 商品搜索
 */
public class ActivitySearch extends BaseActivity {
    //获取当前的热门布局
    @BindView(R.id.flowlayout)
    FlowLayout mFlowLayout;
    private LayoutInflater mInflater;
    //当前热门数据
    private String[] mVals = new String[]{"翡翠", "项链", "和田白玉", "手串",
            "小叶紫檀", "紫砂", "钱币", "袁大头", "光绪元宝", "书画", "绿松石", "南红", "天珠"};//数据模拟，实际应从网络获取此数据
    /************
     * 以上为流式标签相关
     ************/
    public static final String EXTRA_KEY_TYPE = "extra_key_type";
    public static final String EXTRA_KEY_KEYWORD = "extra_key_keyword";
    public static final String KEY_SEARCH_HISTORY_KEYWORD = "key_search_history_keyword";
    private SharedPreferences mPref;//使用SharedPreferences记录搜索历史
    private String mType;
    //当前EditText搜索控件
    @BindView(R.id.et_input)
    ClearEditText input;
    //获取当前的取消按钮控件
    @BindView(R.id.cancel)
    TextView cancel;
    //记录文本
    private List<String> mHistoryKeywords;
    //搜索历史适配器
    private ArrayAdapter<String> mArrAdapter;
    //包裹热门搜索控件
    @BindView(R.id.search_history_ll)
    LinearLayout mSearchHistoryLl;
    //展示搜索数据控件
    @BindView(R.id.search_history_lv)
    ListView listView;
    //清空搜索记录界面
    @BindView(R.id.clear_history_btn)
    TextView clear_history_btn;
    //拿到的搜索结果
    private String keywords;
    private int currIndex;//当前页卡编号
    @BindView(R.id.rl_search_title)
    LinearLayout rl_search_title;

    //设置沉浸式
    @Override
    protected boolean isImmersionBarEnabled() {
        return true;
    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.titleBar(R.id.rl_search_title).init();
    }

    @Override
    public void initParms(Bundle parms) {
        /* MobclickAgent.onEvent(this, "SearchEncyclopediaActivity");*/
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_search;
    }

    @Override
    public void initView() {
        mInflater = LayoutInflater.from(this);
        currIndex = getIntent().getIntExtra("currIndex", 0);
        Logger.e(currIndex + "++++++=currIndex");
        //软键盘点击搜索控件方法
        input.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    keywords = input.getText().toString();
                    if (!TextUtils.isEmpty(keywords)) {
//                    Toast.makeText(this, keywords + "save成功", Toast.LENGTH_LONG).show();
                        //搜索记录成功
                        save();
                        //goToActivity(WikipediaSearchContent.class);
                     /*  Intent intent = new Intent(SearchEncyclopediaActivity.this, WikipediaSearchContent.class);
                        intent.putExtra("currIndex",currIndex);
                        intent.putExtra("title", keywords);
                        startActivity(intent);*/
                        Intent intent = new Intent(ActivitySearch.this, WikipediaSearchContent.class);
                        intent.putExtra("title", keywords);
                        startActivity(intent);
                    } else {
                        Toast.makeText(ActivitySearch.this, "请输入搜索内容", Toast.LENGTH_LONG).show();
                    }
                    return true;
                }
                return false;
            }
        });
        initliu();
        initHistoryView();
    }

    @Override
    public void initData() {

    }


    /**
     * 将数据放入流式布局
     */
    private void initliu() {
        if (currIndex == 0) {
            /*mVals = mValss;*/
        }
        for (int i = 0; i < mVals.length; i++) {
            TextView tv = (TextView) mInflater.inflate(
                    R.layout.search_label_tv, mFlowLayout, false);
            tv.setText(mVals[i]);
            tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
            tv.setTextColor(Color.parseColor("#999999"));
            final String str = tv.getText().toString();
            //点击事件
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //加入搜索历史纪录记录
                    //showToast("hj" + str);
                    input.setText(str);
                    if (mHistoryKeywords.size() > 0) {
                        mSearchHistoryLl.setVisibility(View.VISIBLE);
                    } else {
                        mSearchHistoryLl.setVisibility(View.GONE);
                    }
                    save();
                    //goToActivity(WikipediaSearchContent.class);
                    Intent intent = new Intent(ActivitySearch.this, WikipediaSearchContent.class);
                    intent.putExtra("title", str);
                    startActivity(intent);

                }
            });
            mFlowLayout.addView(tv);
        }
    }

    /************
     * 以上为流式标签相关
     ************/

    private void initHistoryView() {
        mPref = getSharedPreferences("input", Activity.MODE_PRIVATE);
        mType = getIntent().getStringExtra(EXTRA_KEY_TYPE);
        String keyword = getIntent().getStringExtra(EXTRA_KEY_KEYWORD);
        if (!TextUtils.isEmpty(keyword)) {
            input.setText(keyword);
        }
        mHistoryKeywords = new ArrayList<String>();
        input.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (mHistoryKeywords.size() > 0) {
                    mSearchHistoryLl.setVisibility(View.VISIBLE);
                } else {
                    mSearchHistoryLl.setVisibility(View.GONE);
                }
             /*   if (s.length() == 0) {
                    if (mHistoryKeywords.size() > 0) {
                        mSearchHistoryLl.setVisibility(View.VISIBLE);
                    } else {
                        mSearchHistoryLl.setVisibility(View.GONE);
                    }
                } else {
                    mSearchHistoryLl.setVisibility(View.GONE);
                }*/
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        initSearchHistory();
    }

    /**
     * 初始化搜索历史记录
     */
    public void initSearchHistory() {
        String history = mPref.getString(KEY_SEARCH_HISTORY_KEYWORD, "");
        if (!TextUtils.isEmpty(history)) {
            List<String> list = new ArrayList<String>();
            for (Object o : history.split(",")) {
                list.add((String) o);
            }
            mHistoryKeywords = list;
        }
        if (mHistoryKeywords.size() > 0) {
            mSearchHistoryLl.setVisibility(View.VISIBLE);
        } else {
            mSearchHistoryLl.setVisibility(View.GONE);
        }
        mArrAdapter = new ArrayAdapter<String>(this, R.layout.item_search_history, mHistoryKeywords);
        listView.setAdapter(mArrAdapter);
//        setListViewHeightBasedOnChildren(listView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                input.setText(mHistoryKeywords.get(i));
                mSearchHistoryLl.setVisibility(View.GONE);
                //  goToActivity(WikipediaSearchContent.class);
              /*  Intent intent = new Intent(SearchEncyclopediaActivity.this, WikipediaSearchContent.class);
                intent.putExtra("currIndex",currIndex);
                intent.putExtra("title", mHistoryKeywords.get(i));
                startActivity(intent);*/
                Intent intent = new Intent(ActivitySearch.this, WikipediaSearchContent.class);
                intent.putExtra("title", mHistoryKeywords.get(i));
                startActivity(intent);
            }
        });
        mArrAdapter.notifyDataSetChanged();
    }

    /**
     * 储存搜索历史
     */
    public void save() {
        String text = input.getText().toString();
        String oldText = mPref.getString(KEY_SEARCH_HISTORY_KEYWORD, "");
        Log.e("tag", "" + oldText);
        Log.e("Tag", "" + text);
        Log.e("Tag", "" + oldText.contains(text));
        if (!TextUtils.isEmpty(text) && !(oldText.contains(text))) {
            if (mHistoryKeywords.size() > 20) {
                //最多保存条数
                return;
            }
            SharedPreferences.Editor editor = mPref.edit();
            editor.putString(KEY_SEARCH_HISTORY_KEYWORD, text + "," + oldText);
            editor.commit();
            mHistoryKeywords.add(0, text);
        }
        mArrAdapter.notifyDataSetChanged();
    }

    /**
     * 清除历史纪录
     */
    public void cleanHistory() {
        mPref = getSharedPreferences("input", MODE_PRIVATE);
        SharedPreferences.Editor editor = mPref.edit();
        editor.remove(KEY_SEARCH_HISTORY_KEYWORD).commit();
        mHistoryKeywords.clear();
        mArrAdapter.notifyDataSetChanged();
        mSearchHistoryLl.setVisibility(View.GONE);
        Toast.makeText(this, "清除搜索历史成功", Toast.LENGTH_LONG).show();
    }

    @OnClick({R.id.et_input, R.id.clear_history_btn, R.id.cancel})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.et_input:
                break;
            //清空搜索记录方法
            case R.id.clear_history_btn:
                cleanHistory();
                break;
            //取消方法
            case R.id.cancel:
                finish();
                break;
        }
    }
}
