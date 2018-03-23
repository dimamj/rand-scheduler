package ru.randscheduler.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.context.support.WebApplicationObjectSupport;
import org.springframework.web.servlet.view.freemarker.FreeMarkerView;
import ru.randscheduler.tools.UserCookieUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by dimaMJ on 14.03.2018
 */
public class FreemarkerViewConf extends FreeMarkerView {

    public FreemarkerViewConf() {
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void exposeHelpers(Map<String, Object> model, HttpServletRequest request) throws Exception {
        super.exposeHelpers(model, request);

        String userId = UserCookieUtils.getUserIdVal(request);
        if (userId != null) model.put("userShortId", userId.substring(0, 1).toUpperCase());
        model.put("jsonMapper", getWebApplicationContext().getBean("jacksonObjectMapper"));
    }
}

