package org.chobit.spring.ext.mybatis.type;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.chobit.commons.utils.StrKit.isBlank;

@MappedTypes({String.class})
@MappedJdbcTypes(JdbcType.VARCHAR)
public class DlpTypeHandler extends BaseTypeHandler<String> {


    private static final String PREFIX_ID = "ID:";


    @Override
    public void setParameter(PreparedStatement ps, int i, String parameter, JdbcType jdbcType) throws SQLException {
        super.setParameter(ps, i, parameter, jdbcType);
    }


    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, String parameter, JdbcType jdbcType) throws SQLException {
        if (parameter.startsWith(PREFIX_ID)) {
            parameter = parameter.substring(PREFIX_ID.length());
        }

        ps.setString(i, parameter);
    }


    @Override
    public String getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String value = rs.getString(columnName);
        if (isBlank(value)) {
            return value;
        }
        return PREFIX_ID + value;
    }


    @Override
    public String getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String value = rs.getString(columnIndex);
        return null == value ? "" : value;
    }


    @Override
    public String getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String value = cs.getString(columnIndex);
        return null == value ? "" : value;
    }
}
