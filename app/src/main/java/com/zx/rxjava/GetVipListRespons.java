package com.zx.rxjava;


import com.zx.rxjava.bean.Vip;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zx on 2016/4/12.
 */
public class GetVipListRespons extends BaseApiResponse<GetVipListRespons>{

    private DataBean Data;
    public DataBean getData() {
        return Data;
    }

    public void setData(DataBean Data) {
        this.Data = Data;
    }

    public static class DataBean {
        private String Total;
        private List<Vip> Rows;

        public String getTotal() {
            return Total;
        }

        public void setTotal(String Total) {
            this.Total = Total;
        }

        public List<Vip> getRows() {
            return Rows;
        }

        public void setRows(List<Vip> Rows) {
            this.Rows = Rows;
        }

    }
}
