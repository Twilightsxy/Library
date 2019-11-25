package library.db;

import java.util.List;

import library.domain.Member;

/**
 * 团队成员数据库接口
 * 
 * @author ozj
 * @version 1.0
 */
public interface MemberRepository {

	/**
	 * 取得所有的团队成员
	 * 
	 * @return List<Member> 团队成员列表
	 */
	List<Member> getMembers();
	
}
