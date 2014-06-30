package org.wildfly.samples.javaee7.arquillian;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Employees {

   private Employee[] employees;

   public Employee[] getEmployees() {
      return employees;
   }

   public void setEmployees(Employee[] employees) {
      this.employees = employees;
   }
}
