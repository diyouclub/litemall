package org.linlinjava.litemall.db.dao;

import org.apache.ibatis.annotations.Param;
import org.linlinjava.litemall.db.domain.LitemallTabInfo;
import org.linlinjava.litemall.db.domain.LitemallTabInfoExample;

import java.util.List;

public interface LitemallTabInfoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_tab_info
     *
     * @mbg.generated
     */
    long countByExample(LitemallTabInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_tab_info
     *
     * @mbg.generated
     */
    int deleteByExample(LitemallTabInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_tab_info
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer infoId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_tab_info
     *
     * @mbg.generated
     */
    int insert(LitemallTabInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_tab_info
     *
     * @mbg.generated
     */
    int insertSelective(LitemallTabInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_tab_info
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    LitemallTabInfo selectOneByExample(LitemallTabInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_tab_info
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    LitemallTabInfo selectOneByExampleSelective(@Param("example") LitemallTabInfoExample example, @Param("selective") LitemallTabInfo.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_tab_info
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    List<LitemallTabInfo> selectByExampleSelective(@Param("example") LitemallTabInfoExample example, @Param("selective") LitemallTabInfo.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_tab_info
     *
     * @mbg.generated
     */
    List<LitemallTabInfo> selectByExample(LitemallTabInfoExample example);

    List<LitemallTabInfo> selectByMyself(LitemallTabInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_tab_info
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    LitemallTabInfo selectByPrimaryKeySelective(@Param("infoId") Integer infoId, @Param("selective") LitemallTabInfo.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_tab_info
     *
     * @mbg.generated
     */
    LitemallTabInfo selectByPrimaryKey(Integer infoId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_tab_info
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    LitemallTabInfo selectByPrimaryKeyWithLogicalDelete(@Param("infoId") Integer infoId, @Param("andLogicalDeleted") boolean andLogicalDeleted);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_tab_info
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") LitemallTabInfo record, @Param("example") LitemallTabInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_tab_info
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") LitemallTabInfo record, @Param("example") LitemallTabInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_tab_info
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(LitemallTabInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_tab_info
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(LitemallTabInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_tab_info
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    int logicalDeleteByExample(@Param("example") LitemallTabInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_tab_info
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    int logicalDeleteByPrimaryKey(Integer infoId);
}