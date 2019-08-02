package io.renren.common.aspect;

import io.renren.common.annotation.AvoidRepeatableCommit;
import io.renren.common.exception.RRException;
import io.renren.common.utils.Constant;
import io.renren.common.utils.IPUtils;
import io.renren.common.utils.RedisUtils;
import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.UUID;

/**
 * 重复提交aop
 * @author hhz
 * @version
 * @since
 */
@Aspect
@Component
public class AvoidRepeatableCommitAspect {

	@Autowired
	private RedisTemplate redisTemplate;
	@Autowired
	private RedisUtils redisUtils;

	private static final Logger log = LoggerFactory.getLogger(AvoidRepeatableCommit.class);


	/**
	 * @param point
	 */
	@Around("@annotation(io.renren.common.annotation.AvoidRepeatableCommit)")
	public void around(ProceedingJoinPoint point) throws Throwable {

		HttpServletRequest request  = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
		String ip = IPUtils.getIpAddr(request);
		//获取注解
		MethodSignature signature = (MethodSignature) point.getSignature();
		Method method = signature.getMethod();
		//目标类、方法
		String className = method.getDeclaringClass().getName();
		String name = method.getName();
		String ipKey = String.format("%s#%s",className,name);
		int hashCode = Math.abs(ipKey.hashCode());
		String key = String.format("%s_%d",ip,hashCode);
		log.info("ipKey={},hashCode={},key={}",ipKey,hashCode,key);
		AvoidRepeatableCommit avoidRepeatableCommit =  method.getAnnotation(AvoidRepeatableCommit.class);
		long timeout = avoidRepeatableCommit.timeout();
		if (timeout < 0){
			timeout = Constant.AVOID_REPEATABLE_TIMEOUT;
		}
		String value = (String) redisTemplate.opsForValue().get(key);
		if (StringUtils.isNotBlank(value)){
			throw new RRException("请勿重复提交");
		}
		String uuid = UUID.randomUUID().toString().replaceAll("-", "");
		redisUtils.set(key, uuid,timeout);
		//执行方法
		Object object = point.proceed();
	}

}