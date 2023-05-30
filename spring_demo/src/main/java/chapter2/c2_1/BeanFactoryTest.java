package chapter2.c2_1;


import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

/**
 * @author xuben
 * @description TODO
 * @date 2023/5/29 14:29
 */
public class BeanFactoryTest {

  public static void main(String[] args) {
    BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanFactoryTest.xml"));
    MyTestBean bean = (MyTestBean) bf.getBean("myTestBean");
    assert "testStr".equals(bean.getTestStr());
  }
}
