package com.leaf.core.beans.annotation;

import com.leaf.core.BeanReference;
import com.leaf.core.beans.AbstractBeanDefinitionReader;
import com.leaf.core.beans.BeanDefinition;
import com.leaf.core.beans.PropertyValue;
import com.leaf.core.beans.io.ResourceLoader;
import lombok.extern.slf4j.Slf4j;
import org.w3c.dom.Element;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@Slf4j
public class AnnotationBeanDefinitionReader extends AbstractBeanDefinitionReader {


    //从父类继承的字段及方法
//    private Map<String,BeanDefinition> registry;
//
//    private ResourceLoader resourceLoader;
//
//    protected AbstractBeanDefinitionReader(ResourceLoader resourceLoader) {
//        this.registry = new HashMap<String, BeanDefinition>();
//        this.resourceLoader = resourceLoader;
//    }
//
//    public Map<String, BeanDefinition> getRegistry() {
//        return registry;
//    }
//
//    public ResourceLoader getResourceLoader() {
//        return resourceLoader;
//    }
//
    //用来存放扫描到的全限定类名
    private List<String> classnames = new ArrayList<String>();

    //构造方法，可以指定任意的resourLoader
    protected AnnotationBeanDefinitionReader(ResourceLoader resourceLoader) {
        super(resourceLoader);
    }


    @Override
    public void loadBeanDefinitions(String propertiesfile) throws Exception {
        Properties prop = new Properties();

        prop.load(getResourceLoader().getResource(propertiesfile).getInputStream());

        String scanpackage = prop.getProperty("base-package");

        //扫描所有文件，加入到list中
        log.info("开始扫描：" + scanpackage);
        scanFile(scanpackage);

        //开始注册bean
        findBeanAndLoad();

        for (String s : classnames) {
            System.out.println(s);
        }
    }


    private void findBeanAndLoad() {

        for (String classname : classnames) {

            Class c = null;
            try {
                c = Class.forName(classname);
            } catch (ClassNotFoundException e) {
                log.error("未找到class文件：" + classname);
                e.printStackTrace();
                return;
            }

            if (c.isAnnotationPresent(Bean.class) ||
                    c.isAnnotationPresent(Controller.class) ||
                    c.isAnnotationPresent(Service.class)) {
                System.out.println("开始扫描");
                processBeanDefinition(c);

            }
        }


    }

    //
    protected void processBeanDefinition(Class c) {
        if (c.isAnnotationPresent(Controller.class)) {
            System.out.println("是controller");
            String name = ((Controller) c.getAnnotation(Controller.class)).value();
            String className = c.getName();
            BeanDefinition beanDefinition = new BeanDefinition();
            processProperty(c, beanDefinition);
            getRegistry().put(name, beanDefinition);

        } else if (c.isAnnotationPresent(Service.class)) {
            System.out.println("是Service");
            String name = ((Service)c.getAnnotation(Service.class)).value();
            String className = c.getName();
            BeanDefinition beanDefinition = new BeanDefinition();
            processProperty(c, beanDefinition);
            getRegistry().put(name, beanDefinition);


        } else if (c.isAnnotationPresent(Bean.class)) {
            System.out.println("是Bean");
            String name = ((Bean) c.getAnnotation(Bean.class)).value();
            String className = c.getName();

            BeanDefinition beanDefinition = new BeanDefinition();
            processProperty(c, beanDefinition);
            getRegistry().put(name, beanDefinition);

        }


//
//        processProperty(ele, beanDefinition);
//        beanDefinition.setBeanClassName(className);
//        getRegistry().put(name, beanDefinition);
    }


    private void processProperty(Class c, BeanDefinition beanDefinition) {
        beanDefinition.setBeanClassName(c.getName());
        Field[] fields= c.getFields();
        for(Field field:fields){
            field.setAccessible(true);
            System.out.println("Field"+field.getName());
            if(field.isAnnotationPresent(Autowired.class)){
                String value=  ((Autowired) field.getAnnotation(Autowired.class)).value();
                BeanReference beanReference = new BeanReference(value);
                beanDefinition.getPropertyValues().addPropertyValue(new PropertyValue(field.getName(), beanReference));
            }
        }

    }

    //递归扫描文件
    private void scanFile(String base) {
        base = base.replaceAll("\\.", "/");
        URL url = this.getClass().getResource("/" + base);
        String path = url.getFile();
        try {
            path = URLDecoder.decode(path, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        File file = new File(path);
        String[] filename = file.list();
        if (filename != null && filename.length != 0) {
            for (String files : filename) {
                File file1 = new File(path + "/" + files);
                if (file1.isDirectory()) {
                    scanFile(base + "/" + file1.getName());
                } else {
                    String classname = (base + '/' + file1.getName()).replace(".class", "")
                            .replace("/", ".");
                    classnames.add(classname);
                    log.info("扫描到class文件:" + classname);
                }
            }
        } else {
            log.error("文件错误");
        }
    }

}
