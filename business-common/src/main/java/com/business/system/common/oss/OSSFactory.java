

package com.business.system.common.oss;


import com.business.system.util.ConfigUtil;
import com.business.system.util.Constants;

/**
 * 文件上传Factory
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-03-26 10:18
 */
public final class OSSFactory {

    public static CloudStorageService build(Integer type){


        if(type == Constants.CloudService.QINIU.getValue()){
//            return new QiniuCloudStorageService(config);
        }else if(type == Constants.CloudService.ALIYUN.getValue()){
            CloudStorageConfig cloudStorageConfig = new CloudStorageConfig();
            cloudStorageConfig.setAliyunPrefix(ConfigUtil.ALIYUN_PREFIX);
            cloudStorageConfig.setAliyunEndPoint(ConfigUtil.ALIYUN_END_POINT);
            cloudStorageConfig.setAliyunDomain(ConfigUtil.ALIYUN_DOMAIN);
            cloudStorageConfig.setAliyunBucketName(ConfigUtil.ALIYUN_BUCKET_NAME);
            cloudStorageConfig.setAliyunAccessKeySecret(ConfigUtil.ALIYUN_ACCESS_SECRET);
            cloudStorageConfig.setAliyunAccessKeyId(ConfigUtil.ALIYUN_ACCESS_KEY_ID);

            return new AliyunCloudStorageService(cloudStorageConfig);
        }else if(type == Constants.CloudService.QCLOUD.getValue()){
//            return new QcloudCloudStorageService(config);
        }

        return null;
    }

}
