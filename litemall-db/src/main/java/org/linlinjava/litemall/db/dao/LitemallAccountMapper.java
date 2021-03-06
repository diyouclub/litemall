package org.linlinjava.litemall.db.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.linlinjava.litemall.db.domain.LitemallAccount;
import org.linlinjava.litemall.db.domain.LitemallAccountExample;

public interface LitemallAccountMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_account
     *
     * @mbg.generated
     */
    long countByExample(LitemallAccountExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_account
     *
     * @mbg.generated
     */
    int deleteByExample(LitemallAccountExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_account
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_account
     *
     * @mbg.generated
     */
    int insert(LitemallAccount record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_account
     *
     * @mbg.generated
     */
    int insertSelective(LitemallAccount record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_account
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    LitemallAccount selectOneByExample(LitemallAccountExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_account
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    LitemallAccount selectOneByExampleSelective(@Param("example") LitemallAccountExample example, @Param("selective") LitemallAccount.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_account
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    List<LitemallAccount> selectByExampleSelective(@Param("example") LitemallAccountExample example, @Param("selective") LitemallAccount.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_account
     *
     * @mbg.generated
     */
    List<LitemallAccount> selectByExample(LitemallAccountExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_account
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    LitemallAccount selectByPrimaryKeySelective(@Param("id") Integer id, @Param("selective") LitemallAccount.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_account
     *
     * @mbg.generated
     */
    LitemallAccount selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_account
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    LitemallAccount selectByPrimaryKeyWithLogicalDelete(@Param("id") Integer id, @Param("andLogicalDeleted") boolean andLogicalDeleted);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_account
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") LitemallAccount record, @Param("example") LitemallAccountExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_account
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") LitemallAccount record, @Param("example") LitemallAccountExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_account
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(LitemallAccount record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_account
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(LitemallAccount record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_account
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    int logicalDeleteByExample(@Param("example") LitemallAccountExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_account
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    int logicalDeleteByPrimaryKey(Integer id);
}