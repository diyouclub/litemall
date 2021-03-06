package org.linlinjava.litemall.db.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

public class LitemallTabInfoClass {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table litemall_tab_info_class
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    public static final Boolean NOT_DELETED = false;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table litemall_tab_info_class
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    public static final Boolean IS_DELETED = true;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column litemall_tab_info_class.id
     *
     * @mbg.generated
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column litemall_tab_info_class.cls_type
     *
     * @mbg.generated
     */
    private String clsType;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column litemall_tab_info_class.type
     *
     * @mbg.generated
     */
    private Integer type;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column litemall_tab_info_class.cls_name
     *
     * @mbg.generated
     */
    private String clsName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column litemall_tab_info_class.cls_icon
     *
     * @mbg.generated
     */
    private String clsIcon;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column litemall_tab_info_class.parent_id
     *
     * @mbg.generated
     */
    private Integer parentId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column litemall_tab_info_class.show_index
     *
     * @mbg.generated
     */
    private Integer showIndex;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column litemall_tab_info_class.rank
     *
     * @mbg.generated
     */
    private Integer rank;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column litemall_tab_info_class.state
     *
     * @mbg.generated
     */
    private Integer state;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column litemall_tab_info_class.index_limit
     *
     * @mbg.generated
     */
    private Integer indexLimit;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column litemall_tab_info_class.create_user
     *
     * @mbg.generated
     */
    private String createUser;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column litemall_tab_info_class.create_time
     *
     * @mbg.generated
     */
    private LocalDateTime createTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column litemall_tab_info_class.update_user
     *
     * @mbg.generated
     */
    private String updateUser;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column litemall_tab_info_class.update_time
     *
     * @mbg.generated
     */
    private LocalDateTime updateTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column litemall_tab_info_class.deleted
     *
     * @mbg.generated
     */
    private Boolean deleted;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column litemall_tab_info_class.id
     *
     * @return the value of litemall_tab_info_class.id
     *
     * @mbg.generated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column litemall_tab_info_class.id
     *
     * @param id the value for litemall_tab_info_class.id
     *
     * @mbg.generated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column litemall_tab_info_class.cls_type
     *
     * @return the value of litemall_tab_info_class.cls_type
     *
     * @mbg.generated
     */
    public String getClsType() {
        return clsType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column litemall_tab_info_class.cls_type
     *
     * @param clsType the value for litemall_tab_info_class.cls_type
     *
     * @mbg.generated
     */
    public void setClsType(String clsType) {
        this.clsType = clsType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column litemall_tab_info_class.type
     *
     * @return the value of litemall_tab_info_class.type
     *
     * @mbg.generated
     */
    public Integer getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column litemall_tab_info_class.type
     *
     * @param type the value for litemall_tab_info_class.type
     *
     * @mbg.generated
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column litemall_tab_info_class.cls_name
     *
     * @return the value of litemall_tab_info_class.cls_name
     *
     * @mbg.generated
     */
    public String getClsName() {
        return clsName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column litemall_tab_info_class.cls_name
     *
     * @param clsName the value for litemall_tab_info_class.cls_name
     *
     * @mbg.generated
     */
    public void setClsName(String clsName) {
        this.clsName = clsName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column litemall_tab_info_class.cls_icon
     *
     * @return the value of litemall_tab_info_class.cls_icon
     *
     * @mbg.generated
     */
    public String getClsIcon() {
        return clsIcon;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column litemall_tab_info_class.cls_icon
     *
     * @param clsIcon the value for litemall_tab_info_class.cls_icon
     *
     * @mbg.generated
     */
    public void setClsIcon(String clsIcon) {
        this.clsIcon = clsIcon;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column litemall_tab_info_class.parent_id
     *
     * @return the value of litemall_tab_info_class.parent_id
     *
     * @mbg.generated
     */
    public Integer getParentId() {
        return parentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column litemall_tab_info_class.parent_id
     *
     * @param parentId the value for litemall_tab_info_class.parent_id
     *
     * @mbg.generated
     */
    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column litemall_tab_info_class.show_index
     *
     * @return the value of litemall_tab_info_class.show_index
     *
     * @mbg.generated
     */
    public Integer getShowIndex() {
        return showIndex;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column litemall_tab_info_class.show_index
     *
     * @param showIndex the value for litemall_tab_info_class.show_index
     *
     * @mbg.generated
     */
    public void setShowIndex(Integer showIndex) {
        this.showIndex = showIndex;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column litemall_tab_info_class.rank
     *
     * @return the value of litemall_tab_info_class.rank
     *
     * @mbg.generated
     */
    public Integer getRank() {
        return rank;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column litemall_tab_info_class.rank
     *
     * @param rank the value for litemall_tab_info_class.rank
     *
     * @mbg.generated
     */
    public void setRank(Integer rank) {
        this.rank = rank;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column litemall_tab_info_class.state
     *
     * @return the value of litemall_tab_info_class.state
     *
     * @mbg.generated
     */
    public Integer getState() {
        return state;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column litemall_tab_info_class.state
     *
     * @param state the value for litemall_tab_info_class.state
     *
     * @mbg.generated
     */
    public void setState(Integer state) {
        this.state = state;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column litemall_tab_info_class.index_limit
     *
     * @return the value of litemall_tab_info_class.index_limit
     *
     * @mbg.generated
     */
    public Integer getIndexLimit() {
        return indexLimit;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column litemall_tab_info_class.index_limit
     *
     * @param indexLimit the value for litemall_tab_info_class.index_limit
     *
     * @mbg.generated
     */
    public void setIndexLimit(Integer indexLimit) {
        this.indexLimit = indexLimit;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column litemall_tab_info_class.create_user
     *
     * @return the value of litemall_tab_info_class.create_user
     *
     * @mbg.generated
     */
    public String getCreateUser() {
        return createUser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column litemall_tab_info_class.create_user
     *
     * @param createUser the value for litemall_tab_info_class.create_user
     *
     * @mbg.generated
     */
    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column litemall_tab_info_class.create_time
     *
     * @return the value of litemall_tab_info_class.create_time
     *
     * @mbg.generated
     */
    public LocalDateTime getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column litemall_tab_info_class.create_time
     *
     * @param createTime the value for litemall_tab_info_class.create_time
     *
     * @mbg.generated
     */
    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column litemall_tab_info_class.update_user
     *
     * @return the value of litemall_tab_info_class.update_user
     *
     * @mbg.generated
     */
    public String getUpdateUser() {
        return updateUser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column litemall_tab_info_class.update_user
     *
     * @param updateUser the value for litemall_tab_info_class.update_user
     *
     * @mbg.generated
     */
    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column litemall_tab_info_class.update_time
     *
     * @return the value of litemall_tab_info_class.update_time
     *
     * @mbg.generated
     */
    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column litemall_tab_info_class.update_time
     *
     * @param updateTime the value for litemall_tab_info_class.update_time
     *
     * @mbg.generated
     */
    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column litemall_tab_info_class.deleted
     *
     * @return the value of litemall_tab_info_class.deleted
     *
     * @mbg.generated
     */
    public Boolean getDeleted() {
        return deleted;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column litemall_tab_info_class.deleted
     *
     * @param deleted the value for litemall_tab_info_class.deleted
     *
     * @mbg.generated
     */
    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_tab_info_class
     *
     * @mbg.generated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", clsType=").append(clsType);
        sb.append(", type=").append(type);
        sb.append(", clsName=").append(clsName);
        sb.append(", clsIcon=").append(clsIcon);
        sb.append(", parentId=").append(parentId);
        sb.append(", showIndex=").append(showIndex);
        sb.append(", rank=").append(rank);
        sb.append(", state=").append(state);
        sb.append(", indexLimit=").append(indexLimit);
        sb.append(", createUser=").append(createUser);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateUser=").append(updateUser);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", deleted=").append(deleted);
        sb.append("]");
        return sb.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_tab_info_class
     *
     * @mbg.generated
     */
    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        LitemallTabInfoClass other = (LitemallTabInfoClass) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getClsType() == null ? other.getClsType() == null : this.getClsType().equals(other.getClsType()))
            && (this.getType() == null ? other.getType() == null : this.getType().equals(other.getType()))
            && (this.getClsName() == null ? other.getClsName() == null : this.getClsName().equals(other.getClsName()))
            && (this.getClsIcon() == null ? other.getClsIcon() == null : this.getClsIcon().equals(other.getClsIcon()))
            && (this.getParentId() == null ? other.getParentId() == null : this.getParentId().equals(other.getParentId()))
            && (this.getShowIndex() == null ? other.getShowIndex() == null : this.getShowIndex().equals(other.getShowIndex()))
            && (this.getRank() == null ? other.getRank() == null : this.getRank().equals(other.getRank()))
            && (this.getState() == null ? other.getState() == null : this.getState().equals(other.getState()))
            && (this.getIndexLimit() == null ? other.getIndexLimit() == null : this.getIndexLimit().equals(other.getIndexLimit()))
            && (this.getCreateUser() == null ? other.getCreateUser() == null : this.getCreateUser().equals(other.getCreateUser()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateUser() == null ? other.getUpdateUser() == null : this.getUpdateUser().equals(other.getUpdateUser()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getDeleted() == null ? other.getDeleted() == null : this.getDeleted().equals(other.getDeleted()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_tab_info_class
     *
     * @mbg.generated
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getClsType() == null) ? 0 : getClsType().hashCode());
        result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
        result = prime * result + ((getClsName() == null) ? 0 : getClsName().hashCode());
        result = prime * result + ((getClsIcon() == null) ? 0 : getClsIcon().hashCode());
        result = prime * result + ((getParentId() == null) ? 0 : getParentId().hashCode());
        result = prime * result + ((getShowIndex() == null) ? 0 : getShowIndex().hashCode());
        result = prime * result + ((getRank() == null) ? 0 : getRank().hashCode());
        result = prime * result + ((getState() == null) ? 0 : getState().hashCode());
        result = prime * result + ((getIndexLimit() == null) ? 0 : getIndexLimit().hashCode());
        result = prime * result + ((getCreateUser() == null) ? 0 : getCreateUser().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateUser() == null) ? 0 : getUpdateUser().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getDeleted() == null) ? 0 : getDeleted().hashCode());
        return result;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_tab_info_class
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    public void andLogicalDeleted(boolean deleted) {
        setDeleted(deleted ? IS_DELETED : NOT_DELETED);
    }

    /**
     * This enum was generated by MyBatis Generator.
     * This enum corresponds to the database table litemall_tab_info_class
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    public enum Column {
        id("id", "id", "INTEGER", false),
        clsType("cls_type", "clsType", "VARCHAR", false),
        type("type", "type", "INTEGER", true),
        clsName("cls_name", "clsName", "VARCHAR", false),
        clsIcon("cls_icon", "clsIcon", "VARCHAR", false),
        parentId("parent_id", "parentId", "INTEGER", false),
        showIndex("show_index", "showIndex", "INTEGER", false),
        rank("rank", "rank", "INTEGER", true),
        state("state", "state", "INTEGER", true),
        indexLimit("index_limit", "indexLimit", "INTEGER", false),
        createUser("create_user", "createUser", "VARCHAR", false),
        createTime("create_time", "createTime", "TIMESTAMP", false),
        updateUser("update_user", "updateUser", "VARCHAR", false),
        updateTime("update_time", "updateTime", "TIMESTAMP", false),
        deleted("deleted", "deleted", "BIT", false);

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table litemall_tab_info_class
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        private static final String BEGINNING_DELIMITER = "`";

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table litemall_tab_info_class
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        private static final String ENDING_DELIMITER = "`";

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table litemall_tab_info_class
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        private final String column;

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table litemall_tab_info_class
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        private final boolean isColumnNameDelimited;

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table litemall_tab_info_class
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        private final String javaProperty;

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table litemall_tab_info_class
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        private final String jdbcType;

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table litemall_tab_info_class
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public String value() {
            return this.column;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table litemall_tab_info_class
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public String getValue() {
            return this.column;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table litemall_tab_info_class
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public String getJavaProperty() {
            return this.javaProperty;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table litemall_tab_info_class
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public String getJdbcType() {
            return this.jdbcType;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table litemall_tab_info_class
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        Column(String column, String javaProperty, String jdbcType, boolean isColumnNameDelimited) {
            this.column = column;
            this.javaProperty = javaProperty;
            this.jdbcType = jdbcType;
            this.isColumnNameDelimited = isColumnNameDelimited;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table litemall_tab_info_class
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public String desc() {
            return this.getEscapedColumnName() + " DESC";
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table litemall_tab_info_class
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public String asc() {
            return this.getEscapedColumnName() + " ASC";
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table litemall_tab_info_class
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public static Column[] excludes(Column ... excludes) {
            ArrayList<Column> columns = new ArrayList<>(Arrays.asList(Column.values()));
            if (excludes != null && excludes.length > 0) {
                columns.removeAll(new ArrayList<>(Arrays.asList(excludes)));
            }
            return columns.toArray(new Column[]{});
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table litemall_tab_info_class
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public String getEscapedColumnName() {
            if (this.isColumnNameDelimited) {
                return new StringBuilder().append(BEGINNING_DELIMITER).append(this.column).append(ENDING_DELIMITER).toString();
            } else {
                return this.column;
            }
        }
    }
}