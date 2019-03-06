package org.linlinjava.litemall.db.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.linlinjava.litemall.db.domain.LitemallCodeTree;
import org.linlinjava.litemall.db.domain.LitemallCodeTreeExample;

public interface LitemallCodeTreeMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_code_tree
     *
     * @mbg.generated
     */
    long countByExample(LitemallCodeTreeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_code_tree
     *
     * @mbg.generated
     */
    int deleteByExample(LitemallCodeTreeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_code_tree
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_code_tree
     *
     * @mbg.generated
     */
    int insert(LitemallCodeTree record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_code_tree
     *
     * @mbg.generated
     */
    int insertSelective(LitemallCodeTree record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_code_tree
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    LitemallCodeTree selectOneByExample(LitemallCodeTreeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_code_tree
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    LitemallCodeTree selectOneByExampleSelective(@Param("example") LitemallCodeTreeExample example, @Param("selective") LitemallCodeTree.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_code_tree
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    List<LitemallCodeTree> selectByExampleSelective(@Param("example") LitemallCodeTreeExample example, @Param("selective") LitemallCodeTree.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_code_tree
     *
     * @mbg.generated
     */
    List<LitemallCodeTree> selectByExample(LitemallCodeTreeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_code_tree
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    LitemallCodeTree selectByPrimaryKeySelective(@Param("id") Integer id, @Param("selective") LitemallCodeTree.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_code_tree
     *
     * @mbg.generated
     */
    LitemallCodeTree selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_code_tree
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") LitemallCodeTree record, @Param("example") LitemallCodeTreeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_code_tree
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") LitemallCodeTree record, @Param("example") LitemallCodeTreeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_code_tree
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(LitemallCodeTree record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_code_tree
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(LitemallCodeTree record);
}