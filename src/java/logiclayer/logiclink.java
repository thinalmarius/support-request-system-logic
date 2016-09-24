/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logiclayer;

import datalayer.Customers;
import datalayer.Datalink;
import datalayer.Datalink_Service;
import datalayer.Tickets;
import datalayer.Users;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import srs.mail.MailSender;
import srs.nlp.Training;
import srs.nlp.WordDetector;

/**
 *
 * @author Thinal
 */
@WebService(serviceName = "logiclink")
public class logiclink {
    
    static Datalink_Service service = new Datalink_Service();
    static Datalink proxy = service.getDatalinkPort();
    
    
    /*public static void main(String args[]){
        int result=getUserId("admin","admin@gmail.com");
        System.out.println(result);
    }*/

    @WebMethod(operationName = "checkUserPsd")
    public String checkUserPwd(@WebParam(name = "name") String name, @WebParam(name = "password") String password) {
        ArrayList<String> users = new ArrayList<String>();
        users = (ArrayList<String>) proxy.getuser();
        
        String pswd=null;
        String result=null;
       
        for(int i=0;i<users.size();i++){
            if (users.get(i).equals(name)){
                pswd = proxy.getPassword(name);
                //System.out.println(pswd);
                if (pswd.equals(password)){
                    result="ok";break;
                }else
                    result=null;

            }else{
                result=null;            
            }
        }
        return result;
        
    }
    
    @WebMethod(operationName= "getCusId")
    public int getCusId(@WebParam(name = "name") String name, @WebParam(name = "email") String email){
        int id=proxy.getCusId(name, email);
        return id;
    }
    
    @WebMethod(operationName = "getMessage")
    public void getMessage(@WebParam(name = "id")int id, @WebParam(name = "company") String email, @WebParam(name = "line")String line){
        int level=Training.map(line);
        String category=WordDetector.categorizeWord(line);
        int ticketId=proxy.addTicket(id, line, level);
        MailSender.send(email, ticketId, line, category);
    }
    
    @WebMethod(operationName = "viewTickets")
    public List<Tickets> viewTickets() {
        List<Tickets> ticket = new LinkedList<>();
        ticket=(List<Tickets>) proxy.viewTickets();
        return ticket;
        
    }
    @WebMethod(operationName = "deleteTicket")
    public void deleteTicket(int id){
        proxy.deleteTicket(id);
    }
    /**
     * Web service operation
     */
    @WebMethod(operationName = "addCustomer")
    public int addCustomer(@WebParam(name = "name") String name, @WebParam(name = "email") String email, @WebParam(name = "company") String company) {
        int ret=proxy.addCustomer(name, email, company);
        return ret;
    }
    @WebMethod(operationName = "addUser")
    public void addUser(@WebParam(name = "name") String name, @WebParam(name = "username") String username,@WebParam(name = "email") String email, @WebParam(name = "pswd") String pswd){
        proxy.addUser(name, username, email, pswd);
    }
    /**
     * Web service operation
     */
    @WebMethod(operationName = "viewCustomers")
    public List<Customers> viewCustomers() {
        List<Customers> customer = new LinkedList<>();
        customer = (List<Customers>)proxy.viewCustomers();
        return customer;
    }
    
    /**
     * Web service operation
     */
    @WebMethod(operationName = "viewUsers")
    public List<Users> viewUsers() {
        return proxy.viewUsers();
    }
    @WebMethod(operationName = "getUserId")
    public int getUserId(String name, String email){
        int id=0;
        List<Users> users = new LinkedList<>();
        users=proxy.viewUsers();
        for(Users us : users){
            String n = us.getName();
            String e = us.getEmail();
            if(name.equals(n) && email.equals(e)){
                id=us.getId(); 
            }
        }
        return id;
    }
     
    @WebMethod(operationName = "sendPassword")
    public void sendPassword(String email, String pass){
        MailSender.sendPass(email, pass);
    }
    /**
     * Web service operation
     */
    @WebMethod(operationName = "deleteCustomer")
    public void deleteCustomer(int id){
       proxy.deleteCustomer(id);
    }
    
    @WebMethod(operationName = "deleteUser")
    public void deleteUser(int id){
       proxy.deleteUser(id);
    }       

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getPassword")
    public String getPassword(@WebParam(name = "name") String name) {
        //TODO write your implementation code here:
        return proxy.getPassword(name);
    }
    
}
