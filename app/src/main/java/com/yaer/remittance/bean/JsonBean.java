package com.yaer.remittance.bean;

import com.contrarywind.interfaces.IPickerViewData;

import java.util.List;

/**
 * TODO<json数据源>
 */

public class JsonBean  implements IPickerViewData {
    /**
     * name : 省份
     * city : [{"name":"北京市","area":["东城区","西城区","崇文区","宣武区","朝阳区"]}]
     */

    private String name;
    private int id;
    private List<City> child;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<City> getCityList() {
        return child;
    }

    public void setCityList(List<City> child) {
        this.child = child;
    }

    // 实现 IPickerViewData 接口，
    // 这个用来显示在PickerView上面的字符串，
    // PickerView会通过IPickerViewData获取getPickerViewText方法显示出来。
    @Override
    public String getPickerViewText() {
        return this.name;
    }
    public static class City implements IPickerViewData {
        private String name;
        private int id;
        private List<CityBean> child;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public List<CityBean> getChild() {
            return child;
        }

        public void setChild(List<CityBean> child) {
            this.child = child;
        }
        @Override
        public String getPickerViewText() {
            return this.name;
        }
    }
    public static class CityBean implements IPickerViewData {
        private String name;
        private int id;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
        @Override
        public String getPickerViewText() {
            return this.name;
        }
    }
}
