package com.pratice.cafe.serviceImp;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import com.pratice.cafe.JWT.JwtFilter;
import com.pratice.cafe.POJO.Bill;
import com.pratice.cafe.POJO.Category;
import com.pratice.cafe.constants.CafeConstants;
import com.pratice.cafe.dao.BillDao;
import com.pratice.cafe.dao.CategoryDao;
import com.pratice.cafe.service.BillService;
import com.pratice.cafe.service.CategoryService;
import com.pratice.cafe.utils.CafeUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Slf4j
public class BillServiceImpl implements BillService {

    @Autowired
    BillDao billDao;
    @Autowired
    JwtFilter filter;

    @Value("${pdf.directory}")
    private String pdfDirectory;

    @Override
    public ResponseEntity<String> generateReport(Map<String, String> requestMapping) {
        log.info("Inside Generate Remote Method  Method :{}",requestMapping);
        try{
            String fileName;
            if(validRequestMap(requestMapping)){
                if(requestMapping.containsKey("isGenerate")){
                    fileName=(String) requestMapping.get("uuid");
                }
                else{
                    fileName=CafeUtils.getUUID();
                    requestMapping.put("UUID",fileName);
                    insertBill(requestMapping);
                }
                String data="Name:"+requestMapping.get("name")+"/n"+
                            "Contact Number:"+requestMapping.get("contactNumber")+"/n"+
                            "Email:"+requestMapping.get("email")+"/n"+
                            "Payment Method:"+requestMapping.get("paymentMethod");

                    Document doc=new Document();
                PdfWriter.getInstance(doc,new FileOutputStream(pdfDirectory));
                doc.open();
                setRectanglePdf(doc);

                Paragraph headerParagraph=new Paragraph("Cafe Management System",getFont("Header"));
                doc.add(headerParagraph);

                Paragraph dataParagraph=new Paragraph(data,getFont("Data"));
                doc.add(dataParagraph);

                Paragraph amount=new Paragraph("Total Amount"+requestMapping.get("totalAmount"),getFont("Data"));
                doc.add(amount);

                Paragraph thankYou=new Paragraph("Thank You & Visit Again",getFont("Data"));
                doc.add(thankYou);


            }
            else{
                return CafeUtils.getResponseEntity("Required Data not found", HttpStatus.BAD_REQUEST);
            }


        }
        catch (Exception e){
            e.printStackTrace();
        }
        return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WENT_WRONG,HttpStatus.BAD_REQUEST);
    }

    private Font getFont(String type) {
        switch (type){
            case "Header":
                Font headerFont=FontFactory.getFont(FontFactory.COURIER_BOLD,20,BaseColor.BLACK);
                headerFont.setStyle(Font.BOLD);
                return headerFont;
            case "Data":
                return FontFactory.getFont(FontFactory.TIMES_BOLD,12,BaseColor.BLACK);
            default:
                Font defaultFont=FontFactory.getFont(FontFactory.TIMES,12,BaseColor.BLACK);
                return defaultFont;
        }
    }

    private void setRectanglePdf(Document doc) throws DocumentException {
        log.info("Inside Rectangle");
        Rectangle rectangle=new Rectangle(577,825,18,15);
        rectangle.setBackgroundColor(BaseColor.BLACK);
        rectangle.enableBorderSide(1);
        rectangle.enableBorderSide(2);
        rectangle.enableBorderSide(4);
        rectangle.enableBorderSide(8);
        rectangle.setBorderWidth(1);
        doc.add(rectangle);

    }

    private void insertBill(Map<String, String> requestMapping) {
        Bill bill=new Bill();
        bill.setName((String) requestMapping.get("UUID"));
        bill.setEmail(requestMapping.get("email"));
        bill.setContacNumber(requestMapping.get("contactNumber"));
        bill.setPaymentMethod(requestMapping.get("paymentMethod"));
        bill.setTotalAmount(Integer.parseInt(requestMapping.get("totalAmount")));
        bill.setCreatedBy(filter.getCurrentUser());
        bill.setProductDetails((String) requestMapping.get("productDetails"));
        billDao.save(bill);

    }

    private boolean validRequestMap(Map<String, String> requestMapping) {
        return requestMapping.containsKey("name") && requestMapping.containsKey("email") &&
                requestMapping.containsKey("contactNumber") && requestMapping.containsKey("paymentMethod")
                && requestMapping.containsKey("productDetails") && requestMapping.containsKey("totalAmount");
    }


}
