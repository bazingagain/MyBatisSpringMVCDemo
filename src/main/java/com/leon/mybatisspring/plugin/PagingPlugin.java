package com.leon.mybatisspring.plugin;

import com.leon.mybatisspring.pojo.PageParams;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.apache.ibatis.scripting.defaults.DefaultParameterHandler;
import org.apache.ibatis.session.Configuration;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * Created on 20/10/2017.
 *
 * Paging plugin.
 *
 * @author Xiaolei-Peng
 */

@Intercepts({
    @Signature(
        type = StatementHandler.class,
        method = "prepare",
        args = {Connection.class, Integer.class}
    )
}
)
public class PagingPlugin implements Interceptor {

    private Integer defaultPage;  // 默认页码
    private Integer defaultPageSize;  // 默认每页条数
    private Boolean defaultUseFlag;  //默认是否启动插件
    private Boolean defaultCheckFlag;  //默认是否检测当前插件的正确性

    public Object intercept(Invocation invocation) throws Throwable {
        StatementHandler statementHandler = getUnProxyObject(invocation);
        MetaObject metaObject = SystemMetaObject.forObject(statementHandler);
        String sql = (String) metaObject.getValue("delegate.boundSql.sql");
        if (!checkSelect(sql)) {
            return invocation.proceed();
        }
        BoundSql boundSql = (BoundSql) metaObject.getValue("delegate.boundSql");
        Object parameterObject = boundSql.getParameterObject();
        PageParams pageParams = getPageParames(parameterObject);
        if (pageParams == null) { //无分页参数,不启动插件
            return invocation.proceed();
        }
        Integer pageNum = pageParams.getPage() == null ? this.defaultPage : pageParams.getPage();
        Integer pageSize = pageParams.getPageSize() == null ? this.defaultPageSize : pageParams.getPageSize();
        Boolean useFlag = pageParams.getUseFlag() == null ? this.defaultUseFlag : pageParams.getUseFlag();
        Boolean checkFlag = pageParams.getCheckFlag() == null ? this.defaultCheckFlag : pageParams.getCheckFlag();
        if (!useFlag) {
            return invocation.proceed();
        }
        int total = getTotal(invocation, metaObject, boundSql);
        //回填总数到分页参数
        setTotalToPageParams(pageParams, total, pageSize);
        //检查当前页码的有效性
        checkPage(checkFlag, pageNum, pageParams.getTotalPage());
        //修改SQL
        return changeSQL(invocation, metaObject, boundSql, pageNum, pageSize);
    }

    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    public void setProperties(Properties properties) {
        String strDefaultPage = properties.getProperty("default.page", "1");
        String strDefaultPageSize = properties.getProperty("default.pageSize", "50");
        String strDefaultUseFlag = properties.getProperty("default.useFlag", "false");
        String strDefaultCheckFlag = properties.getProperty("default.useFlag", "false");
        this.defaultPage = Integer.parseInt(strDefaultPage);
        this.defaultPageSize = Integer.parseInt(strDefaultPageSize);
        this.defaultUseFlag = Boolean.parseBoolean(strDefaultUseFlag);
        this.defaultCheckFlag = Boolean.parseBoolean(strDefaultCheckFlag);
    }

    /**
     * 从代理对象中分理出真实的对象
     * @param invocation
     * @return 非代理StatementHandler对象
     */
    private StatementHandler getUnProxyObject(Invocation invocation) {
        StatementHandler handler = (StatementHandler) invocation.getTarget();
        MetaObject metaObject = SystemMetaObject.forObject(handler);
        Object object = null;
        while (metaObject.hasGetter("h")) {
            object = metaObject.getValue("h");
        }
        if (object == null) {
            return handler;
        }
        return (StatementHandler) object;
    }

    private boolean checkSelect(String sql) {
        String trimSql = sql.trim();
        int index = trimSql.toLowerCase().indexOf("select");
        return index == 0;
    }

    /**
     * 分解分页参数, 支持Map或@Param,或者POJO继承PageParams
     * @param parameterObject  sql参数
     * @return  分页参数
     */
    private PageParams getPageParames(Object parameterObject) {
        if (parameterObject == null) {
            return null;
        }
        PageParams pageParams = null;
        //Map参数方式,或者@Param注解方式
        if (parameterObject instanceof Map) {
            Map<String, Object> paramMap = (Map<String, Object>) parameterObject;
            Set<String> keySet = paramMap.keySet();
            Iterator<String> iterator = keySet.iterator();
            while (iterator.hasNext()) {
                Object value = paramMap.get(iterator.next());
                if (value instanceof PageParams) {
                    return (PageParams) value;
                }
            }
        } else if (parameterObject instanceof PageParams) { //继承的方式
            pageParams = (PageParams) parameterObject;
        }
        return pageParams;
    }

    private int getTotal(Invocation invocation, MetaObject metaObject, BoundSql boundSql) throws SQLException {
        MappedStatement mappedStatement = (MappedStatement) metaObject.getValue("delegate.mappedStatement");
        Configuration cfg = mappedStatement.getConfiguration();
        String sql = (String) metaObject.getValue("delegate.boundSql.sql");
        String countSql = "select count(*) as total from (" + sql + ") $_paging";
        Connection connection = (Connection) invocation.getArgs()[0];
        PreparedStatement ps = null;
        int total = 0;
        try {
            ps = connection.prepareStatement(countSql);
            //构建统计总数的BoundSql
            BoundSql countBoundSql = new BoundSql(cfg, countSql, boundSql.getParameterMappings(), boundSql.getParameterObject());
            //构建MyBatis的ParameterHandler用来处理总数的SQL参数
            ParameterHandler handler = new DefaultParameterHandler(mappedStatement, boundSql.getParameterObject(), countBoundSql);
            //设置总数SQL参数
            handler.setParameters(ps);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                total = resultSet.getInt("total");
            }
        } finally {
            if (ps != null) {
                ps.close();
            }
        }
        return total;
    }

    /**
     * 回填总条数、总页数
     * @param pageParams
     * @param total
     * @param pageSize
     */
    private void setTotalToPageParams(PageParams pageParams, int total, int pageSize) {
        pageParams.setTotal(total);
        int totalPage = total % pageSize == 0 ? total / pageSize : total / pageSize + 1;
        pageParams.setTotalPage(totalPage);
    }

    private void checkPage(Boolean checkFlag, Integer pageNum, Integer pageTotal) throws Exception {
        if (checkFlag) {
            if (pageNum > pageTotal) {
                throw new Exception("查询失败, 查询页码: " + pageNum + " 大于总页码: " + pageTotal);
            }
        }
    }

    private Object changeSQL(Invocation invocation, MetaObject metaObject, BoundSql boundSql, int page, int pageSize) throws Exception {
        String sql = (String) metaObject.getValue("delegate.boundSql.sql");
        //TODO 修改SQL,这里使用Mysql语法,其它数据库需修改为相对应的sql
        String newSql = "select * from (" + sql + ")" + "$_paging_table limit ?, ?";
        //修改当前需要执行的SQL
        metaObject.setValue("delegate.boundSql.sql", newSql);
        //相当于调用了Statementhandler的prepare方法,预编译了当前SQL并设置原有的参数,但是少了两个分页参数,
        //它返回的是一个PreparedStatement对象
        PreparedStatement ps = (PreparedStatement) invocation.proceed();
        //计算SQL总参数个数
        int count = ps.getParameterMetaData().getParameterCount();
        ps.setInt(count - 1, (page - 1) * pageSize);
        ps.setInt(count, pageSize);
        return ps;
    }

}
