package com.dng.cs.core.repository.utils;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.Map;

@Repository
public class UtilBaseRepository {

    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcCall functionJdbc;
    private final SimpleJdbcCall procJdbc;
    private final DataSource dataSource;
    private final static String CHECKING_CARD = "CHECKING_CARD";
    private final static String MASK_CARD = "MASK_CARD";

    public UtilBaseRepository(JdbcTemplate jdbcTemplate, DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = jdbcTemplate;
        this.functionJdbc = new SimpleJdbcCall(jdbcTemplate).withFunctionName(CHECKING_CARD);
        this.procJdbc = new SimpleJdbcCall(dataSource).withProcedureName(MASK_CARD);
    }

    public boolean checkingCard(String cardNumber){
        SqlParameterSource in = new MapSqlParameterSource().addValue("CONTRACT_NUMBER", cardNumber);
        String result = functionJdbc.executeFunction(String.class, in);
        return result.equals("Y");
    }

    public String maskPan(String cardNumber){
        SqlParameterSource in = new MapSqlParameterSource().addValue("CONTRACT_NUMBER", cardNumber);
        Map<String, Object> out = procJdbc.execute(in);
        return out.get("MASK_NUMBER").toString();
    }
}
