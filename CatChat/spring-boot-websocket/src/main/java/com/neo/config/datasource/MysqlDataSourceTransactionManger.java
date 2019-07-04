package com.neo.config.datasource;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.support.DefaultTransactionStatus;

import javax.sql.DataSource;

/**
 * @Description: msql数据源  拓展DataSourceTransactionManager  可以在此基础上加逻辑处理
 * @Version V1.0.0
 * @Since 1.0
 * @Date 2019/7/3 0003
 * @Author 毛双领 <shuangling.mao>
 */
@Component
@Slf4j
public class MysqlDataSourceTransactionManger extends DataSourceTransactionManager{


    @Autowired
    public MysqlDataSourceTransactionManger(@Qualifier("dataSource") DataSource dataSource) {
        super(dataSource);
    }

    @Override
    protected void doBegin(Object transaction, TransactionDefinition definition) {
        super.doBegin(transaction, definition);
    }

    @Override
    protected void doCommit(DefaultTransactionStatus status) {
        super.doCommit(status);
    }

    @Override
    protected void doRollback(DefaultTransactionStatus status) {
        super.doRollback(status);
    }
}
