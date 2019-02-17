package org.linlinjava.litemall.db.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.linlinjava.litemall.db.domain.LitemallBankcard;
import org.linlinjava.litemall.db.domain.LitemallBankcardExample;

public interface LitemallBankcardMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_bankcard
     *
     * @mbg.generated
     */
    long countByExample(LitemallBankcardExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_bankcard
     *
     * @mbg.generated
     */
    int deleteByExample(LitemallBankcardExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_bankcard
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_bankcard
     *
     * @mbg.generated
     */
    int insert(LitemallBankcard record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_bankcard
     *
     * @mbg.generated
     */
    int insertSelective(LitemallBankcard record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_bankcard
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    LitemallBankcard selectOneByExample(LitemallBankcardExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_bankcard
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    LitemallBankcard selectOneByExampleSelective(@Param("example") LitemallBankcardExample example, @Param("selective") LitemallBankcard.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_bankcard
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    List<LitemallBankcard> selectByExampleSelective(@Param("example") LitemallBankcardExample example, @Param("selective") LitemallBankcard.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_bankcard
     *
     * @mbg.generated
     */
    List<LitemallBankcard> selectByExample(LitemallBankcardExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_bankcard
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    LitemallBankcard selectByPrimaryKeySelective(@Param("id") Integer id, @Param("selective") LitemallBankcard.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_bankcard
     *
     * @mbg.generated
     */
    LitemallBankcard selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_bankcard
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    LitemallBankcard selectByPrimaryKeyWithLogicalDelete(@Param("id") Integer id, @Param("andLogicalDeleted") boolean andLogicalDeleted);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_bankcard
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") LitemallBankcard record, @Param("example") LitemallBankcardExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_bankcard
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") LitemallBankcard record, @Param("example") LitemallBankcardExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_bankcard
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(LitemallBankcard record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_bankcard
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(LitemallBankcard record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_bankcard
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    int logicalDeleteByExample(@Param("example") LitemallBankcardExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_bankcard
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    int logicalDeleteByPrimaryKey(Integer id);
}