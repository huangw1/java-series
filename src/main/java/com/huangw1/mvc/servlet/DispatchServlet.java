package com.huangw1.mvc.servlet;

import com.huangw1.mvc.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.io.File.separator;

/**
 * Created by huangw1 on 2018/5/6.
 */

@WebServlet(name = "dispatcherServlet", urlPatterns = "/*", loadOnStartup = 1,
        initParams = { @WebInitParam(name = "base-package", value = "com.huangw1.mvc")})
public class DispatchServlet extends HttpServlet {

    private Logger logger = LoggerFactory.getLogger(DispatchServlet.class);

    private List<String> packageNames = new ArrayList<>();

    private Map<String, Object> annotationMap = new HashMap<>();

    private Map<String, Method> urlMethodMap = new HashMap<>();

    private Map<String, String> packageMap = new HashMap<>();

    private Map<Method, String> methodPackageMap = new HashMap<>();

    @Override
    public void init(ServletConfig config) throws ServletException {
        String basePackage = config.getInitParameter("base-package");

        scanBasePackage(basePackage);
        try {
            scanAnnotation();
            ioc();
            handleUrlMethodMap();
        } catch (Exception e) {
            logger.error("Init error: %s", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI();
        String contextPath = req.getContextPath();
        String path = uri.replace(contextPath, "");
        Method method = urlMethodMap.get(path);

        if(method != null) {
            String packageName = methodPackageMap.get(method);
            String controllerName = packageMap.get(packageName);
            try {
                method.setAccessible(true);
                method.invoke(annotationMap.get(controllerName));
            } catch (Exception e) {
                logger.error("Handler url error: %s", e);
            }
        }
    }

    private void scanBasePackage(String basePackage) {
        URL url = this.getClass().getClassLoader().getResource(basePackage.replaceAll("\\.", separator));
        File baseFile = new File(url.getPath());
        File[] files = baseFile.listFiles();

        for(File file: files) {
            if(file.isDirectory()) {
                scanBasePackage(basePackage + "." + file.getName());
            } else if(file.isFile()) {
                packageNames.add(basePackage + file.getName().split("\\.")[0]);
            }
        }
    }

    private void scanAnnotation() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        if(packageNames.isEmpty()) {
            return;
        }

        for(String packageName: packageNames) {
            Class clazz = Class.forName(packageName);

            if(clazz.isAnnotationPresent(Controller.class)) {
                Controller controller = (Controller)clazz.getAnnotation(Controller.class);
                String controllerName = controller.value();
                annotationMap.put(controllerName, clazz.newInstance());
                packageMap.put(packageName, controllerName);
                logger.info("Controller: %s, value: %s", packageName, controllerName);
            }

            if(clazz.isAnnotationPresent(Service.class)) {
                Service service = (Service)clazz.getAnnotation(Service.class);
                String serviceName = service.value();
                annotationMap.put(serviceName, clazz.newInstance());
                packageMap.put(packageName, serviceName);
                logger.info("Service: %s, value: %s", packageName, serviceName);
            }

            if(clazz.isAnnotationPresent(Repository.class)) {
                Repository repository = (Repository)clazz.getAnnotation(Repository.class);
                String repositoryName = repository.value();
                annotationMap.put(repositoryName, clazz.newInstance());
                packageMap.put(packageName, repositoryName);
                logger.info("Repository: %s, value: %s", packageName, repositoryName);
            }
        }
    }

    private void ioc() throws IllegalAccessException {
        for(Map.Entry<String, Object> entry: annotationMap.entrySet()) {
            Field[] fields = entry.getValue().getClass().getDeclaredFields();
            for(Field field: fields) {
                if(field.isAnnotationPresent(Qualifier.class)) {
                    String name = field.getAnnotation(Qualifier.class).value();
                    field.setAccessible(true);
                    field.set(entry.getValue(), annotationMap.get(name));
                }
            }
        }
    }

    private void handleUrlMethodMap() throws ClassNotFoundException {
        if(packageNames.isEmpty()) {
            return;
        }

        for(String packageName: packageNames) {
            Class clazz = Class.forName(packageName);

            if(clazz.isAnnotationPresent(Controller.class)) {
                Method[] methods = clazz.getMethods();
                String baseUrl = "";
                if(clazz.isAnnotationPresent(RequestMapping.class)) {
                    RequestMapping requestMapping = (RequestMapping)clazz.getAnnotation(RequestMapping.class);
                    baseUrl = requestMapping.value();
                }
                for(Method method: methods) {
                    if(method.isAnnotationPresent(RequestMapping.class)) {
                        RequestMapping requestMapping = method.getAnnotation(RequestMapping.class);
                        String url = baseUrl + requestMapping.value();
                        urlMethodMap.put(url, method);
                        methodPackageMap.put(method, packageName);
                    }
                }
            }
        }
    }
}
