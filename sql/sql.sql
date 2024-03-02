CREATE TABLE `interface_info` (
                                  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
                                  `name` text COLLATE utf8mb4_unicode_ci COMMENT '接口名',
                                  `description` text COLLATE utf8mb4_unicode_ci COMMENT '描述',
                                  `requestExample` text COLLATE utf8mb4_unicode_ci COMMENT '请求示例',
                                  `detail` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '接口描述细节',
                                  `url` varchar(512) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '接口地址',
                                  `requestParams` text COLLATE utf8mb4_unicode_ci COMMENT '请求参数',
                                  `requestHeader` text COLLATE utf8mb4_unicode_ci COMMENT '请求头',
                                  `status` int DEFAULT '1' COMMENT '接口状态（0-免费，1-vip）',
                                  `responseExample` int DEFAULT NULL,
                                  `method` varchar(256) COLLATE utf8mb4_unicode_ci DEFAULT 'GET' COMMENT '请求类型',
                                  `userId` bigint DEFAULT NULL COMMENT '创建人',
                                  `createTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                  `updateTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                                  `isDelete` tinyint NOT NULL DEFAULT '0' COMMENT '是否删除(0-未删, 1-已删)',
                                  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='接口信息'

CREATE TABLE `params_details` (
                                  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '用户名',
                                  `paramsName` varchar(256) COLLATE utf8mb4_unicode_ci DEFAULT '无' COMMENT '参数名称',
                                  `isRequest` varchar(256) COLLATE utf8mb4_unicode_ci DEFAULT '无' COMMENT '是否必填',
                                  `type` varchar(256) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '无' COMMENT '参数类型',
                                  `description` varchar(256) COLLATE utf8mb4_unicode_ci DEFAULT '无' COMMENT '参数说明',
                                  `interfaceId` bigint NOT NULL COMMENT '接口id',
                                  PRIMARY KEY (`id`),
                                  KEY `fk_interfaceId` (`interfaceId`),
                                  CONSTRAINT `fk_interfaceId` FOREIGN KEY (`interfaceId`) REFERENCES `interface_info` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='参数细节'

CREATE TABLE `user` (
                        `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
                        `userName` varchar(256) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '用户昵称',
                        `email` varchar(256) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '账号',
                        `userAvatar` varchar(1024) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '用户头像',
                        `gender` tinyint DEFAULT NULL COMMENT '性别',
                        `userRole` varchar(256) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'user' COMMENT '用户角色：user / admin/vip',
                        `userPassword` varchar(512) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '密码',
                        `publickey` text COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '公钥',
                        `privatekey` text COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '私钥',
                        `sign` text COLLATE utf8mb4_unicode_ci COMMENT '签名',
                        `createTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                        `updateTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                        `isDelete` tinyint NOT NULL DEFAULT '0' COMMENT '是否删除',
                        PRIMARY KEY (`id`),
                        UNIQUE KEY `uni_userAccount` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='用户'

CREATE TABLE `user_interface_info` (
                                       `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
                                       `userId` bigint NOT NULL COMMENT '调用用户 id',
                                       `interfaceInfoId` bigint NOT NULL COMMENT '接口 id',
                                       `totalNum` int NOT NULL DEFAULT '10' COMMENT '总调用次数',
                                       `leftNum` int NOT NULL DEFAULT '0' COMMENT '剩余调用次数',
                                       `status` int NOT NULL DEFAULT '0' COMMENT '0-正常，1-禁用',
                                       `createTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                       `updateTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                                       `isDelete` tinyint NOT NULL DEFAULT '0' COMMENT '是否删除(0-未删, 1-已删)',
                                       PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户调用接口关系'

