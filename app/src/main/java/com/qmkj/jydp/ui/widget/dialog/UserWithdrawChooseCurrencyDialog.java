package com.qmkj.jydp.ui.widget.dialog;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.qmkj.jydp.R;
import com.qmkj.jydp.bean.response.UserCoinWithdrawInfo;
import com.qmkj.jydp.ui.widget.dialog.base.BaseBottomDialog;
import com.qmkj.jydp.util.ListUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 选择币种dialog
 */
public class UserWithdrawChooseCurrencyDialog extends BaseBottomDialog {
    private ListView lv;
    private List<UserCoinWithdrawInfo.CoinWithdrawInfo> itemBeanList = new ArrayList<>();
    private UserCoinWithdrawInfo.CoinWithdrawInfo mChooseModel;
    private ListViewAdapter adapter;
    private OnChooseCurrencyListener onChooseCurrencyListener;

    public void setOnChooseCurrencyListener(OnChooseCurrencyListener onChooseCurrencyListener) {
        this.onChooseCurrencyListener = onChooseCurrencyListener;
    }

    public interface OnChooseCurrencyListener {
        void onChooseCurrency(UserCoinWithdrawInfo.CoinWithdrawInfo bean);
    }

    public UserWithdrawChooseCurrencyDialog(@NonNull Context context, List<UserCoinWithdrawInfo.CoinWithdrawInfo> itemBeanList) {
        super(context);
        if(itemBeanList!=null){
            this.itemBeanList.addAll(itemBeanList);
        }
    }

    @Override
    protected View setContentView() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_below_user_withdraw_choose_currency_content, null);
        return view;
    }

    @Override
    protected void initContentView(View contentView) {
        itemBeanList = new ArrayList<>();
        lv = contentView.findViewById(R.id.dialog_below_choose_currency_lv);
        setTitleText("选择币种");
        adapter = new ListViewAdapter();
        lv.setAdapter(adapter);
        setSelected(mChooseModel);
        lv.setOnItemClickListener((parent, view, position, id) -> {
            mChooseModel = itemBeanList.get(position);
            setSelected(mChooseModel);
        });
        setOnDismissListener(dialog -> {
            if (onChooseCurrencyListener != null && mChooseModel != null) {
                onChooseCurrencyListener.onChooseCurrency(mChooseModel);
            }
        });
    }

    /**
     * 设置选中
     *
     * @param chooseModel
     */
    private void setSelected(UserCoinWithdrawInfo.CoinWithdrawInfo chooseModel) {
        if (ListUtils.isEmpty(itemBeanList)) {
            return;
        }
        //默认第一个选中
        if (chooseModel == null) {
            for (UserCoinWithdrawInfo.CoinWithdrawInfo model : itemBeanList) {
                if (itemBeanList.indexOf(model) == 0) {
                    model.setSelected(true);
                    mChooseModel = model;
                } else {
                    model.setSelected(false);
                }
            }
        } else {
            for (UserCoinWithdrawInfo.CoinWithdrawInfo model : itemBeanList) {
                model.setSelected(chooseModel.getCurrencyId() == model.getCurrencyId());
            }
        }
        adapter.notifyDataSetChanged();
    }

    private class ListViewAdapter extends BaseAdapter {
        ViewHolder viewHolder;

        @Override
        public int getCount() {
            return itemBeanList.size();
        }

        @Override
        public Object getItem(int position) {
            return itemBeanList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_choose_currency_dialog_lv, parent, false);
                viewHolder = new ViewHolder();
                viewHolder.tv = convertView.findViewById(R.id.item_choose_currency_tv);
                viewHolder.rb = convertView.findViewById(R.id.item_choose_currency_rb);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            UserCoinWithdrawInfo.CoinWithdrawInfo itemBean = itemBeanList.get(position);
            viewHolder.tv.setText(itemBean.getCurrencyName());
            viewHolder.rb.setChecked(itemBean.isSelected());
            return convertView;
        }

        class ViewHolder {
            TextView tv;
            RadioButton rb;
        }
    }
}
