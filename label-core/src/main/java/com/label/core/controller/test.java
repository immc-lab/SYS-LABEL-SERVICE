package com.label.core.controller;

import com.label.core.pojo.vo.Label.MyData;
import com.label.core.pojo.vo.Label.MyDataList;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class test {
    public static void main(String[] args) throws IOException {
        List<MyData> list2 = new ArrayList<>();
        MyData myData = new MyData();
        myData.setAge("78");
        myData.setEmail("7895@qq.com");
        myData.setName("没啥");
        MyDataList myDataList = new MyDataList();
        myDataList.setAge("15");
        myDataList.setName("haha");
        myDataList.setEmail("4554815@qq.com");
        List<MyDataList> list = new ArrayList<>();
        list.add(myDataList);
        myData.setChildDataList(list);
        list2.add(myData);
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Data");

        // 创建表头
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("Name");
        headerRow.createCell(1).setCellValue("Age");
        headerRow.createCell(2).setCellValue("Email");

        // 写入数据
        int rowNum = 1;
        for (MyData data : list2) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(data.getName());
            row.createCell(1).setCellValue(data.getAge());
            row.createCell(2).setCellValue(data.getEmail());

            for (MyDataList childData : data.getChildDataList()) {
                Row childRow = sheet.createRow(rowNum++);
                childRow.createCell(0).setCellValue(childData.getName());
                childRow.createCell(1).setCellValue(childData.getAge());
            }
        }

        FileOutputStream fileOut = new FileOutputStream("E:/xml/lala.xlsx");
        workbook.write(fileOut);
        fileOut.close();
        workbook.close();

    }


}
