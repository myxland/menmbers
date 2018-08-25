package com.haffee.menmbers.repository;

import com.haffee.menmbers.entity.AdminUser;
import com.haffee.menmbers.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;


/**
 * create by jacktong
 * date  2018/7/17 下午7:46
 **/
public interface UserRepository extends JpaRepository<User,Integer> {

    /**
     * 根据手机号查询用户
     * @param user_phone
     * @return
     */
    @Query(value="select u from User u where u.userPhone = ?1")
    User findByUserPhone(String user_phone);


    /**
     * 登录更新
     * @param loginKey
     * @param lastLoginTime
     * @param id
     * @return
     */
    @Modifying(clearAutomatically = true)
    @Query(value="update User set loginKey = ?1 , lastLoginTime = ?2 where id = ?3")
    int updateUser(String loginKey, Date lastLoginTime, int id);

    /**
     * 根据卡号查询用户
     * @param cardNo
     * @return
     */
    @Query(value="select u from User u,Card c where u.cardId = c.id and c.cardNo = ?1")
    User getUserByCardNo(String cardNo);

    /**
     * 根据手机号查询用户
     * @param userPhone
     * @return
     */
    @Query(value="select u from User u where u.userPhone = ?1")
    Page<User> findAllByUserPhone(Pageable pageable,String userPhone);

}
