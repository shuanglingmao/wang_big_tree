package aop;
import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import javax.servlet.http.HttpSession;

public class RpcInvocation implements Serializable {
    private static final long serialVersionUID = -4355285085441097045L;
    private String methodName;
    private String serviceid;
    private Class<?>[] parameterTypes;
    private Object[] arguments;
    private HttpSession session;
    private Map<String, String> attachments;

    public RpcInvocation() {
    }

    public RpcInvocation(String serviceid, HttpSession session, Object[] arguments) {
        this.serviceid = serviceid;
        this.arguments = arguments;
        this.session = session;
    }

    public String getServiceid() {
        return this.serviceid;
    }

    public void setServiceid(String serviceid) {
        this.serviceid = serviceid;
    }

    public HttpSession getSession() {
        return this.session;
    }

    public void setSession(HttpSession session) {
        this.session = session;
    }

    public String getMethodName() {
        return this.methodName;
    }

    public Class<?>[] getParameterTypes() {
        return this.parameterTypes;
    }

    public Object[] getArguments() {
        return this.arguments;
    }

    public Map<String, String> getAttachments() {
        return this.attachments;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public void setParameterTypes(Class<?>[] parameterTypes) {
        this.parameterTypes = parameterTypes == null ? new Class[0] : parameterTypes;
    }

    public void setArguments(Object[] arguments) {
        this.arguments = arguments == null ? new Object[0] : arguments;
    }

    public void setAttachments(Map<String, String> attachments) {
        this.attachments = (Map)(attachments == null ? new HashMap() : attachments);
    }

    public void setAttachment(String key, String value) {
        if (this.attachments == null) {
            this.attachments = new HashMap();
        }

        this.attachments.put(key, value);
    }

    public void setAttachmentIfAbsent(String key, String value) {
        if (this.attachments == null) {
            this.attachments = new HashMap();
        }

        if (!this.attachments.containsKey(key)) {
            this.attachments.put(key, value);
        }

    }

    public void addAttachments(Map<String, String> attachments) {
        if (attachments != null) {
            if (this.attachments == null) {
                this.attachments = new HashMap();
            }

            this.attachments.putAll(attachments);
        }
    }

    public void addAttachmentsIfAbsent(Map<String, String> attachments) {
        if (attachments != null) {
            Iterator i$ = attachments.entrySet().iterator();

            while(i$.hasNext()) {
                Entry<String, String> entry = (Entry)i$.next();
                this.setAttachmentIfAbsent((String)entry.getKey(), (String)entry.getValue());
            }

        }
    }

    public String getAttachment(String key) {
        return this.attachments == null ? null : (String)this.attachments.get(key);
    }

    public String getAttachment(String key, String defaultValue) {
        if (this.attachments == null) {
            return defaultValue;
        } else {
            String value = (String)this.attachments.get(key);
            return value != null && value.length() != 0 ? value : defaultValue;
        }
    }
    @Override
    public String toString() {
        return "RpcInvocation [methodName=" + this.methodName + ", parameterTypes=" + Arrays.toString(this.parameterTypes) + ", arguments=" + Arrays.toString(this.arguments) + ", attachments=" + this.attachments + "]";
    }
}

