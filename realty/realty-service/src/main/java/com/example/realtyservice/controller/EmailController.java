package com.example.realtyservice.controller;
import com.df.commonmodel.exceptions.FactoryException;
import com.df.commonmodel.models.ApiResponse;
import com.example.realtyservice.model.Message;
import com.example.realtyservice.model.MyConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class EmailController {
    @Autowired
    public JavaMailSender emailSender;

    @ResponseBody
    @RequestMapping(value ={"/test/sendEmail","/sendMail"} )
    public ResponseEntity sendSimpleEmail(Message message1) {
        try {
            // Create a Simple MailMessage.
            SimpleMailMessage message = new SimpleMailMessage();
            String content ="";
            if (message1.getName()!=null){
                content+="chào anh! \n Ten toi là: "+message1.getName();
            }
            if (message1.getContent()!=null){
                content+="\n "+message1.getContent();
            }
            content+="\n Thông tin người \n\n";
            if (message1.getEmail()!=null){
                content+="Email: "+message1.getEmail();
            }
            if (message1.getLocation()!=null){
                content+="\nĐịa chỉ: "+message1.getLocation();
            }
            message.setTo(MyConstants.FRIEND_EMAIL);
            message.setSubject("realty");
            message.setText(content);

            // Send Message!
            this.emailSender.send(message);

            return new ApiResponse(true, HttpStatus.OK).build();
        }catch (Exception e){
            return new ApiResponse( HttpStatus.INTERNAL_SERVER_ERROR, new FactoryException()).build();
        }


    }
}
