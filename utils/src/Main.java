import cn.hutool.core.text.csv.*;
import io.micrometer.common.util.StringUtils;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class Main {
//    public static void main(String[] args) {
//        testwash();
//    }

    public static void testwash(){
        CsvReader reader = CsvUtil.getReader();
        CsvData read1 = reader.read(new File("D:\\test\\youshu\\有数-2024-06-12-公告类型-1.csv"),Charset.forName("GBK"));
        List<CsvRow> rows = read1.getRows();
        List<CsvRow> newRows = new ArrayList<>();
        for (int i = 1; i < rows.size(); i++) {
            CsvRow row = rows.get(i);
            String docChannel = row.get(1);
            if(StringUtils.isNotBlank(docChannel)){
                newRows.add(row);
            }
        }
        CsvWriter writer = CsvUtil.getWriter(new File("D:\\有数-2024-06-13-公告类型.csv"), Charset.defaultCharset(), true);
        writer.write(newRows);
        writer.flush();
        writer.close();

    }

}