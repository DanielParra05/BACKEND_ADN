
package com.ceiba.infraestructura.servicio.externo.superfinancieratrm.libreria;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for queryTCRM complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="queryTCRM">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="tcrmQueryAssociatedDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "queryTCRM", propOrder = {
    "tcrmQueryAssociatedDate"
})
public class QueryTCRM {

    protected String tcrmQueryAssociatedDate;

    /**
     * Gets the value of the tcrmQueryAssociatedDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTcrmQueryAssociatedDate() {
        return tcrmQueryAssociatedDate;
    }

    /**
     * Sets the value of the tcrmQueryAssociatedDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTcrmQueryAssociatedDate(String value) {
        this.tcrmQueryAssociatedDate = value;
    }

}
