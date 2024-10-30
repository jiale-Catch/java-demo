package com.jiale.test.excel;


import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.read.builder.ExcelReaderBuilder;
import com.alibaba.excel.read.builder.ExcelReaderSheetBuilder;

import java.io.File;

public class ExcelUtils {

    public ExcelUtils(){

    }

    public static ExcelReaderBuilder read(String path){
        if(!path.endsWith(".xlsx") || !path.endsWith(".xls")){
            return null;
        }
        File file = new File(path);
        return EasyExcel.read(file);
    }



}
