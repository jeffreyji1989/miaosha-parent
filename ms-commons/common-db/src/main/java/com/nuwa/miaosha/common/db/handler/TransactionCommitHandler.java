package com.nuwa.miaosha.common.db.handler;

import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionSynchronizationAdapter;
import org.springframework.transaction.support.TransactionSynchronizationManager;

/**
 * @Author jijunhui
 * @Date 2021/12/12 22:09
 * @Version 1.0.0
 * @Description 事务管理
 */
@Component
public class TransactionCommitHandler {
//    private T t;

    /**
     * 事务成功后提交
     *
     * @param action
     */
    public void commitSuccess(Runnable action) {
        if (TransactionSynchronizationManager.isActualTransactionActive()) {
            TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronizationAdapter() {
                @Override
                public void afterCommit() {
                    //具体的异步操作
                    action.run();
                }
            });
        }
    }

//    public void commitSuccess(Callable<T> callable) {
//        if (TransactionSynchronizationManager.isActualTransactionActive()) {
//            TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronizationAdapter() {
//                @Override
//                public void afterCommit() {
//                    //具体的异步操作
//                    try {
//                        t = callable.call();
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                    return;
//                }
//            });
//        }
//    }

}
