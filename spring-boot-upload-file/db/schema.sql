DROP TABLE IF EXISTS `tb_file`;
CREATE TABLE `tb_file`
(
    `id`          bigint       NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `name`        varchar(100) NOT NULL COMMENT '名称',
    `md5`         varchar(32)  NULL DEFAULT NULL COMMENT 'MD5',
    `path`        varchar(100) NOT NULL COMMENT '路径',
    `upload_time` datetime     NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '上传时间',
    PRIMARY KEY (`id`) USING BTREE
);
