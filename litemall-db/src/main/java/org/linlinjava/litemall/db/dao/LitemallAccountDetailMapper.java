package org.linlinjava.litemall.db.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.linlinjava.litemall.db.domain.LitemallAccountDetail;
import org.linlinjava.litemall.db.domain.LitemallAccountDetailExample;

public interface LitemallAccountDetailMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_account_detail
     *
     * @mbg.generated
     */
    long countByExample(LitemallAccountDetailExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_account_detail
     *
     * @mbg.generated
     */
    int deleteByExample(LitemallAccountDetailExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_account_detail
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_account_detail
     *
     * @mbg.generated
     */
    int insert(LitemallAccountDetail record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_account_detail
     *
     * @mbg.generated
     */
    int insertSelective(LitemallAccountDetail record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_account_detail
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    LitemallAccountDetail selectOneByExample(LitemallAccountDetailExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_account_detail
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    LitemallAccountDetail selectOneByExampleSelective(@Param("example") LitemallAccountDetailExample example, @Param("selective") LitemallAccountDetail.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_account_detail
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    List<LitemallAccountDetail> selectByExampleSelective(@Param("example") LitemallAccountDetailExample example, @Param("selective") LitemallAccountDetail.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_account_detail
     *
     * @mbg.generated
     */
    List<LitemallAccountDetail> selectByExample(LitemallAccountDetailExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_account_detail
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    LitemallAccountDetail selectByPrimaryKeySelective(@Param("id") Integer id, @Param("selective") LitemallAccountDetail.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_account_detail
     *
     * @mbg.generated
     */
    LitemallAccountDetail selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_account_detail
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    LitemallAccountDetail selectByPrimaryKeyWithLogicalDelete(@Param("id") Integer id, @Param("andLogicalDeleted") boolean andLogicalDeleted);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_account_detail
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") LitemallAccountDetail record, @Param("example") LitemallAccountDetailExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_account_detail
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") LitemallAccountDetail record, @Param("example") LitemallAccountDetailExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_account_detail
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(LitemallAccountDetail record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_account_detail
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(LitemallAccountDetail record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_account_detail
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    int logicalDeleteByExample(@Param("example") LitemallAccountDetailExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_account_detail
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    int logicalDeleteByPrimaryKey(Integer id);
}