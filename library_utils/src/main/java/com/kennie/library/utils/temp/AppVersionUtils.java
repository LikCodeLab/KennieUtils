package com.kennie.library.utils.temp;

import java.util.ArrayList;
import java.util.List;

/**
 * @项目名 KennieUtils
 * @类名称 AppVersionUtils
 * @类描述 APP版本号工具
 * @创建人 Administrator
 * @修改人
 * @创建时间 2021/11/5 20:16
 */
public class AppVersionUtils {

    /**
     * 将字母和数字拆分出来
     *
     * @param str
     * @return
     */
    public static List<String> splitCell(String str) {
        List<String> list = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        char[] chars = str.toCharArray();
        Boolean isNum = null;
        for (char c : chars) {
            boolean same = true;
            boolean num = c >= '0' && c <= '9';
            if (isNum == null) {
                isNum = num;
            } else {
                same = isNum && num;
                isNum = num;
            }
            if (!same) {
                list.add(sb.toString());
                sb.setLength(0);
            }
            sb.append(c);
        }
        //添加最后一个
        list.add(sb.toString());
        return list;
    }

    public static Integer toInt(String s) {
        try {
            return Integer.parseInt(s);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 比较版本号大小
     *
     * @param ver1     版本号1
     * @param ver2     版本号2
     * @param shortBig 当较长的版本号包含短的时，true则短的为大，false则短的为小
     * @return 0 相等 -1 小于 1 大于
     */
    public static int compare(String ver1, String ver2, boolean shortBig) {
        //将将版本号字符串以.分割进行比较
        String s1 = ver1.replaceAll(" ", "");
        String s2 = ver2.replaceAll(" ", "");
        if (s1.equals(s2)) {
            return 0;
        } else if (s1.contains(s2)) {
            return shortBig ? -1 : 1;
        } else if (s2.contains(s1)) {
            return shortBig ? 1 : -1;
        }
        String[] vCells1 = s1.split("\\.");
        String[] vCells2 = s2.split("\\.");
        //以长度短的，对分割元素逐个比较
        int len = Math.min(vCells1.length, vCells2.length);
        for (int i = 0; i < len; i++) {
            List<String> cellList1 = splitCell(vCells1[i]);
            List<String> cellList2 = splitCell(vCells2[i]);
            //同样逐个比较
            int len1 = Math.min(cellList1.size(), cellList2.size());
            for (int j = 0; j < len1; j++) {
                String cell1 = cellList1.get(j);
                String cell2 = cellList2.get(j);
                Integer cell1Int = toInt(cell1);
                Integer cell2Int = toInt(cell2);
                int result;
                if (cell1Int != null && cell2Int != null) {
                    result = cell1Int.compareTo(cell2Int);
                } else {
                    result = cell1.compareTo(cell2);
                }
                if (result != 0) {
                    return result;
                }
            }
        }
        return 0;
    }
}
