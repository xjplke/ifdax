/**
 * 
 */
package cn.adfi.rbac.dao.user;


import org.springframework.stereotype.Repository;

import cn.adfi.rbac.model.User;

import cn.adfi.rbac.dao.BaseDAO;



/**
 * @author shaojunwu  --sjw
 * @date 2014-5-3
 */
@Repository
public class UserDaoImpl extends BaseDAO<User, Long> implements IUserDao {

}
