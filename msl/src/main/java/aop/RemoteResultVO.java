package aop;


import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Description: ${todo}
 *
 * @author shuangling.mao
 * @date 2019/3/14 17:01
 */
public class RemoteResultVO implements Serializable {
    private static final long serialVersionUID = 5429502113437794525L;
    private Map<String, Object> updateMap;
    private List<String> removeList;
    private Object result;

    public RemoteResultVO() {
//        this.initSession();
    }

    public List<String> getRemoveList() {
        return this.removeList;
    }

    public Map<String, Object> getUpdateMap() {
        return this.updateMap;
    }

    public Object getResult() {
        return this.result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

//    private void initSession() {
//        HttpSession session = (HttpSession) RequestContext.RPC_LOCAL_SESSION.get();
//        if (session != null) {
//            if (session instanceof RemoteHandlerHttpSession) {
//                if (((RemoteHandlerHttpSession)session).getUpdateMap().size() > 0) {
//                    this.updateMap = ((RemoteHandlerHttpSession)session).getUpdateMap();
//                }
//
//                if (((RemoteHandlerHttpSession)session).getRemoveList().size() > 0) {
//                    this.removeList = ((RemoteHandlerHttpSession)session).getRemoveList();
//                }
//            }
//
//            RequestContext.RPC_LOCAL_SESSION.remove();
//        }
//    }
}
