package dataBase;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;

/*
 * @author      :1OrNaiwohe
 * @date        :2022/10/3 13:41
 * @description :处理excel表的数据结构
 */
public class XlsTable {

    //增加物品
    public static String add(String goods, int num) throws Exception {
        String filePath = "data.xlsx";
        FileInputStream fileInputStream = new FileInputStream(filePath);
        XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
        XSSFSheet sheet = workbook.getSheetAt(0);


        for (int i = 0; i <= sheet.getLastRowNum(); i++) {
            XSSFCell cell = sheet.getRow(i).getCell(0);
            if (cell.getStringCellValue().equals(goods.trim())) {
                XSSFCell cell2 = sheet.getRow(i).getCell(1);
                double bef = cell2.getNumericCellValue();
                int res = (int) bef+num;
                cell2.setCellValue(res);
                FileOutputStream fileOutputStream = new FileOutputStream(filePath);
                workbook.write(fileOutputStream);
                fileInputStream.close();
                workbook.close();

                return "添加成功"+goods+"当前数目为: "+res;
            }
        }
        XSSFRow row = sheet.createRow(sheet.getLastRowNum() + 1);
        row.createCell(0).setCellValue(goods);
        row.createCell(1).setCellValue(num);

        FileOutputStream fileOutputStream = new FileOutputStream(filePath);
        workbook.write(fileOutputStream);
        fileInputStream.close();
        workbook.close();
        return "添加成功"+goods+"当前数目为: "+ (int)num;
    }
    //删除物品
    public static String del(String goods,int num) throws IOException {
        if (XlsTable.search(goods)==0){
            return "删除失败，未找到该物品或该物品数量为0";
        }
        String filePath = "data.xlsx";
        FileInputStream fileInputStream = new FileInputStream(filePath);
        XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
        XSSFSheet sheet = workbook.getSheetAt(0);


        for (int i = 0; i <= sheet.getLastRowNum(); i++) {
            XSSFCell cell = sheet.getRow(i).getCell(0);
            if (cell.getStringCellValue().equals(goods.trim())) {
                XSSFCell cell2 = sheet.getRow(i).getCell(1);
                double bef = cell2.getNumericCellValue();//进行操作之前的数目
                double res = bef-num>0 ? bef-num : 0;
                cell2.setCellValue(res);
                String s = "成功,"+goods+"当前数目为: "+(int) res;
                FileOutputStream fileOutputStream = new FileOutputStream(filePath);
                workbook.write(fileOutputStream);
                fileInputStream.close();
                workbook.close();
                return s;
            }
        }

        workbook.close();

        return "删除失败，未找到该物品或该物品数量为0";
    }

    //显示物品列表
    public static HashMap<String, Integer> viewList() throws IOException {
        HashMap<String, Integer> hs = new HashMap<>();
        XSSFWorkbook workbook = new XSSFWorkbook("data.xlsx");
        //路径data.xlsx可能不存在，这里没有捕获异常而是直接抛出
        XSSFSheet sheet = workbook.getSheetAt(0);
        for (Row row : sheet) {
            String goods = null;
            int num = 0;
            for (Cell cell : row) {
                if (cell.getCellType() == CellType.STRING) {
                    goods = cell.getRichStringCellValue().getString();
                } else if (cell.getCellType() == CellType.NUMERIC) {
                    num = (int) cell.getNumericCellValue();
                }
            }
            hs.put(goods, num);
        }
        workbook.close();
        return hs;
    }

    //查找物品
    public static int search(String s) throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook("data.xlsx");
        //路径data.xlsx可能不存在，这里没有捕获异常而是直接抛出
        XSSFSheet sheet = workbook.getSheetAt(0);
        String goods = null;
        int res = 0;
        for (Row row : sheet) {
            for (Cell cell : row) {
                if (cell.getCellType() == CellType.STRING) {
                    goods = cell.getRichStringCellValue().getString();
                    if (!goods.equals(s)){
                        break;
                    }
                }
                if (cell.getCellType() == CellType.NUMERIC) {
                    res = (int) cell.getNumericCellValue();
                }
            }
        }
    workbook.close();
    return res;
    }
}