package com.label.core.util;

import com.label.core.pojo.vo.Label.*;
import com.label.core.pojo.vo.model.ChildrenItem;
import com.label.core.pojo.vo.model.DataItem;
import com.label.core.pojo.vo.model.SaveModelDataReq;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class ExportExcel {
    public static void getExcelData(List<LabelDataMessage> list, SaveModelDataReq model) throws IOException {
        String newModel = "";
        String area = model.getArea();
        List<DataItem> areaList = model.getAreaData();
        List<DataItem> globalList = model.getGlobalData();
        Workbook workbook = new XSSFWorkbook();
        //区域音频
        if("area".equals(area)) {
            Sheet areaSheet = workbook.createSheet("局部标注");
            Sheet globalSheet = workbook.createSheet("全局标注");
            Row headerRow = areaSheet.createRow(0);
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
                    Row bodyRow = areaSheet.createRow(row++);
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
                        for (childrenItem childItem : childrenItemList) {
                            bodyRow.createCell(rowCount++).setCellValue(String.join(",", childItem.getValue()));
                        }
                        //
                    }

                }
            }
        }
        FileOutputStream fileOut = new FileOutputStream("E:/xml/new.xlsx");
        workbook.write(fileOut);
        fileOut.close();
        workbook.close();
    }
}
