package com.example.electricpower;

/**
 */
public interface InitViews
{

    /**
 * 给控件绑定事件
 */
public void bindListener();

    /**
     * 给控件初始化数据
     */
    public void initData();

    /**
     * layout id
     * @return
     */
    public int getLayoutId();
}
