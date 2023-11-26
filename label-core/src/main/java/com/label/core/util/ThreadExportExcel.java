package com.label.core.util;

import com.label.core.pojo.vo.Label.*;
import com.label.core.pojo.vo.model.ChildrenItem;
import com.label.core.pojo.vo.model.DataItem;
import com.label.core.pojo.vo.model.SaveModelDataReq;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class ThreadExportExcel {
   public static void ThreadAction(List<LabelDataMessage> list, SaveModelDataReq model,String path) throws InterruptedException, IOException {
       String type = model.getArea();
       Workbook workbook = new XSSFWorkbook();
       Sheet areaSheet = workbook.createSheet("局部标注");
       Sheet globalSheet = workbook.createSheet("全局标注");
       if("area".equals(type)) {
           Export areaExport = new Export(areaSheet, list, model, "area");
           Thread areaThread = new Thread(areaExport);
           areaThread.start();
           areaThread.join();
       }
       Export globalExport = new Export(globalSheet, list, model,"global");
       Thread globalThread = new Thread(globalExport);
       globalThread.start();
       globalThread.join();
       FileOutputStream fileOut = new FileOutputStream(path);
       workbook.write(fileOut);
       fileOut.close();
       workbook.close();
   }
}
