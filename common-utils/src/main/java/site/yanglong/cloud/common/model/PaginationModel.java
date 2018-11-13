package site.yanglong.cloud.common.model;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.util.List;

/**
 * functional describe:分页模型
 *
 * @author DR.YangLong [410357434@163.com]
 * @version 1.0    2018/9/17
 */
@Data
@ApiModel("分页对象")
public class PaginationModel<T> implements Serializable {
    private static final long serialVersionUID = -2L;
    @ApiModelProperty("当前页")
    private Long current;
    @ApiModelProperty("分页大小")
    private Long pageSize;
    @ApiModelProperty("总页数")
    private Long totalPage;
    @ApiModelProperty("数据")
    private List<T> records;
    private transient boolean empty=Boolean.TRUE;

    /**
     * 使用Page<T>对象获得返回数据的分页封装
     *
     * @param iPage Page
     */
    public PaginationModel(IPage<T> iPage) {
        this.wrapperByIPage(iPage);
    }

    private void wrapperByIPage(IPage<T> iPage) {
        if (null == iPage || CollectionUtils.isEmpty(iPage.getRecords())) {
            return;
        }
        this.current = iPage.getCurrent();
        this.pageSize = iPage.getSize();
        this.totalPage = iPage.getPages();
        this.records = iPage.getRecords();
        this.empty = false;
        iPage = null;
    }
}
