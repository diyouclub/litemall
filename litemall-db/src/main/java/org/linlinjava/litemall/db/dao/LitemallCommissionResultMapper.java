package org.linlinjava.litemall.db.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.linlinjava.litemall.db.domain.LitemallCommissionResult;
import org.linlinjava.litemall.db.domain.LitemallCommissionResultExample;

public interface LitemallCommissionResultMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_commission_result
     *
     * @mbg.generated
     */
    long countByExample(LitemallCommissionResultExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_commission_result
     *
     * @mbg.generated
     */
    int deleteByExample(LitemallCommissionResultExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_commission_result
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_commission_result
     *
     * @mbg.generated
     */
    int insert(LitemallCommissionResult record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_commission_result
     *
     * @mbg.generated
     */
    int insertSelective(LitemallCommissionResult record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_commission_result
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    LitemallCommissionResult selectOneByExample(LitemallCommissionResultExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_commission_result
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    LitemallCommissionResult selectOneByExampleSelective(@Param("example") LitemallCommissionResultExample example, @Param("selective") LitemallCommissionResult.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_commission_result
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    List<LitemallCommissionResult> selectByExampleSelective(@Param("example") LitemallCommissionResultExample example, @Param("selective") LitemallCommissionResult.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_commission_result
     *
     * @mbg.generated
     */
    List<LitemallCommissionResult> selectByExample(LitemallCommissionResultExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_commission_result
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    LitemallCommissionResult selectByPrimaryKeySelective(@Param("id") Integer id, @Param("selective") LitemallCommissionResult.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_commission_result
     *
     * @mbg.generated
     */
    LitemallCommissionResult selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_commission_result
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    LitemallCommissionResult selectByPrimaryKeyWithLogicalDelete(@Param("id") Integer id, @Param("andLogicalDeleted") boolean andLogicalDeleted);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_commission_result
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") LitemallCommissionResult record, @Param("example") LitemallCommissionResultExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_commission_result
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") LitemallCommissionResult record, @Param("example") LitemallCommissionResultExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_commission_result
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(LitemallCommissionResult record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_commission_result
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(LitemallCommissionResult record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_commission_result
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    int logicalDeleteByExample(@Param("example") LitemallCommissionResultExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_commission_result
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    int logicalDeleteByPrimaryKey(Integer id);

    List<LitemallCommissionResult> getCommissionList(Integer user_id);
}