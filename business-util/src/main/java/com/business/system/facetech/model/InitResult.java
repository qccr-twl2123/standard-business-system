/*
 * Copyright 2018 ArcSoft, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http:/**www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */

package com.business.system.facetech.model;

/**
 * @Description 引擎初始化返回信息载体
 */
public class InitResult extends Result {
	/**
	 * 引擎地址
	 */
	private long engineaddr;

	/**
	 * 内存缓存地址
	 */
	private long membufaddr;

	public long getEngineaddr() {
		return engineaddr;
	}

	public void setEngineaddr(long engineaddr) {
		this.engineaddr = engineaddr;
	}

	public long getMembufaddr() {
		return membufaddr;
	}

	public void setMembufaddr(long membufaddr) {
		this.membufaddr = membufaddr;
	}

}
