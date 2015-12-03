package cn.imethan.common.hibernate.listener;

import java.util.Date;

import org.hibernate.HibernateException;
import org.hibernate.event.internal.DefaultSaveOrUpdateEventListener;
import org.hibernate.event.spi.SaveOrUpdateEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import cn.imethan.common.hibernate.BaseEntity;
import cn.imethan.common.security.service.SecurityContext;

/**
 * 系统实体保存或者更新事件监听
 * AuditListener.java
 *
 * @author Ethan Wong
 * @since JDK 1.7
 * @datetime 2015年12月3日下午10:29:44
 */
@Component
public class AuditListener extends DefaultSaveOrUpdateEventListener {

    private static final long serialVersionUID = -4125997105718175896L;

    private static Logger logger = LoggerFactory.getLogger(AuditListener.class);

    public void onSaveOrUpdate(SaveOrUpdateEvent event) throws HibernateException {
        Object object = event.getObject();
        // 如果对象是BaseEntity子类,添加审计信息.
        if (object instanceof BaseEntity) {
        	BaseEntity entity = (BaseEntity) object;
            String operateUser = SecurityContext.getUserInfo().getUsername();
            String ipAddress = SecurityContext.getRemoteAddress();
            

            if (entity.getId() == null) {
                // 创建新对象.
                entity.setCreateTime(new Date());
            } else {
                // 修改旧对象.
                entity.setModifyTime(new Date());
            }
            entity.setIpAddress(ipAddress);
            entity.setOperateUser(operateUser);
        }
        // 调用父类提供的功能，进行保存和更新.
        super.onSaveOrUpdate(event);
    }

}
