/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package srs.mail;

import datalayer.Datalink;
import datalayer.Datalink_Service;
import java.util.Arrays;
import java.util.List;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import srs.nlp.WordDetector;

/**
 *
 * @author Thinal
 */
@WebService(serviceName = "mailservice")
public class mailservice {
    static Datalink_Service service = new Datalink_Service();
    static Datalink proxy = service.getDatalinkPort();
    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "getMail")
    public int getMail(@WebParam(name = "from") String from, @WebParam(name = "sentDate") String sentDate, @WebParam(name = "subject") String subject, @WebParam(name = "content") String content) {
        int id = proxy.getCusId(from, from);
        if(id!=0){
            int level = WordDetector.lineParser(content);
            String name = proxy.verifyCustomer(id);
            proxy.addTicket(id, name, level);
            return 1;
        }
        else 
            return 0;
    }
}
