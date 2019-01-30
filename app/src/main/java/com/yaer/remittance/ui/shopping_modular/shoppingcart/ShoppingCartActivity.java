package com.yaer.remittance.ui.shopping_modular.shoppingcart;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ocnyang.cartlayout.bean.CartItemBean;
import com.ocnyang.cartlayout.bean.ICartItem;
import com.ocnyang.cartlayout.listener.CartOnCheckChangeListener;
import com.yaer.remittance.R;
import com.yaer.remittance.base.BaseActivity;
import com.yaer.remittance.ui.login_modular.LoginActivity;
import com.yaer.remittance.ui.shopping_modular.shoppingcart.bean.BuyerBean;
import com.yaer.remittance.ui.shopping_modular.shoppingcart.bean.ComFirmOrderBean;
import com.yaer.remittance.ui.shopping_modular.shoppingcart.bean.GoodsAndShopInfo;
import com.yaer.remittance.ui.shopping_modular.shoppingcart.bean.GoodsBean;
import com.yaer.remittance.ui.shopping_modular.shoppingcart.bean.ShopBean;
import com.yaer.remittance.ui.shopping_modular.shoppingcart.bean.ShopCartBeanDaoUtil;
import com.yaer.remittance.ui.shopping_modular.shoppingcart.bean.ShopInfoModelBean;
import com.yaer.remittance.utils.AppUtile;
import com.yaer.remittance.utils.NetworkUtils;
import com.yaer.remittance.utils.ToastUtils;
import com.yaer.remittance.view.CustomTitlebar;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/6/18.
 * 购物车
 */
public class ShoppingCartActivity extends BaseActivity implements CustomTitlebar.TitleBarOnClickListener {
    /*显示数据的view*/
    @BindView(R.id.recycler)
    RecyclerView recyclerView;
    /*全选按钮*/
    @BindView(R.id.checkbox_all)
    CheckBox mCheckBoxAll;
    /*合计金额*/
    @BindView(R.id.tv_total_price)
    TextView mTvTotal;
    /*去结算按钮*/
    @BindView(R.id.btn_go_to_pay)
    Button mBtnSubmit;
    /*移入收藏*/
 /*   @BindView(R.id.btn_add_favorites)
    Button mBtnFavorites;*/
    /*标题栏*/
    @BindView(R.id.ct_shopping_cart)
    CustomTitlebar ct_shopping_cart;
    /*合计LL*/
    @BindView(R.id.ll_total)
    LinearLayout ll_total;
    /*返回按钮
    @BindView(R.id.iv_return)
    AppCompatImageView iv_return;*/
    /*暂无数据*/
    @BindView(R.id.rl_no_contant)
    RelativeLayout rl_no_contant;
    @BindView(R.id.ll_bottom)
    LinearLayout ll_bottom;
    ShoppingCartAdapter mAdapter;

    private boolean isEditing;//是否处于编辑状态
    private int totalCount;//购物车商品ChildItem的总数量，店铺条目不计算在内
    private int totalCheckedCount;//勾选的商品总数量，店铺条目不计算在内
    private double totalPrice;//勾选的商品总价格

    //设置沉浸式
    @Override
    protected boolean isImmersionBarEnabled() {
        return true;
    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.titleBar(R.id.ct_shopping_cart).init();
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_shopping_cart;
    }

    @Override
    public void initView() {
        ct_shopping_cart.setAction(this);
        ct_shopping_cart.setTilte(getString(R.string.cart, 0));
        mBtnSubmit.setText(getString(R.string.go_settle_X, 0));
        mTvTotal.setText(getString(R.string.rmb_X, 0.00));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new ShoppingCartAdapter(this, getData());
        mAdapter.setOnCheckChangeListener(new CartOnCheckChangeListener(recyclerView, mAdapter) {
            @Override
            public void onCalculateChanged(ICartItem cartItemBean) {
                calculate();
            }
        });
        recyclerView.setAdapter(mAdapter);
        // 给列表注册 ContextMenu 事件。
        // 同时如果想让ItemView响应长按弹出菜单，需要在item xml布局中设置 android:longClickable="true"
        registerForContextMenu(recyclerView);
    }

    @Override
    public void initData() {
    }
    @Override
    public void performAction(View view) {
        switch (view.getId()) {
            case R.id.iv_left:
                finish();
                break;
            case R.id.tv_right:
                isEditing = !isEditing;
                ct_shopping_cart.setTvRight(getString(isEditing ? R.string.edit_done : R.string.edit));
                /*mTvEdit.setText();*/
                mBtnSubmit.setText(getString(isEditing ? R.string.delete_X : R.string.go_settle_X, totalCheckedCount));
                if (isEditing) {
                    //mBtnFavorites.setVisibility(View.VISIBLE);
                    ll_total.setVisibility(View.INVISIBLE);
                } else {
                   // mBtnFavorites.setVisibility(View.GONE);
                    ll_total.setVisibility(View.VISIBLE);
                }
                break;
        }
    }

    @OnClick({R.id.checkbox_all, R.id.btn_go_to_pay})
    public void onClick(View v) {
        switch (v.getId()) {
            /*全选按钮*/
            case R.id.checkbox_all:
                mAdapter.checkedAll(((CheckBox) v).isChecked());
                break;
            /*结算按钮*/
            case R.id.btn_go_to_pay:
                if (!NetworkUtils.isNetworkConnected(this)) {
                    ToastUtils.showToast("当前无网络请链接网络");
                } else if (AppUtile.getTicket(this).equals("")) {
                    goToActivity(LoginActivity.class, "type", "5");
                }else{
                    submitEvent();
                }
                break;
            /*加入收藏*/
       /*     case R.id.btn_add_favorites:
                submitFavorites();
                break;*/
        }
    }

    private GoodsAndShopInfo goodsAndShopInfo = null;

    /**
     * 统计操作<br>
     * 1.先清空全局计数器<br>
     * 2.遍历所有子元素，只要是被选中状态的，就进行相关的计算操作<br>
     * 3.给相关的 textView 进行数据填充
     */
    private void calculate() {
        //这样可以吗？看一下哈，这样分别存商品还有店铺可以吗？这样子应该就拿到了啊，我测试一下
        //清空ok了卡吗，有点

        List<ComFirmOrderBean> comFirmOrderBeanList = new ArrayList<>();
        goodsAndShopInfo = new GoodsAndShopInfo(comFirmOrderBeanList);

        totalCheckedCount = 0;
        totalCount = 0;
        totalPrice = 0.00;
        int notChildTotalCount = 0;
        if (mAdapter.getData() != null) {
            ShopBean shopBean = null;
            BuyerBean buyerBean = null;
            for (ICartItem iCartItem : mAdapter.getData()) {
                if (iCartItem.getItemType() == ICartItem.TYPE_CHILD) {
                    totalCount++;
                    if (iCartItem.isChecked()) {
                        totalCheckedCount++;
                        double currentPrice = ((GoodsBean) iCartItem).getGoods_price() * ((GoodsBean) iCartItem).getGoods_amount();
                        totalPrice += currentPrice;
                        //等我想想怎么转换成我们想要的那个数据类型
                        if (comFirmOrderBeanList.isEmpty() || comFirmOrderBeanList.get(comFirmOrderBeanList.size() - 1).getShopBean() != shopBean) {
                            ComFirmOrderBean comFirmOrderBean = new ComFirmOrderBean(shopBean, buyerBean);
                            comFirmOrderBeanList.add(comFirmOrderBean);
                        }
                        comFirmOrderBeanList.get(comFirmOrderBeanList.size() - 1).addGoodsBeanInList((GoodsBean) iCartItem);
                        if (buyerBean != null) {
                            buyerBean.addInCommodityNum(1);
                            buyerBean.addInTotalMoney(currentPrice);
                        }
                    }
                } else {
                    notChildTotalCount++;
                    shopBean = (ShopBean) iCartItem;
                    buyerBean = new BuyerBean();
                }
            }
        }

        ct_shopping_cart.setTilte(getString(R.string.cart, totalCount));
        mBtnSubmit.setText(getString(isEditing ? R.string.delete_X : R.string.go_settle_X, totalCheckedCount));
        mTvTotal.setText(getString(R.string.rmb_X, totalPrice));
        if (mCheckBoxAll.isChecked() && (totalCheckedCount == 0 || (totalCheckedCount + notChildTotalCount) != mAdapter.getData().size())) {
            mCheckBoxAll.setChecked(false);
        }
        if (totalCheckedCount != 0 && (!mCheckBoxAll.isChecked()) && (totalCheckedCount + notChildTotalCount) == mAdapter.getData().size()) {
            mCheckBoxAll.setChecked(true);
        }

        goodsAndShopInfo.setTotalCount(totalCheckedCount);
        goodsAndShopInfo.setTotalPrice(totalPrice);
    }

    private void submitFavorites() {
        if (isEditing) {
            if (totalCheckedCount == 0) {
                Toast.makeText(this, "请勾选你要移入收藏的商品", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "移入收藏成功", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void submitEvent() {
        if (isEditing) {
            if (totalCheckedCount == 0) {
                Toast.makeText(this, "请勾选你要删除的商品", Toast.LENGTH_SHORT).show();
            } else {
                if (goodsAndShopInfo != null) {
                    for (ComFirmOrderBean comFirmOrderBean : goodsAndShopInfo.getComFirmOrderBeanList()) {
                        for (GoodsBean goodsBean : comFirmOrderBean.getGoodsBeanList()) {
                            ShopCartBeanDaoUtil.deleteGoodsById(goodsBean.getGid(), goodsBean.getSid());
                        }
                    }
                }
                mAdapter.removeChecked();
            }
        } else {
            if (totalCheckedCount == 0) {
                Toast.makeText(this, "你还没有选择任何商品", Toast.LENGTH_SHORT).show();
            } else {
             /*   for (int i = 0; i < goodsBeanList.size(); i++) {
                    goodsBeanList.get(i).getGimg();
                }
                for (int i = 0; i < shopBeanList.size(); i++) {
                    shopBeanList.get(i).getShop_name();
                }*/
               /* Toast.makeText(this,
                        new StringBuilder().append("你选择了").append(totalCheckedCount).append("件商品")
                                .append("共计 ").append(totalPrice).append("元"),
                        Toast.LENGTH_SHORT).show();*/
                Bundle bundle = new Bundle();
                bundle.putSerializable("GoodsAndShopInfo", goodsAndShopInfo);
                goToActivity(ConfirmOrderActivity.class, bundle);
            }
        }
//        comFirmOrderBeanList = null;
        //避免内存泄露
    }

    /**
     * 数据初始化尤其重要
     * 1. childItem 数据全部在 GroupItem 数据的下方，数据顺序严格按照对应关系；
     * 2. GroupItem 下的 ChildItem 数据不能为空；
     * 3. 初始化时如果不需要，所有类型的条目都可以不设置ID，GroupItem也不用设置setChilds()；
     * <p>
     * 列表操作时数据动态的变化设置：
     * 1. 通过 CartAdapter 的 addData、setNewData；
     * 2. 单个添加各个条目可以通过对应的 add 方法；
     * 3. 单独添加一个 GroupItem ,可以把它的 ChildItem 数据放到 setChilds 中。
     * 这里是绑定数据
     *
     * @return
     */
    private List<ShopInfoModelBean> shopInfoModelBeanList;

    private List getData() {
        ArrayList<CartItemBean> cartItemBeans = new ArrayList<>();
        String shopAndGoodsAllGson = ShopCartBeanDaoUtil.getShopAndGoodsAllGson();
        Type type = new TypeToken<List<ShopInfoModelBean>>() {
        }.getType();
        shopInfoModelBeanList = new Gson().fromJson(shopAndGoodsAllGson, type);
        if (shopInfoModelBeanList == null || shopInfoModelBeanList.size() == 0) {
            rl_no_contant.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
            ll_bottom.setVisibility(View.GONE);
        } else {
            rl_no_contant.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
            ll_bottom.setVisibility(View.VISIBLE);
            for (int i = 0; i < shopInfoModelBeanList.size(); i++) {
                ShopBean shopBean = new ShopBean();
                shopBean.setShop_sid(shopInfoModelBeanList.get(i).getSid());
                shopBean.setShop_name(shopInfoModelBeanList.get(i).getSname());
                shopBean.setShop_sid(shopInfoModelBeanList.get(i).getSid());
                shopBean.setShop_simg(shopInfoModelBeanList.get(i).getSimg());
                shopBean.setItemType(CartItemBean.TYPE_GROUP);
                cartItemBeans.add(shopBean);
                for (int j = 0; j < shopInfoModelBeanList.get(i).getShopCartBeanList().size(); j++) {
                    GoodsBean goodsBean = new GoodsBean();
                    goodsBean.setUid(shopInfoModelBeanList.get(i).getShopCartBeanList().get(j).getUid());
                    goodsBean.setGdesc(shopInfoModelBeanList.get(i).getShopCartBeanList().get(j).getGdesc());
                    goodsBean.setGid(shopInfoModelBeanList.get(i).getShopCartBeanList().get(j).getGid());
                    goodsBean.setSid(shopInfoModelBeanList.get(i).getShopCartBeanList().get(j).getSid());
                    goodsBean.setGcid(shopInfoModelBeanList.get(i).getShopCartBeanList().get(j).getGcid());
                    goodsBean.setGoods_name(shopInfoModelBeanList.get(i).getShopCartBeanList().get(j).getGname());
                    goodsBean.setGimg(shopInfoModelBeanList.get(i).getShopCartBeanList().get(j).getGimg());
                    goodsBean.setGpostage(shopInfoModelBeanList.get(i).getShopCartBeanList().get(j).getGpostage());//邮费
                    goodsBean.setGnumber(shopInfoModelBeanList.get(i).getShopCartBeanList().get(j).getGnumber());
                    goodsBean.setItemType(CartItemBean.TYPE_CHILD);
                    goodsBean.setItemId(Long.parseLong(shopInfoModelBeanList.get(i).getShopCartBeanList().get(j).getGid()));
                    goodsBean.setGoods_price(shopInfoModelBeanList.get(i).getShopCartBeanList().get(j).getGmoney());
                    goodsBean.setGroupId(i);
                    cartItemBeans.add(goodsBean);
                }
            }
        }
        return cartItemBeans;
    }

    @Override
    protected void onResume() {
        super.onResume();
        //刷新数据
        mAdapter.setNewData(getData());
        mAdapter.notifyDataSetChanged();
    }
}
