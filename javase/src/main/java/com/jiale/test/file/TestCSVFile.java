package com.jiale.test.file;

import cn.hutool.core.text.csv.CsvData;
import cn.hutool.core.text.csv.CsvReader;
import cn.hutool.core.text.csv.CsvUtil;
import cn.hutool.core.text.csv.CsvWriter;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

public class TestCSVFile {

    public static void main(String[] args) {
        try{
            readCSVFile("D:\\test.csv");
        }catch (Exception e){
            System.out.println("读取csv文件错误" + e.getMessage());
        }
    }

    public static String getCSVFile(String filePath){
        CsvWriter writer = CsvUtil.getWriter(filePath, Charset.defaultCharset(),true);
        CsvWriter write = writer.write(new String[]{"1", "2", "3"});
        write.flush();
        write.close();
        return null;
    }

    public static void readCSVFile(String filePath) throws IOException {
        CsvReader reader = CsvUtil.getReader();
        //读取文件的时候读取中文文件需要使用默认的字符集，手动写 utf-8 的字符集还是会乱码
        reader.read(new File(filePath), Charset.defaultCharset());
        CsvData read = reader.read();
        read.getRows().forEach(System.out::println);
        reader.close();

    }

}
