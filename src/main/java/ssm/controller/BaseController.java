package ssm.controller;

import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServlet;
import java.util.List;
import java.util.Map;

public class BaseController <T> extends HttpServlet{

    protected List<T> jsonList;
    protected Map<String,Object> respMap;
    protected String result;

    public List<T> getJsonList() {
        return jsonList;
    }

    public void setJsonList(List<T> jsonList) {
        this.jsonList = jsonList;
    }

    public Map<String, Object> getRespMap() {
        return respMap;
    }

    public void setRespMap(Map<String, Object> respMap) {
        this.respMap = respMap;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    /**
     * 请求异常
     * @return
     * @throws Exception
     * String
     */
    @RequestMapping(value = "/error_404", produces = "text/html;charset=UTF-8")
    public String error_404() throws Exception {
        return "{\"status\":\"404\",\"msg\":\"找不到页面\"}";
    }

    @RequestMapping(value = "/error_400", produces = "text/html;charset=UTF-8")
    public String error_400() throws Exception {
        return "{\"status\":\"400\",\"msg\":\"请求参数非法\"}";
    }
    /**
     * 服务器异常
     * @return
     * String
     */
    @RequestMapping(value ="/error_500", produces = "text/html;charset=UTF-8")
    public String error_500() {
        return "{\"status\":\"500\",\"msg\":\"服务器处理失败\"}";
    }


}
