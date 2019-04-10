package com.business.system.facetech.jni;

import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

public class FDJNIFactory extends BasePooledObjectFactory<FDServiceJNI> {
	/**
	 * FDServiceJNI 对象池
	 */
	static GenericObjectPool<FDServiceJNI> pool = null;

	/**
	 * 加载配置信息
	 */
	@Override
	public FDServiceJNI create() throws Exception {
		 return new FDServiceJNI();
	}

	/**
	 * 让对象支持对象池中的特定的一些特性
	 */
	@Override
	public PooledObject<FDServiceJNI> wrap(FDServiceJNI fdObj) {
		return new DefaultPooledObject<FDServiceJNI>(fdObj);
	}

	/**
	 * 清理引擎池
	 */
	public static void clear() throws Exception{
		FDJNIFactory.getInstance().clear();
    }

	/**
	 * 根据配置初始化引擎池
	 */
	public synchronized static GenericObjectPool<FDServiceJNI> getInstance() {
		if (pool == null) {
			GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
			poolConfig.setMaxIdle(4);
			poolConfig.setMaxTotal(4);
			poolConfig.setMinIdle(4);
			poolConfig.setLifo(false);
			pool = new GenericObjectPool<FDServiceJNI>(new FDJNIFactory(), poolConfig);
		}
		return pool;
	}

	/**
	 * 从引擎池里取一个引擎，新创建makeObject或将以前闲置的对象取出来
	 */
	public static FDServiceJNI borrowObject() throws Exception{
        return (FDServiceJNI) FDJNIFactory.getInstance().borrowObject();
    }

	/**
	 * 用完以后归还引擎对象
	 */
	public static void returnObject(FDServiceJNI fdobj) throws Exception{
		FDJNIFactory.getInstance().returnObject(fdobj);
    }
	/**
	 * 关闭引擎池
	 */
    public static void close() throws Exception{
    	FDJNIFactory.getInstance().close();
    }

}
