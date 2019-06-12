package com.neo.mapper;

import com.neo.model.User;

import java.util.List;
/**  
 *    
 * @Description:  
 * @author shuangling.mao 
 * @date 2019/6/10 15:25
 * @param   
 * @return   
 */ 
public interface UserMapper {
	
	List<User> getAll();
	
	User getOne(Long id);

	User getUserByNameAndPassword(User user);

	void insert(User user);

	void update(User user);

	void delete(Long id);

	List<User> getUserByCondition(User user);

}