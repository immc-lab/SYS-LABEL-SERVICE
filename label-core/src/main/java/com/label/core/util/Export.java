package com.label.core.util;

import com.label.core.pojo.vo.Label.*;
import com.label.core.pojo.vo.model.ChildrenItem;
import com.label.core.pojo.vo.model.DataItem;
import com.label.core.pojo.vo.model.SaveModelDataReq;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.List;

public class Export implements Runnable {

    private Sheet sheet;
    private List<LabelDataMessage> list;
    private SaveModelDataReq model;
    private String type;

    public Export(Sheet sheet, List<LabelDataMessage> list, SaveModelDataReq model, String type) {
        this.sheet = sheet;
        this.list = list;
        this.model = model;
        this.type = type;
    }

    @Override
    public void run() {
        //区域标注写入Excel
        if ("area".equals(type)) {
            Row headerRow = sheet.createRow(0);
            List<DataItem> areaList = model.getAreaData();
            headerRow.createCell(0).setCellValue("音频");
            headerRow.createCell(1).setCellValue("区域");
            headerRow.createCell(2).setCellValue("开始时间");
            headerRow.createCell(3).setCellValue("结束时间");
            int headerRowCount = 4;
            int row = 1;
            for (DataItem item : areaList) {
                headerRow.createCell(headerRowCount++).setCellValue(item.getTextValue());
                for (ChildrenItem childrenItem : item.getChildren()) {
                    headerRow.createCell(headerRowCount++).setCellValue(childrenItem.getTextValue());
                }
            }
            for (LabelDataMessage labelDataMessage : list) {
                String path = labelDataMessage.getPath();
                SaveLabelDataRes item = labelDataMessage.getItem();
                List<SaveLabelDataAreaItem> areaSaveData = item.getAreaSaveData();
                List<SaveLabelDataItem> globalSaveData = item.getGlobalSaveData();
                for (SaveLabelDataAreaItem saveLabelDataAreaItem : areaSaveData) {
                    Row bodyRow = sheet.createRow(row++);
                    int rowCount = 0;
                    String startTime = saveLabelDataAreaItem.getStartTime();
                    String endTime = saveLabelDataAreaItem.getEndTime();
                    //区域id
                    String areaId = saveLabelDataAreaItem.getId();
                    //音频地址
                    bodyRow.createCell(rowCount++).setCellValue(path);
                    //区域名称
                    bodyRow.createCell(rowCount++).setCellValue("区域" + areaId);
                    //开始时间
                    bodyRow.createCell(rowCount++).setCellValue(startTime);
                    //结束时间
                    bodyRow.createCell(rowCount++).setCellValue(endTime);
                    //每个条目，每个条目下还可能有子条目，非常之复杂，愿接手的同学加油！！
                    List<SaveLabelDataItem> dataItemList = saveLabelDataAreaItem.getSaveData();
                    for (SaveLabelDataItem dataItem : dataItemList) {
                        //标签名
                        String value = String.join(",", dataItem.getValue());
                        List<childrenItem> childrenItemList = dataItem.getChildren();
                        bodyRow.createCell(rowCount++).setCellValue(value);
                        if(childrenItemList!=null) {
                            for (childrenItem childItem : childrenItemList) {
                                bodyRow.createCell(rowCount++).setCellValue(String.join(",", childItem.getValue()));
                            }
                        }
                        //
                    }

                }
            }
            //写入全局标注到Excel
        } else {
            Row headerRow = sheet.createRow(0);
            List<DataItem> globalList = model.getGlobalData();
            headerRow.createCell(0).setCellValue("音频");
            int headerRowCount = 1;
            int row = 1;
            for (DataItem item : globalList) {
                headerRow.createCell(headerRowCount++).setCellValue(item.getTextValue());
                for (ChildrenItem childrenItem : item.getChildren()) {
                    headerRow.createCell(headerRowCount++).setCellValue(childrenItem.getTextValue());
                }
            }

            for (LabelDataMessage labelDataMessage : list) {
                Row bodyRow = sheet.createRow(row++);
                int rowCount = 0;
                String path = labelDataMessage.getPath();
                bodyRow.createCell(rowCount++).setCellValue(path);
                SaveLabelDataRes item = labelDataMessage.getItem();
                List<SaveLabelDataItem> globalSaveData = item.getGlobalSaveData();
                for (SaveLabelDataItem globalItem : globalSaveData) {
                    String value = String.join(",", globalItem.getValue());
                    //每个条目，每个条目下还可能有子条目，非常之复杂，愿接手的同学加油！！
                    bodyRow.createCell(rowCount++).setCellValue(value);
                    if(globalItem.getChildren()!=null) {
                        for (childrenItem childItem : globalItem.getChildren()) {
                            bodyRow.createCell(rowCount++).setCellValue(String.join(",", childItem.getValue()));
                        }
                    }
                }
            }
        }
    }
}

