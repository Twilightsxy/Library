package library.domain;

/**
 * 团队成员类
 * 
 * @author ozj
 *
 */
public class Member {
	
	private int memberNo;
	private String describe;
	private String memberName;
	private String picture;

	public Member() {
		super();
	}

	/**
	 * 
	 * @param memberNo   团队成员no
	 * @param describe   描叙
	 * @param memberName 名字
	 * @param picture    图片路径
	 */
	public Member(int memberNo, String describe, String memberName, String picture) {
		super();
		this.memberNo = memberNo;
		this.describe = describe;
		this.memberName = memberName;
		this.picture = picture;
	}

	/**
	 * 返回团队成员no
	 * 
	 * @return the memberNo
	 */
	public int getMemberNo() {
		return memberNo;
	}

	/**
	 * 返回成员描叙
	 * 
	 * @return the describe
	 */
	public String getDescribe() {
		return describe;
	}

	/**
	 * 返回成员姓名
	 * 
	 * @return the memberName
	 */
	public String getMemberName() {
		return memberName;
	}

	/**
	 * 返回图片路径
	 * 
	 * @return the picture
	 */
	public String getPicture() {
		return picture;
	}

	/**
	 * 设置成员no
	 * 
	 * @param memberNo the memberNo to set
	 */
	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}

	/**
	 * 设置成员描叙
	 * 
	 * @param describe the describe to set
	 */
	public void setDescribe(String describe) {
		this.describe = describe;
	}

	/**
	 * 设置成员名字
	 * 
	 * @param memberName the memberName to set
	 */
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	/**
	 * 设置成员图片路径
	 * 
	 * @param picture the picture to set
	 */
	public void setPicture(String picture) {
		this.picture = picture;
	}

}
