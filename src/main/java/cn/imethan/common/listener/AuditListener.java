package cn.imethan.common.listener;

import org.hibernate.HibernateException;
import org.hibernate.event.internal.DefaultSaveOrUpdateEventListener;
import org.hibernate.event.spi.SaveOrUpdateEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 系统实体保存或者更新事件监听.
 * 
 * @author zblink
 * @since JDK 1.7
 * @see
 */
@Component
public class AuditListener extends DefaultSaveOrUpdateEventListener {

    private static final long serialVersionUID = -4125997105718175896L;

    private static Logger logger = LoggerFactory.getLogger(AuditListener.class);

    public void onSaveOrUpdate(SaveOrUpdateEvent event) throws HibernateException {
        Object object = event.getObject();
        // 如果对象是BaseEntity子类,添加审计信息.
//        if (object instanceof AbstractEntity) {
//            AbstractEntity entity = (AbstractEntity) object;
//            String loginName = SecurityContext.getLoginName();
//            String ip = SecurityContext.getUserIp();
//
//            String nowDate = DateUtil.getNowTime(DatePattern.PATTERN_YMDHMS);
//            if (entity.getId() == null) {
//                // 创建新对象.
//                entity.setCreatedUser(loginName);
//                entity.setCreatedDate(new Date());
//                entity.setCreatedIp(ip);
//                
//                logger.info("{}:{}对象(ID:{}) 被{}在{}添加.", new Object[] { LogType.CREATER.getValue(), object, entity.getId(), loginName, nowDate });
//            } else {
//                // 修改旧对象.
//                entity.setLastModifiedUser(loginName);
//                entity.setLastModifiedDate(new Date());
//                entity.setLastModifiedIp(ip);
//
//                logger.info("{}:{}对象(ID:{}) 被{}在{}修改.", new Object[] { LogType.UPDATE.getValue(), object, entity.getId(), loginName, nowDate });
//            }
//        }
        // 调用父类提供的功能，进行保存和更新.
        super.onSaveOrUpdate(event);
    }

}
