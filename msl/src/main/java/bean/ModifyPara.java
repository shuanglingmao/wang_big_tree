package bean;

import java.io.Serializable;

/**
 * Description: ${todo}
 *
 * @author shuangling.mao
 * @date 2018/6/19 19:01
 */
public class ModifyPara<T> implements Serializable {
    private T originVO;
    private T modifyVO;

    public ModifyPara() {
    }

    public T getOriginVO() {
        return this.originVO;
    }

    public void setOriginVO(T originVO) {
        this.originVO = originVO;
    }

    public T getModifyVO() {
        return this.modifyVO;
    }

    public void setModifyVO(T modifyVO) {
        this.modifyVO = modifyVO;
    }
}
