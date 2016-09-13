/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package srs.mail;

import datalayer.Datalink;
import datalayer.Datalink_Service;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import srs.nlp.Training;
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
    public int getMail(@WebParam(name = "fname") String fname, @WebParam(name = "email") String email, @WebParam(name = "subject") String subject, @WebParam(name = "content") String content) {
        int id = proxy.getCusId(fname, email);
        if(id!=0){
            int level = Training.map(content);
            String category=WordDetector.categorizeWord(content);
            //String name = proxy.verifyCustomer(id);
            int ticketId=proxy.addTicket(id, content, level);
            MailSender.send(email, ticketId, content, category);
            return 1;
        }
        else 
            return 0;
    }
}
