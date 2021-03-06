package org.linlinjava.litemall.db.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.linlinjava.litemall.db.domain.LitemallBank;
import org.linlinjava.litemall.db.domain.LitemallBankExample;

public interface LitemallBankMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_bank
     *
     * @mbg.generated
     */
    long countByExample(LitemallBankExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_bank
     *
     * @mbg.generated
     */
    int deleteByExample(LitemallBankExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_bank
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_bank
     *
     * @mbg.generated
     */
    int insert(LitemallBank record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_bank
     *
     * @mbg.generated
     */
    int insertSelective(LitemallBank record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_bank
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    LitemallBank selectOneByExample(LitemallBankExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_bank
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    LitemallBank selectOneByExampleSelective(@Param("example") LitemallBankExample example, @Param("selective") LitemallBank.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_bank
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    List<LitemallBank> selectByExampleSelective(@Param("example") LitemallBankExample example, @Param("selective") LitemallBank.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_bank
     *
     * @mbg.generated
     */
    List<LitemallBank> selectByExample(LitemallBankExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_bank
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    LitemallBank selectByPrimaryKeySelective(@Param("id") Integer id, @Param("selective") LitemallBank.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_bank
     *
     * @mbg.generated
     */
    LitemallBank selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_bank
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    LitemallBank selectByPrimaryKeyWithLogicalDelete(@Param("id") Integer id, @Param("andLogicalDeleted") boolean andLogicalDeleted);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_bank
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") LitemallBank record, @Param("example") LitemallBankExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_bank
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") LitemallBank record, @Param("example") LitemallBankExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_bank
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(LitemallBank record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_bank
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(LitemallBank record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_bank
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    int logicalDeleteByExample(@Param("example") LitemallBankExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_bank
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    int logicalDeleteByPrimaryKey(Integer id);
}