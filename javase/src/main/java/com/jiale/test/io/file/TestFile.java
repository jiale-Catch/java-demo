package com.jiale.test.io.file;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.io.FileUtil;
import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TestFile {

    public static void  main(String ...ags){
        String path= "/usr/local/nginx/data1/infositemap/";
        File subFile = new File(path);
        File[] tempList = subFile.listFiles();
        // 子文件所在文件夹
        assert tempList != null;
        List<File> list = Arrays.asList(tempList);
        List<String> subFileListName = list.stream().map(File::getName).filter(s -> s.contains("_o")).collect(Collectors.toList());
        int days =15;
        List<String> cityStrList = CollUtil.newArrayList("bj","gd","hainan","gx","tj","hb","shanxi","nmg","sh","js","zj","ah","fj","jx","sd","henan","hub","hunan","cq","sc");
        for (int i = days; i >0; i--) {
            for (String cityStr : cityStrList) {
                String dayStr = String.valueOf(i).length() == 1 ? "0" + i : String.valueOf(i);
                String fileNamePrefix = String.format("%s_oinfo%s", cityStr, dayStr);
                // 获取对应天数对应的文件名
                List<String> dayFileNameList = subFileListName.stream().filter(fileName -> {
                    return fileName.startsWith(fileNamePrefix);
                }).collect(Collectors.toList());
                if(i == days) {
                    // 进行删除
                    dayFileNameList.stream().map(obj->new File(path+obj)).forEach(FileUtil::del);
                } else {
                    // 重命名
                    for (String dayFileName : dayFileNameList) {
                        String dayStrSub = String.valueOf(i).length() == 1 ? ("0" + (i + 1)) : String.valueOf(i + 1);
                        String newFileName = dayFileName.replaceAll("_oinfo\\d{0,2}", "_oinfo"+dayStrSub);
                        FileUtil.rename(new File(path + dayFileName), newFileName, true);
                    }
                }
            }
        }

    }
}
