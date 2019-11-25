package library.jdbc;

import java.util.Date;
import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.*;

import org.springframework.stereotype.Repository;

import library.db.EmailSendRepository;
/**
 * 邮件发送接口实现
 * @author ozj
 * @version 1.0
 *
 */
@Repository
public class JdbcEmailSendRepository implements EmailSendRepository {

	@Override
	public boolean sendMail(String emailNo, String password) {
		String to =emailNo;// 
		String from = "1145790078@qq.com";     
		Properties properties = System.getProperties();
		properties.put("mail.smtp.host", "smtp.qq.com");
		properties.put("mail.smtp.auth", "true");
		Session session = Session.getDefaultInstance(properties);
		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			message.setSubject("Retrieve Password");
			message.setSentDate(new Date());
			message.setText("Dear reader,your password is "+password);
			Transport transport = session.getTransport("smtp");
			transport.connect("smtp.qq.com", "1145790078", "hlkhhxgrhjaejjcd");					
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
			return true;
			
		} catch (MessagingException mex) {
			mex.printStackTrace();
			return false;
		}
	}

}
