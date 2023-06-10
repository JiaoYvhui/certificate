package com.manage.certificate.util;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;
import com.alibaba.excel.util.ListUtils;
import com.alibaba.fastjson.JSON;
import com.manage.certificate.entity.CertificateStaff;
import com.manage.certificate.service.CertificateStaffService;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CertificateStaffListener implements ReadListener<CertificateStaff> {

    /**
     * 每隔5条存储数据库，实际使用中可以100条，然后清理list ，方便内存回收
     */
    private static final int BATCH_COUNT = 100;

    /**
     * 缓存的数据
     */
    private List<CertificateStaff> cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);
    /**
     * 假设这个是一个DAO，当然有业务逻辑这个也可以是一个service。当然如果不用存储这个对象没用。
     */

    private final CertificateStaffService servicer;

    private List<String> staffCards = new ArrayList<>();

    private List<String> staffCardNull = new ArrayList<>();

    /**
     * 结果
     */
    public static String SUCCESS;

    public CertificateStaffListener(CertificateStaffService servicer) {
        // 这里是demo，所以随便new一个。实际使用如果到了spring,请使用下面的有参构造函数
        this.servicer = servicer;
    }

    /**
     * 这个每一条数据解析都会来调用
     *
     * @param data    one row value. Is is same as {@link AnalysisContext#readRowHolder()}
     * @param context
     */
    @Override
    public void invoke(CertificateStaff staff, AnalysisContext context) {
        if (staff != null) {
            if (staff.getStaffCard() != null) {
                cachedDataList.add(staff);
                // 达到BATCH_COUNT了，需要去存储一次数据库，防止数据几万条数据在内存，容易OOM
                if (cachedDataList.size() >= BATCH_COUNT) {
                    saveData();
                    // 存储完成清理 list
                    cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);
                }
            } else {
                staffCardNull.add(staff.getStaffName());
            }
        }
    }

    /**
     * 所有数据解析完成了 都会来调用
     *
     * @param context
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        // 这里也要保存数据，确保最后遗留的数据也存储到数据库
        saveData();
        StringBuilder stringBuilder = new StringBuilder();
        if (staffCards != null && staffCards.size() > 0) {
            stringBuilder.append("身份证号为").append(staffCards).append("已存在，");
        }
        if (staffCardNull != null && staffCardNull.size() > 0) {
            stringBuilder.append("姓名为").append(staffCardNull).append("没有填写身份证，");
        }
        stringBuilder.append("已导入正常数据");
        SUCCESS = stringBuilder.toString();
    }

    /**
     * 加上存储数据库
     */
    private void saveData() {
        List<String> staffCard = new ArrayList<>();
        for (CertificateStaff certificateStaff : cachedDataList) {
            staffCard.add(certificateStaff.getStaffCard());
        }
        List<String> staffCardList = servicer.findStaffCard(staffCard);
        staffCards.addAll(staffCardList);
        Iterator<CertificateStaff> iterator = cachedDataList.iterator();
        while (iterator.hasNext()) {
            String staff = iterator.next().getStaffCard();
            for (String s : staffCardList) {
                if (staff.equals(s)) {
                    iterator.remove();
                }
            }
        }
        servicer.batchInsert(cachedDataList);
    }
}
