package com.business.system.facetech.jni;

import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

/**
 * @Description FR引擎池，根据配置文件设置初始化引擎池
 */
public class FRJNIFactory extends BasePooledObjectFactory<FRServiceJNI> {
	/**
	 * FDServiceJNI 对象池
	 */
	static GenericObjectPool<FRServiceJNI> pool = null;

	/**
	 * 加载配置信息
	 */
	@Override
	public FRServiceJNI create() throws Exception {
		 return new FRServiceJNI();
	}

	/**
	 * 让对象支持对象池中的特定的一些特性
	 */
	@Override
	public PooledObject<FRServiceJNI> wrap(FRServiceJNI fdObj) {
		return new DefaultPooledObject<FRServiceJNI>(fdObj);
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
	public synchronized static GenericObjectPool<FRServiceJNI> getInstance() {
		if (pool == null) {
            GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
            poolConfig.setMaxIdle(4);
            poolConfig.setMaxTotal(4);
            poolConfig.setMinIdle(4);
            poolConfig.setLifo(false);
            pool = new GenericObjectPool<FRServiceJNI>(new FRJNIFactory(), poolConfig);
        }
        return pool;
	}

	/**
	 * 从引擎池里取一个引擎，新创建makeObject或将以前闲置的对象取出来
	 */
	public static FRServiceJNI borrowObject() throws Exception{
        return (FRServiceJNI) FRJNIFactory.getInstance().borrowObject();
    }

	/**
	 * 用完以后归还引擎对象
	 */
	public static void returnObject(FRServiceJNI fdobj) throws Exception{
		FRJNIFactory.getInstance().returnObject(fdobj);
    }

	/**
	 * 关闭引擎池
	 */
    public static void close() throws Exception{
    	FRJNIFactory.getInstance().close();
    }

}
