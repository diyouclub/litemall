package org.linlinjava.litemall.db.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.linlinjava.litemall.db.domain.LitemallExpressOrder;
import org.linlinjava.litemall.db.domain.LitemallExpressOrderExample;

public interface LitemallExpressOrderMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_express_order
     *
     * @mbg.generated
     */
    long countByExample(LitemallExpressOrderExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_express_order
     *
     * @mbg.generated
     */
    int deleteByExample(LitemallExpressOrderExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_express_order
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_express_order
     *
     * @mbg.generated
     */
    int insert(LitemallExpressOrder record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_express_order
     *
     * @mbg.generated
     */
    int insertSelective(LitemallExpressOrder record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_express_order
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    LitemallExpressOrder selectOneByExample(LitemallExpressOrderExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_express_order
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    LitemallExpressOrder selectOneByExampleSelective(@Param("example") LitemallExpressOrderExample example, @Param("selective") LitemallExpressOrder.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_express_order
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    List<LitemallExpressOrder> selectByExampleSelective(@Param("example") LitemallExpressOrderExample example, @Param("selective") LitemallExpressOrder.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_express_order
     *
     * @mbg.generated
     */
    List<LitemallExpressOrder> selectByExample(LitemallExpressOrderExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_express_order
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    LitemallExpressOrder selectByPrimaryKeySelective(@Param("id") Integer id, @Param("selective") LitemallExpressOrder.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_express_order
     *
     * @mbg.generated
     */
    LitemallExpressOrder selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_express_order
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    LitemallExpressOrder selectByPrimaryKeyWithLogicalDelete(@Param("id") Integer id, @Param("andLogicalDeleted") boolean andLogicalDeleted);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_express_order
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") LitemallExpressOrder record, @Param("example") LitemallExpressOrderExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_express_order
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") LitemallExpressOrder record, @Param("example") LitemallExpressOrderExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_express_order
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(LitemallExpressOrder record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_express_order
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(LitemallExpressOrder record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_express_order
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    int logicalDeleteByExample(@Param("example") LitemallExpressOrderExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_express_order
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    int logicalDeleteByPrimaryKey(Integer id);
}