package cn.myst.web.upload.file.model.file;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author ziming.xing
 * Create Date：2020/12/25
 */
@ApiModel(value = "cn-myst-web-upload-file-model-file-File")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "tb_file")
public class File implements Serializable {
    /**
     * ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value = "ID")
    private Long id;

    /**
     * 名称
     */
    @TableField(value = "`name`")
    @ApiModelProperty(value = "名称")
    private String name;

    /**
     * MD5
     */
    @TableField(value = "md5")
    @ApiModelProperty(value = "MD5")
    private String md5;

    /**
     * 路径
     */
    @TableField(value = "`path`")
    @ApiModelProperty(value = "路径")
    private String path;

    /**
     * 上传时间
     */
    @TableField(value = "upload_time")
    @ApiModelProperty(value = "上传时间")
    private Date uploadTime;

    private static final long serialVersionUID = 1L;

    public static final String COL_ID = "id";

    public static final String COL_NAME = "name";

    public static final String COL_MD5 = "md5";

    public static final String COL_PATH = "path";

    public static final String COL_UPLOAD_TIME = "upload_time";
}