package com.qmkj.jydp.module.exchangecenter.view;

import android.graphics.Matrix;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.widget.TextView;

import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.CandleData;
import com.github.mikephil.charting.data.CandleDataSet;
import com.github.mikephil.charting.data.CandleEntry;
import com.github.mikephil.charting.data.CombinedData;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.qmkj.jydp.R;
import com.qmkj.jydp.base.BaseMvpActivity;
import com.qmkj.jydp.bean.request.ExchangeDealRecodeReq;
import com.qmkj.jydp.bean.request.KlineReq;
import com.qmkj.jydp.bean.response.DealRecodeRes;
import com.qmkj.jydp.common.Constants;
import com.qmkj.jydp.module.exchangecenter.presenter.DealRecodeRecAdapter;
import com.qmkj.jydp.module.exchangecenter.presenter.ExchangeCenterPresenter;
import com.qmkj.jydp.ui.widget.kline.CoupleChartGestureListener;
import com.qmkj.jydp.ui.widget.kline.DataParse;
import com.qmkj.jydp.ui.widget.kline.KLineBean;
import com.qmkj.jydp.ui.widget.kline.KMAEntity;
import com.qmkj.jydp.ui.widget.kline.MyBottomMarkerView;
import com.qmkj.jydp.ui.widget.kline.MyCombinedChart;
import com.qmkj.jydp.ui.widget.kline.MyHMarkerView;
import com.qmkj.jydp.ui.widget.kline.MyLeftMarkerView;
import com.qmkj.jydp.util.CommonUtil;
import com.qmkj.jydp.util.LogUtil;
import com.qmkj.jydp.util.VolFormatter;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * author：rongkui.xiao --2018/3/30
 * email：dovexiaoen@163.com
 * description:k线图界面
 */

public class KlineActivity extends BaseMvpActivity<ExchangeCenterPresenter> {

    private static final int KLINE_DATA_TAG = 1;
    private static final int EXCHANGE_DEAL_RECODE_TAG = 2;
    @BindView(R.id.kline_tv_close)
    TextView mTvClose;
    //开，收，高，低，量，换，额，查，比
    @BindView(R.id.exchange_high_title_tv)
    TextView exchangeHighTitleTv;
    @BindView(R.id.kline_tv_max)
    TextView mTvMax;
    @BindView(R.id.exchange_lowest_title_tv)
    TextView exchangeLowestTitleTv;
    @BindView(R.id.kline_tv_min)
    TextView mTvMin;
    @BindView(R.id.exchange_buy_one_title_tv)
    TextView exchangeBuyOneTitleTv;
    @BindView(R.id.exchange_buy_one_price_tv)
    TextView exchangeBuyOnePriceTv;
    @BindView(R.id.exchange_sell_one_title_tv)
    TextView exchangeSellOneTitleTv;
    @BindView(R.id.exchange_sell_one_price_tv)
    TextView exchangeSellOnePriceTv;
    @BindView(R.id.kline_tv_num)
    TextView mTvNum;
    @BindView(R.id.kline_chart_k)
    MyCombinedChart mChartKline;
    @BindView(R.id.kline_chart_volume)
    MyCombinedChart mChartVolume;
    @BindView(R.id.exchange_deal_recode_rv)
    RecyclerView exchangeDealRecodeRv;
    @BindView(R.id.title_header_tv)
    TextView titleHeaderTv;
    private XAxis xAxisKline;
    private YAxis axisLeftKline;
    private YAxis axisRightKline;
    private DataParse mData;
    private DataParse mCacheData;
    private ArrayList<KLineBean> kLineDatas;
    private XAxis xAxisVolume;
    private YAxis axisLeftVolume;
    private YAxis axisRightVolume;
    private String currencyId;
    private String currencyName;
    private DealRecodeRecAdapter dealRecodeRecAdapter;
    List<DealRecodeRes.DealListBean> recodeDatas = new ArrayList<>();

    @Override
    protected void injectPresenter() {
        getActivityComponent().inject(this);
    }

    @Override
    protected void initData() {
        initChartKline();
        initChartVolume();
        initCharData();
        setKLineByChart(mChartKline);
        setVolumeByChart(mChartVolume);

        setChartListener();

        mChartKline.moveViewToX(kLineDatas.size() - 1);
        mChartVolume.moveViewToX(kLineDatas.size() - 1);

        getKlineData(true);
        getExchangeDealRecode(true);
    }

    private void getExchangeDealRecode(boolean b) {
        ExchangeDealRecodeReq exchangeDealRecodeReq = new ExchangeDealRecodeReq();
        exchangeDealRecodeReq.setCurrencyId(currencyId);
        presenter.getExchangeDealRecode(exchangeDealRecodeReq, EXCHANGE_DEAL_RECODE_TAG, b);
    }

    private void getKlineData(boolean b) {
        KlineReq klineReq = new KlineReq();
        klineReq.setCurrencyId(currencyId);
        klineReq.setNode(System.currentTimeMillis());
        presenter.getKlineData(klineReq, KLINE_DATA_TAG, b);
    }

    @Override
    protected void initTitle() {
        titleHeaderTv.setText(currencyName);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.exchange_activity_k_line;
    }

    @Override
    protected void initView() {
        currencyId = getIntent().getStringExtra(Constants.INTENT_PARAMETER_1);
        currencyName = getIntent().getStringExtra(Constants.INTENT_PARAMETER_2);
        initRecycleView();
    }

    private void initRecycleView() {
        dealRecodeRecAdapter = new DealRecodeRecAdapter(mContext, recodeDatas, R.layout
                .exchange_deal_recode_item);
        exchangeDealRecodeRv.setLayoutManager(new LinearLayoutManager(mContext));
        exchangeDealRecodeRv.setAdapter(dealRecodeRecAdapter);
    }


    private void initCharData() {
        getOffLineData();
        setKLineDatas();

        setMarkerViewKline(mData, mChartKline);
        setMarkerViewVolume(mData, mChartVolume);
    }

    private void setMarkerViewKline(DataParse mData, MyCombinedChart mChartKline) {
        MyLeftMarkerView leftMarkerView = new MyLeftMarkerView(mContext, R.layout.exchange_kline_left_view);
        MyHMarkerView hMarkerView = new MyHMarkerView(mContext, R.layout.exchange_kline_line_view);
        mChartKline.setMarker(leftMarkerView, hMarkerView, mData);
    }

    private void setMarkerViewVolume(DataParse mData, MyCombinedChart combinedChart) {
        MyLeftMarkerView leftMarkerView = new MyLeftMarkerView(mContext, R.layout.exchange_kline_left_view);
        MyHMarkerView hMarkerView = new MyHMarkerView(mContext, R.layout.exchange_kline_line_view);
        MyBottomMarkerView bottomMarkerView = new MyBottomMarkerView(mContext, R.layout.exchange_kline_left_view);
        combinedChart.setMarker(leftMarkerView, bottomMarkerView, hMarkerView, mData);
    }

    private void setChartListener() {
        // 将K线控的滑动事件传递给交易量控件
        mChartKline.setOnChartGestureListener(new CoupleChartGestureListener(mChartKline, new Chart[]{mChartVolume}) {
            @Override
            public void onChartSingleTapped(MotionEvent me) {
                super.onChartSingleTapped(me);
                //图标单事件
            }

            @Override
            public void onChartScale(MotionEvent me, float scaleX, float scaleY) {
                super.onChartScale(me, scaleX, scaleY);
                LogUtil.i("scaleX=" + scaleX);
            }
        });
        // 将交易量控件的滑动事件传递给K线控件
        mChartVolume.setOnChartGestureListener(new CoupleChartGestureListener(mChartVolume, new Chart[]{mChartKline}));

        mChartKline.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, int dataSetIndex, Highlight h) {
                Highlight highlight = new Highlight(h.getXIndex(), h.getValue(), h.getDataIndex(), h.getDataSetIndex());

                float touchY = h.getTouchY() - mChartKline.getHeight();
                Highlight h1 = mChartVolume.getHighlightByTouchPoint(h.getXIndex(), touchY);
                highlight.setTouchY(touchY);
                if (null == h1) {
                    highlight.setTouchYValue(0);
                } else {
                    highlight.setTouchYValue(h1.getTouchYValue());
                }
                mChartVolume.highlightValues(new Highlight[]{highlight});

                updateText(e.getXIndex());
            }

            @Override
            public void onNothingSelected() {
                mChartVolume.highlightValue(null);
            }
        });

        mChartVolume.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, int dataSetIndex, Highlight h) {
                Highlight highlight = new Highlight(h.getXIndex(), h.getValue(), h.getDataIndex(), h.getDataSetIndex());

                float touchY = h.getTouchY() + mChartKline.getHeight();
                Highlight h1 = mChartKline.getHighlightByTouchPoint(h.getXIndex(), touchY);
                highlight.setTouchY(touchY);
                if (null == h1) {
                    highlight.setTouchYValue(0);
                } else {
                    highlight.setTouchYValue(h1.getTouchYValue());
                }
                mChartKline.highlightValues(new Highlight[]{highlight});

                updateText(e.getXIndex());
            }

            @Override
            public void onNothingSelected() {
                mChartKline.highlightValue(null);
            }
        });

    }

    private void updateText(int index) {
        if (index >= 0 && index < kLineDatas.size()) {
            KLineBean klData = kLineDatas.get(index);
//            mTvOpen.setText(CommonUtil.getDecimalFormatVol(klData.open));
            mTvClose.setText(CommonUtil.getDecimalFormatVol(klData.close));
            mTvMax.setText(CommonUtil.getDecimalFormatVol(klData.high));
            mTvMin.setText(CommonUtil.getDecimalFormatVol(klData.low));
//            mTvAmount.setText(CommonUtil.getDecimalFormatVol(klData.vol));

            int unit = CommonUtil.getVolUnitNum(klData.vol);
            mTvNum.setText(CommonUtil.getVolUnitText((int) Math.pow(10, unit), klData.vol));
        }
    }

    private void addKlineData() {
        CandleData combinedData = mChartKline.getCandleData();
        LineData lineData = mChartKline.getLineData();

        int count = 0;
        int i = kLineDatas.size() - 1;
        String xVals = mData.getXVals().get(mData.getXVals().size() - 1);
        if (combinedData != null) {
            int indexLast = getLastDataSetIndex(combinedData);
            CandleDataSet lastSet = (CandleDataSet) combinedData.getDataSetByIndex(indexLast);

            if (lastSet == null) {
                lastSet = createCandleDataSet();
                combinedData.addDataSet(lastSet);
            }
            count = lastSet.getEntryCount();

//            combinedData.addXValue(xVals);
            // 位最后一个DataSet添加entry
            combinedData.addEntry(new CandleEntry(count, kLineDatas.get(i).high, kLineDatas.get(i).low, kLineDatas
                    .get(i).open, kLineDatas.get(i).close), indexLast);
        }

        if (lineData != null) {
            int index = getDataSetIndexCount(lineData);
            LineDataSet lineDataSet5 = (LineDataSet) lineData.getDataSetByIndex(0);//五日均线;
            LineDataSet lineDataSet10 = (LineDataSet) lineData.getDataSetByIndex(1);//十日均线;
            LineDataSet lineDataSet20 = (LineDataSet) lineData.getDataSetByIndex(2);//二十日均线;
            LineDataSet lineDataSet30 = (LineDataSet) lineData.getDataSetByIndex(3);//三十日均线;

            if (lineDataSet5 != null) {
                mData.getMa5DataL().add(new Entry(KMAEntity.getLastMA(kLineDatas, 5), count));
                lineData.addEntry(mData.getMa5DataL().get(mData.getMa5DataL().size() - 1), 0);
            }

            if (lineDataSet10 != null) {
                mData.getMa10DataL().add(new Entry(KMAEntity.getLastMA(kLineDatas, 10), count));
                lineData.addEntry(mData.getMa10DataL().get(mData.getMa10DataL().size() - 1), 1);
            }

            if (lineDataSet20 != null) {
                mData.getMa20DataL().add(new Entry(KMAEntity.getLastMA(kLineDatas, 20), count));
                lineData.addEntry(mData.getMa20DataL().get(mData.getMa20DataL().size() - 1), 2);
            }

            if (lineDataSet30 != null) {
                mData.getMa30DataL().add(new Entry(KMAEntity.getLastMA(kLineDatas, 30), count));
                lineData.addEntry(mData.getMa30DataL().get(mData.getMa30DataL().size() - 1), 3);
            }
        }
    }

    private CandleDataSet createCandleDataSet() {
        CandleDataSet dataSet = new CandleDataSet(null, "DataSet 1");
        dataSet.setAxisDependency(YAxis.AxisDependency.LEFT);
        dataSet.setValueTextSize(12f);

        return dataSet;
    }

    /**
     * 获取最后一个CandleDataSet的索引
     */
    private int getLastDataSetIndex(CandleData candleData) {
        int dataSetCount = candleData.getDataSetCount();
        return dataSetCount > 0 ? (dataSetCount - 1) : 0;
    }

    /**
     * 获取最后一个LineDataSet的索引
     */
    private int getDataSetIndexCount(LineData lineData) {
        int dataSetCount = lineData.getDataSetCount();
        return dataSetCount;
    }

    private void addData() {
        int i = getRandom(mCacheData.getKLineDatas().size() - 1, 0);
        kLineDatas.add(kLineDatas.get(i));
        mData.getXVals().add(kLineDatas.get(i).date);
    }

    int i = 1;

    private int getRandom(int max, int min) {
        int index = i;
        i++;
        if (index > max) {
            i = 0;
            index = i;
        }
        return index;
    }

    private void setKLineDatas() {
        kLineDatas = mData.getKLineDatas();
        mData.initLineDatas(kLineDatas);
    }

    private void getOffLineData() {
        /*方便测试，加入假数据*/
        mData = new DataParse();
        JSONObject object = null;
        try {
            object = new JSONObject(Constants.KLINEURL);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        mData.parseKLine(object);

        mCacheData = new DataParse();
        mCacheData.parseKLine(object);
    }

    private void initChartKline() {
        mChartKline.setScaleEnabled(true);//启用图表缩放事件
        mChartKline.setDrawBorders(true);//是否绘制边线
        mChartKline.setBorderWidth(1);//边线宽度，单位dp
        mChartKline.setDragEnabled(true);//启用图表拖拽事件
        mChartKline.setScaleYEnabled(false);//启用Y轴上的缩放
        mChartKline.setBorderColor(getResources().getColor(R.color.colorGreen_3));//边线颜色
        mChartKline.setDescription("");//右下角对图表的描述信息
        mChartKline.setMinOffset(0f);
        mChartKline.setExtraOffsets(0f, 0f, 0f, 3f);
        mChartKline.setNoDataText("暂无数据");
        mChartKline.setTouchEnabled(true);//设置chart是否可以触摸
        mChartKline.setDoubleTapToZoomEnabled(false); //设置是否可以通过双击屏幕放大图表。默认是true

        Legend legend = mChartKline.getLegend();
        legend.setEnabled(false);//是否绘制 Legend 图例
        //设置垂直方向上还是下或中
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        //设置水平方向是左边还是右边或中
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        //设置所有图例位置排序方向
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        //设置图例的形状 有圆形、正方形、线
        legend.setForm(Legend.LegendForm.CIRCLE);
        //是否支持自动换行 目前只支持BelowChartLeft, BelowChartRight, BelowChartCenter
        legend.setWordWrapEnabled(true);

        //设置x轴标签数
        //        xAxisKline.setLabelCount(5, true);

        //设置限制线 12代表某个该轴某个值，也就是要画到该轴某个值上
        LimitLine limitLine = new LimitLine(32);
        //设置限制线的宽
        limitLine.setLineWidth(0.5f);
        //设置限制线的颜色
        limitLine.setLineColor(CommonUtil.getColor(R.color.status_bar_color));
        //设置基线的位置
        limitLine.setLabelPosition(LimitLine.LimitLabelPosition.LEFT_TOP);
//        limitLine.setLabel("也可以叫我水位线");
        //设置限制线为虚线
        limitLine.enableDashedLine(10f, 10f, 0f);

        axisLeftKline = mChartKline.getAxisLeft();
        axisLeftKline.setDrawGridLines(true);
        axisLeftKline.setDrawAxisLine(false);
        axisLeftKline.setDrawZeroLine(false);
        axisLeftKline.setDrawLabels(true);
        axisLeftKline.enableGridDashedLine(10f, 10f, 0f);
        axisLeftKline.setTextColor(getResources().getColor(R.color.colorGreen_1));
//        axisLeftKline.setGridColor(getResources().getColor(R.color.minute_grayLine));
        axisLeftKline.setPosition(YAxis.YAxisLabelPosition.INSIDE_CHART);//左边的标注在X轴外面
        axisLeftKline.setLabelCount(4, false); //第一个参数是Y轴坐标的个数，第二个参数是 是否不均匀分布，true是不均匀分布
        axisLeftKline.setSpaceTop(10);//距离顶部留白
        axisLeftKline.setXOffset(CommonUtil.getDimen(R.dimen.x1));//chart距左边边距
        //左边Y轴添加限制线
        axisLeftKline.addLimitLine(limitLine);

        //bar x y轴
        xAxisKline = mChartKline.getXAxis();
        //是否启用X轴
        xAxisKline.setEnabled(false);
        xAxisKline.setDrawLabels(false); //是否显示X坐标轴上的刻度，默认是true
//        xAxisKline.setDrawGridLines(false);//是否显示X坐标轴上的刻度竖线，默认是true
//        xAxisKline.setDrawAxisLine(false); //是否绘制坐标轴的线，即含有坐标的那条线，默认是true
//        xAxisKline.enableGridDashedLine(10f, 10f, 0f);
//        //虚线表示X轴上的刻度竖线(float lineLength, float spaceLength, float
//        // phase)三个参数，1.线长，2.虚线间距，3.虚线开始坐标
//        xAxisKline.setTextColor(getResources().getColor(R.color.colorBlack_1));//设置字的颜色
//        xAxisKline.setPosition(XAxis.XAxisPosition.BOTTOM);//设置值显示在什么位置
//        xAxisKline.setAvoidFirstLastClipping(true);//设置首尾的值是否自动调整，避免被遮挡

        axisRightKline = mChartKline.getAxisRight();
        axisRightKline.setDrawLabels(false);
        axisRightKline.setDrawGridLines(false);
        axisRightKline.setDrawAxisLine(false);
        axisRightKline.setLabelCount(4, false); //第一个参数是Y轴坐标的个数，第二个参数是 是否不均匀分布，true是不均匀分布
        mChartKline.setAutoScaleMinMaxEnabled(true);

        mChartKline.setDragDecelerationEnabled(true);//如果设置为true，手指滑动抛掷图表后继续减速滚动。 默认值：true。
        mChartKline.setDragDecelerationFrictionCoef(0.2f);//减速的摩擦系数在[0; 1]区间，数值越高表示速度会缓慢下降，

        mChartKline.setScaleX(1);//初始化显示的大小

        mChartKline.animateXY(0, 0);

//        mChartKline.zoomIn();  //默认视图放大1.4倍，
//        mChartKline.zoomOut();  //默认视图缩小到0.7倍，
        mChartKline.fitScreen();
        mChartKline.setScaleMinima(2, 1);
    }

    private void initChartVolume() {
        mChartVolume.setDrawBorders(true);  //边框是否显示
        mChartVolume.setBorderWidth(1);//边框的宽度，float类型，dp单位
        mChartVolume.setBorderColor(getResources().getColor(R.color.colorGreen_3));//边框颜色
        mChartVolume.setDescription(""); //图表默认右下方的描述，参数是String对象
        mChartVolume.setDragEnabled(true);// 是否可以拖拽
        mChartVolume.setScaleYEnabled(false); //是否可以缩放 仅y轴
        mChartVolume.setMinOffset(3f);
        mChartVolume.setExtraOffsets(0f, 0f, 0f, 5f);

        Legend combinedchartLegend = mChartVolume.getLegend(); // 设置比例图标示，就是那个一组y的value的
        combinedchartLegend.setEnabled(false);//是否绘制比例图

        //bar x y轴
        xAxisVolume = mChartVolume.getXAxis();
        xAxisVolume.setEnabled(true);
        xAxisVolume.setDrawLabels(true); //是否显示X坐标轴上的刻度，默认是true
        xAxisVolume.setDrawGridLines(false);//是否显示X坐标轴上的刻度竖线，默认是true
        xAxisVolume.setDrawAxisLine(false); //是否绘制坐标轴的线，即含有坐标的那条线，默认是true
        xAxisVolume.enableGridDashedLine(10f, 10f, 0f);//虚线表示X轴上的刻度竖线(float lineLength, float spaceLength, float
// phase)三个参数，1.线长，2.虚线间距，3.虚线开始坐标
        xAxisVolume.setTextColor(getResources().getColor(R.color.colorBlack_1));//设置字的颜色
        xAxisVolume.setPosition(XAxis.XAxisPosition.BOTTOM);//设置值显示在什么位置
        xAxisVolume.setAvoidFirstLastClipping(true);//设置首尾的值是否自动调整，避免被遮挡

        axisLeftVolume = mChartVolume.getAxisLeft();
        axisLeftVolume.setAxisMinValue(0);//设置Y轴坐标最小为多少
//        axisLeftVolume.setShowOnlyMinMax(true);//设置Y轴坐标最小为多少
        axisLeftVolume.setDrawGridLines(false);
        axisLeftVolume.setDrawAxisLine(false);
//        axisLeftVolume.setShowOnlyMinMax(true);
        axisLeftVolume.setDrawLabels(true);
        axisLeftVolume.enableGridDashedLine(10f, 10f, 0f);
        axisLeftVolume.setTextColor(getResources().getColor(R.color.colorBlack_1));
        axisLeftVolume.setGridColor(getResources().getColor(R.color.colorBlack_1));
        axisLeftVolume.setPosition(YAxis.YAxisLabelPosition.INSIDE_CHART);
        axisLeftVolume.setLabelCount(4, false); //第一个参数是Y轴坐标的个数，第二个参数是 是否不均匀分布，true是不均匀分布
        axisLeftVolume.setSpaceTop(5);//距离顶部留白
//        axisLeftVolume.setSpaceBottom(0);//距离顶部留白

        axisRightVolume = mChartVolume.getAxisRight();
        axisRightVolume.setAxisMinValue(0);//设置Y轴坐标最小为多少
//        axisLeftVolume.setShowOnlyMinMax(true);//设置Y轴坐标最小为多少
        axisRightVolume.setDrawGridLines(false);
        axisRightVolume.setDrawAxisLine(false);
//        axisLeftVolume.setShowOnlyMinMax(true);
        axisRightVolume.setDrawLabels(true);
        axisRightVolume.enableGridDashedLine(10f, 10f, 0f);
        axisRightVolume.setTextColor(getResources().getColor(R.color.colorBlack_1));
        axisRightVolume.setGridColor(getResources().getColor(R.color.colorBlack_1));
        axisRightVolume.setPosition(YAxis.YAxisLabelPosition.INSIDE_CHART);
        axisRightVolume.setLabelCount(2, false); //第一个参数是Y轴坐标的个数，第二个参数是 是否不均匀分布，true是不均匀分布
        axisRightVolume.setSpaceTop(5);//距离顶部留白
//        axisLeftVolume.setSpaceBottom(0);//距离顶部留白


        mChartVolume.setDragDecelerationEnabled(true);
        mChartVolume.setDragDecelerationFrictionCoef(0.2f);

        mChartVolume.setScaleX(1);//初始化显示的大小
        mChartVolume.animateXY(0, 0);
        mChartVolume.setScaleMinima(2, 1);
    }

    public void setKLineByChart(MyCombinedChart combinedChart) {
        CandleDataSet set = new CandleDataSet(mData.getCandleEntries(), "");
        set.setDrawHorizontalHighlightIndicator(false);
        set.setHighlightEnabled(true);
        set.setAxisDependency(YAxis.AxisDependency.LEFT);
        set.setShadowWidth(1f);
        set.setValueTextSize(10f);
        set.setDecreasingColor(getResources().getColor(R.color.decreasing_color));//设置开盘价高于收盘价的颜色
        set.setDecreasingPaintStyle(Paint.Style.FILL);
        set.setIncreasingColor(getResources().getColor(R.color.increasing_color));//设置开盘价地狱收盘价的颜色
        set.setIncreasingPaintStyle(Paint.Style.STROKE);
        set.setNeutralColor(getResources().getColor(R.color.decreasing_color));//设置开盘价等于收盘价的颜色
        set.setShadowColorSameAsCandle(true);
        set.setHighlightLineWidth(1f);
        set.setHighLightColor(getResources().getColor(R.color.colorBlack_2));
        set.setDrawValues(true);
        set.setValueTextColor(getResources().getColor(R.color.colorBlack_2));
        CandleData candleData = new CandleData(mData.getXVals(), set);

        mData.initKLineMA(kLineDatas);
        ArrayList<ILineDataSet> sets = new ArrayList<>();
        /******此处修复如果显示的点的个数达不到MA均线的位置所有的点都从0开始计算最小值的问题******************************/
//        sets.add(setMaLine(5, mData.getXVals(), mData.getMa5DataL()));//均线图，先不需要
//        sets.add(setMaLine(10, mData.getXVals(), mData.getMa10DataL()));
//        sets.add(setMaLine(20, mData.getXVals(), mData.getMa20DataL()));
//        sets.add(setMaLine(30, mData.getXVals(), mData.getMa30DataL()));

        LineData lineData = new LineData(mData.getXVals(), sets);

        CombinedData combinedData = new CombinedData(mData.getXVals());
        combinedData.setData(lineData);
        combinedData.setData(candleData);
        combinedChart.setData(combinedData);

        setHandler(combinedChart, 10);
    }

    private void setVolumeByChart(MyCombinedChart combinedChart) {
        String unit = CommonUtil.getVolUnit(mData.getVolmax());
        String wan = getString(R.string.thousand_unit);
        String yi = getString(R.string.billion_unit);
        int u = 1;
        if (wan.equals(unit)) {
            u = 4;
        } else if (yi.equals(unit)) {
            u = 8;
        }
        combinedChart.getAxisLeft().setValueFormatter(new VolFormatter((int) Math.pow(10, u), VolFormatter.LEFT));
        combinedChart.getAxisLeft().setAxisMaxValue(mData.getVolmax());

        combinedChart.getAxisRight().setValueFormatter(new VolFormatter((int) Math.pow(10, u), VolFormatter.RIGTH));
        combinedChart.getAxisRight().setAxisMaxValue(mData.getVolmax());

        BarDataSet set = new BarDataSet(mData.getBarEntries(), "成交量");
        set.setBarSpacePercent(20); //bar空隙
        set.setHighlightEnabled(true);
        set.setHighLightAlpha(255);
        set.setHighLightColor(getResources().getColor(R.color.colorBlack_2));
        set.setDrawValues(false);//是否显示柱状图的值

        List<Integer> list = new ArrayList<>();
        list.add(getResources().getColor(R.color.increasing_color));
        list.add(getResources().getColor(R.color.decreasing_color));
        set.setColors(list);
        BarData barData = new BarData(mData.getXVals(), set);

        mData.initVlumeMA(kLineDatas);
        ArrayList<ILineDataSet> sets = new ArrayList<>();

        /******此处修复如果显示的点的个数达不到MA均线的位置所有的点都从0开始计算最小值的问题******************************/
//        sets.add(setMaLine(5, mData.getXVals(), mData.getMa5DataV()));//均线图，先不需要
//        sets.add(setMaLine(10, mData.getXVals(), mData.getMa10DataV()));
//        sets.add(setMaLine(20, mData.getXVals(), mData.getMa20DataV()));
//        sets.add(setMaLine(30, mData.getXVals(), mData.getMa30DataV()));

        LineData lineData = new LineData(mData.getXVals(), sets);

        CombinedData combinedData = new CombinedData(mData.getXVals());
        combinedData.setData(barData);
        combinedData.setData(lineData);
        combinedChart.setData(combinedData);

        setHandler(combinedChart, 10);
    }

    @NonNull
    private LineDataSet setMaLine(int ma, ArrayList<String> xVals, ArrayList<Entry> lineEntries) {
        LineDataSet lineDataSetMa = new LineDataSet(lineEntries, "ma" + ma);
        if (ma == 5) {
            lineDataSetMa.setHighlightEnabled(true);
            lineDataSetMa.setDrawHorizontalHighlightIndicator(false);
            lineDataSetMa.setHighLightColor(getResources().getColor(R.color.colorBlack_2));
        } else {/*此处必须得写*/
            lineDataSetMa.setHighlightEnabled(false);
        }
        lineDataSetMa.setDrawValues(false);
        if (ma == 5) {
            lineDataSetMa.setColor(getResources().getColor(R.color.colorGreen_1));
        } else if (ma == 10) {
            lineDataSetMa.setColor(getResources().getColor(R.color.colorGreen_3));
        } else if (ma == 20) {
            lineDataSetMa.setColor(getResources().getColor(R.color.colorRed_2));
        } else {
            lineDataSetMa.setColor(getResources().getColor(R.color.status_bar_color));
        }
        lineDataSetMa.setLineWidth(1f);
        lineDataSetMa.setDrawCircles(false);
        lineDataSetMa.setAxisDependency(YAxis.AxisDependency.LEFT);

        lineDataSetMa.setHighlightEnabled(false);
        return lineDataSetMa;
    }

    private void setHandler(MyCombinedChart combinedChart, float xscale) {
        final ViewPortHandler viewPortHandlerBar = combinedChart.getViewPortHandler();
        viewPortHandlerBar.setMaximumScaleX(culcMaxscale(mData.getXVals().size()));
        Matrix touchmatrix = viewPortHandlerBar.getMatrixTouch();
        touchmatrix.postScale(xscale, 1f);
    }

    private float culcMaxscale(float count) {
        float max = 1;
        max = count / 127 * 5;
        return max;
    }

    @Override
    public void onSuccess(Object response, int tag) {
        super.onSuccess(response, tag);
        switch (tag) {
            case KLINE_DATA_TAG:
                break;
            case EXCHANGE_DEAL_RECODE_TAG:

                DealRecodeRes dealRecodeRes = (DealRecodeRes) response;
                if (dealRecodeRes == null || dealRecodeRes.getDealList().size() == 0) {
                    LogUtil.i("最近成交记录为空");
                    return;
                }
                recodeDatas.clear();
                recodeDatas.addAll(dealRecodeRes.getDealList());
                dealRecodeRecAdapter.notifyDataSetChanged();
                break;
        }
    }

}
