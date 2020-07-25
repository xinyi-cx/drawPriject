package com.tt.prize.service.impl;

import com.tt.base.entity.UserEnvelope;
import com.tt.prize.Exception.MyException;
import com.tt.prize.mapper.PrizeInfoMapper;
import com.tt.prize.mapper.UserEnvelopeMapper;
import com.tt.prize.service.CustomUserService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Encoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
public class CustomUserServiceImpl implements CustomUserService {

    @Autowired
    private UserEnvelopeMapper userEnvelopeMapper;
    @Autowired
    private PrizeInfoMapper prizeInfoMapper;

    @Override
    public boolean uploadExcelFile(String fileName, MultipartFile file) throws MyException {
        boolean notNull = false;
        List<UserEnvelope> userList = new ArrayList<UserEnvelope>();
        if (!fileName.matches("^.+\\.(?i)(xls)$") && !fileName.matches("^.+\\.(?i)(xlsx)$")) {
            return false;
        }
        boolean isExcel2003 = true;
        if (fileName.matches("^.+\\.(?i)(xlsx)$")) {
            isExcel2003 = false;
        }
        InputStream is = null;
        try {
            is = file.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Workbook wb = null;
        if (isExcel2003) {
            try {
                wb = new HSSFWorkbook(is);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                wb = new XSSFWorkbook(is);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Sheet sheet = wb.getSheetAt(0);
        if(sheet!=null){
            notNull = true;
        }
        UserEnvelope user;
        for (int r = 1; r <= sheet.getLastRowNum(); r++) {
            Row row = sheet.getRow(r);
            if (row == null){
                continue;
            }

            user = new UserEnvelope();

            if( row.getCell(0).getCellType() !=1){
                throw new MyException("导入失败(第"+(r+1)+"行,姓名请设为文本格式)");
            }
            String name = row.getCell(0).getStringCellValue();
            if(name == null || name.isEmpty()){
                throw new MyException("导入失败(第"+(r+1)+"行,姓名未填写)");
            }

            row.getCell(1).setCellType(Cell.CELL_TYPE_STRING);
            String number = row.getCell(1).getStringCellValue();
            if(number==null || number.isEmpty()){
                throw new MyException("导入失败(第"+(r+1)+"行,未填写红包数)");
            }
            String prize = "";
            String money = "";
            try {
                money = row.getCell(2).getStringCellValue();
                prize = row.getCell(3).getStringCellValue();
            }catch (Exception e){
                prize = " ";
                money = " ";
            }
            user.setUserName(name);
            user.setEnvelopeNum(Integer.parseInt(number));
            user.setMoneyRange(money);
            user.setPrize(prize);
            user.setUploadTime(new Date(System.currentTimeMillis()));
            userList.add(user);
        }
        for (UserEnvelope userResord : userList) {
            String name = userResord.getUserName();
            UserEnvelope userEnvelope = new UserEnvelope();
            userEnvelope.setUserName(name);
            List<UserEnvelope> userEnvelopes = userEnvelopeMapper.selectUserEnvelopeList(userEnvelope);
            if (userEnvelopes.size() == 0) {
                userEnvelopeMapper.insertUserEnvelope(userResord);
                System.out.println(" 插入 "+userResord);
            } else {
                userEnvelopeMapper.updateUserEnvelope(userResord);
                System.out.println(" 更新 "+userResord);
            }
        }
        return notNull;
    }

    @Override
    public String downloadExcelFile(Date startTime, Date endTime, HttpServletRequest request, HttpServletResponse response) {

        return "下载成功";
    }


    public String fileNameEncoding(String fileName, HttpServletRequest request)
            throws IOException {
        String agent = request.getHeader("User-Agent");
        if (agent.contains("Firefox")) {
            BASE64Encoder base64Encoder = new BASE64Encoder();
            fileName = "=?utf-8?B?"
                    + base64Encoder.encode(fileName.getBytes("utf-8")) + "?=";
        } else {
            fileName = URLEncoder.encode(fileName, "utf-8");
        }
        return fileName;
    }

}
