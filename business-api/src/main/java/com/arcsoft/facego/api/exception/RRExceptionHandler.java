package com.arcsoft.facego.api.exception;

import com.arcsoft.facego.api.base.Result;
import com.arcsoft.facego.api.base.Results;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 异常处理器
 * 
 * @author xierongli
 * @email sunlightcs@gmail.com
 * @date 2016年10月27日 下午10:16:19
 */
@RestControllerAdvice
public class RRExceptionHandler {
	private Logger logger = LoggerFactory.getLogger(getClass());


	@ExceptionHandler(RRException.class)
	public Result handleRRException(RRException e){
		return Results.newFailedResult(e.getMessage());
	}

	@ExceptionHandler(DuplicateKeyException.class)
	public Result handleDuplicateKeyException(DuplicateKeyException e){
		logger.error(e.getMessage(), e);
		return Results.newFailedResult("数据库中已存在该记录");
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public Result handleIllegalArgumentException(IllegalArgumentException e){
		return Results.newFailedResult(e.getMessage());
	}

}
