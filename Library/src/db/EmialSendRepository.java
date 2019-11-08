package db;

/**
 * 发邮件的传输接口
 * 
 * @author ozj
 * @version 1.0
 */
public interface EmialSendRepository {

	/**
	 * 实现发邮件
	 * 
	 * @param emailNo
	 * @param password
	 * @return
	 */
	boolean sendMail(String emailNo, String password);
}
